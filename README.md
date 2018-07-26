# 段落解析代码文档

>段落解析的代码分析文档，供于参考

## 项目加载
python 工程打包，部署命令
```
python setup.py sdist --formats=zip 
zip解压后，进入解压的文件夹
python setup.py install
启动：nohup ie-paragraph &
```

~~直接下载下来的工程是没有办法直接运行的~~已更新

[>_>]:
<span style="border-bottom:2px solid yellow;">把整个文件在上一级目录打开就不会报错引用错误</span>

将dict解压之后文件夹中的setup.py和一些其他配置的文件放到paragraph外层根目录，可以用上面的命令来对整个项目打包，以及运行整个项目工程文件启动REST服务
如果nohup直接退出，很有可能是那条命令本来就不能运行

## 项目结构
![Alt text](./images/1532571387816.png)  

整个项目是在朋哥的句子REST服务的基础上修改的  
- `api`文件夹中对段落API做详细的实现（接受输入输出参数，预测）  
- `dist`为打包后文件存储位置  
- `experiment`类似于test的作用（在这里没有用到）  
- `intelli_extract_paragraph.egg-info`  Same concept as a .jar file in Java, it is a .zip file with some metadata files renamed .egg, for distributing code as bundles.  
- `middleware` 请求日志打印  
- `preprocessing` 预处理  
- `setting` REST服务的一些配置（包括模型路径）  
- `train` 段落解析模型训练代码
余下的单个文件，服务的启动，打包，部署，日志和配置
- `test`和`testdata`为测试用的文件夹

---
## 训练代码

1. **getParaLabel.py**  将标注文件中的“Para”符号等替换，并在句子和段落之间添加制表符和/msxf
2.  **prcocessing.py** 对文档做处理，将文件夹下的所有数据转换为段落和对应的标签，另存为两个文件
3. **get_w2v_avg.py** 获得平均词向量，model.vec应该是训练的词向量模型，平均词向量，把每一段分成一个一个词，从word2vec模型中获取该词的向量，一段话一个向量，每个词累加到该向量上，然后做平均
4. **train.py** 训练得到模型 --->tarin param~~
利用utils中的函数获得训练所用的数据和标签对应关系等
Keras的版本要匹配，2.2.0的不行，[BUG:AttributeError: module 'pandas' has no attribute 'computation'](https://blog.csdn.net/hungryof/article/details/76730328)
通过`bilstm_ctc.py`中的`get_model`函数创建模型，定义`callback`类（来观察训练过程中网络内部的状态和统计信息，以及打印训练的效果）第一个txt中的段落为验证集，其余的txt中的段落用作训练集训练模型


### bilstm_ctc.py
#### `get_model` 
模型构造，n\_class为输出的分类的个数，X就是模型的输入（文本最长段数*规定的w2v_size）

两层，双向GRU网络，[一些参数说明](https://www.2cto.com/net/201708/664769.html)
lambda用以对上一层的输出施以任何Theano/TensorFlow表达式，于ctc.ctc_loss中使用ctc_merge_repeated参数，确保重复标签不合并

####  `test1`
每个epoch训练完后，会调用此函数，利用训练的模型，对验证集做预测，并打印loss和acc，以及预测正确的标签

keras:[中文文档](http://keras-cn.readthedocs.io/en/latest/)
- [merge函数（concatenate）](http://keras-cn.readthedocs.io/en/latest/layers/merge/)
- [dropout](http://keras-cn.readthedocs.io/en/latest/layers/core_layer/#dropout)，防止过拟合
- [TimeDistributed包装器](https://blog.csdn.net/xiaojiajia007/article/details/76665016)
- [tf.squeeze删除维数为1的](https://blog.csdn.net/qq_31780525/article/details/72280284)
-  K.ctc_label_dense_to_sparse：将ctc标签从稠密形式转换为稀疏形式
- 防止某些算法中出现无穷大或NaN的问题，如1/0 、log(0)等发生如：tf.reduce_sum(ys * tf.log(prediction)，改成：tf.reduce_sum(ys * tf.log(prediction+1e-8)
- [lamda函数](https://www.cnblogs.com/evening/archive/2012/03/29/2423554.html)
- 优化器和loss是keras中的两个必须参数，loss_out估计就是loss，Adam，随机优化
- [enumerate函数](http://www.runoob.com/python/python-func-enumerate.html)
- [keras的callback函数](http://keras-cn.readthedocs.io/en/latest/other/callbacks/)


### **utils.py**
####  `get_train_source_matrix`
得到每个txt有多少个段落，读取文件，然后计数，将csv中对应段的平均词向量取出来作为模型的输入
`texts`为 [[0], [1, 2], [3, 4, 5], [6, 7, 8, 9]]形式的数组，相当于把每个合同的段落，在汇总的段落中所占的位置标记出来，然后如果长度小于最大文本段落的就补0对齐
`train_source_matric` 三维[samples,timestep,d2v\_embedding]，samplestxt文本的个数，timestep最长txt文本段落条数，w2v_size词向量维数，这个矩阵可以这样理解，前两维就可以定位到在哪个文本中的哪一段，第三维为对应段的平均词向量
这个函数有很大部分都是把顺序处理的二维的段的平均词向量，变成三维的，方便直接送入网络
`MAX_SOURCE_LENGTH`为多个txt文件中段落最多的个数

####  `get_target`
将标签对应到每个文件之后，将总的标签去重，然后按顺序，构建序号和词之间相互的字典（target_vocab_to_int，target_int_to_vocab），再利用标签和文件对应的关系，得到Y（target_label_to_int），也就是每个文件里的标签对应的序号数组（二维，文件和序号）

#### `text_to_int`
实现上面函数中的一个功能，利用词也就是标签和序号之间的对应关系，和一个文件的标签，映射为一个一维数组


## 段落解析实验
段落结果预测准确率

段落解析的代码中，getPara中对location的处理，要有`</t>`的结尾的时候才会去掉
利用getPara.py中的方法，处理已标记文件为合适的输入方式并放回标签，再利用api中的方法，得到预测结果和标签对比

7.11号的模型利用验证集验证的结果：

| 10     |     20 |   50   | 100     |     47 | 
| :--------: | :--------:| :------: | :-------: | :--------:|
| 1780   |   4081 |  8859  | 19738    |   9423| 
| 0.550000|   0.542514 | 0.441585  | 0.302462    |  0.163748 | 

7.26重新训练的模型利用验证集验证的结果（前后对比）：

 |     before |   after   |
 | :--------:| :------: |
  |   1320  |  1320 |
 |   0.277273 |  0.389394  |
 
 
 a

[^_^]:
b

[>_>]:
c

d
 


