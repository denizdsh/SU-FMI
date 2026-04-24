#include <iostream>
#include "notes.h"

// Seminar 1.1 - Lambda
bool isOdd(int x) {
	return x % 2 != 0;
}

void printIf(const int* arr, int size, bool (*predicate)(int)) {
	for (size_t i = 0; i < size; i++)
	{
		if (predicate(arr[i])) {
			std::cout << arr[i] << std::endl;
		}
	}
}

void lambda() {
	const int size = 10;
	int arr[size] = { 1,2,3,4,5,6,7,8,9,10 };

	printIf(arr, size, ([](int num) -> bool {return num > 5;}));
	std::cout << "\n\n";

	printIf(arr, size, isOdd);
	std::cout << "\n\n";

	printIf(arr, size, &isOdd);
	std::cout << "\n\n";

	bool (*predicate)(int num) = ([](int num) -> bool {
		return num % 2 == 0;
		});

	printIf(arr, size, predicate);
	std::cout << "\n\n";

	predicate = ([](int num) {return num < 6;});
	printIf(arr, size, predicate);
	std::cout << "\n\n";

	predicate = isOdd;
	printIf(arr, size, predicate);
	std::cout << "\n\n";

	predicate = &isOdd;
	printIf(arr, size, predicate);
	std::cout << "\n\n";
}


// Seminar 1.2 - Strings revision
void print(const char* txt) {
	while (*txt != '\0') {
		std::cout << *txt;
		txt++;
	}
}

void strings() {
	const char* text = "Hello";
	// std::cout << text; -> Hello
	// 'H' 'e' ... 'o' '\0'; 
	// Терминираща нула накрая - винаги!
	// => не подаваме размер на масива, а го обработваме,
	// докато стигнем терминиращата нула (край на низа)

	print("Test"); // Литерал работи само при CONST char* txt !
}


// Seminar 1.3 - Enums
enum Weekday : int
	// :char, :long -> може да е всякакъв целочислен тип
{
	MONDAY,
	TUESDAY,
	WEDNESDAY = 30,
	THURSDAY, //31
	FRIDAY, //32
	SATURDAY = 'a',
	SUNDAY //98
};

void printWeekday(Weekday weekday) {
	switch (weekday)
	{
	case MONDAY:
		std::cout << "It is monday!";
		break;
	case TUESDAY:
		std::cout << "It is tuesday!";
		break;
	case WEDNESDAY:
		break;
	case THURSDAY:
		break;
	case FRIDAY:
		break;
	case SATURDAY:
		break;
	case SUNDAY:
		break;
	default:
		break;
	}
}

enum RGB {
	RED,
	GREEN,
	BLUE
};

enum class PCLights {
	YELLOW,
	RED,
	BLUE,
	BROWN
};

void enums() {
	Weekday tuesday = TUESDAY;
	printWeekday(tuesday);
	printWeekday((Weekday)0);

	RED; //-> колизия (RGB -> 0, PCLights -> 1), 
	//взима се последното дефинирано

	PCLights::RED; //1
	RGB::RED; //0

	PCLights blue = PCLights::BLUE; // ✔️
	// PCLights blue = RGB::BLUE; ✖️ 
	// въпреки че RGB::BLUE == PCLights::BLUE

	// ИЗВОД -> ВИНАГИ ПОЛЗВАМЕ enum class
}


// Seminar 1.4 - Structs
enum class Direction {
	UP,
	DOWN
};

struct Point3D {
	double x;
	double y;
	double z = 0; // Default value
	Direction dir;
};

struct IntArray {
	int* arr;
	int size;
};

void printIntArr(IntArray arr) {
	for (size_t i = 0; i < arr.size; i++)
	{
		std::cout << arr.arr[i] << " ";
	}
}

IntArray initArr(int size) {
	IntArray newArr = { new int[size], size };
	//...
}
// Delete..Arr
void structs() {
	Point3D point{ 10, 20, 10, Direction::DOWN };
	std::cout << point.z << std::endl;

	IntArray myArr{ new int[5], 5 };
	myArr.arr[0] = 1;
	myArr.arr[1] = 2;
	myArr.arr[2] = 3;
	myArr.arr[3] = 4;
	myArr.arr[4] = 5;

	printIntArr(myArr);
	delete[] myArr.arr;
}


int main()
{
	//lambda();
	//strings();
	//enums();
	structs();
}
