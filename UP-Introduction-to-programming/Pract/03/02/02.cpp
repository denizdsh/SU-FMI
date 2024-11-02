#include <iostream>

int main()
{
	int a, b;
	std::cin >> a >> b;

	char operation;
	std::cin >> operation;

	double result;
	switch (operation)
	{
	case '+':
		result = a + b;
		break;
	case '-':
		result = a - b;
		break;
	case '*':
		result = a * b;
		break;
	case '/':
		result = (a * 1.0) / b;
		break;
	default:
		//throw std::invalid_argument("Invalid operation");
		result = 0;
		break;
	}

	std::cout << result;
}