package day09triangleeasier;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;

public class MainWindowTriangleEasier extends JFrame {

    public static void main(String[] args) {
        MainWindowTriangleEasier dd = new MainWindowTriangleEasier();
    }

    public MainWindowTriangleEasier() {
        setLayout(new BorderLayout());
        setSize(500, 375);
        setTitle("Triangle Easier Fractal");
        DrawingArea drawingArea = new DrawingArea();
        add("Center", drawingArea);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on the screen
        setVisible(true);
    }

    private class DrawingArea extends Canvas {

        @Override
        public void paint(Graphics g) {
            int x1 = 10, y1 = getHeight() - 10;
            int x2 = getWidth() - 10, y2 = getHeight() - 10;
            int x3 = getWidth() / 2, y3 = 10;
            drawTriangleFractal(g, x1, y1, x2, y2, x3, y3, 0);
        }
    }

    private void drawTriangleFractal(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int level) {
        // recursion must end (base case)
        // if (Math.abs(x1 - x3) < 10) return;
        if (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) < 10) {
            return;
        }
        // draw the current triangle
        // g.setColor(randomColor());
        g.setColor(colorArray[level >= colorArray.length ? 0 : level]);
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x1, y1, x3, y3);
        // compute middle points of each edge
        int xA = (x1 + x2) / 2;
        int yA = (y1 + y2) / 2;
        int xB = (x3 + x2) / 2;
        int yB = (y3 + y2) / 2;
        int xC = (x1 + x3) / 2;
        int yC = (y1 + y3) / 2;
        // call to draw the next level
        drawTriangleFractal(g, xA, yA, xB, yB, xC, yC, level + 1);
    }

    private Color randomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    private Color[] colorArray = {
        new Color(255, 90, 90),
        new Color(0, 200, 0),
        new Color(90, 90, 255),
        new Color(22, 21, 61),
        new Color(21, 98, 69),
        new Color(217, 146, 54),
        new Color(10, 10, 84),
        new Color(63, 121, 186),
        new Color(131, 121, 11)
    };
}
