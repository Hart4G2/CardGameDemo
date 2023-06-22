package com.badlogic.demo.cardGame.visual_objects;

import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class CardDropZone extends Group {

    private DragAndDrop dragAndDrop;

    private final Rectangle dropZone = new Rectangle(Gdx.graphics.getWidth() / 6f,
            Gdx.graphics.getHeight() / 4f,
            Gdx.graphics.getWidth() * 2f / 3f,
            Gdx.graphics.getHeight() / 2f);

    public CardDropZone(Stage stage) {
        dragAndDrop = new DragAndDrop();

        setZIndex(1);

        Image texture = new Image(new Texture(Gdx.files.internal("cardgame/cardbg.png")));
        texture.setBounds(dropZone.x, dropZone.y, dropZone.width, dropZone.height);

        stage.addActor(texture);

        // Add the drop target
        Target target = new Target(this) {
            @Override
            public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
                // Check if the payload object is an actor and if it's not already in this group
                if (payload.getObject() instanceof Actor && !getChildren().contains((Actor) payload.getObject(), true)) {
                    Actor draggedActor = (Actor) payload.getObject();

                    // If the drop zone contains the mouse coordinates, then snap the actor to the drop zone
                    if (dropZone.contains(x, y)) {
                        draggedActor.setPosition(dropZone.x + dropZone.width / 2f - draggedActor.getWidth() / 2f,
                                dropZone.y + dropZone.height / 2f - draggedActor.getHeight() / 2f);
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void drop(Source source, Payload payload, float x, float y, int pointer) {
                Actor droppedActor = (Actor) payload.getObject();

                // Add the dropped actor to this group
                addActor(droppedActor);
            }
        };
        dragAndDrop.addTarget(target);
    }

    public boolean isInDropZone(float x, float y) {
        return dropZone.contains(x, y);
    }

    public void addCard(Actor actor) {
        addActor(actor);
    }
}
