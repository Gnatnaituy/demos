val array = Array("a", "b", "c", "d", "e")
val array2 = Array("f", "g", "h")

// 遍历
array.foreach(x => print(x + " "))

// 加运算
array ++ array2

// maps
array.map(i => i + i)
array2.flatMap(i => i + i + "cv")
array.collect(i => i + i)

// 转换
array.toList
array.toIterable
array.toSeq
array.toIndexedSeq
array.toSet
array.map(x => (x, "value-" + x)).toMap

// foreach很像map，但没有返回值。foreach仅用于有副作用[side-effects]的函数。

// zip将两个列表的内容聚合到一个对偶列表中。
array.zip(array2)
array2.zip(array).zip(array2) // 取较短的那个

// partition将使用给定的谓词函数分割列表
array.partition(_ == "c")

// find返回集合中第一个匹配谓词函数的元素
array.find(i => i == "z").getOrElse("Not found")

// drop 将删除前i个元素
// dropRight 删除后i个元素
array.drop(1)
array.dropRight(1)
// dropWhile丢弃条件所在集合开头的所有项目true。一旦第一项失败，它就会停止丢弃
array.dropWhile(_ < "c")
array.dropWhile(_ == "b")
// 与drop相反的是take
array.take(1)
array.takeRight(1)
array.takeWhile(_ < "c")
array.takeWhile(_ == "b")
// 想要按条件过滤使用filter
array.filter(_ != "c")
array.filterNot(_ == "c")
array.withFilter(_ == "c")

array.fold("z"){(m, n) => m + " " + n}
array.foldRight("z")((m, n) => m + " "  + n)

array.scan("z")(_ + _)
array.scanRight("z")(_ + "-" + _)

array.reduce(_ + _)
array.reduceRight(_ + "-" + _)