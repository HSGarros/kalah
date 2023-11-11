package kalah.entity;

import kalah.util.ConfigUtil;
import kalah.util.OutputUtil;

//A standard board has two players and keeps track of whose turn is currently on
public class Board{

    private static final int houseSize;
    private static final int seedNum;
    private final Player player1;
    private final Player player2;
    private boolean quitFlag;


    private Player nowPlayer;

    private Player nextPlayer;

    static {
        houseSize = ConfigUtil.getMaxHouseSize();
        seedNum = ConfigUtil.getInitSeedNum();
    }
    public Board() {
        player1 = new Player(houseSize, seedNum);
        player2 = new Player(houseSize, seedNum);
        nowPlayer = player1;
        nextPlayer = player2;
        quitFlag = false;
    }

    public Board(int[] p1SeedList,int p1SeedInStore,int[] p2SeedList,int p2SeedInStore ) {
        player1 = new Player(p1SeedList, p1SeedInStore);
        player2 = new Player(p2SeedList, p2SeedInStore);
        nowPlayer = player1;
        nextPlayer = player2;
        quitFlag = false;
    }

    public Board(Player prototype1, Player prototype2 ) {
        player1 = new Player(prototype1.getHouseList(), prototype1.getSeedInStore());
        player2 = new Player(prototype2.getHouseList(), prototype2.getSeedInStore());
        nowPlayer = player1;
        nextPlayer = player2;
        quitFlag = false;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getP1Score(){
        return player1.getSumSeed();
    }

    public int getP2Score(){
        return player2.getSumSeed();
    }

    public Player getNowPlayer() {
        return nowPlayer;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }
    public void quitGame(){ this.quitFlag = true; }
    public boolean getQuitFlag(){ return quitFlag; }


    //Check that the input to the house corresponding to position
    public boolean checkHouseEmpty(int input){
        if (input == -1 || nowPlayer.getSeedInhouse(input) == 0){
            return false;
        } else {
            return true;
        }
    }

    //Determine if the game is over
    public boolean checkFinish(){
        if ( nowPlayer.checkAllOfHousesIsEmpty() ){
            return true;
        }else {
            return false;
        }
    }
    //Determine who has the highest total seed
    public String checkWinner(){
        if ( player1.getSumSeed() > player2.getSumSeed() ){
            return "1";
        }else {
            return "2";
        }
    }

    //Find out whose turn it is
    public String whoIsTurn(){
        if ( nowPlayer == player1){
            return "1";
        }else {
            return "2";
        }
    }

    //Change the next round of players
    public void changeTurn(){
        Player mediator;
        mediator = nowPlayer;
        nowPlayer = nextPlayer;
        nextPlayer = mediator;
    }


    public void restart(){
        player1.restart();
        player2.restart();
        nowPlayer = player1;
        nextPlayer = player2;
        quitFlag = false;
    }

    public boolean tryMove(int houseChoice){
        Move move = new Move(this, houseChoice);
        if (move.checkPosition()){
            move.executeMove();
            return true;
        } else {
            return false;
        }
    }

    public Memento save() {
        return new BoardSnapshot(this, player1, player2, whoIsTurn());
    }

    public void setState(BoardSnapshot boardSnapshot) {
        this.player1.setHouse(boardSnapshot.p1HouseList);
        this.player2.setHouse(boardSnapshot.p2HouseList);
        this.player1.setStore(boardSnapshot.p1SeedNum);
        this.player2.setStore(boardSnapshot.p2SeedNum);
        if (whoIsTurn() != boardSnapshot.turnFlag){
            changeTurn();
        }
    }
}
