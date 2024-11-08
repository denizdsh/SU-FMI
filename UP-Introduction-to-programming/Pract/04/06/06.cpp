#include <iostream>

int main()
{
	int n;
	std::cin >> n;

	if (n > 1) {
		std::cout << 1 << " ";
	}
	else {
		return -1;
	}

	int previous = 0, current = 1, next = 1;

	for (size_t i = 1; i < n; i++)
	{
		next = previous + current;

		std::cout << next << " ";

		previous = current;
		current = next;
	}
}
