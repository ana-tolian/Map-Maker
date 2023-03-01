package com.an.rozhnov.ui_elements;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {

    public MenuButton(String str) {
        super(str);
        setFocusable(false);
        setPreferredSize(new Dimension(200, 80));
        setAlignmentX(SwingConstants.CENTER);
        setAlignmentY(SwingConstants.CENTER);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        setFont(new Font ("Arial", Font.BOLD, 20));
    }
}
