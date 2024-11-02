#include <iostream>

int main()
{
	int a, b, c, d, e;
	std::cin >> a >> b >> c >> d >> e;

	if ((b >= a && b >= c
		&& d >= c && d >= e) // (0 1 0 1 0)
		|| (b <= a && b <= c
			&& d <= c && d <= e)) //(1 0 1 0 1)
	{
		std::cout << "yes";
	}
	else {
		std::cout << "no";
	}
}