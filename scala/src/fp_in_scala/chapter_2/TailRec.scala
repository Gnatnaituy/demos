package fp_in_scala.chapter_2

import scala.annotation.tailrec

/**
 * @Project scala
 * @Author Ravooo
 * @Since 2020/7/27 00:24
 * @Description TailRec
 */
object TailRec {

    // Recursive
    def fibRec(n: Int): Int = {
        if (n == 0) return 0
        if (n == 1) return 1
        fibRec(n - 1) + fibRec(n - 2)
    }

    /**
     * Practice 2.1
     * Tail recursive
     */
    def fibTailRec(n: Int): Int = {
        @tailrec
        def loop(n: Int, prev: Int, cur: Int): Int = {
            if (n == 0) prev
            else loop(n - 1, cur, prev + cur)
        }

        loop(n, 0, 1)
    }

    def main(args: Array[String]): Unit = {
        val a = fibRec(10)
        val b = fibTailRec(10)
        println(a, b)
    }
}
