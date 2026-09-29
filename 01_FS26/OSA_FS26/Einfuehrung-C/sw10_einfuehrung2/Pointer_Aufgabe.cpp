#include <stdio.h>

void f() {
    static int counter = 0;
    counter++;
    printf("counter = %d\n", counter);
}

int main() {
    f();
    f();
    printf("%s\n", "Hello, world!");
    return 0;
}