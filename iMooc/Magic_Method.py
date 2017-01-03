# coding:utf-8

# 两头加 __的方法，如 __init__ , __repr__....

class Programer(object):
    """docstring for Programer"""
    def __init__(self, name, age):
        self.name = name
        if isinstance(age, int):
            self.age = age
        else:
            raise Exception('age must be int')

    def __eq__(self, other):
        if isinstance(other, Programer):
            if self.age == other.age:
                return True
            else:
                return False
        else:
            raise Exception('the type of object must be Programer')

    def __add__(self, other):
        if isinstance(other, Programer):
            return self.age + other.age
        else:
            raise Exception('the type of object must be Programer')

if __name__ == '__main__':
    programer = Programer('余天堂', 22)
    programer2 = Programer('Father', 33)
    print(programer == programer2)
    print(programer + programer2)
    print(programer.__dict__.keys())
