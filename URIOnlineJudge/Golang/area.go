/*

Make a program that reads three floating point values: A, B and C. Then, calculate and show:
a) the area of the rectangled triangle that has base A and height C.
b) the area of the radius's circle C. (pi = 3.14159)
c) the area of the trapezium which has A and B by base, and C by height.
d) the area of ​​the square that has side B.
e) the area of the rectangle that has sides A and B.
Input

The input file contains three double values with one digit after the decimal point.
Output

The output file must contain 5 lines of data. Each line corresponds to one of the areas described above, always with a corresponding message (in Portuguese) and one space between the two points and the value. The value calculated must be presented with 3 digits after the decimal point.*/

package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func main() {

	pi := 3.14159

	reader := bufio.NewReader(os.Stdin)
	line, _ := reader.ReadString('\n')

	values := strings.Fields(line)

	A, _ := strconv.ParseFloat(values[0], 64)
	B, _ := strconv.ParseFloat(values[1], 64)
	C, _ := strconv.ParseFloat(values[2], 64)

	fmt.Println("TRIANGULO:", fmt.Sprintf("%0.3f", (A*C)/2))
	fmt.Println("CIRCULO:", fmt.Sprintf("%0.3f", pi*math.Pow(C, 2)))
	fmt.Println("TRAPEZIO:", fmt.Sprintf("%0.3f", ((A+B)/2)*C))
	fmt.Println("QUADRADO:", fmt.Sprintf("%0.3f", math.Pow(B, 2)))
	fmt.Println("RETANGULO:", fmt.Sprintf("%0.3f", A*B))
}
