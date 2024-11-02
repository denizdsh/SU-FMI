#include <iostream>

int main()
{
	int num;
	std::cin >> num;
	std::cout << ((num % 2 == 0) ? "Even" : "Odd");
}