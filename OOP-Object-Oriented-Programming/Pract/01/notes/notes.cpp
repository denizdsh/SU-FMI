#include <iostream>
namespace Constants {
	constexpr int INVALID_COUNT = -1;
}

enum class Criteria {
	FIND_DIGITS,
	FIND_LOWERS,
	FIND_UPPERS
};

int getCountOfSymbols(const char* string, bool (*pred)(char ch)) {
	if (!string) {
		return Constants::INVALID_COUNT;
	}

	int count = 0;

	while (*string) {
		if (pred(*string)) {
			++count;
		}

		++string;
	}

	return count;
}

bool isDigit(char ch) {
	return ch >= '0' && ch <= '9';
}
bool isLower(char ch) {
	return ch >= 'a' && ch <= 'z';
}
bool isUpper(char ch) {
	return ch >= 'A' && ch <= 'Z';
}
int getCountOfSymbolsByCriteriaFunctions(const char* string, Criteria criteria) {
	switch (criteria)
	{
	case Criteria::FIND_DIGITS:
		return getCountOfSymbols(string, isDigit);
	case Criteria::FIND_LOWERS:
		return getCountOfSymbols(string, isLower);
	case Criteria::FIND_UPPERS:
		return getCountOfSymbols(string, isUpper);
	default:
		return Constants::INVALID_COUNT;
	}
}
int getCountOfSymbolsByCriteriaLambda(const char* string, Criteria criteria) {
	switch (criteria)
	{
	case Criteria::FIND_DIGITS:
		return getCountOfSymbols(string, [](char ch) {	return ch >= '0' && ch <= '9';});
	case Criteria::FIND_LOWERS:
		return getCountOfSymbols(string, [](char ch) {return ch >= 'a' && ch <= 'z';});
	case Criteria::FIND_UPPERS:
		return getCountOfSymbols(string, [](char ch) {return ch >= 'A' && ch <= 'Z';});
	default:
		return Constants::INVALID_COUNT;
	}
}

void functions() {
	char str[12] = { 'a','b','c','A','B','C','0','1','1','1','2','\0' };

	std::cout << getCountOfSymbolsByCriteriaFunctions(str, Criteria::FIND_LOWERS)
		<< std::endl;
	std::cout << getCountOfSymbolsByCriteriaLambda(str, Criteria::FIND_DIGITS);
}

struct Point {
	int x;
	int y;
};

struct CentralPoint : Point {};

void printPoint(Point point) {
	std::cout << std::endl << "x=" << point.x
		<< std::endl << "y=" << point.y;
}

void structs() {
	Point a1 = { 2,4 };
	CentralPoint o;
	o.x = 0;
	o.y = 0;

	printPoint(a1);
	printPoint(o);
}

int main()
{
	//functions();
	//structs();
}