// 函数组合
def f(s: String): String = "f(" + s + ")"
def g(s: String): String = "g(" + s + ")"
// compose
val fComposeG = f _ compose g _
fComposeG("Good")
val fAndThenG = f _ andThen g _
fAndThenG("Good")

// 柯里化 VS 偏应用
// 理解Partial Function(偏函数)
// 对给定的输入参数类型，函数可接受该类型的任何值。
// 对给定的输入参数类型，偏函数只能接受该类型的某些特定的值。
// isDefinedAt 是PartialFunction的一个方法，用来确定PartialFunction是否能接受一个给定的参数。

