package com.an.rozhnov.ui_elements;

import com.an.rozhnov.utils.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BrushPaletteButton extends SelectStatusButton {

    private BufferedImage img;

    public BrushPaletteButton(BufferedImage img, String actionCommand) {
        super(img);
        this.img = img;

        setActionCommand(actionCommand);
        setPreferredSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        setMinimumSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
        setMaximumSize(new Dimension(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT));
    }

    public BufferedImage getImg() {
        return img;
    }
}
