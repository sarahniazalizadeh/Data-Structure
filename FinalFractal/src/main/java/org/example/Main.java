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
        setTitle("First Fractal");
        DrawingArea drawingArea = new DrawingArea();
        add("Center", drawingArea);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class DrawingArea extends Canvas {

        @Override
        public void paint(Graphics g) {
            int initialLength = Math.min(getWidth(), getHeight()) / 4;
            int x1 = (getWidth() / 2) - (initialLength / 2);
            int y1 = (getHeight() / 2) - (initialLength / 2);
            drawFractal(g, x1, y1, initialLength);
        }

    }

    private void drawFractal(Graphics g, int x1, int y1, int length) {
        //base case
        if (length < 1) {
            return;
        }

        g.drawLine(x1, y1, x1+length, y1);
        g.drawLine(x1+length, y1, x1+length, y1+length);
        g.drawLine(x1+length, y1+length, x1, y1+length);
        g.drawLine(x1, y1+length, x1, y1);

        //reduce length for next level
        length = length/3;

        // call to draw the next level
        drawFractal(g, x1 + length, y1 - length, length);
        drawFractal(g, x1+3*length, y1+length, length);
        drawFractal(g, x1 + length, y1+3*length, length);
        drawFractal(g, x1-length, y1+length, length);
    }
}
