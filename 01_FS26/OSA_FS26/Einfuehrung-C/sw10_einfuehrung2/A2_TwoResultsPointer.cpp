#include <stdio.h>

void berechne(int a, int b, int *summe, int *differenz) {
    *summe = a + b;
    *differenz = a - b;
}

int main(void) {
    int a = 10;
    int b = 4;
    int summe;
    int differenz;

    berechne(a, b, &summe, &differenz);

    printf("a = %d, b = %d\n", a, b);
    printf("Summe     = %d\n", summe);
    printf("Differenz = %d\n", differenz);

    return 0;
}