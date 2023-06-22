package com.Objects;

import java.util.List;

public class Hero extends Player {

    private Card selectedCard;

    public Hero(String name, String img, int stamina, int maxStamina, int hp, List<Card> handCards, List<Card> deckCards) {
        super(name, img, stamina, maxStamina, hp, handCards, deckCards);
    }

    public boolean playCard(Player player){
        if(selectedCard != null) {
            getHand().remove(selectedCard);
            setStamina(getStamina() - selectedCard.getStaminaCost());

            Card card = selectedCard;
            card.release(this, player);

            selectedCard = null;

            return true;
        }
        return false;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }
}
