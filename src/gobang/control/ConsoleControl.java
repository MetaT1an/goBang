package gobang.control;

import gobang.model.ChessPiece;
import gobang.model.ChessPiecesList;
import gobang.model.GameModel;
import gobang.ui.ChessBoard;
import gobang.ui.Console;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;

import java.util.Optional;

/**
 * add events relating to console(button, indication)
 */

public class ConsoleControl {
    private static GameModel gameModel = GameModel.getGameModel();
    private static Console console = Console.getConsole();
    private static ChessPiecesList chessPiecesList = ChessPiecesList.getPieceList();
    private static ChessBoard chessBoard = ChessBoard.getChessBoard();

    private ConsoleControl(){}

    public static void changeIndicationEvent(){
        if(gameModel.isBlack()){
            console.getLight(0).setFill(Color.rgb(0, 230, 0, 1));
            console.getLight(1).setFill(Color.rgb(0, 230, 0, 0));
        } else {
            console.getLight(0).setFill(Color.rgb(0, 230, 0, 0));
            console.getLight(1).setFill(Color.rgb(0, 230, 0, 1));
        }
    }

    public static void addChoiceEvent(){
        restart();
        quit();
        playBack();
        rePent();
        stepFunction();
        playMode(true);
    }

    private static void restart(){
        console.getChoice(0).setOnAction(event -> {
            ChessBoardControl.addPieceEvent();
            playMode(true);

            gameModel.reStart();
            chessPiecesList.reStart();
            chessBoard.drawBoard();
            changeIndicationEvent();
        });
    }

    private static void playBack(){
        console.getChoice(3).setOnAction(event -> {
            //repaint the Board
            chessBoard.drawBoard();
            playMode(false);

            //make sure in this situation,the board is read-only
            //add chessPieces into it is forbidden
            chessBoard.getBoard().setOnMouseClicked(event1 -> {});
        });
    }

    //make sure the relevant buttons is working
    private static void playMode(boolean choice){
        for(int i = 0;i<2;i++)
            console.getStep(i).setDisable(choice);
        console.getChoice(2).setDisable(!choice);
    }
    //enable the single-step mode
    private static void stepFunction(){
        console.getStep(0).setOnAction(event ->{
            chessPiecesList.subPoint();
            ChessBoardControl.drawPieceAccordingIndex(chessPiecesList.PointValue(),chessPiecesList);
        });
        console.getStep(1).setOnAction(event -> {
            chessPiecesList.addPoint();
            ChessBoardControl.drawPieceAccordingIndex(chessPiecesList.PointValue(),chessPiecesList);
        });
    }

    private static void rePent(){
        console.getChoice(2).setOnAction(event -> {
            //delete this piece from list,gameModel
            ChessPiece p = chessPiecesList.removePiece();
            if(p != null){
                gameModel.deleteChess(p.getX(),p.getY());

                //repaint the game
                changeIndicationEvent();
                ChessBoardControl.drawPieceAccordingIndex(chessPiecesList.getSize()-1, chessPiecesList);
            }
        });
    }

    private static void quit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");
        alert.setHeaderText("");
        alert.setContentText("Do you want to quit the game?");

        console.getChoice(1).setOnAction(event -> {
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                System.exit(0);
            }
        });
    }
}