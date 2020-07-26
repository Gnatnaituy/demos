package fp_in_scala.chapter_2

/**
 * @Project scala
 * @Author Ravooo
 * @Since 2020/7/27 01:12
 * @Description Compose
 */
object Compose {

    def compose[A, B, C](f: B => C, g: A => B): A => C = a => f(g(a))
}
