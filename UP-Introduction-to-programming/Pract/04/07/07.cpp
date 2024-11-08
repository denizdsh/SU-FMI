#include <iostream>

int main()
{
	int n;
	std::cin >> n;

	for (int i = 101; i <= n; i++) {
		bool isPrime = true;

		for (size_t p = 2; p <= i / p; p++)
		{
			if (i % p == 0) {
				isPrime = false;
				break;
			}
		}

		if (!isPrime) {
			continue;
		}

		int sum = 0;

		int temp = i;

		while (temp != 0)
		{
			sum += temp % 10;

			if (sum >= 9) {
				break;
			}

			temp /= 10;
		}

		if (sum > 9 || temp) {
			continue;
		}

		std::cout << i << " ";
	}
}
