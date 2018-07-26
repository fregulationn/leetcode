## 保顾NLP解析服务
>保顾解析服务的总结文档 v1

### 1. 对外提供的接口
业务方数据上传到腾讯云后，会调用解析服务接口，服务先返回调用状态，再异步解析，解析完毕后回调业务方接口  

#### 1.1 解析接口（服务中唯一对外暴露的接口）
接口地址：IP:Port/extract/extract  
与业务方协调的接口参数（示例）：  

```
{
    "planId": 27,
    "planItemVOList": [
        {
            "channel": "惠择",
            "dataUrl": "crawler/20180625/惠择/少儿平安综合保障计划  /主条款/平安学生一年定期寿险条款.pdf",
            "itemType": "主条款"
        },
        {
            "channel": "惠择",
            "dataUrl": "crawler/20180625/惠择/少儿平安综合保障计划  /附加条款/平安附加学生残疾意外伤害保险（2013版）条款（2014年新版）.pdf",
            "itemType": "附加条款"
        }
    ],
    "productName": "少儿平安综合保障计划  "
}
```
dataUrl为需要解析的文件在腾讯云中的路径  

`Response Class (Status 200)`：未定  

#### 1.2 回调业务方接口（业务方提供）
[Swagger](http://10.250.100.176:8005/swagger-ui.html#!/dispatch45controller/nlpCompleteNoticeUsingPOST)：POST /dispatch/notice/nlpComplete  


```
ProgramParseCompleteDTO {
dataUrl (string, optional): 文件路径（上传到腾讯云中的） ,
etag (string, optional): 文件上传返回的etag ,
planId (integer, optional): 计划ID
}
```
`Response Class (Status 200)`：

```
ResponseDataModel {
    code (string, optional): 业务异常码：成功：SYS.SUCCESS；异常：其他 ,
    data (object, optional): 业务数据，正常响应结果 ,
    msg (string, optional): 异常信息 ,
    syscode (string, optional): 异常码，用于前端打印
}
```

### 2. 整体服务及内部模块调用形式
- 整体服务使用Spingbot对外服务  
- 内部通过http服务调用段落、句子、文本分类、实体、统计等模块

### 3. 保顾内部接口及数据预处理
目录结构：每个模块的请求参数为2个，分别是输入文件夹路径input\_dir，输出文件夹路径output_dir  

      basedir|（保顾数据处理的根目录）
	            downPdf|
               pdfParse|
                          paragraphParse|
                           SentenceParse|
              paragraph|
               sentence|
         textclassifier|
				 entity|
			  statistic|

内部服务请求的返回都是类似的
`Response Class (Status 200)`：
```
~~~Response {
    description(string, optional): 业务异常码：成功："return success"；异常：其他 ,
    message(string, optional): 响应结果 ,
    title(string, optional): 服务接口名称（eg：STATISTIC API V1.0）
}
```

#### 3.1 pdf下载
根据业务方请求参数在腾讯云中下载pdf文档，存储到`downPdf`目录（下载的时候利用失败重试机制，如果下载的文件数量与Json中的不符，重新下载，尝试三次放弃）

#### 3.2 pdf解析
通过pdf解析工具将`downPdf`中的pdf转换为方便标注格式的文本文档
为方便段落和句子解析，将pdf的段落和句子提取的结果分别存储到`paragraphParse`和`SentenceParse`目录下

#### 3.3 段落解析（调用内部服务）
段落解析服务，服务的主要功能是读取`paragraphParse`目录下的数据并进行段落标注，将段落标注后的数据存储到`paragraph`目录下

输入文件的格式：
段落中每个段落给出段落分割符及段落中的句子个数，段落末尾\t Para—Num，Num为本段句子个数。示例如下：
>1.1 保险合同构成	Para—1
本附加保险合同（以下简称“本附加合同”）附加于我们规定的主保险合同（下简称“主合同”）上。主合同所附条款、投保单及与本附加合同有关的其他投保文件、合法有效的声明、批注、批单和其他书面协议，凡与本附加合同相关者，均为本附加合同的构成部分。Para—2

输出文件：输入文件中每段对应的标签（暂时只输出标签）

段落服务请求参数：

```
~~~Request {
    input_dir(string, optional): 输入待处理数据文件夹路径 ,
    output_dir(string, optional): 输出处理完毕数据文件夹路径
}
```


#### 3.4 句子解析（调用内部服务）
句子解析服务，服务的主要功能是读取`SentenceParse`目录下的数据并进行句子标注，将句子标注后的数据存储到`sentence`目录下

输入文件的格式：
句子解析给出段落与句子分割标识符。段落结束使用\tisParagraph,句子末尾结束使用\tisSentence。示例如下：
>1.1 保险合同构成	isParagraph
本附加保险合同（以下简称“本附加合同”）附加于我们规定的主保险合同（下简称“主合同”）上。	isSentence
主合同所附条款、投保单及与本附加合同有关的其他投保文件、合法有效的声明、批注、批单和其他书面协议，凡与本附加合同相关者，均为本附加合同的构成部分。	isSentence

输出文件：输入文件中每句对应的标签（暂时只输出标签）


句子服务请求参数：

```
~~~Request {
    input_dir(string, optional): 输入待处理数据文件夹路径 ,
    output_dir(string, optional): 输出处理完毕数据文件夹路径
}
```

#### 3.5 文本分类（调用内部服务）
服务的主要功能是读取`sentence`目录下的数据并进行文本分类，将句子存储到`textclassifier`目录下。按照约定，文本分类会重写原始输入文件，并文件头部加三行

文本分类服务请求参数

```
~~~Request {
    input_dir(string, optional): 输入待处理数据文件夹路径 ,
    output_dir(string, optional): 输出处理完毕数据文件夹路径
}
```



#### 3.6 实体识别（调用内部服务）
服务的主要功能是读取目录`paragraphParse,paragraph,SentenceParse,sentence`下的数据，也就是段落和句子的原始和处理过的文件，并进行实体识别，将实体识别的结果到`entity`目录下

实体识别服务请求参数

```
~~~Request {
    para_origin_dir(string, optional): 段落原始文件夹路径 ,
    para_label_dir(string, optional): 段落解析处理结果文件夹路径,
    sen_origin_dir(string, optional): 句子原始文件夹路径 ,
    sen_label_dir(string, optional): 句子解析处理结果文件夹路径,
    out_put_dir(string, optional): 实体识别结果储存文件夹路径
}
```

#### 3.7 类别统计（调用内部服务）
服务的主要功能是读取实体识别结果`entity`和文本分类的结果`textclassifier`下的数据，并将统计结果存储到`statistic`目录下，每个条款统计的结果以json格式存储

类别统计服务请求参数

```
~~~Request {
    entity_dir(string, optional): 实体识别结果文件夹路径 ,
    textclassify_dir(string, optional): 文本分类结果文件夹路径,、
    out_put_dir(string, optional): 类别统计结果储存文件夹路径
}
```




#### 3.8 结果上传及回调
将类别统计得到的多个json文件合并为一个json文件，并上传至腾讯云，将上传至的腾讯云中的路径和上传返回的etag作为参数回调业务方接口，将整个NLP解析的结果返回给业务方



