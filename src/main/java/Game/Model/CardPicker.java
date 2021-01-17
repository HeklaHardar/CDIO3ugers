package Game.Model;

public class CardPicker {

    private int random1;
    private int random2;
    private int temp;
    private int[] cards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private int first;
    private int cardNumber = 0;

    public void CardScrambler() {
        for (int i = 0; i < 1000 - 1; i++) {
            temp = cards[random1 = (int) (Math.random() * (cards.length))];
            cards[random1] = cards[random2 = (int) (Math.random() * (cards.length))];
            cards[random2] = temp;
        }
    }

    public int DrawCard() {
        cardNumber = cards[0];
        first = cards[0];

        for (int i = 0; i < cards.length - 1; i++)
            cards[i] = cards[i + 1];
        cards[cards.length - 1] = first;

        return cardNumber;
    }
}