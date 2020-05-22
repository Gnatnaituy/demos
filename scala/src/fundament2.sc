// apply方法
class Foo {}

object FooMaker {
  def apply() = new Foo
}

val newFoo = FooMaker()

// 单例对象
// 单例对象用于持有一个类的唯一实例。通常用于工厂模式。
object Timer {
  var count = 0

  def currentCount(): Long = {
    count += 1
    count
  }
}

Timer.currentCount()
Timer.currentCount()
Timer.currentCount()

// 函数即对象
object addOne extends (Int => Int) {
  override def apply(v1: Int): Int = v1 + 1
}
addOne(9)

// 包 同Java
// 匹配模式
val times = 1
times match {
  case 1 => "one"
  case 2 => "two"
  // 使用守卫进行匹配
  case i if i == 3 => "three"
  // _ 是一个通配符，这里作用类似于Java中的default
  case _ => "some other number"
}

// 匹配类型
def bigger(o: Any): Any = {
  o match {
    case i: Int if i < 0 => i - 1
    case i: Int => i + 1
    case d: Double if d < 0.0 => d - 0.1
    case d: Double => d + 0.1
    case text: String => text + " is string"
  }
}

bigger(12)
bigger(-123)
bigger(23.5)
bigger("Cool")

// 匹配类成员 && 样本类(Case Classes)
case class Calculator(brand: String, model: String)
val hp20 = Calculator("HP", "20GB")
val hp40 = Calculator("HP", "40GB")
val hp80 = Calculator("HP", "80GB")

def calcType(calc: Calculator) = calc match {
  case Calculator("HP", "20GB") => "business"
  case Calculator("HP", "40GB") => "scientific"
  case Calculator("HP", "80GB") => "financial"
  case Calculator(ourBrand, ourType) => ("Calculator: %s %s " +
    "if of unknown type").format(ourBrand, ourType)
}

calcType(hp20)
calcType(hp40)
calcType(Calculator("ST", "1024GB"))

// 异常

