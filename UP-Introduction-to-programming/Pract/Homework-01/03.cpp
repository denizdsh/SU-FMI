#include <iostream>

int main()
{
	short n;
	std::cin >> n;

	int prevNum, currNum;
	std::cin >> currNum;
	prevNum = currNum;

	int bestLen = 0, bestIdx = 0, currIdx = 0;

	for (size_t i = 2; i <= n; i++)
	{
		std::cin >> currNum;

		if (prevNum != currNum) {
			currIdx = i - 1;
		}

		int currLen = i - currIdx;

		if (currLen >= bestLen) {
			bestIdx = currIdx;
			bestLen = currLen;
		}

		prevNum = currNum;
	}

	std::cout << std::endl
		<< bestLen
		<< std::endl
		<< bestIdx;
}