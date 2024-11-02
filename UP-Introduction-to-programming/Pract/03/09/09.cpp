#include <iostream>

int main()
{
	/*
	* Zeller's congruence
	* h = 0 = Saturday, 1 = Sunday, 2 = Monday, ..., 6 = Friday
	*/
	int q, m, y;
	std::cin >> q >> m >> y;

	int J = y / 100;
	int K = y % 100;

	int h = (q + (13 * (m + 1) / 5) + K + K / 4 + J / 4 - 2 * J) % 7;

	if (h < 0) {
		h += 7;
	}

	switch (h) {
	case 0:
		std::cout << "Saturday";
		break;
	case 1:
		std::cout << "Sunday";
		break;
	case 2:
		std::cout << "Monday";
		break;
	case 3:
		std::cout << "Tuesday";
		break;
	case 4:
		std::cout << "Wednesday";
		break;
	case 5:
		std::cout << "Thursday";
		break;
	case 6:
		std::cout << "Friday";
		break;
	default:
		std::cout << "Beerday";
		break;
	}
}