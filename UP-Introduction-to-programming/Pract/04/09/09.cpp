
#include <iostream>

int main()
{
	int x;
	std::cin >> x;

	int prev = 1;
	int i = 0;

	do {
		i++;

		int product = i * i;
		int difference = x - product;

		if (product > x
			&& prev < x) {
			double closestRealNumber = i;
			double closestAbsDifference = x - product > 0
				? x - product
				: product - x;

			for (double r = i; r > i - 1; r -= 0.001)
			{
				double product = r * r;
				double difference = product - x;
				double absDifference = difference > 0
					? difference
					: 0 - difference;

				if (closestAbsDifference > absDifference) {
					closestAbsDifference = absDifference;
					closestRealNumber = r;
				}
				else if (closestRealNumber != i
					&& absDifference > closestAbsDifference) {
					// getting further from the closest root
					break;
				}
			}

			std::cout << closestRealNumber;

			break;
		}

		prev = product;

		if (product == x) {
			std::cout << i;
			break;
		}
	} while (i <= (x / i) + 1);
}
