#include <stdio.h>
#define MAX_STRINGS 50

// struct animal {
//     char animal_name[MAX_STRINGS + 1], animal_color[MAX_STRINGS + 1];
//     int animal_number;
// };

typedef struct{
    char animal_name[MAX_STRINGS + 1], animal_color[MAX_STRINGS + 1];
    int animal_number;
} Animal;

int main(void){

    Animal animal1 = (Animal){
        .animal_name = "Dog",
        .animal_color = "Black",
        .animal_number = 12
    };

    Animal animal2 = (Animal){
        .animal_name = "Cat",
        .animal_color = "White",
        .animal_number = 5
    };

    printf("Name: %s \nColor: %s \nNumber: %d\n", animal1.animal_name, animal1.animal_color, animal1.animal_number);
    printf("\nName: %s \nColor: %s \nNumber: %d\n", animal2.animal_name, animal2.animal_color, animal2.animal_number);
}