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
            drawFractal(g, getWidth()/2, 50, 50, getHeight()-50, getWidth()-50, getHeight()-50);
        }
    }

    private void drawFractal(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {

        int spanX = x2-x1;
        if (Math.abs(spanX) < 3) {
            return;
        }
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x3, y3, x1, y1);
        int middleX12 = (x1 + x2) / 2;
        int middleY12 = (y1 + y2) / 2;
        int middleX23 = (x2 + x3) / 2;
        int middleY23 = (y2 + y3) / 2;
        int middleX13 = (x1 + x3) / 2;
        int middleY13 = (y1 + y3) / 2;
        drawFractal(g, x1, y1, middleX12, middleY12, middleX13, middleY13);
        drawFractal(g, x2, y2, middleX12, middleY12, middleX23, middleY23);
        drawFractal(g, x3, y3, middleX13, middleY13, middleX23, middleY23);
    }
}