package goBang.model;

/**
 * this class is intended to maintain the basic rules of the game
 * 1.store the data of the game including position of each piece
 * 2.provide a method to check whether one side has won the game
 * 3.add/delete pieces into/from the board
 */
public class GameModel {
    private int[][] board;
    private boolean isBlack;
    private boolean status;

    public static final int N = 15;
    public static GameModel gameModel = null;

    /*
    singleton patterns makes it possible for different
    parts to alter the same gameModel
     */
    public static GameModel getGameModel(){
        if(gameModel == null)
            gameModel = new GameModel();
        return gameModel;
    }

    private GameModel(){
        //initialize the board
        this.board = new int[N][];
        for(int i = 0;i<N;i++)
            this.board[i] = new int[N];

        this.isBlack = true;
        this.status = false;
    }

    public boolean isBlack(){
        return isBlack;
    }
    //shift to the other side to play game
    public void inTurn(){
        this.isBlack = !this.isBlack;
    }

    //the most important part
    //check whether one side has won the game
    public void setStatus(int i,int j){
        status = (line(i,j)||column(i,j)||leftR(i,j)||rightL(i,j));
    }

    public boolean getStatus(){
        return status;
    }

    public boolean line(int i,int j){
        int cx = 0;
        for(int m =i+1;m<N && board[m][j]==board[i][j];++m)
            cx++;
        for(int m = i-1;m>=0 && board[m][j]==board[i][j];--m)
            cx++;
        return cx>=4;
    }
    public boolean column(int i,int j){
        int cx = 0;
        for(int n =j+1;n<N && board[i][n]==board[i][j];++n)
            cx++;
        for(int n = j-1;n>=0 && board[i][n]==board[i][j];--n)
            cx++;
        return cx>=4;
    }
    public boolean leftR(int i,int j){
        int cx = 0;
        for(int m=i+1,n=j+1;m<N && n<6 && board[m][n]==board[i][j];++m,++n)
            cx++;
        for(int m=i-1,n=j-1;m>=0 && n>=0 && board[m][n]==board[i][j];--m,--n)
            cx++;
        return cx>=4;
    }
    public boolean rightL(int i,int j){
        int cx = 0;
        for(int m=i-1,n=j+1;m>=0 && n<N && board[m][n]==board[i][j];--m,++n)
            cx++;
        for(int m=i+1,n=j-1;m<N && n>=0 && board[m][n]==board[i][j];++m,--n)
            cx++;
        return cx>=4;
    }

    public boolean addChess(int i,int j){
        /*
        if the current chessPiece is black,set the location 1,else 2
        when one chessPiece has been dropped,change the color of chessPiece for the next Drop
         */
        if(i<N && j<N && board[i][j] == 0){
            board[i][j] = isBlack() ? 1 : 2;
            inTurn();
            return true;
        }
        else
            return false;
    }

    public void deleteChess(int i,int j){
        if(i<N && j<N){
            board[i][j] = 0;
            inTurn();
        }
    }

    public void reStart(){
        for(int i = 0;i<N;++i)
            for(int j = 0;j<N;++j){
                board[i][j] = 0;
            }
        isBlack = true;
        status = false;
    }
}
