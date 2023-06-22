package com.Objects;

import java.util.*;

public class Opponent extends Player {

    public Opponent(String name,
                    String img,
                    int stamina,
                    int maxStamina,
                    int hp,
                    List<Card> handCards,
                    List<Card> deckCards) {
        super(name, img, stamina, maxStamina, hp, handCards, deckCards);
    }

    public boolean playCard(Player player){
        Card cardToPlay = calculateBestCardToPlay(player);
        if(cardToPlay != null) {
            getHand().remove(cardToPlay);
            setStamina(getStamina() - cardToPlay.getStaminaCost());

            cardToPlay.release(this, player);

            return true;
        }
        return false;
    }

    private Card calculateBestCardToPlay(Player player){
        List<Card> cards = getHand().stream().filter(
                c -> (c.getCardDescription().getEffect().equals(Effect.DAMAGE) ||
                        c.getCardDescription().getEffect().equals(Effect.DAMAGE_ARMOR)) &&
                        c.getStaminaCost() <= this.getStamina() &&
                        c.getCardDescription().getProperties().get(0) >= player.getHp()
        ).toList();

        if(cards.size() > 0){
            List<Card> sortedCards = new ArrayList<>(cards);
            sortedCards.sort(new SortByStaminaCost());
            return sortedCards.get(0);
        } else {
            return calculateProfitableCard(player);
        }

    }

    private Card calculateProfitableCard(Player player){
        if(this.getHp() < 15) {
            List<Card> cards = getHand().stream().filter(
                    c -> (c.getCardDescription().getEffect().equals(Effect.ARMOR) ||
                            c.getCardDescription().getEffect().equals(Effect.DAMAGE_ARMOR)) &&
                            c.getStaminaCost() <= this.getStamina()
            ).toList();

            if (cards.size() > 0) {
                List<Card> sortedCards = new ArrayList<>(cards);
                sortedCards.sort(new SortByStaminaCost());
                return sortedCards.get(0);
            }
        }
        return getTheMostCheapCard(player);
    }

    private Card getTheMostCheapCard(Player player){
        List<Card> cards = getHand().stream().filter(
                c -> (c.getStaminaCost() <= this.getStamina())
        ).toList();

        if(cards.size() > 0){
            List<Card> sortedCards = new ArrayList<>(cards);
            sortedCards.sort(new SortByStaminaCost());
            return sortedCards.get(0);
        } else {
            return null;
        }
    }
}
