#include <iostream>

int main()
{
	long binary;
	std::cin >> binary;

	int number = binary % 10, power = 0;
	binary /= 10;

	while (binary > 0) {
		int current = binary % 10;
		binary /= 10;
		power++;

		if (!current) {
			continue;
		}

		int product = 2;

		for (size_t i = 2; i <= power; i++) {
			product *= 2;
		}

		number += product;
	}

	std::cout << number;
}
