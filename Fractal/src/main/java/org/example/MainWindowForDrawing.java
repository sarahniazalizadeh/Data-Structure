package org.example;

import java.awt.*;
import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainWindowForDrawing extends JFrame {
    public static void main(String[] args) {
        MainWindowForDrawing mainWindowForDrawing = new MainWindowForDrawing();
    }

    public MainWindowForDrawing() {
        setLayout(new BorderLayout());
        setSize(500, 375);
        setTitle("First window to draw in");
        DrawingArea drawingArea = new DrawingArea();
        add("Center", drawingArea);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class DrawingArea extends Canvas {
        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(200, 30, 10));
            g.drawLine(50,50,getWidth()-50, 50);
            drawFractal(g, 50, getWidth()-50, 100);
        }
    }

    private void drawFractal(Graphics g, int fromX, int toX, int levelY) {

        int spanX = toX - fromX;
        if (spanX <= 1) {
            return;
        }
        int oneThirdX = fromX + spanX / 3;
        int twoThirdX = fromX + spanX * 2 / 3;
        g.drawLine(fromX, levelY, oneThirdX, levelY);
        g.drawLine(twoThirdX, levelY, toX, levelY);
        drawFractal(g, fromX, oneThirdX, levelY + 50);
        drawFractal(g, twoThirdX, toX, levelY + 50);
    }
}