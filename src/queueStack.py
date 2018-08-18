# class MyQueue:
#     def __init__(self):
#         """
#         Initialize your data structure here.
#         """
#         self.list1 = []
#         self.list2 = []
#
#     def push(self, x):
#         """
#         Push element x to the back of queue.
#         :type x: int
#         :rtype: void
#         """
#         self.list1.append(x)
#
#     def pop(self):
#         """
#         Removes the element from in front of queue and returns that element.
#         :rtype: int
#         """
#         if len(self.list2) != 0:
#             return self.list2.pop()
#         while len(self.list1) > 0:
#             self.list2.append(self.list1.pop())
#
#         return self.list2.pop()
#
#
#     def peek(self):
#         """
#         Get the front element.
#         :rtype: int
#         """
#         if len(self.list2) != 0:
#             return self.list2[-1]
#         while len(self.list1) > 0:
#             self.list2.append(self.list1.pop())
#
#
#         return self.list2[-1]
#
#
#     def empty(self):
#         """
#         Returns whether the queue is empty.
#         :rtype: bool
#         """
#         if len(self.list1) == 0 and len(self.list2) == 0:
#             return True
#         return False

# class MyStack:
#     def __init__(self):
#         """
#         Initialize your data structure here.
#         """
#         self.list1 = []
#         self.list2 = []
#
#     def push(self, x):
#         """
#         Push element x onto stack.
#         :type x: int
#         :rtype: void
#         """
#         if len(self.list1) != 0:
#             self.list1.append(x)
#         else:
#             self.list2.append(x)
#
#
#     def pop(self):
#         """
#         Removes the element on top of the stack and returns that element.
#         :rtype: int
#         """
#         if len(self.list1) != 0:
#             while len(self.list1) > 1:
#                 self.list2.append(self.list1.pop(0))
#             return self.list1.pop()
#
#         while len(self.list2) > 1:
#             self.list1.append(self.list2.pop(0))
#         return self.list2.pop()
#
#
#     def top(self):
#         """
#         Get the top element.
#         :rtype: int
#         """
#         if len(self.list1) != 0:
#             while len(self.list1) > 1:
#                 self.list2.append(self.list1.pop(0))
#             res = self.list1[0]
#             self.list2.append(self.list1.pop(0))
#             return res
#
#         while len(self.list2) > 1:
#             self.list1.append(self.list2.pop(0))
#         res = self.list2[0]
#         self.list1.append(self.list2.pop(0))
#         return res
#
#
#     def empty(self):
#         """
#         Returns whether the stack is empty.
#         :rtype: bool
#         """
#         if len(self.list1) == 0 and len(self.list2) == 0:
#             return True
#         return False


