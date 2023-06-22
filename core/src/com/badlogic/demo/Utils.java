package com.badlogic.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Utils {

    public static Pixmap textureToPixmap(Texture texture, int scale, boolean rounded, boolean padding){

        Pixmap pixmap = textureToPixmap(texture, scale, padding);

        return rounded ? createRoundedPixmap(pixmap, 10) : pixmap;
    }

    public static Pixmap createRoundedPixmap(Pixmap pixmap, int cornerRadius) {
        int width = pixmap.getWidth();
        int height = pixmap.getHeight();
        Pixmap rounded = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        // Set blending to overwrite alpha values.
        rounded.setBlending(Pixmap.Blending.None);

        // Copy the original pixmap onto the rounded pixmap.
        rounded.drawPixmap(pixmap, 0, 0);

        // Set the alpha values of the corners to 0.
        for (int x = 0; x < cornerRadius; x++) {
            for (int y = 0; y < cornerRadius; y++) {
                if (x * x + y * y <= cornerRadius * cornerRadius) {
                    rounded.drawPixel(x, y, 0);
                    rounded.drawPixel(width - 1 - x, y, 0);
                    rounded.drawPixel(x, height - 1 - y, 0);
                    rounded.drawPixel(width - 1 - x, height - 1 - y, 0);
                }
            }
        }

        // Set the blending back to the default mode.
        rounded.setBlending(Pixmap.Blending.SourceOver);

        return rounded;
    }

    private static Pixmap textureToPixmap(Texture texture, int scale, boolean padding){

        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }

        int size = 15 * scale;
        int indent = padding ? scale / 2 : 0;

        Pixmap pixmap = new Pixmap(size - indent * 2, size - indent * 2, texture.getTextureData().getFormat());

        pixmap.drawPixmap(texture.getTextureData().consumePixmap(),
                0,
                0,
                texture.getWidth(),
                texture.getHeight(),
                0,
                0,
                size - indent * 2,
                size - indent * 2);

        texture.dispose();

        return pixmap;
    }
}
