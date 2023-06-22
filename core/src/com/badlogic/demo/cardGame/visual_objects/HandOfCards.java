package com.badlogic.demo.cardGame.visual_objects;

import com.badlogic.demo.cardGame.Objects.Card;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandOfCards extends Group {
    private List<Card> cards;
    private final boolean isHero;

    public HandOfCards(List<Card> cards, boolean isHero, Stage stage) {
        this.cards = new ArrayList<>(cards);
        this.isHero = isHero;
        sortCardsByStaminaCost();
        layoutCards(stage);
    }

    private void layoutCards(Stage stage) {
        float centerX = Gdx.graphics.getWidth() / 2f;
        float CARD_WIDTH = Gdx.graphics.getWidth() / 20f;
        float previousCardX = centerX - (CARD_WIDTH * cards.size() + CARD_WIDTH / 2f);
        float previousCardY = isHero ? 10 : Gdx.graphics.getHeight() - 10;
        float DISTANCE_BETWEEN_CARDS = CARD_WIDTH / 3;

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);

            if(!isHero) {
                card.getCardImage().setScale(0.6f, -0.6f);
            }

            float x = previousCardX + CARD_WIDTH + DISTANCE_BETWEEN_CARDS;

            card.getCardImage().addAction(Actions.moveTo(x, previousCardY, 0.5f, Interpolation.bounce));

            stage.addActor(card.getCardImage());

            previousCardX = x;
        }

        stage.addActor(this);
    }

    public void addCard(Card card){
        cards.add(card);
        sortCardsByStaminaCost();
        layoutCards(this.getStage());
    }

    public void removeCard(CardImage cardImage){
        Card cardToRemove = null;

        for(Card card : cards){
            if(card.getCardImage().equals(cardImage)){
                cardToRemove = card;
            }
        }

        if(cardToRemove != null){
            cards.remove(cardToRemove);
        }
    }

    private void sortCardsByStaminaCost() {
        Collections.sort(cards, new Card.SortByStaminaCost());
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isHero() {
        return isHero;
    }
}
