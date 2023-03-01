package com.an.rozhnov.ui_elements;

import com.an.rozhnov.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectStatusButton extends JButton {

    protected boolean pushed = false;

    public SelectStatusButton(BufferedImage img) {
        setIcon(new ImageIcon(img));
        setFocusable(false);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Constants.LEFT_MENU_BUTTON_WIDTH, Constants.LEFT_MENU_BUTTON_HEIGHT));
        setMinimumSize(new Dimension(Constants.LEFT_MENU_BUTTON_WIDTH, Constants.LEFT_MENU_BUTTON_HEIGHT));
        setMaximumSize(new Dimension(Constants.LEFT_MENU_BUTTON_WIDTH, Constants.LEFT_MENU_BUTTON_HEIGHT));
    }

    public SelectStatusButton(String str) {
        super(str);
        setFocusable(false);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Constants.LEFT_MENU_BUTTON_WIDTH, Constants.LEFT_MENU_BUTTON_HEIGHT));
        setMinimumSize(new Dimension(Constants.LEFT_MENU_BUTTON_WIDTH, Constants.LEFT_MENU_BUTTON_HEIGHT));
        setMaximumSize(new Dimension(Constants.LEFT_MENU_BUTTON_WIDTH, Constants.LEFT_MENU_BUTTON_HEIGHT));
    }

    public boolean isPushed() {
        return pushed;
    }

    public void pushButton() {
        this.pushed = true;
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
    }

    public void pullButton() {
        this.pushed = false;
        this.setBorder(null);
    }
}
