#include <stdio.h>
/* print Fahrenheit-Celsius tablefor fahr = 0, 20, ..., 300 */

celsius()
{
    int fahr, celsius;
    int lower, upper, step;

    lower = 0;
    upper = 300;
    step = 20;

    celsius = lower;
    printf("C\tF\n");
    while (celsius <= upper)
    {
        fahr = (celsius * 5/9) + 32;
        printf("%d\t%d\n", celsius, fahr);
        celsius = celsius + step;
    }
}

fahrenheit()
{
    int fahr, celsius;
    int lower, upper, step;

    lower = 0;
    upper = 300;
    step = 20;

    fahr = lower;
    printf("F\tC\n");
    while (fahr <= upper)
    {
        celsius = 5 * (fahr-32) /9;
        printf("%d\t%d\n", fahr, celsius);
        fahr = fahr + step;
    }
}

main(){
	fahrenheit();
	printf("\n");
	celsius();
}
