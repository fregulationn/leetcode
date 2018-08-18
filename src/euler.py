import sys
for line in sys.stdin:
    a = line.split()
a.sort()
if a[0]==1:
    res = 0
else:
    res = 1
for i in range(0,3):
    if a[i] == 1 :
        res += a[i]
    else:
        res *= a[i]
print(res)
