# python判读是不是等差数列，要求时间复杂度为O(NlogN)
# 输入一个整数N，然后输入N个整数，判断着N个整数是否能构成等差数列


def isArithmetic(*a):
    n = len(a)
    a1 = min(a)
    d = 2*(float(sum(a))/n-a1)/(n-1)

    for i in range(n):
        if (a1 + i*d) not in a:
            return False
    return True

if __name__ == '__main__':
    print(isArithmetic(1, 3, 5, 7, 9, 2, 4, 6, 8))
    print(isArithmetic(1, 2, 3, 4, 6, 7, 8, 9))
