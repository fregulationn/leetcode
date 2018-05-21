
import numpy as np

def test (N):
    a = [];

    for i in range(1,N+1):
        for j in range(1,N+1):
           a.append(i/j)

    li = list(set(a))
    N = len(li) +2
    return N


def euler_vecter(N):
    Prim  = np.zeros(N+2, dtype=np.int)
    Prim[1] = 1
    Prim[2] = 1

    for i in range(2,N+1):
        for j in range(2,i):
            if i % j ==0:
                temp =int( i/j)
                if (i/j)% j!= 0:
                    Prim[i] = (j-1)* Prim[temp]
                else:
                    Prim[i] = (j)* Prim[temp]
                break
            else:
                continue

        if Prim[i] == 0:
            Prim[i] = i-1
    return Prim

def hr(N):
    return sum(2*euler_vecter(N))+1




if __name__ == '__main__':
    a = euler_vecter(20)
    # for i in range(1,19):
    #     print("%d : %d ,%d"%(i,(test(i)-test(i-1))/2,a[i]))

    for i in range(1,19):
        print("%d : %d ,%d"%(i,(test(i)),hr(i)))

    print("fuck")
    print(hr(3))
