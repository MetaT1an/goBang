package gobang.control;

import gobang.model.ChessPiece;
import gobang.model.ChessPiecesList;
import gobang.model.GameModel;
import gobang.ui.ChessBoard;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

/**
 * add events relating to mouseclicked,
 */
public class ChessBoardControl {

    private static ChessBoard chessBoard = ChessBoard.getChessBoard();
    private static ChessPiecesList cplist = ChessPiecesList.getPieceList();
    private static GameModel gameModel = GameModel.getGameModel();

    private ChessBoardControl(){}

    public static void addPieceEvent(){
        chessBoard.getBoard().setOnMouseClicked(event -> {
            /*
            if no side won the game
            get its position in the GameModel
            choose the point that is close to where the player clicked
            if one side has won the game
            do nothing, to "freeze" the chessboard
             */
            if(!gameModel.getStatus()){
                double tempX = event.getX() - ChessBoard.MARGIN;
                int n = (int)tempX / ChessBoard.WEIGHT;
                int x = (tempX > (n + 0.5) * ChessBoard.WEIGHT) ? n+1 : n ;

                double tempY = event.getY() - ChessBoard.MARGIN;
                n = (int)tempY / ChessBoard.WEIGHT;
                int y = (tempY > (n + 0.5) * ChessBoard.WEIGHT) ? n+1 : n ;

                //generate the chessPiece after clicked
                ChessPiece p = generatePiece(x,y);

                //if this piece is valid in the gameModel,add it into the board
                if(gameModel.addChess(x,y)){
                    cplist.addPiece(p);
                    drawPiece(p);
                    //change the color of the light
                    ConsoleControl.changeIndicationEvent();
                }
                //after dropping a piece, update the game status
                gameModel.setStatus(x,y);

                if(gameModel.getStatus())
                    showWinInfo();
            }
        });
    }

    private static ChessPiece generatePiece(int x, int y){
        ChessPiece p = new ChessPiece(x,y);
        if(gameModel.isBlack())
            p.setColor(Color.BLACK);
        else
            p.setColor(Color.WHITE);
        return p;
    }

    private static void drawPiece(ChessPiece p){
        chessBoard.getGc().setFill(p.getColor());
        double x = p.getX() * ChessBoard.WEIGHT + ChessBoard.MARGIN - ChessPiece.D / 2;
        double y = p.getY() * ChessBoard.WEIGHT + ChessBoard.MARGIN - ChessPiece.D / 2;
        chessBoard.getGc().fillOval(x,y,ChessPiece.D,ChessPiece.D);
    }

    public static void drawPieceAccordingIndex(int index,ChessPiecesList list){
        chessBoard.drawBoard();
        for(int i = 0;i<=index;++i){

            ChessPiece p = list.getPiece(i);
            chessBoard.getGc().setFill(p.getColor());

            //the picture's properties are determined by the chessPiece in the list
            int x = p.getX() * ChessBoard.WEIGHT + ChessBoard.MARGIN -ChessPiece.D / 2;
            int y = p.getY() * ChessBoard.WEIGHT + ChessBoard.MARGIN -ChessPiece.D / 2;
            chessBoard.getGc().fillOval(x, y, ChessPiece.D, ChessPiece.D);
        }
    }

    private static void showWinInfo(){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information");
        a.setHeaderText("Game Over!");
        String info = (gameModel.isBlack() ? "White " : "Black ") + "side win!!";
        a.setContentText(info);
        a.showAndWait();
    }
}
