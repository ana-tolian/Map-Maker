package com.an.rozhnov.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Viewport {

    private int translationX;
    private int translationY;

    private int currentMouseX;
    private int currentMouseY;

    private int mouseDragX1;
    private int mouseDragY1;
    private int mouseDragX2;
    private int mouseDragY2;

    private int scalingPointX;
    private int scalingPointY;

    private double anchorX;
    private double anchorY;

    private double scale;


    public Viewport () {
        translationX = Constants.START_TRANSLATION_X;
        translationY = Constants.START_TRANSLATION_Y;

        currentMouseX = 0;
        currentMouseY = 0;

        mouseDragX1 = 0;
        mouseDragY1 = 0;
        mouseDragX2 = 0;
        mouseDragY2 = 0;

        scalingPointX = 0;
        scalingPointY = 0;

        anchorX = 0;
        anchorY = 0;

        scale = 1;

    }

    public void draw (Graphics g, BufferedImage img) {
        Graphics2D g2 = (Graphics2D) g;
        draw(g2, img);
    }

    public void draw (Graphics2D g2, BufferedImage img) {
        g2.drawImage(img,
                    (int) (anchorX),
                    (int) (anchorY),
                    (int) (img.getWidth() * scale),
                    (int) (img.getHeight() * scale), null);
    }

    public void setMouseMoved (int x, int y) {
        currentMouseX = x;
        currentMouseY = y;
    }

    public void setMousePressed (int x, int y) {
        mouseDragX1 = x;
        mouseDragY1 = y;
    }

    public void setMouseDragged (int x2, int y2) {
        mouseDragX2 = x2;
        mouseDragY2 = y2;

        recalculateTranslation();

        mouseDragX1 = x2;
        mouseDragY1 = y2;
    }

    public void setScale (double multiplicity) {
        scale += multiplicity * 0.1;

        if (scale < 0.1)
            scale = 0.1;
    }

    public void setMouseScalingPoint () {
        scalingPointX = currentMouseX;
        scalingPointY = currentMouseY;

        recalculateStartPoint();
    }

    public Point getCoordReal (int x, int y) {
        return new Point(getNewCoord(scale, anchorX, x, scalingPointX),
                getNewCoord(scale, anchorY, y, scalingPointY));
    }

    private void recalculateTranslation () {
        translationX += mouseDragX2 - mouseDragX1;
        translationY += mouseDragY2 - mouseDragY1;

        recalculateStartPoint();
    }

    private void recalculateStartPoint () {
        anchorX = getAnchor(scale, translationX, scalingPointX);
        anchorY = getAnchor(scale, translationY, scalingPointY);
    }

    public static int getAnchor (double scale, int translation, int coord) {
        return (int) (coord + (translation - coord) * scale);
    }

    public static int getAnchor (double scale, double translation, double coord) {
        return (int) (coord + (translation - coord) * scale);
    }

    public static int getNewCoord (double scale, double anchor, int coord, int scaleCoord) {
        return (int) (coord / scale - anchor / scale);
    }

}
