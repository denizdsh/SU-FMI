#include <iostream>

bool isPalindrome(int number) {
	//while (number != 0) {
		//number
	//}

	std::cout << number % 10 << "\n";
	std::cout << number / 10 << "\n";
	std::cout << number / 10000 << "\n";

	return false;
}

int main()
{
	int sequence;
	std::cin >> sequence;
	std::cout << std::boolalpha << isPalindrome(sequence);
}
