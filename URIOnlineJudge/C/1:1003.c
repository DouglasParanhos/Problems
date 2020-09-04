//Read two integer values, in this case, the variables A and B. After this, calculate the sum between them and assign it to the variable SOMA. Write the value of this variable.

//Input
//The input file contains 2 integer numbers.

//Output
//Print the variable SOMA with all the capital letters, with a blank space before and after the equal signal followed by the corresponding value to the sum of A and B. Like all the problems, don't forget to print the end of line, otherwise you will receive "Presentation Error"

//Language: C
//Id: 1003
//Difficulty: 1

#include <stdio.h>

int main()
{
    int a, b, x = 0;
    scanf("%d", &a);
    scanf("%d", &b);
    
    x = a + b;
    
    printf("SOMA = %d\n", x);
    return 0;
}
