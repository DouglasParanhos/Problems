/*

Make a program that reads 3 integer values and present the greatest one followed by the message "eh o maior". Use the following formula:

Input

The input file contains 3 integer values.
Output

Print the greatest of these three values followed by a space and the message “eh o maior”.*/

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

	reader := bufio.NewReader(os.Stdin)
	line, _ := reader.ReadString('\n')

	values := strings.Fields(line)

	A, _ := strconv.Atoi(values[0])
	B, _ := strconv.Atoi(values[1])
	C, _ := strconv.Atoi(values[2])

	maior1 := (A + B + int(math.Abs(float64(A-B)))) / 2
	maior2 := (A + C + int(math.Abs(float64(A-C)))) / 2

	if maior1 > maior2 {
		fmt.Println(maior1, "eh o maior")
	} else {
		fmt.Println(maior2, "eh o maior")
	}

}
