package com.badlogic.demo.cardGame.Objects;

import java.util.List;

public class Hero extends Player {

    private Card selectedCard;

    public Hero(String name, String img, int stamina, int maxStamina, int hp, List<Card> handCards, List<Card> deckCards) {
        super(name, img, stamina, maxStamina, hp, handCards, deckCards);
    }

    public boolean playCard(Player player){
        try{
            if(selectedCard != null) {
                getHand().remove(selectedCard);
                setStamina(getStamina() - selectedCard.getStaminaCost());

                Card card = selectedCard;
                card.release(this, player);

                selectedCard = null;

                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }
}
