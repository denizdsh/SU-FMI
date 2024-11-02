#include <iostream>

int main()
{
	double x, y, z;

	std::cin >> x >> y >> z;

	// x - smallest 
	if (x <= y && x <= z) {
		if (y < z) {
			std::cout << x << " "
				<< y << " "
				<< z;
		}
		else {
			std::cout << x << " "
				<< z << " "
				<< y;
		}
	}

	// y - smallest 
	else if (y <= x && y <= z) {
		if (x < z) {
			std::cout << y << " "
				<< x << " "
				<< z;
		}
		else {
			std::cout << y << " "
				<< z << " "
				<< x;
		}
	}

	// z - smallest 
	else {
		if (x < y) {
			std::cout << z << " "
				<< x << " "
				<< y;
		}
		else {
			std::cout << z << " "
				<< y << " "
				<< x;
		}
	}
}