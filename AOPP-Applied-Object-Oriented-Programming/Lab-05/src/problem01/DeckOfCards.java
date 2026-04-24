package problem01;
// Fig. 7.10: DeckOfCards.java
// DeckOfCards class represents a deck of playing cards.

import java.util.Arrays;
import java.util.Random;

public class DeckOfCards {
    private Card deck[]; // array of Card objects
    private int currentCard; // index of next Card to be dealt
    private final int NUMBER_OF_CARDS = 52; // constant number of Cards
    private Random randomNumbers; // random number generator

    private int[] faceCounters;
    private int[] suitCounters;
    private Card[] handOfCards;

    // constructor fills deck of Cards
    public DeckOfCards() {
        faceCounters = new int[Card.faces.length];
        suitCounters = new int[Card.suits.length];
        handOfCards = new Card[5];

        deck = new Card[NUMBER_OF_CARDS]; // create array of Card objects
        currentCard = 0; // set currentCard so first Card dealt is deck[ 0 ]
        randomNumbers = new Random(); // create random number generator

        // populate deck with Card objects
        for (int count = 0; count < deck.length; count++)
            deck[count] = new Card(count % 13, count / 13);
    } // end DeckOfCards constructor

    // shuffle deck of Cards with one-pass algorithm
    public void shuffle() {
        // after shuffling, dealing should start at deck[ 0 ] again
        currentCard = 0; // reinitialize currentCard

        // for each Card, pick another random Card and swap them
        for (int first = 0; first < deck.length; first++) {
            // select a random number between 0 and 51
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            // swap current Card with randomly selected Card
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        } // end for
    } // end method shuffle

    // deal one Card
    public Card dealCard() {
        // determine whether Cards remain to be dealt
        if (currentCard < deck.length) return deck[currentCard++]; // return current Card in array
        else return null; // return null to indicate that all Cards were dealt
    } // end method dealCard

    public void dealHand() {
        for (int i = 0; i < handOfCards.length; i++) {
            handOfCards[i] = dealCard();
        }
    }

    public void printHandOfCards() {
        for (Card card : handOfCards) {
            if (card == null) continue;

            System.out.println(card);
        }
    }

    public void makeStatistics() {
        Arrays.fill(faceCounters, 0);
        Arrays.fill(suitCounters, 0);

        for (Card handOfCard : handOfCards) {
            if (handOfCard != null) {
                ++faceCounters[handOfCard.getFace()];
                ++suitCounters[handOfCard.getSuit()];
            }
        }

        System.out.println(Arrays.toString(faceCounters));
        System.out.println(Arrays.toString(suitCounters));
    }

    public boolean hasSameFaces(int faceNum) {
        if (faceNum < 2 || faceNum > 4) {
            return false;
        }

        for (int face : faceCounters) {
            if (face == faceNum) {
                return true;
            }
        }

        return false;
    }

    public boolean hasSameSuits(int suitNum) {
        if (suitNum < 2 || suitNum > 5) {
            return false;
        }

        for (int suit : suitCounters) {
            if (suit == suitNum) {
                return true;
            }
        }

        return false;
    }
} // end class DeckOfCards


