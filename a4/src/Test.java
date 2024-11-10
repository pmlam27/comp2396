public class Test {
    public static void main(String[] args) {
        CardList list = new CardList();
        Card[] cards = {
                new BigTwoCard(0, 3),
                new BigTwoCard(0, 4),
                new BigTwoCard(0, 5),
                new BigTwoCard(0, 6),
                new BigTwoCard(0, 7)
        };

        for(Card card : cards) {
            System.out.println(card);
            list.addCard(card);
        }

        CardGamePlayer player = new CardGamePlayer();
        Hand result = BigTwo.composeHand(player, list);
        if(result != null) {
            System.out.println(result.getType());
            System.out.println(result.getTopCard());
        } else {
            System.out.println("null");
        }
    }

}
