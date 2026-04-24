#include <iostream>

// Character identifier funcitons

bool isInASCIIRange(char symbol, int from, int to) {
	return symbol >= from && symbol <= to;
}

bool isDigit(char symbol) {
	return isInASCIIRange(symbol, '0', '9');
}

bool isUpper(char symbol) {
	return isInASCIIRange(symbol, 'A', 'Z');
}

bool isLower(char symbol) {
	return isInASCIIRange(symbol, 'a', 'z');
}

bool isAlpha(char symbol) {
	return isUpper(symbol) || isLower(symbol);
}

bool isAlnum(char symbol) {
	return isDigit(symbol) || isAlpha(symbol);
}

// Character manipulation funcitons

char capitalize(char character) {
	if (!isAlpha(character)) {
		return '\0';
	}

	if (isLower(character)) {
		return (character + ('A' - 'a'));
	}

	return character;
}

char makeLower(char character) {
	if (!isAlpha(character)) {
		return '\0';
	}

	if (isUpper(character)) {
		return (character - ('A' - 'a'));
	}

	return character;
}

int parseInt(char character) {
	if (!isDigit(character)) {
		return -1;
	}

	return character - '0';
}

char toChar(int number) {
	char character = number + '0';

	if (!isDigit(character)) {
		return '\0';
	}

	return character;
}

// Mathematics helper funcitons

// Only works with natural numbers
double myAbs(double number) {
	if (number < 0) {
		return 0 - number;
	}

	return number;
}

int myLog(int base, int number) {
	if (base < 0 || number < 0) {
		return -1;
	}

	int power = 0;

	do {
		if (number % base != 0) {
			return -1;
		}
		number /= base;
		++power;

	} while (number >= base);

	return power;
}

double myPow(double number, int power) {
	if (power == 0) {
		return 1;
	}

	double product = number;

	for (int i = 2; i <= myAbs(power); i++) {
		product *= number;
	}

	if (power < 0) {
		return 1 / product;
	}

	return product;
}

double myFloor(double number) {
	int result = number;

	if (result == number || result > 0) {
		return result;
	}

	return result - 1;
}

double myCeil(double number) {
	int result = number;

	if (result == number || result <= 0)
	{
		return result;
	}

	return result + 1;
}

int main()
{
	std::cout << "Character identifier funcitons" << std::endl << std::endl;

	std::cout << "isDigit" << std::endl;
	std::cout << isDigit('9') << std::endl; // 1
	std::cout << isDigit('A') << std::endl; // 0
	std::cout << isDigit('$') << std::endl; // 0

	std::cout << "isUpper" << std::endl;
	std::cout << isUpper('A') << std::endl; // 1
	std::cout << isUpper('a') << std::endl; // 0
	std::cout << isUpper('7') << std::endl; // 0

	std::cout << "isLower" << std::endl;
	std::cout << isLower('a') << std::endl; // 1
	std::cout << isLower('A') << std::endl; // 0
	std::cout << isLower('9') << std::endl; // 0

	std::cout << "isAlpha" << std::endl;
	std::cout << isAlpha('a') << std::endl; // 1
	std::cout << isAlpha('A') << std::endl; // 1
	std::cout << isAlpha('5') << std::endl; // 0
	std::cout << isAlpha('#') << std::endl; // 0

	std::cout << "isAlnum" << std::endl;
	std::cout << isAlnum('4') << std::endl; // 1
	std::cout << isAlnum('Z') << std::endl; // 1
	std::cout << isAlnum('v') << std::endl; // 1
	std::cout << isAlnum('%') << std::endl; // 0


	std::cout << std::endl << "Character manipulation funcitons" << std::endl << std::endl;

	std::cout << "capitalize" << std::endl;
	std::cout << capitalize('a') << std::endl; // A
	std::cout << capitalize('A') << std::endl; // A

	std::cout << "makeLower " << std::endl;
	std::cout << makeLower('A') << std::endl; // a
	std::cout << makeLower('a') << std::endl; // a

	std::cout << "parseInt " << std::endl;
	std::cout << parseInt('7') << std::endl; // 7
	std::cout << parseInt('a') << std::endl; // -1

	std::cout << "toChar" << std::endl;
	std::cout << toChar(7) << std::endl; // '7'
	std::cout << toChar('a') << std::endl; // '\0'


	std::cout << std::endl << "Mathematics helper funcitons" << std::endl << std::endl;

	std::cout << "myAbs" << std::endl;
	std::cout << myAbs(-2) << std::endl; // 2
	std::cout << myAbs(2) << std::endl; // 2

	std::cout << "myLog" << std::endl;
	std::cout << myLog(2, 64) << std::endl; // 6
	std::cout << myLog(2, 65) << std::endl; // -1

	std::cout << "myPow" << std::endl;
	std::cout << myPow(2, 6) << std::endl; // 64
	std::cout << myPow(2, 1) << std::endl; // 2
	std::cout << myPow(2, 0) << std::endl; // 1
	std::cout << myPow(2, -6) << std::endl; // 0.015...

	std::cout << "myFloor" << std::endl;
	std::cout << myFloor(-2.5) << std::endl; // -3
	std::cout << myFloor(-2) << std::endl; // -2

	std::cout << "myCeil" << std::endl;
	std::cout << myCeil(2.5) << std::endl; // 3
	std::cout << myCeil(2) << std::endl; // 2
}