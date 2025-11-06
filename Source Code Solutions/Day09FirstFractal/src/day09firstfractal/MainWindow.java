package day09firstfractal;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;

public class MainWindow extends JFrame {

    public static void main(String[] args) {
        MainWindow mainWindowForDrawing = new MainWindow();
    }

    public MainWindow() {
        setLayout(new BorderLayout());
        setSize(500, 375);
        setTitle("First window to draw in");
        DrawingArea drawingArea = new DrawingArea();
        add("Center", drawingArea);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen
        setVisible(true);
    }

    private class DrawingArea extends Canvas {

        @Override
        public void paint(Graphics g) {
            int stepY = 25;
            // g.drawLine(10, 10, getWidth()-10, getHeight()-10);
            g.drawLine(10, 10, getWidth() - 10, 10);
            drawFractal(g, 10, getWidth() - 10, 10 + stepY, stepY);
        }
    }

    private void drawFractal(Graphics g, int fromX, int toX, int levelY, int stepY) {
        // recursion must end
        int width = toX - fromX;
        if (width < 3) {
            return;
        }
        // draw left and right 1/3rd of the width
        g.setColor(randomColor());
        g.drawLine(fromX, levelY, fromX + width/3, levelY);
        g.drawLine(toX - width / 3, levelY, toX, levelY);
        // recursively call for left and right 1/3rd
        drawFractal(g, fromX, fromX + width/3, levelY+stepY, stepY);
        drawFractal(g, toX - width / 3, toX, levelY+stepY, stepY);
    }

    private Color randomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }
}
