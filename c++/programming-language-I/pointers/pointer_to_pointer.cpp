#include <iostream>

int main(int argc, char const *argv[])
{
    int x = 10;
    int* ptr = &x;
    int** pptr = &ptr;
    std::cout << "Value address x: " <<  &x << std::endl;
    std::cout << "Value x: " <<  x << std::endl;
    std::cout << "Value: address of ptr: " << ptr << std::endl;
    std::cout << "Value: x: " << *ptr << std::endl;
    std::cout << "Value address pptr: : " << pptr << std::endl;
    std::cout << "Value address ptr: : " << *pptr << std::endl;
    std::cout << "Value x: " << **pptr << std::endl;
    return 0;
}
