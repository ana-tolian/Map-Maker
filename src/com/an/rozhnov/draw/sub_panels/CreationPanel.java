package com.an.rozhnov.draw.sub_panels;

import com.an.rozhnov.entity.TileBrush;
import com.an.rozhnov.utils.Viewport;
import com.an.rozhnov.entity.Map;
import com.an.rozhnov.file.load.MapGetter;
import com.an.rozhnov.utils.Constants;
import com.an.rozhnov.draw.MapBuildingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import static com.an.rozhnov.draw.MapBuildingPanel.map;

public class CreationPanel extends JPanel {

    private BufferedImage field;
    private MapGetter getter;
    public Viewport viewport;


    public CreationPanel (int width, int height) {
        map = new Map (width, height);

        init();
    }

    public CreationPanel (String levelPath) {
        try {
            getter = new MapGetter (levelPath, MapBuildingPanel.loader);
        } catch (FileNotFoundException e) {e.printStackTrace();}

        map = getter.getMap();

        init();
    }

    private void init () {
        field = new BufferedImage (map.getWidth() * Constants.SPR_WIDTH,
                             map.getHeight() * Constants.SPR_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        viewport = new Viewport();

        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.requestFocus();

        this.addMouseListener(new MouseL());
        this.addMouseMotionListener(new MouseL());
        this.addMouseWheelListener(new MouseL());
    }

    protected void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2;
        if (map.isMapChanged()) {
            g2 = field.createGraphics();
            map.draw(g2);
            map.fixateChanges();
        }

        g2 = (Graphics2D) g;
        viewport.draw(g2, field);
    }

    private class MouseL extends MouseAdapter implements MouseWheelListener {

        @Override
        public void mousePressed (MouseEvent e) {
            if (MapBuildingPanel.brushMode || MapBuildingPanel.eraseMode) {
                setTile(viewport.getCoordReal(e.getX(), e.getY()));

            } else if (MapBuildingPanel.dragMode) {
                viewport.setMousePressed(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseMoved (MouseEvent e) {
            viewport.setMouseMoved(e.getX(), e.getY());
        }

        @Override
        public void mouseDragged (MouseEvent e) {
            if (MapBuildingPanel.brushMode || MapBuildingPanel.eraseMode) {
                setTile(viewport.getCoordReal(e.getX(), e.getY()));

            } else if (MapBuildingPanel.dragMode) {
                viewport.setMouseDragged(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseWheelMoved (MouseWheelEvent e) {
            if (-e.getWheelRotation() > 0) {
                viewport.setScale(1.0);
            } else if (-e.getWheelRotation() < 0) {
                viewport.setScale(-1.0);
            }

            viewport.setMouseScalingPoint();
        }

        private void setTile (Point p) {
            if (MapBuildingPanel.eraseMode)
                MapBuildingPanel.brush = new TileBrush(null, null, true);

            if (MapBuildingPanel.brush != null) {
                map.setTile(p.x, p.y);
            }
        }

    }
}
