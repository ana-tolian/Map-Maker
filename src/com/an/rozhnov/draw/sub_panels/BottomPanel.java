package com.an.rozhnov.draw.sub_panels;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {

    public static JLabel coords;

    public BottomPanel () {
        coords = new JLabel("");
        coords.setBackground(Color.GRAY);
        coords.setForeground(Color.WHITE);
        add(coords);
        setBackground(Color.GRAY);
    }
}
