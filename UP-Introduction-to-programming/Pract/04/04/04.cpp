#include <iostream>

int main()
{
	int sequence;
	std::cin >> sequence;

	int mostCommonDigit = sequence % 10, counter = 1;

	while (sequence != 0)
	{
		int current = sequence % 10;
		int localCounter = 1;
		int tempSequence = sequence / 10;

		while (tempSequence != 0)
		{
			if (tempSequence % 10 == current)
			{
				++localCounter;
			}

			tempSequence /= 10;
		}

		if ((localCounter > counter)
			|| (localCounter == counter && current > mostCommonDigit)) {
			counter = localCounter;
			mostCommonDigit = current;
		}

		sequence /= 10;
	}

	std::cout << mostCommonDigit;
}
