//Make a program that reads a seller's name, his/her fixed salary and the sale's total made by himself/herself in the month (in money). Considering that this seller receives 15% over all products sold, write the final salary (total) of this seller at the end of the month , with two decimal places.

//- Don’t forget to print the line's end after the result, otherwise you will receive “Presentation Error”.

//- Don’t forget the blank spaces.
//Input

//The input file contains a text (employee's first name), and two double precision values, that are the seller's salary and the total value sold by him/her.
//Output

//Print the seller's total salary, according to the given example.

//Language: C
//Id: 1009
//Difficulty: 1

#include <stdio.h>

#define MAX_NAME_LENGTH 256

int main()
{
    
    double a, b = 0;
    char s[MAX_NAME_LENGTH];
    
    scanf("%s", s);
    scanf("%lf", &a);
    scanf("%lf", &b);
    
    printf("TOTAL = R$ %.2lf\n", (a + ((0.15)*b)));
    return 0;
}
