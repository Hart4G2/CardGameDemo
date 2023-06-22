package com.badlogic.demo.cardGame.Objects;

import com.badlogic.demo.cardGame.visual_objects.CardImage;
import com.badlogic.demo.cardGame.visual_objects.HeroCardImage;
import com.badlogic.demo.cardGame.visual_objects.OpponentCardImage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Comparator;

public class Card {
    private CardImage cardImage;
    private CardDescription cardDescription;
    private int staminaCost;
    private boolean specialCard;
    private boolean isHeroCard;

    public Card(Texture texture, CardDescription cardDescription, int staminaCost, boolean specialCard, boolean isHeroCard) {
        this.cardDescription = cardDescription;
        this.staminaCost = staminaCost;
        this.specialCard = specialCard;
        this.isHeroCard = isHeroCard;

        if(isHeroCard)
            cardImage = new HeroCardImage(texture);
        else
            cardImage = new OpponentCardImage(texture);

        cardImage.setScale(0.6f);
    }

    public void setFrontSide(){
        cardImage.setFrontSide();
    }

    public void setBackSide(){
        cardImage.setBackSide();
    }

    public void release(Player player, Player affected){
        if(!cardDescription.getProperties().isEmpty()) {
            switch (this.getCardDescription().getEffect()) {
                case ARMOR : {
                    player.setHp(player.getHp() + cardDescription.getProperties().get(0));
                    break;
                }
                case DAMAGE : {
                    affected.setHp(affected.getHp() - cardDescription.getProperties().get(0));
                    break;
                }
                case DAMAGE_ARMOR : {
                    affected.setHp(affected.getHp() - cardDescription.getProperties().get(0));
                    player.setHp(player.getHp() + cardDescription.getProperties().get(1));
                    break;
                }
                case BONUS_STAMINA : {
                    int newStamina = player.getStamina() + cardDescription.getProperties().get(0);

                    if(newStamina > player.getMaxStamina()){
                        player.setStamina(player.getMaxStamina());
                    } else {
                        player.setStamina(player.getStamina() + cardDescription.getProperties().get(0));
                    }
                    break;
                }
                default : {
                    //TODO придумать как сделать карту, которая добавит +2 к урону
                    break;
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Properties of card description is empty.");
        }

        if(cardImage.isOnBackSide()){
            setFrontSide();
        }

        //todo когда карта разыгрывается должна быть анимация и разворачивание, если она была развёрнута
    }

    public CardImage getCardImage() {
        return cardImage;
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

    public static class SortByStaminaCost implements Comparator<Card> {

        @Override
        public int compare(Card o1, Card o2) {
            return o1.getStaminaCost() - o2.getStaminaCost();
        }
    }
}