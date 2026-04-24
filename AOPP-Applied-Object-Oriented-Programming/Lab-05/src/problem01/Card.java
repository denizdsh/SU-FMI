package problem01;
// Fig. 7.9: Card.java
// Card class represents a playing card.

public class Card {
    public static String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    public static String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

    private int face; // face of card ("Ace", "Deuce", ...)
    private int suit; // suit of card ("Hearts", "Diamonds", ...)

    public int getFace() {
        return face;
    }

    private void setFace(int face) {
        // given fallback value = 3
        this.face = face >= 0 && face < faces.length ? face : 3;
    }

    public int getSuit() {
        return suit;
    }

    private void setSuit(int suit) {
        // given fallback value = 3
        this.suit = suit >= 0 && suit < suits.length ? suit : 3;
    }

    // two-argument constructor initializes card's face and suit
    public Card(int cardFaceIdx, int cardSuitIdx) {
        setFace(cardFaceIdx);
        ; // initialize face of card
        setSuit(cardSuitIdx); // initialize suit of card
    } // end two-argument Card constructor

    // return String representation of Card
    public String toString() {
        return faces[face] + " of " + suits[suit];
    } // end method toString
} // end class Card

