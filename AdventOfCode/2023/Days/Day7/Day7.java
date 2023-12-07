package Days.Day7;

import Days.Day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day7 extends Day {

    List<Hand> hands = new ArrayList<>();

    public Day7() {
        super(7);
        this.inputLines.forEach(line -> {
            String[] parts = line.split(" ");
            hands.add(new Hand(parts[0], Integer.parseInt(parts[1])));
        });
    }

    @Override
    public Number execPart1() {
        Collections.sort(hands);
        long sum = 0L;
        System.out.println(hands);
        for(int i=0; i<hands.size(); i++) {
            sum += ((long) hands.get(i).bid * (i + 1));
        }
        return sum;
    }

    @Override
    public Number execPart2() {
        return null;
    }

    private static class Hand implements Comparable<Hand>{

        private final String cards;

        private final int bid;

        private final TYPE_HAND type;

        private Hand(String cards, int bid) {
            this.cards = cards;
            this.bid = bid;
            this.type = setType();
        }

        private TYPE_HAND setType() {
            HashMap<String, Integer> cards_count = new HashMap<>();
            String[] separate_cards = this.cards.split("");
            for(String s: separate_cards) {
                try {
                    int count = cards_count.get(s);
                    cards_count.put(s, ++count);
                } catch(Exception e) {
                    cards_count.put(s, 1);
                }
            }

            return TYPE_HAND.getHandTypeFromMap(cards_count);
        }

        @Override
        public int compareTo(Hand o) {
            if(TYPE_HAND.getValue(this.type) == TYPE_HAND.getValue(o.type)) {
                String[] thisCards = this.cards.split("");
                String[] otherCards = o.cards.split("");

                for(int i=0; i< thisCards.length; i++) {
                    if(!thisCards[i].equals(otherCards[i])) {
                        if(TYPE_CARD.getCardValueFromCard(thisCards[i]) == TYPE_CARD.getCardValueFromCard(otherCards[i])) {
                            continue;
                        }

                        if(TYPE_CARD.getCardValueFromCard(thisCards[i]) > TYPE_CARD.getCardValueFromCard(otherCards[i])) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                }
            } else {
                if(TYPE_HAND.getValue(this.type) > TYPE_HAND.getValue(o.type)) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            return "{cards: " + cards + ", bid: " + bid + ", type:" + type + "}";
        }
    }

    private enum TYPE_HAND {
        FIVE, FOUR, FULL_HOUSE, THREE, TWO_PAIR, ONE_PAIR, HIGH_CARD;

        private static int getValue(TYPE_HAND type){
            return switch(type) {
                case FIVE -> 6;
                case FOUR -> 5;
                case FULL_HOUSE -> 4;
                case THREE -> 3;
                case TWO_PAIR -> 2;
                case ONE_PAIR -> 1;
                default -> 0;
            };
        }

        private static TYPE_HAND getHandTypeFromMap(HashMap<String, Integer> cards_count) {
            return switch(cards_count.keySet().size()) {
                case 1 -> FIVE;
                case 2 -> {
                    var amount = cards_count.values().stream().toList().get(0);

                    yield amount == 1 || amount == 4  ? FOUR : FULL_HOUSE;
                }
                case 3 -> {
                    var amount = cards_count.values().stream().toList().contains(3);

                    yield amount ? THREE : TWO_PAIR;
                }
                case 4 -> ONE_PAIR;
                default -> HIGH_CARD;
            };
        }
    }

    private enum TYPE_CARD implements Comparable<TYPE_CARD>{
        A, K, Q, J, T, C9, C8, C7, C6, C5, C4, C3, C2;

        private static TYPE_CARD getCardTypeFromCard(String s) {
            return switch(s) {
                case "A" -> A;
                case "K" -> K;
                case "Q" -> Q;
                case "J" -> J;
                case "T" -> T;
                case "9" -> C9;
                case "8" -> C8;
                case "7" -> C7;
                case "6" -> C6;
                case "5" -> C5;
                case "4" -> C4;
                case "3" -> C3;
                default -> C2;
            };
        }

        private static int getValueFromTYPE(TYPE_CARD type) {
            return switch(type) {
                case A -> 12;
                case K -> 11;
                case Q -> 10;
                case J -> 9;
                case T -> 8;
                case C9 -> 7;
                case C8 -> 6;
                case C7 -> 5;
                case C6 -> 4;
                case C5 -> 3;
                case C4 -> 2;
                case C3 -> 1;
                default -> 0;
            };
        }

        private static int getCardValueFromCard(String card) {
            return switch(card) {
                case "A" -> getValueFromTYPE(A);
                case "K" -> getValueFromTYPE(K);
                case "Q" -> getValueFromTYPE(Q);
                case "J" -> getValueFromTYPE(J);
                case "T" -> getValueFromTYPE(T);
                case "9" -> getValueFromTYPE(C9);
                case "8" -> getValueFromTYPE(C8);
                case "7" -> getValueFromTYPE(C7);
                case "6" -> getValueFromTYPE(C6);
                case "5" -> getValueFromTYPE(C5);
                case "4" -> getValueFromTYPE(C4);
                case "3" -> getValueFromTYPE(C3);
                default -> getValueFromTYPE(C2);
            };
        }
    }
}
