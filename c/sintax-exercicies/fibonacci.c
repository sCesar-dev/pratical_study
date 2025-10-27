#include <stdio.h>
#include <stdlib.h>

int main()
{
    int next, before, actual;

    before = 0;
    actual = 0;

    printf("Sequencia de fibonacci \n");

    for(int cont = 0; cont <= 15; ){

        if (cont == 1)
            actual = 1;
        next = before + actual;
        printf("%d + %d = %d \n", actual, before, next);
        before = actual;
        actual = next;
        cont++;

    }

    return 0;
}
