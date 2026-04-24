#include <iostream>

int* cycleSortWithMemoryAllocation(const int unordered[], int size) {
	int* res = new int[size];
	for (int i = 0; i < size; i++) {
		res[i] = unordered[i];
	}

	for (int cycleStart = 0; cycleStart < size - 1; cycleStart++) {
		int item = res[cycleStart];
		int pos = cycleStart;

		for (int i = cycleStart + 1; i < size; i++) {
			if (res[i] < item) {
				pos++;
			}
		}

		if (pos == cycleStart) {
			continue;
		}

		while (item == res[pos]) {
			pos++;
		}

		if (pos != cycleStart) {
			std::swap(item, res[pos]);
		}

		while (pos != cycleStart) {
			pos = cycleStart;

			for (int i = cycleStart + 1; i < size; i++) {
				if (res[i] < item) {
					pos++;
				}
			}

			while (item == res[pos]) {
				pos++;
			}

			if (item != res[pos]) {
				std::swap(item, res[pos]);
			}
		}
	}

	return res;
}

int binarySearch(int arr[], int size, bool(*pred)(int num)) {
	int low = 0;
	int high = size - 1;
	int resIdx = -1;

	while (low <= high) {
		int mid = low + (high - low) / 2;

		if (pred(arr[mid])) {
			resIdx = mid;
			high = mid - 1;
		}
		else {
			low = mid + 1;
		}
	}

	return arr[resIdx];
}

int find(
	const int unordered[],
	int size,
	int* (*sortingFn)(const int nums[], int size),
	bool (*pred)(int num)
) {
	int* nums = sortingFn(unordered, size);
	int res = binarySearch(nums, size, pred);

	delete[] nums;

	return res;
}

int main()
{
	const int size = 10;
	int nums[size] = { 2, 10, 3, 4, 6, 7, 5, 1, 9, 8 };
	std::cout << find(nums, size, cycleSortWithMemoryAllocation, [](int num) {return num % 10 == 0;});
}
