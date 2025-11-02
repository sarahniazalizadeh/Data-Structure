package org.example;

import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame {
    public static void main(String[] args) {
        Main mainWindowForDrawing = new Main();
    }

    public Main() {
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
            drawFractal(g, 50, 50, getWidth() - 50, getHeight() - 50);
        }
    }

    private void drawFractal(Graphics g, int x1, int y1, int x2, int y2) {

        int spanX = x2 - x1;
        if (spanX < 4) {
            return;
        }
        g.drawLine(x1, y1, x2, y1);
        g.drawLine(x2, y1, x2, y2);
        g.drawLine(x2, y2, x1, y2);
        g.drawLine(x1, y2, x1, y1);
        int twoFifthX12 = x1 + (x2 - x1) * 2 / 5;
        int threeFifthX12 = x1 + (x2 - x1) * 3 / 5;
        int twoFifthY12 = y1 + (y2 - y1) * 2 / 5;
        int threeFifthY12 = y1 + (y2 - y1) * 3 / 5;

        drawFractal(g, x1, y1, twoFifthX12, twoFifthY12);
        drawFractal(g, threeFifthX12, y1, x2, twoFifthY12);
        drawFractal(g, threeFifthX12, threeFifthY12, x2, y2);
        drawFractal(g, x1, threeFifthY12, twoFifthX12, y2);
    }
}