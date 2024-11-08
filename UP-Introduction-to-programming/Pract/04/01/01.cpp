#include <iostream>

int main()
{
	int x;
	std::cin >> x;	
	bool isPrime = true;

	//x = 17 ->  8, 5, 4 | 3, 2
	//i = x/i -> 2, 3, 4 | 5, 6
	for (int i = 2; i <= x / i; i++)
	{
		if (x % i == 0) {
			isPrime = false;
			break;
		}
	}

	std::cout << (isPrime ? "prime" : "not prime");
}