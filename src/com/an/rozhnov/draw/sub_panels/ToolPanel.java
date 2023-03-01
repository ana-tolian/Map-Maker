package com.an.rozhnov.draw.sub_panels;

import com.an.rozhnov.entity.TileBrush;
import com.an.rozhnov.ui_elements.BrushPaletteButton;
import com.an.rozhnov.draw.MapBuildingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class ToolPanel extends JPanel implements ActionListener {

    private final BrushPaletteButton[] buttons;
    private final HashMap<String, BufferedImage> tilesSpriteMap;
    private final HashMap<String, Boolean> overlaySpriteMap;

    public ToolPanel() {
        setLayout(new FlowLayout());
        setBackground(Color.GRAY);

        setFocusable(false);

        tilesSpriteMap = MapBuildingPanel.loader.getSpriteSet();
        overlaySpriteMap = MapBuildingPanel.loader.getOverlaySprites();

        ArrayList<String> temp = new ArrayList<>();
        for (String name : tilesSpriteMap.keySet())
            if (name.contains("0"))
                temp.add(name);

        buttons = new BrushPaletteButton[temp.size()];

        String [] names = temp.toArray(new String[0]);
        Arrays.sort(names);

        for (int i = 0; i < names.length; i++) {
            buttons[i] = new BrushPaletteButton(tilesSpriteMap.get(names[i]), names[i]);
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BrushPaletteButton button = (BrushPaletteButton) e.getSource();

        for (BrushPaletteButton brushPaletteButton : buttons) {
            brushPaletteButton.pullButton();
        }

        button.pushButton();
        String spriteName = button.getActionCommand();
        MapBuildingPanel.brush = new TileBrush(tilesSpriteMap.get(spriteName), spriteName, overlaySpriteMap.get(spriteName));
    }
}
