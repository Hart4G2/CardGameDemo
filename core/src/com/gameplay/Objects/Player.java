package com.gameplay.Objects;

import java.util.List;
import java.util.Random;

public abstract class Player {

    private String name;
    private String img;

    private int stamina;
    private int maxStamina;
    private int hp;

    private List<Card> hand;

    private List<Card> deck;

    public Player(String name, String img, int stamina, int maxStamina, int hp, List<Card> hand, List<Card> deck) {
        this.name = name;
        this.img = img;
        this.stamina = stamina;
        this.maxStamina = maxStamina;
        this.hp = hp;
        this.hand = hand;
        this.deck = deck;
    }

    public boolean takeCard(){

        Random random = new Random();

        Card newCard = deck.get(random.nextInt(deck.size()));

        this.getHand().add(newCard);

        this.getDeck().remove(newCard);

        return true;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    @Override
    public String toString() {
        return "Имя = '" + name + '\'' +
                ", стамина = " + stamina +
                ", hp = " + hp +
                ", кол-во карт = " + getHand().size();
    }

    public boolean canPlayAnyCard() {
        for(Card card : this.getHand()){
            if(card.getStaminaCost() <= this.getStamina()){
                return true;
            }
        }
        return false;
    }
}
