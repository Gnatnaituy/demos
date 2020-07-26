val array = Array(1, 2, 3, 4, 5, 6)
val list = List(1, 2, 3, 4, 5)
val set = Set(1 ,2 ,3, 4, 5)
// 元祖 Tuple
// 与样本类不同，元组不能通过名称获取字段，而是使用位置下标来读取对象；而且这个下标基于1，而不是基于0。
val hostPort = ("localhost", 80)
hostPort._1
hostPort._2
println(hostPort)

// 映射 Map
Map(1 -> 2)
Map(2 -> "bar")

// 选项 Option

// 函数组合子(Functional Combinator)
// map
array.map((i: Int) => i * i)

def addTen(i: Int) = i + 10
array.map(addTen)
// foreach
// foreach很像map，但没有返回值。foreach仅用于有副作用[side-effects]的函数。

// filter
array.filter((i: Int) => i % 2 == 0)

// zip
// zip将两个列表的内容聚合到一个对偶列表中。
set.zip(list)
array.zip(list) // 取较短的那个

// partition
// partition将使用给定的谓词函数分割列表
array.partition(_ % 2 == 0)

// find
// find返回集合中第一个匹配谓词函数的元素
array.find((i: Int) => i % 3 == 0)

// drop && dropWhile
// drop 将删除前i个元素
// dropWhile 将删除匹配谓词函数的第一个元素
array.dropWhile(_ % 2 != 0)

// foldLeft && foldRight
array.foldLeft(0) {(m: Int, n: Int) => m * n - m + n}
