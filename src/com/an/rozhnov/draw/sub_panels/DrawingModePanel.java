package com.an.rozhnov.draw.sub_panels;

import com.an.rozhnov.file.load.SpriteLoader;
import com.an.rozhnov.ui_elements.SelectStatusButton;
import com.an.rozhnov.utils.Constants;
import com.an.rozhnov.draw.MapBuildingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingModePanel extends JPanel implements ActionListener {

    private final SelectStatusButton[] buttons = {
            new SelectStatusButton(new SpriteLoader(Constants.PNG_PATH).getSpriteSet().get("brush")),
            new SelectStatusButton(new SpriteLoader(Constants.PNG_PATH).getSpriteSet().get("cursor")),
            new SelectStatusButton(new SpriteLoader(Constants.PNG_PATH).getSpriteSet().get("eraser"))
    };

    public DrawingModePanel () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.GRAY);
        setFocusable(false);

        buttons[1].pushButton();

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setActionCommand(i + "");
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SelectStatusButton b = (SelectStatusButton) e.getSource();

        if (b.getActionCommand().equals("0")) {
            pushButton(0);

            MapBuildingPanel.brushMode = true;
            MapBuildingPanel.dragMode = false;
            MapBuildingPanel.eraseMode = false;
        }

        if (b.getActionCommand().equals("1")) {
            pushButton(1);

            MapBuildingPanel.brushMode = false;
            MapBuildingPanel.dragMode = true;
            MapBuildingPanel.eraseMode = false;
        }

        if (b.getActionCommand().equals("2")) {
            pushButton(2);

            MapBuildingPanel.brushMode = false;
            MapBuildingPanel.dragMode = false;
            MapBuildingPanel.eraseMode = true;
        }
    }

    public void pushButton (int j) {
        for (int i = 0; i < buttons.length; i++)
            buttons[i].pullButton();
        buttons[j].pushButton();
    }

}
