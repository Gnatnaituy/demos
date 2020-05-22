// 表达式 值 变量
1 + 2

// 函数
def pow(x: Int): Int = x * x
pow(35) // 1225

// 匿名函数
(x: Int) => x * x * x

// 匿名函数将其保存成不变量
val plusOne = (x: Int) => x + 1
val plusTwo = (x: Int) => {
  println("This is a plus 2 anonymous function")
  x + 2
}
plusTwo(235)

// 部分应用(Partial application)
def adder(x: Int, y: Int): Int = x + y
def add2 = adder(2, _:Int)
add2(3) // 5

// 柯里化函数
// 允许别人一会在你的函数上应用一些参数，然后又应用另外的一些参数
def multiply(x: Int)(y: Int) = x * y
val timeTwo = multiply(2) _
timeTwo(5) // 10

// 可变长度参数
def capitalizeAll(args: String*): Seq[String] = {
  args.map { arg =>
    arg.capitalize
  }
}

capitalizeAll("surprise, m***** f*****")

// 类
class Calculator(brand: String) {
  val theBrand: String = brand
  def add(m: Int, n: Int) = m + n
}
val calculator = new Calculator(brand = "HP")
calculator.add(23, 45)

// 构造函数
class Car(brand: String) {
  /**
    * A constructor
    */
  val color: String = if (brand == "宝马") {
    "blue"
  } else if (brand == "大奔") {
    "yellow"
  } else {
    "white"
  }

  def run = "The car can run"
}
val myCar = new Car("宝马")
myCar.color
myCar.run

// 继承
class ScientificCalculator(brand: String) extends Calculator(brand) {
  def log(m: Double, base: Double) = math.log(m) / math.log(base)
}

// 重载方法
class EvenMoreScientificCalculator(brand: String) extends ScientificCalculator(brand) {
  def log(m: Int): Double = log(m, math.exp(1))
}

// 抽象类
abstract class Shape {
  def getArea: Int
}

class Circle(r: Int) extends Shape {
  override def getArea: Int = r * r * 3
}

val c = new Circle(5)
c.getArea

// 特质(Traits)
trait Apple {
  val color: String
}

trait Phone {
  val price: Int
}

class Iphone extends Apple with Phone {
  val color = "white"
  val price: Int = 1000
}

// 类型
trait Cache[K, V] {
  def get(key: K): V
  def put(key: K, value: V)
  def delete(key: K)
}
