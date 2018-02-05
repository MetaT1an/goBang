package gobang.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * to define the main part of the game--chessboard
 * with several variables to determine the properties of the board
 * with a single function to draw the raw plain board
 */

public class ChessBoard {
    private Canvas board;
    private GraphicsContext gc;

    public static final int N = 15;
    public static final int MARGIN = 40;
    public static final int WEIGHT = 36;
    public static final int SCALE = 2 * MARGIN + N * WEIGHT;

    private static ChessBoard chessBoard = null;

    public static ChessBoard getChessBoard(){
        if(chessBoard == null)
            chessBoard = new ChessBoard();
        return chessBoard;
    }

    private ChessBoard(){
        board = new Canvas(SCALE,SCALE);
        gc = board.getGraphicsContext2D();
    }

    public Canvas getBoard() {
        drawBoard();
        return board;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void drawBoard(){
        gc.clearRect(0,0,SCALE,SCALE);
        gc.setLineWidth(2);
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,SCALE,SCALE);
        gc.setFill(Color.BLACK);
        for(int i = 0;i<N;i++){
            gc.strokeLine(MARGIN, MARGIN + WEIGHT * i, MARGIN + WEIGHT * (N-1), MARGIN + WEIGHT * i);
            gc.strokeLine(MARGIN + WEIGHT * i, MARGIN, MARGIN + WEIGHT * i, MARGIN + WEIGHT * (N-1));
            gc.fillOval(MARGIN + WEIGHT * (N / 2) - 6, MARGIN + WEIGHT * (N / 2) - 6, 12, 12);
            gc.fillOval(MARGIN + WEIGHT * (N / 2 - 4) - 6, MARGIN + WEIGHT * (N / 2 - 4) - 6, 12, 12);
            gc.fillOval(MARGIN + WEIGHT * (N / 2 - 4) - 6, MARGIN + WEIGHT * (N / 2 + 4) - 6, 12, 12);
            gc.fillOval(MARGIN + WEIGHT * (N / 2 + 4) - 6, MARGIN + WEIGHT * (N / 2 - 4) - 6, 12, 12);
            gc.fillOval(MARGIN + WEIGHT * (N / 2 + 4) - 6, MARGIN + WEIGHT * (N / 2 + 4) - 6, 12, 12);
        }
    }
}
