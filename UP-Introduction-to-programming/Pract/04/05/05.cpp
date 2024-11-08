#include <iostream>

int main()
{
	int n;
	std::cin >> n;
	int sum = 0, min = INT32_MAX, max = INT32_MIN;

	for (size_t i = 0; i < n; i++)
	{
		int current;
		std::cin >> current;

		sum += current;
		min = min > current ? current : min;
		max = max > current ? max : current;
	}

	std::cout << "min = " << min
		<< ", max = " << max
		<< ", avg = " << (1.0 * sum) / n;
}
