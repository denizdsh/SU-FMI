#include <iostream>

unsigned recursionSum(unsigned n, unsigned counter = 1) {
	if (counter >= n) {
		return counter;
	}

	return recursionSum(n, counter + 1) + counter;
}

bool findDigit(long n, unsigned short k, unsigned long denominator = 1) {
	if (((n > 0) && denominator > n)
		|| ((n < 0) && (-1 * denominator) < n)) {
		return false;
	}

	if (k == (n / denominator) % 10) { // k ?= current digit
		return true;
	}

	return findDigit(n, k, denominator * 10);
}

long digitSum(long n, unsigned long denominator = 1) {
	if (((n >= 0) && denominator > n)
		|| ((n < 0) && (-1 * denominator) < n)) {
		return 0;
	}

	return digitSum(n, denominator * 10) + (n / denominator) % 10;
}

bool isSequenceAscending(int* sequence, unsigned short size) {
	if (size == 0) {
		return true;
	}

	return *(sequence + size - 1) > *(sequence + size - 2)
		&& isSequenceAscending(sequence, size - 1);
}

int main()
{
	/* 1)
	unsigned n;
	std::cin >> n;
	std::cout << recursionSum(n);
	*/

	/* 2)
	long n;
	unsigned short k;
	std::cin >> n;
	std::cin >> k;
	std::cout << findDigit(n, k);
	*/

	/* 3)
	long n;
	std::cin >> n;
	std::cout << digitSum(n);
	*/

	/* 4)
	const short n = 5;
	int arr[n] = { 3,4,6,9,10 };
	std::cout << isSequenceAscending(arr, n);
	*/
}
