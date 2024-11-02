#include <iostream>
#include <cmath>

int main()
{
	double x, y, r;
	std::cin >> x >> y >> r;

	double z = sqrt(x * x + y * y);

	if (z == r) {
		std::cout << "On the circle";
	}
	else if (z < r) {
		std::cout << "Inside the circle";
	}
	else {
		std::cout << "Outside the circle";
	}
}