package fp_in_scala.chapter_2

/**
 * @Project scala
 * @Author Ravooo
 * @Since 2020/7/27 01:02
 * @Description CurryAndUncurry
 */
object CurryAndUncurry {

    def curry[A, B, C](f: (A, B) => C): A => B => C = a => b => f(a, b)

    def uncurry[A, B, C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)
}
