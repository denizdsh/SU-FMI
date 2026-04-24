#include <iostream>

int countOfFilteredNumbers(const int nums[], int size, bool (*filter)(int num)) {
	int count = 0;

	for (size_t i = 0; i < size; i++)
	{
		if (filter(nums[i])) {
			++count;
		}
	}

	return count;
}

bool isEven(int num) {
	return num % 2 == 0;
}

int main() {
	const size_t size = 15;
	int nums[size] = { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 };

	int minNum = 10;

	std::cout << countOfFilteredNumbers(nums, size, isEven) << std::endl;
	std::cout << countOfFilteredNumbers(nums, size, ([](int num) -> bool {return num % 2 != 0;}));
}