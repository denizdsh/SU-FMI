#include <iostream>

const int CARD_DECK_SIZE = 128;

const int CARD_FORMAT = 1000000;

const int CARD_COLOR_MIN = 1;
const int CARD_COLOR_MAX = 5;
const int CARD_COLOR_DIVISOR = 1000000;

// 1...4
const int CARD_CODE_COLOR_BLACK = 5;

// 1...11, 13
const int CARD_CODE_VALUE_STOP = 12;
const int CARD_CODE_VALUE_PLUS_FOUR = 14;

const int CARD_VALUE_MIN = 0;
const int CARD_VALUE_MAX = 14;
const int CARD_VALUE_MOD_DIVISOR = 1000;
const int CARD_VALUE_CLEAN_DIVISOR = 10;

void endGame() {
	//...
	throw std::exception("Game over");
}

int getCardColor(int code) {
	int color = code / CARD_COLOR_DIVISOR;

	if (color >= CARD_COLOR_MIN && color <= CARD_COLOR_MAX) {
		return color;
	}

	return 0;
}

bool isCardValueValid(int code, int colorCode) {
	int value = (code % CARD_VALUE_MOD_DIVISOR) / CARD_VALUE_CLEAN_DIVISOR;

	switch (value)
	{
	case CARD_CODE_VALUE_STOP:
		return colorCode != CARD_CODE_COLOR_BLACK;
	case CARD_CODE_VALUE_PLUS_FOUR:
		return colorCode == CARD_CODE_COLOR_BLACK;
	}

	return value >= CARD_VALUE_MIN && value <= CARD_VALUE_MAX;
}

bool isCardValid(int code) {
	if (code < CARD_FORMAT || code >= CARD_FORMAT * 10) {
		return false;
	}

	int color = getCardColor(code);
	return color && isCardValueValid(code, color);
}

void addCardToDeckSorted(int(&deck)[CARD_DECK_SIZE], int element, int cardIdx) {
	for (size_t i = 0; i <= cardIdx; i++)
	{
		if (deck[i] == 0 || element < deck[i]) {
			int temp = deck[i];
			deck[i] = element;
			element = temp;
		}
	}
}

void initializeDeck(int(&deck)[CARD_DECK_SIZE], int cardsCount) {
	if (cardsCount < 0 || cardsCount > CARD_DECK_SIZE) {
		return endGame();
	}

	for (size_t i = 0; i < cardsCount; i++)
	{
		int card;
		std::cin >> card;

		if (isCardValid(card)) {
			addCardToDeckSorted(deck, card, i);
		}
	}
}

void printDeck(int deck[], int cardsCount) {
	for (size_t i = 0; i <= cardsCount; i++) {
		if (deck[i]) {
			std::cout << std::endl << deck[i];
		}
	}
}

int main()
{
	int cardsCount;
	std::cin >> cardsCount;

	int deck[CARD_DECK_SIZE] = {};

	initializeDeck(deck, cardsCount);

	printDeck(deck, cardsCount);
}