class Solution:
    def findMin(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return None

        if len(nums) == 1:
            return nums[0]

        left = 0
        right = len(nums) - 1

        # 已经降序
        if nums[right - 1] > nums[right]:
            return nums[right]

        while left < right:
            # 已经升序,没有被旋转
            if nums[left] < nums[right]:
                return nums[left]

            mid = (left + right) // 2

            if nums[mid] > nums[right]:
                left = mid + 1
            elif nums[mid] < nums[right]:
                right = mid
            else:
                left += 1

        return nums[right]

    def Fibonacci(self, n):
        if n == 0:
            return 0
        if n == 1:
            return 1

        pre1, pre2, now = 0, 1, 0
        for i in range(2, n + 1):
            now = pre1 + pre2
            pre1 = pre2
            pre2 = now

        return now

    def hammingWeight(self, n):
        """
        :type n: int
        :rtype: int
        """
        res = 0

        if n < 0:
            n += pow(2, 32)

        while n != 0:
            if n % 2 == 1:
                res += 1
            n = n // 2

        return res

    def findPeakElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        left = 0
        if len(nums) == 0:
            return None
        if len(nums) == 1:
            return 0

        tmp_nums = [float("-inf")]
        tmp_nums.extend(nums)
        tmp_nums.append(float("-inf"))
        nums = tmp_nums

        right = len(nums) - 1

        while left < right:
            mid = (left + right) // 2
            if nums[mid] > nums[mid + 1] and nums[mid] > nums[mid - 1]:
                return mid - 1

            if nums[mid] < nums[mid - 1]:
                right = mid
                continue
            if nums[mid] < nums[mid + 1]:
                left = mid
                continue

                # #
                # left, right = 0, len(A) - 1
                # mid = (left + right) // 2
                # while left <= right and (A[mid] < A[mid - 1] or A[mid] < A[mid + 1]):
                #     # mid 左侧存在峰值
                #     if A[mid] < A[mid - 1]:
                #         right = mid - 1
                #     # mid 右侧存在峰值
                #     elif A[mid] < A[mid + 1]:
                #         left = mid + 1
                #     mid = (left + right) // 2
                # return mid

    def canJump(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if len(nums) == 1:
            return True
        tmp = [nums[0]]
        for i in range(len(nums) - 1):
            tmp.append(max(nums[i], tmp[-1]) - 1)
            if tmp[-1] < 0:
                return False
        return True

    # arr = [None]
    #
    # def coinChange(self, coins, amount):
    #     """
    #     :type coins: List[int]
    #     :type amount: int
    #     :rtype: int
    #     """
    #     self.arr = [-1] * (amount + 1)
    #     self.arr[0] = 0
    #     coins.sort()
    #
    #     for i in range(len(coins)):
    #         if coins[i] < amount + 1:
    #             self.arr[coins[i]] = 1
    #
    #     return self.recursion(coins, amount)
    #
    # def recursion(self, coins, amount):
    #     if self.arr[amount] >= 0:
    #         return self.arr[amount]
    #
    #     minValue = amount
    #     for i in range(len(coins))[::-1]:
    #         if coins[i] > amount:
    #             continue
    #
    #         tmp = self.recursion(coins, amount - coins[i])
    #         if tmp == 0:
    #             continue
    #
    #         self.arr[amount] = 1 + tmp
    #         return self.arr[amount]
    #
    #     return 0

    def searchRange(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        left = 0
        right = len(nums) - 1

        if (len(nums) == 1 and nums[0] == target):
            return [0, 0]

        first = -1
        while (left <= right):
            mid = (left + right) // 2
            if nums[mid] > target:
                right = mid - 1
            elif nums[mid] < target:
                left = mid + 1
            else:
                if (mid > 0 and nums[mid - 1] == target):
                    right = mid
                else:
                    first = mid
                    break

        left = 0
        right = len(nums) - 1
        last = -1
        while (left <= right):
            mid = (left + right) // 2
            if nums[mid] > target:
                right = mid - 1
            elif nums[mid] < target:
                left = mid + 1
            else:
                if (mid < len(nums) - 1 and nums[mid + 1] == target):
                    left = mid + 1
                else:
                    last = mid
                    break

        return [first, last]

    # 56. 合并区间
    def merge(self, intervals):
        """
        :type intervals: List[Interval]
        :rtype: List[Interval]
        """
        res = []

        intervals = sorted(intervals, key=lambda tmp: tmp.start)

        if (len(intervals) == 0):
            return []
        else:
            res.append(intervals[0])

        for i in range(1, len(intervals)):
            if res[-1].end >= intervals[i].start:
                res[-1].start = min(res[-1].start, intervals[i].start)
                res[-1].end = max(res[-1].end, intervals[i].end)

            else:
                res.append(intervals[i])

        return res

    # 33. 搜索旋转排序数组
    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        left = 0
        right = len(nums) - 1
        if right == -1:
            return -1

        while (left < right):
            mid = (left + right) // 2
            if nums[mid] > nums[right]:
                left = mid + 1
            elif nums[mid] < nums[right]:
                right = mid

        mid_index = left
        right = len(nums) - 1
        if (target > nums[right]):
            left = 0
            right = mid_index - 1
        elif target < nums[right]:
            left = mid_index
            right = right - 1
        else:
            return right

        while (left <= right):
            mid = (left + right) // 2
            if target > nums[mid]:
                left = mid + 1
            elif target < nums[mid]:
                right = mid - 1
            else:
                return mid

        return -1

    def titleToNumber(self, s):
        """
        :type s: str
        :rtype: int
        """
        length = len(s)

        res = 0
        for i in range(length):
            res = res * 26 + ord(s[i]) - 64
        return res

    def myPow(self, x, n):
        """
        :type x: float
        :type n: int
        :rtype: float
        """
        res = 1
        tmp_n = abs(n)
        while (tmp_n // 2 > 0):
            if (tmp_n % 2 != 0):
                res *= x
            x = x * x
            tmp_n //= 2
        if n > 0:
            return res * x
        elif n == 0:
            return 1
        else:
            return 1 / (res * x)

    def mySqrt(self, x):
        """
        :type x: int
        :rtype: int
        """
        r = x
        while abs(r * r - x > 0.00001):
            r = (r + x // r) // 2
        return r

    def divide(self, dividend, divisor):
        """
        :type dividend: int
        :type divisor: int
        :rtype: int
        """
        res = 0
        tmp_dividend = abs(dividend)
        tmp_divisor = abs(divisor)
        if tmp_dividend < tmp_divisor:
            return 0
        while tmp_dividend >= tmp_divisor:
            t, r = tmp_divisor, 1
            while (tmp_dividend > (t << 1)):
                t <<= 1
                r <<= 1
            tmp_dividend -= t
            res += r
        if (dividend > 0) != (divisor > 0):
            res = -res
        if res > 2147483647:
            return 2147483647
        else:
            return res

    def fractionToDecimal(self, numerator, denominator):
        """
        :type numerator: int
        :type denominator: int
        :rtype: str
        """
        if numerator == 0:
            return 0

        tmp_n = abs(numerator)
        tmp_d = abs(denominator)

        tmp = {}

        res = str(tmp_n // tmp_d)
        reminder = tmp_n % tmp_d

        if reminder != 0:
            res += "."

        decimals = ""
        index = 1

        while (reminder != 0 and not tmp.get(reminder)):
            decimals += str((reminder * 10) // tmp_d)

            tmp[reminder] = index
            index += 1

            reminder = (reminder * 10) % tmp_d

        sign = ""
        if (numerator > 0) != (denominator > 0):
            sign += "-"

        if reminder == 0:
            return sign + res + decimals

        res = sign + res + decimals[0:tmp.get(reminder) - 1] + "(" + decimals[tmp.get(reminder) - 1:index] + ")"
        return res

    def getSum(self, a, b):
        """
        :type a: int
        :type b: int
        :rtype: int
        """

        x = a ^ b
        c = (a & b) << 1
        if c != 0:
            return self.getSum(x, c)
        return x

    def evalRPN(self, tokens):
        """
        :type tokens: List[str]
        :rtype: int
        """

        stack = []

        for token in tokens:
            if token == "+":
                stack.append(stack.pop() + stack.pop())
            elif token == "-":
                stack.append(- stack.pop() + stack.pop())
            elif token == "*":
                stack.append(stack.pop() * stack.pop())
            elif token == "/":
                divider = stack.pop()
                divided = stack.pop()
                if (divider > 0) != (divided > 0):
                    stack.append(-(abs(divided) // abs(divider)))
                else:
                    stack.append(divided // divider)
            else:
                stack.append(int(token))
        return stack.pop()

    def leastInterval(self, tasks, n):
        """
        :type tasks: List[str]
        :type n: int
        :rtype: int
        """
        # tasks.sort()

        from collections import Counter

        count = Counter(tasks)

        all_len = []
        for task in set(tasks):
            all_len.append(tasks.count(task))

        # all_len.sort(reverse=True)

        # print(seq[seq.index(min(seq))])
        # print(max(seq))

        tmp = (max(all_len) - 1) * (n + 1) + all_len.count(max(all_len))
        return max(len(tasks), (max(all_len) - 1) * (n + 1) + all_len.count(max(all_len)))

    def maxProduct(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """

        if len(nums) == 0:
            return

        Max = nums[:]
        Min = nums[:]

        for i in range(1, len(nums)):
            Max[i] = max(nums[i], max(Max[i - 1] * nums[i], Min[i - 1] * nums[i]))
            Min[i] = min(nums[i], min(Max[i - 1] * nums[i], Min[i - 1] * nums[i]))

        return max(Max)

    def numSquares(self, n):
        """
        :type n: int
        :rtype: int
        """
        minnum = [0] * (n + 1)

        all_square = []
        for i in range(1, n + 1):
            if i * i > n:
                break
            all_square.append(i * i)

        for i in range(1, n + 1):
            if i in all_square:
                minnum[i] = 1
                continue
            tmp_min = 1000
            for j in all_square:
                if j > i:
                    break
                if minnum[j] + minnum[i - j] < tmp_min:
                    tmp_min = minnum[j] + minnum[i - j]
            minnum[i] = tmp_min
        return minnum[n]

    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        if len(wordDict) == 0 or s == None:
            return False

        res = [False] * (len(s) + 1)
        res[0] = True
        for i in range(0, len(s)):
            tmp_str = s[0:i+1]
            for j in range(i+1):
                if res[j] == True and tmp_str[j:i+1] in wordDict:
                    res[i+1] = True
                    break
        return res[len(s)]


class Interval:
    def __init__(self, s=0, e=0):
        self.start = s
        self.end = e


# interval1 = Interval(1,4)
# interval2 = Interval(0,4)
# interval3 = Interval(8,10)
# interval4 = Interval(15,18)
nums = [2, -4, 5]
so = Solution()
# print(so.search(nums, 3))

letter = ["abbcbda", "cbdaaa", "b", "dadaaad", "dccbbbc", "dccadd", "ccbdbc", "bbca", "bacbcdd", "a", "bacb", "cbc",
          "adc", "c", "cbdbcad", "cdbab", "db", "abbcdbd", "bcb", "bbdab", "aa", "bcadb", "bacbcb", "ca", "dbdabdb",
          "ccd", "acbb", "bdc", "acbccd", "d", "cccdcda", "dcbd", "cbccacd", "ac", "cca", "aaddc", "dccac", "ccdc",
          "bbbbcda", "ba", "adbcadb", "dca", "abd", "bdbb", "ddadbad", "badb", "ab", "aaaaa", "acba", "abbb"]

# print(so.divide(10, 4))
# print(so.getSum(-1, 1))
a = "acaa"
print(a[0:4])
print(so.wordBreak("acaaaaabbbdbcccdcdaadcdccacbcccabbbbcdaaaaaadb", letter))

import torch

x = torch.randn(3, requires_grad=True)
print(x)

y = x * 2
while y.data.norm() < 1000:
    print(y.data)
    print()
    print(y.data.norm())
    y = y * 2

print(y)
