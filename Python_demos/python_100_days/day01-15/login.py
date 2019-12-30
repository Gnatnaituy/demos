username = input("username: ")
password = input("password: ")

if username == "admin" and password == "123":
    print("Success!")
else:
    print("Failed!")


value = float(input('请输入长度: '))
unit = input('请输入单位: ')
if unit == 'in' or unit == '英寸':
    print('%f英寸 = %f厘米' % (value, value * 2.54))
elif unit == 'cm' or unit == '厘米':
    print('%f厘米 = %f英寸' % (value, value / 2.54))
else:
    print('请输入有效的单位')


row = int(input('请输入行数: '))
for i in range(row):
    for _ in range(i + 1):
        print('*', end='')
    print()


for i in range(row):
    for j in range(row):
        if j < row - i - 1:
            print(' ', end='')
        else:
            print('*', end='')
    print()

for i in range(row):
    for _ in range(row - i - 1):
        print(' ', end='')
    for _ in range(2 * i + 1):
        print('*', end='')
    print()


# 求最大公约数和最小公倍数
def gcd(x, y):
    (x, y) = (y, x) if x > y else (x, y)
    for factor in range(x, 0, -1):
        if x % factor == 0 and y % factor == 0:
            return factor


def lcm(x, y):
    return x * y // gcd(x, y)


# 判断一个数是不是回文数
def is_palindrome(num):
    temp = num
    total = 0
    while temp > 0:
        total = total + total * 10 + temp
        temp //= 10
    return total == num


# 判断一个数是不是素数
def is_prime(num):
    for factor in range(2, num):
        if num % factor == 0:
            return False
    return True if num != 1 else False


# 判断一个数是不是会问素数
def is_prime_palindrome(num):
    return is_palindrome(num) and is_prime(num)

