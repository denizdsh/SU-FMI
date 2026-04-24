#include <iostream>

bool isPrime(int number) {
	bool isPrime = true;

	for (size_t p = 2; p <= number / p; p++)
	{
		if (number % p == 0) {
			isPrime = false;
			break;
		}
	}

	return isPrime;
}

int digitSum(int number) {
	int sum = 0;

	while (number != 0)
	{
		sum += number % 10;
		number /= 10;
	}

	return sum;
}

int main()
{
	int n;
	std::cin >> n;

	for (int i = 101; i <= n; i++) {
		if (!isPrime(i)) {
			continue;
		}

		if (digitSum(i) >= 10) {
			continue;
		}

		std::cout << i << " ";
	}
}
