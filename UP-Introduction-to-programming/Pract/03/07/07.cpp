#include <iostream>

int main()
{
	int hour, minute;
	std::cin >> hour >> minute;

	minute += 15;
	if (minute >= 60) {
		minute -= 60;

		hour += 1;
		if (hour >= 24) {
			hour -= 24;
		}
	}

	std::cout << (hour > 9 ? "" : "0")
		<< hour
		<< " "
		<< (minute > 9 ? "" : "0")
		<< minute;
}