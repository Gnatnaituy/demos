class classss(object):
    """docstring for classss"""

    hobby = "Coding"

    def __init__(self, name, age, weight):
        self.name = name
        self._age = age
        self.__weight = weight

    @classmethod
    def get_hobby(cls):
        return cls.hobby

    @property
    def gett_weight(self):
        return self.__weight

    def self_introduction(self):
        print("I am your father")


# 继承
class Backendclassss(classss):

    def __init__(self, name, age, weight, language):
        super().__init__()
        self.language = language


# 多态
class Duotaiclassss(classss):

    def __init__(self, name, age, weight, language):
        super(Duotaiclassss, self).__init__(name, age, weight)
        self.language = language

    def self_introduction(self):
        print('My name is %s and I use %s' % (self.name, self.language))


def introduce(classss_instance):
    if isinstance(classss_instance, classss):
        classss_instance.self_introduction()

if __name__ == '__main__':
    classss_instance = classss('余天堂', '22', '60')
    Duotai_classss_instance = Duotaiclassss('Jobs', '50', '70', 'Python')
    introduce(classss_instance)
    introduce(Duotai_classss_instance)

