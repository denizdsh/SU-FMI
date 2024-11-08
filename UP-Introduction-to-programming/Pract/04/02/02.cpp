#include <iostream>

int main()
{
	int n;
	std::cin >> n;

	int number = 0;

	for (int i = 0; i < n; i++)
	{
		int current;
		std::cin >> current;

		if (current % 2 == 0) {
			number = (number * 10) + current;
		}
	}

	std::cout << number;
}