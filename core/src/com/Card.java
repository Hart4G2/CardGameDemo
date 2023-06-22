package com;

import java.util.Comparator;

public class Card{

    private String img;
    private CardDescription cardDescription;
    private int staminaCost;
    private boolean specialCard;

    public Card(String img, CardDescription cardDescription, int staminaCost, boolean specialCard) {
        this.img = img;
        this.cardDescription = cardDescription;
        this.staminaCost = staminaCost;
        this.specialCard = specialCard;
    }

    public void release(Player player, Player affected){
        if(!this.getCardDescription().getProperties().isEmpty()) {
            switch (this.getCardDescription().getEffect()) {
                case ARMOR -> {
                    player.setHp(player.getHp() + this.getCardDescription().getProperties().get(0));
                }
                case DAMAGE -> {
                    affected.setHp(affected.getHp() - this.getCardDescription().getProperties().get(0));
                }
                case DAMAGE_ARMOR -> {
                    affected.setHp(affected.getHp() - this.getCardDescription().getProperties().get(0));
                    player.setHp(player.getHp() + this.getCardDescription().getProperties().get(1));
                }
                case BONUS_STAMINA -> {
                    int newStamina = player.getStamina() + this.getCardDescription().getProperties().get(0);

                    if(newStamina > player.getMaxStamina()){
                        player.setStamina(player.getMaxStamina());
                    } else {
                        player.setStamina(player.getStamina() + this.getCardDescription().getProperties().get(0));
                    }
                }
                case BONUS_CARD -> {
                    int i = 0;
                    while(i < this.getCardDescription().getProperties().get(0)) {
                        if (player.getDeck().size() > 0) {
                            player.takeCard();
                        }
                        i++;
                    }
                }
                default -> {
                    //TODO придумать как сделать карту, которая добавит +2 к урону
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Properties of card description is empty.");
        }
    }

    public String getImg() {
        return img;
    }

    public CardDescription getCardDescription() {
        return cardDescription;
    }

    public int getStaminaCost() {
        return staminaCost;
    }

    public boolean isSpecialCard() {
        return specialCard;
    }

    @Override
    public String toString() {
        return cardDescription +
                ", Стоимость = " + staminaCost ;
    }
}

class SortByStaminaCost implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return o1.getStaminaCost() - o2.getStaminaCost();
    }
}
