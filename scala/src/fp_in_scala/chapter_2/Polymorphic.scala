package fp_in_scala.chapter_2

/**
 * @Project scala
 * @Author Ravooo
 * @Since 2020/7/27 00:51
 * @Description Polymorphic
 */
object Polymorphic {

    /**
     * Practice 2.2
     */
    def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
        Array.range(1, as.length).forall(index => ordered(as(index - 1), as(index)))
    }

    def main(args: Array[String]): Unit = {
        println(isSorted(Array(1, 2, 3, 4, 5), (a: Int, b: Int) => a < b))
        println(isSorted(Array('a', 'b', 'c'), (a: Char, b: Char) => a < b))
    }
}
