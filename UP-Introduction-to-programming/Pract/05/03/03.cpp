#include <iostream>

bool isPerfectNumber(int number)
{
	int sum = 1;

	for (size_t i = 2; i <= number / 2; i++)
	{
		if (number % i == 0)
			sum += i;
	}

	return sum == number;
}
int main()
{
	int x;
	std::cin >> x;
	std::cout << std::boolalpha << isPerfectNumber(x);
}