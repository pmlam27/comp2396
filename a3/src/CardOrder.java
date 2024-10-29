public class CardOrder {
    public static int bigTwoCompareWithRankFirst(Card cardA, Card cardB) {
        int ABigTwoRank = fromNormalToBigTwoOrder(cardA.getRank());
        int BBigTwoRank = fromNormalToBigTwoOrder(cardB.getRank());

        if (ABigTwoRank > BBigTwoRank) {
            return 1;
        } else if (ABigTwoRank < BBigTwoRank) {
            return -1;
        } else if (cardA.getSuit() > cardB.getSuit()) {
            return 1;
        } else if (cardA.getSuit() < cardB.getSuit()) {
            return -1;
        } else {
            return 0;
        }
    }

    public static int bigTwoCompareWithSuitFirst(Card cardA, Card cardB) {
        int ABigTwoRank = fromNormalToBigTwoOrder(cardA.getRank());
        int BBigTwoRank = fromNormalToBigTwoOrder(cardB.getRank());

        if (cardA.getSuit() > cardB.getSuit()) {
            return 1;
        } else if (cardA.getSuit() < cardB.getSuit()) {
            return -1;
        } else if (ABigTwoRank > BBigTwoRank) {
            return 1;
        } else if (ABigTwoRank < BBigTwoRank) {
            return -1;
        } else {
            return 0;
        }
    }

    public static int fromNormalToBigTwoOrder(int normalOrder) {
        /*
        lowest  0   1   2   3   4   5   6   7   8   9   10  11  12  highest
        normal: A   2   3   4   5   6   7   8   9   10  J   Q   K
        big 2:  3   4   5   6   7   8   9   10  J   Q   K   A   2

        normal to big2 mapping:
        {11, 12, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
         */

        int[] mapping = {11, 12, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        return mapping[normalOrder];
    }

//    public int getNormalOrderOfRank(CardRank rank) {
//       return switch(rank) {
//            case rA -> 0;
//            case r2 -> 1;
//            case r3 -> 2;
//            case r4 -> 3;
//            case r5 -> 4;
//            case r6 -> 5;
//            case r7 -> 6;
//            case r8 -> 7;
//            case r9 -> 8;
//            case r0 -> 9;
//            case rJ -> 10;
//            case rQ -> 11;
//            case rK -> 12;
//        };
//    }
//
//    public CardRank getRankOfNormalOrder(int order) {
//        CardRank[] mapping = {
//                CardRank.rA, CardRank.r2, CardRank.r3, CardRank.r4, CardRank.r5, CardRank.r6, CardRank.r7,
//                CardRank.r8, CardRank.r9, CardRank.r0, CardRank.rJ, CardRank.rQ, CardRank.rK
//        };
//        return mapping[order];
//    }
//
//    public int getBigTwoOrderOfRank(CardRank rank) {
//        return switch(rank) {
//            case r3 -> 0;
//            case r4 -> 1;
//            case r5 -> 2;
//            case r6 -> 3;
//            case r7 -> 4;
//            case r8 -> 5;
//            case r9 -> 6;
//            case r0 -> 7;
//            case rJ -> 8;
//            case rQ -> 9;
//            case rK -> 10;
//            case rA -> 11;
//            case r2 -> 12;
//        };
//    }
//
//    public CardRank getRankOfBigTwoOrder(int order) {
//        CardRank[] mapping = {
//                CardRank.r3, CardRank.r4, CardRank.r5, CardRank.r6, CardRank.r7, CardRank.r8, CardRank.r9,
//                CardRank.r0, CardRank.rJ, CardRank.rQ, CardRank.rK, CardRank.rA, CardRank.r2
//        };
//        return mapping[order];
//    }
}
