package com.badlogic.demo.cardGame.Objects;

import java.util.*;

public class Opponent extends Player {

    private List<Card> ableCards = new ArrayList<>();

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
        try{
            setAbleCards();
            Card cardToPlay = calculateBestCard(player);
            if(cardToPlay != null) {
                getHand().remove(cardToPlay);
                setStamina(getStamina() - cardToPlay.getStaminaCost());

                Card card = cardToPlay;
                card.release(this, player);

                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void setAbleCards(){
        List<Card> handCards = getHand();

        for (Card c : handCards) {
            if(this.getStamina() >= c.getStaminaCost()){
                ableCards.add(c);
            }
        }
    }

    private Card calculateBestCard(Player player){
        try{
            List<Card> cards = new ArrayList<>();

            for (Card c : ableCards) {
                if((c.getCardDescription().getEffect().equals(Effect.DAMAGE) ||
                        c.getCardDescription().getEffect().equals(Effect.DAMAGE_ARMOR)) &&
                        c.getCardDescription().getProperties().get(0) >= player.getHp())
                    cards.add(c);
            }

            if(cards.size() > 0){
                List<Card> sortedCards = new ArrayList<>(cards);
                Collections.sort(sortedCards, new Card.SortByStaminaCost());
                return sortedCards.get(0);
            } else {
                return calculateProfitableCard(player);
            }

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Card calculateProfitableCard(Player player){
        try{
            if(this.getHp() < 15) {
                List<Card> cards = new ArrayList<>();

                for (Card c : ableCards) {
                    if((c.getCardDescription().getEffect().equals(Effect.DAMAGE_ARMOR) &&
                            c.getCardDescription().getProperties().get(1) >= player.getHp()) ||
                            (c.getCardDescription().getEffect().equals(Effect.ARMOR)) &&
                                    c.getCardDescription().getProperties().get(0) >= player.getHp())
                        cards.add(c);
                }

                if (cards.size() > 0) {
                    Collections.sort(cards, new Card.SortByStaminaCost());
                    return cards.get(0);
                }
            }
            return getTheMostExpensiveCard();

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Card getTheMostExpensiveCard(){
        try{
            if(ableCards.size() > 0){
                List<Card> sortedCards = new ArrayList<>(ableCards);
                Collections.sort(sortedCards, new Card.SortByStaminaCost());
                return sortedCards.get(0);
            } else {
                return null;
            }

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
