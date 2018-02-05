package gobang.model;

import javafx.scene.paint.Color;

/**
 * this class is to define each chessPiece in the game
 * containing its position(x,y),color,radius
 */

public class ChessPiece {
    private int x;
    private int y;
    private Color color;

    public static int D = 28;

    public ChessPiece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}
