package main

import (
	"fmt"
	"math/cmplx"
)

const Pi = 3.1415926
const (
	Big   = 1 << 100
	Small = Big >> 99
)

func main() {
	var a = "Good, Job"
	fmt.Println("Hello , GO!")
	fmt.Println(a)

	var b, c = 1, 2
	fmt.Println(b, c)

	var hello = "Hello"
	var price int
	var time complex64
	fmt.Println(hello, price, time)

	intnumber := 1234.56789
	fmt.Println(intnumber)

	fmt.Println(Swap2("A", "B"))
	fmt.Println(Swap3("A", "B", "C"))
	fmt.Println(NakedReturn(99))

	const cSharp, java, python = true, false, "Newbee"
	fmt.Println(cSharp, java, python)
	fmt.Println(cmplx.Sqrt(-5 + 12i))

	fmt.Println("Happy,", Pi, "Day!")
	fmt.Println(NeedInt(Small))
	fmt.Println(NeedFloat(Small))
	fmt.Println(NeedFloat(Big))

	for i := 10; i < 15; i++ {
		fmt.Print(i, " ")
	}

	fmt.Print("输出到控制台不换行")
	fmt.Println("---")
	fmt.Println("输出到控制台并换行")
	fmt.Printf("name=%s,age=%d\n", "Tom", 30)
	fmt.Printf("name=%s,age=%d,height=%v\n", "Tom", 30, fmt.Sprintf("%.2f", 180.567))
}

func Swap2(x, y string) (string, string) {
	return y, x
}

func Swap3(x, y, z string) (string, string, string) {
	return z, y, x
}

// NakedReturn naked return
func NakedReturn(sum int) (x, y int) {
	x = sum*4 + 9
	y = sum/2 - 9
	return
}

func NeedInt(x int) int {
	return x*10 + 1
}

func NeedFloat(x float64) float64 {
	return x * 0.1
}
