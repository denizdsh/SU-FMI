#include <iostream>

int main()
{
	char symbol;
	std::cin >> symbol;

	if (symbol > 47 && symbol < 58) {
		std::cout << "digit";
	}
	else if (symbol > 72 && symbol < 91) {
		std::cout << "uppercase letter";
	}
	else if (symbol > 96 && symbol < 123) {
		std::cout << "lowercase letter";
	}
	else {
		std::cout << "other";
	}
}
