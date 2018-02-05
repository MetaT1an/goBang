package gobang.ui;

import gobang.control.ChessBoardControl;
import gobang.control.ConsoleControl;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainFrame extends Application {
    private BorderPane frame = new BorderPane();
    private ChessBoard chessBoard = ChessBoard.getChessBoard();
    private Console console = Console.getConsole();

    public void start(Stage stage){
        GridPane consolePane = console.generateFrame();

        frame.setCenter(chessBoard.getBoard());
        frame.setRight(consolePane);

        ConsoleControl.addChoiceEvent();
        ChessBoardControl.addPieceEvent();

        BorderPane.setMargin(consolePane,new Insets(20,20,20,30));
        Scene scene = new Scene(frame);
        stage.setScene(scene);
        stage.setTitle("~Gobang game~");
        stage.show();
    }

}


