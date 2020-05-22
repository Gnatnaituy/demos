package main

import "fmt"

const Pi = 3.1415926
const (
	Big = 1 << 100
	Small = Big >> 99
)

func main()  {
	//var a string = "Good, Job"
	//fmt.Println("Hello , GO!")
	//fmt.Println(a)
	//var b, c int = 1, 2
	//fmt.Println(b, c)
	//
	//var hello = "Hello"
	//var price int
	//var time complex64
	//fmt.Println(hello, price, time)
	//
	//intnumber := 1234.56789
	//fmt.Println(intnumber)
	//
	//fmt.Println(swap("A", "B", "C"))
	//fmt.Println(NakedReturn(99))
	//
	//cSharp, java, python := true, false, "Niubilty"
	//fmt.Println(cSharp, java, python)
	//fmt.Println(cmplx.Sqrt(-5 + 12i))
	//
	//fmt.Println("Happy,", Pi, "Day!")
	//fmt.Println(needInt(Small))
	//fmt.Println(needFloat(Small))
	//fmt.Println(needFloat(Big))

	for i := 10; i < 15; i++  {
		fmt.Print(i, " ")
	}
}

func swap(x, y, z string) (string, string, string)  {
	return z, y, x
}

// naked return
func NakedReturn(sum int) (x, y int)  {
	x = sum * 4 + 9
	y = sum / 2 - 9
	return
}

func needInt(x int) int {
	return x * 10 + 1
}

func needFloat(x float64) float64  {
	return x * 0.1
}
