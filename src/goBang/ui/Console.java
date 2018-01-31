package goBang.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * to define the console part of the game
 * including status info(which side is going to drop chess)
 * and different buttons containing diverse functions
 */

public class Console {
    private Circle[] light;
    private Button[] side;
    private Button[] choice;
    private Button[] step;
    private GridPane frame;
    private Font font;

    private static Console console = null;

    public static Console getConsole(){
        if(console == null)
            console = new Console();
        return console;
    }
    private Console(){
        light = new Circle[2];
        side = new Button[2];
        choice = new Button[4];
        step = new Button[2];
        frame = new GridPane();
        font = new Font(20);
    }

    public GridPane generateFrame(){
        init();
        load();
        return frame;
    }

    public Circle getLight(int i){
        return light[i];
    }

    public Button getChoice(int i) {
        return choice[i];
    }

    public Button getStep(int i) {
        return step[i];
    }

    private void init(){
        for(int i = 0;i<2;i++){
            //set the indication light
            light[i] = new Circle(16);
            light[i].setFill(Color.rgb(0,230,0,1-i));
            //set for the prompt info
            side[i] = new Button();
            side[i].setPrefSize(100,40);
            side[i].setMouseTransparent(true);
            side[i].setFont(font);
            //set for the step button
            step[i] = new Button();
            step[i].setPrefSize(40,40);
        }
        step[0].setText("<<");
        step[1].setText(">>");

        choice[0] = new Button("Restart");
        choice[1] = new Button("Quit");
        choice[2] = new Button("Repent");
        choice[3] = new Button("PlayBack");
        for(int i = 0;i<4;i++){
            choice[i].setPrefSize(150,40);
            choice[i].setFont(font);
        }

        side[0].setText("Black");
        side[1].setText("White");
    }

    private void load(){
        for(int i = 0;i<2;i++){
            frame.add(light[i],0,i);
            frame.add(side[i],1,i);
        }
        for(int i = 0;i<4;i++){
            frame.add(choice[i], 0, i+2, 2, 1);
        }
        for(int i = 0;i<2;i++)
            frame.add(step[i],i,6);
        frame.setVgap(20);
        GridPane.setMargin(step[1],new Insets(0,0,0,70));
    }
}
