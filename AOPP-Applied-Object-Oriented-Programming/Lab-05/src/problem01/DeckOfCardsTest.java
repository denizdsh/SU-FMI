package problem01;
// Fig. 7.11: DeckOfCardsTest.java
// Card shuffling and dealing application.

public class DeckOfCardsTest {
    // execute application
    public static void main(String args[]) {
        DeckOfCards myDeckOfCards = new DeckOfCards();
        myDeckOfCards.shuffle(); // place Cards in random order

//        // print all 52 Cards in the order in which they are dealt
//        for (int i = 0; i < 13; i++) {
//            // deal and print 4 Cards
//            System.out.printf("%-20s%-20s%-20s%-20s\n",
//                    myDeckOfCards.dealCard(), myDeckOfCards.dealCard(),
//                    myDeckOfCards.dealCard(), myDeckOfCards.dealCard());
//        } // end for

        int batch = 0;
        while (batch < 52) {
            myDeckOfCards.dealHand();
            myDeckOfCards.printHandOfCards();
            myDeckOfCards.makeStatistics();
            batch += 5;

            System.out.printf("Has 2 cards of same face: %b%n", myDeckOfCards.hasSameFaces(2));

            System.out.printf("Has 3 cards of same face: %b%n", myDeckOfCards.hasSameFaces(3));

            System.out.printf("Has 2 cards of same suit: %b%n", myDeckOfCards.hasSameSuits(2));

            System.out.printf("Has 3 cards of same suit: %b%n", myDeckOfCards.hasSameSuits(3));
        }

    } // end main
} // end class DeckOfCardsTest

