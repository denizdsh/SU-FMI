#include <iostream>

bool isPrime(int number) {
	for (int i = 2; i <= number / i; i++)
	{
		if (number % i == 0) {
			return false;
		}
	}

	return true;
}

void printMersennePrimes(unsigned range) {
	for (size_t i = 2; i <= range; i *= 2)
	{
		if (isPrime(i - 1)) {
			std::cout << i - 1 << std::endl;
		}
	}
}

int main()
{
	unsigned short n;

	std::cin >> n;

	printMersennePrimes(n + 1);
}

