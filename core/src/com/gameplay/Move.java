package com.gameplay;

import gameplay.Objects.*;

import java.util.*;

public class Move {

    Hero hero;

    Opponent opponent;

    List<Card> opponentCards = new ArrayList<>();
    List<Card> heroCards = new ArrayList<>();

    public Move() {
        initCardsForOpponent();
        initCardsForHero();
        opponent = new Opponent("Оппонент", "No img!", 10, 10, 15, new ArrayList<>(), opponentCards);
        hero = new Hero("Игрок", "No img!", 10, 10, 15, new ArrayList<>(), heroCards);

        opponent.takeCard();
        opponent.takeCard();
        opponent.takeCard();

        hero.takeCard();
        hero.takeCard();
        hero.takeCard();
    }

    private void initCardsForOpponent() {

        List<Integer> props = new ArrayList<>();

        props.add(5);
        CardDescription cardDescriptionWith5Dmg =
                new CardDescription("Наносит 5 ед. урона", new ArrayList<>(props), Effect.DAMAGE);
        props.clear();

        props.add(3);
        CardDescription cardDescriptionWith5armor =
                new CardDescription("Добавляет 3 ед. брони", new ArrayList<>(props), Effect.ARMOR);
        props.clear();

        props.add(2);
        CardDescription cardDescriptionWith2stamina =
                new CardDescription("Добавляет 2 ед. выносливости", new ArrayList<>(props), Effect.BONUS_STAMINA);
        props.clear();

        props.add(4);
        CardDescription cardDescriptionWith4Dmg =
                new CardDescription("Наносит 4 ед. урона", new ArrayList<>(props), Effect.DAMAGE);
        props.clear();

        props.add(3);
        CardDescription cardDescriptionWith3Dmg =
                new CardDescription("Наносит 3 ед. урона", new ArrayList<>(props), Effect.DAMAGE);
        props.clear();

        props.add(1);
        CardDescription cardDescriptionWith1Card =
                new CardDescription("+1 карта", new ArrayList<>(props), Effect.BONUS_CARD);
        props.clear();

        props.add(2);
        CardDescription cardDescriptionWith2Card =
                new CardDescription("+2 карты", new ArrayList<>(props), Effect.BONUS_CARD);
        props.clear();

        props.add(2);
        CardDescription cardDescriptionWith2Dmg =
                new CardDescription("Наносит 2 ед. урона", new ArrayList<>(props), Effect.DAMAGE);


        Card[] cards = new Card[]{
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5armor, 3, false),
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5armor, 3, false),
                new Card("No img!", cardDescriptionWith5armor, 3, false),
                new Card("No img!", cardDescriptionWith5armor, 3, false),
                new Card("No img!", cardDescriptionWith2stamina, 0, true),
                new Card("No img!", cardDescriptionWith4Dmg, 2, false),
                new Card("No img!", cardDescriptionWith3Dmg, 2, false),
                new Card("No img!", cardDescriptionWith1Card, 2, true),
                new Card("No img!", cardDescriptionWith1Card, 2, true),
                new Card("No img!", cardDescriptionWith2Card, 3, true),
                new Card("No img!", cardDescriptionWith2Card, 3, true),
                new Card("No img!", cardDescriptionWith2Dmg, 1, false),
                new Card("No img!", cardDescriptionWith2Dmg, 1, false)
        };

        opponentCards.addAll(Arrays.asList(cards));
    }

    private void initCardsForHero() {
        List<Integer> props = new ArrayList<>();

        props.add(5);
        CardDescription cardDescriptionWith5Dmg =
                new CardDescription("Наносит 5 ед. урона", new ArrayList<>(props), Effect.DAMAGE);
        props.clear();

        props.add(3);
        CardDescription cardDescriptionWith5armor =
                new CardDescription("Добавляет 3 ед. брони", new ArrayList<>(props), Effect.ARMOR);
        props.clear();

        props.add(2);
        CardDescription cardDescriptionWith2stamina =
                new CardDescription("Добавляет 2 ед. выносливости", new ArrayList<>(props), Effect.BONUS_STAMINA);
        props.clear();

        props.add(4);
        CardDescription cardDescriptionWith4Dmg =
                new CardDescription("Наносит 4 ед. урона", new ArrayList<>(props), Effect.DAMAGE);
        props.clear();

        props.add(3);
        CardDescription cardDescriptionWith3Dmg =
                new CardDescription("Наносит 3 ед. урона", new ArrayList<>(props), Effect.DAMAGE);
        props.clear();

        props.add(1);
        CardDescription cardDescriptionWith1Card =
                new CardDescription("+1 карта", new ArrayList<>(props), Effect.BONUS_CARD);
        props.clear();

        props.add(2);
        CardDescription cardDescriptionWith2Card =
                new CardDescription("+2 карты", new ArrayList<>(props), Effect.BONUS_CARD);
        props.clear();

        props.add(2);
        CardDescription cardDescriptionWith2Dmg =
                new CardDescription("Наносит 2 ед. урона", new ArrayList<>(props), Effect.DAMAGE);

        Card[] cards = new Card[]{
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5armor, 3, false),
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5Dmg, 3, false),
                new Card("No img!", cardDescriptionWith5armor, 3, false),
                new Card("No img!", cardDescriptionWith5armor, 3, false),
                new Card("No img!", cardDescriptionWith5armor, 3, false),
                new Card("No img!", cardDescriptionWith2stamina, 0, true),
                new Card("No img!", cardDescriptionWith4Dmg, 2, false),
                new Card("No img!", cardDescriptionWith3Dmg, 2, false),
                new Card("No img!", cardDescriptionWith1Card, 2, true),
                new Card("No img!", cardDescriptionWith1Card, 2, true),
                new Card("No img!", cardDescriptionWith2Card, 3, true),
                new Card("No img!", cardDescriptionWith2Card, 3, true),
                new Card("No img!", cardDescriptionWith2Dmg, 1, false),
                new Card("No img!", cardDescriptionWith2Dmg, 1, false)
        };

        heroCards.addAll(Arrays.asList(cards));
    }

    public boolean move() {
        try {
            System.out.println("The game has started! glhv <3");

            System.out.println(opponent);
            System.out.println("|\t\t\t\t\t|");
            System.out.println(hero);

            Scanner sc = new Scanner(System.in);
            String input = "";

            while (!input.equalsIgnoreCase("Exit")) {


                while (hero.canPlayAnyCard()) {
                    System.out.println("----------Ваш ход! ----------");

                    List<Card> handCards = hero.getHand();
                    for (Card card : handCards) {
                        System.out.print(card + "  \t");
                        System.out.println();
                    }

                    input = sc.next();

                    if(input.equalsIgnoreCase("end")) {
                        break;
                    }

                    int numberOfCard = Integer.parseInt(input) - 1;
                    Card selectedCard = hero.getHand().get(numberOfCard);

                    hero.setSelectedCard(selectedCard);
                    hero.playCard(opponent);

                    System.out.println(opponent);
                    System.out.println("|\t\t\t\t\t|");
                    System.out.println(hero);

                    if(selectedCard.getCardDescription().getEffect() == Effect.BONUS_CARD){
                        continue;
                    }

                    if (isHeroWon()) {
                        return true;
                    }
                }

                while (opponent.canPlayAnyCard()) {
                    System.out.println("----------Ходит соперник! ----------");

                    opponent.playCard(hero);

                    System.out.println(opponent);
                    System.out.println("|\t\t\t\t\t|");
                    System.out.println(hero);

                    if (isOpponentWon()) {
                        return true;
                    }
                }

                System.out.println("----------Конец хода +1 карта! ----------");

                hero.setStamina(hero.getMaxStamina());
                opponent.setStamina(opponent.getMaxStamina());

                if (hero.getDeck().size() > 0) {
                    hero.takeCard();
                }

                if (opponent.getDeck().size() > 0) {
                    opponent.takeCard();
                }
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean isOpponentWon(){
        if (hero.getHp() <= 0) {
            System.out.println("Opponent won!");
            return true;
        }
        return false;
    }

    private boolean isHeroWon(){
        if (opponent.getHp() <= 0) {
            System.out.println("You won!");
            return true;
        }
        return false;
    }
}
