#include <stdio.h>
#include <stdlib.h>

void verdoppeln(int *p) {
    *p = *p * 2;
}

int main(void) {
    int *p = (int *) malloc(sizeof(int));
    if (p == NULL) {
        printf("Speicherreservierung fehlgeschlagen\n");
        return 1;
    }

    *p = 21;
    printf("Vor   Aenderung: %d\n", *p);

    verdoppeln(p);
    printf("Nach  Aenderung: %d\n", *p);

    free(p);
    return 0;
}