#include <iostream>

int main()
{
	int n;
	std::cin >> n;

	int max = INT32_MIN, second = INT32_MIN;

	for (size_t i = 2; i <= n + 1; i++)
	{
		int number;
		std::cin >> number;

		if (number > max) {
			if (max > second) {
				second = max;
			}

			max = number;
		}
		else if (number > second) {
			second = number;
		}
	}

	std::cout << max << " " << second;
}
