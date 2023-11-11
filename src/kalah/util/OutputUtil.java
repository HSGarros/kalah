package kalah.util;

import com.qualitascorpus.testsupport.IO;
import kalah.entity.Board;
import kalah.entity.Player;

//Output the corresponding result according to the board
public class OutputUtil {

    private static final int houseSize;

    private IO io;



    static {
        houseSize = ConfigUtil.getMaxHouseSize();
    }

    public OutputUtil (){}

    public void setIo(IO io) {
        this.io = io;
    }

    public String fomateIntToStr(int num){
        if (num > 9){
            return "" + num;
        }else {
            return " " + num;
        }
    }

    public int mappingPosition(int postion){
        return houseSize - postion + 1;
    }

    public void printBoardInfo(Board board) {
        String fristLine;
        String secondLine;
        Player player1;
        Player player2;

        player1 = board.getPlayer1();
        player2 = board.getPlayer2();

        if (ConfigUtil.getVertical()) {

            io.println("+---------------+");
            io.println("|       | P2 " + fomateIntToStr(player2.getSeedInStore()) + " |");
            io.println("+-------+-------+");
            for (int i = 1; i <= houseSize; i++) {
                io.println("| "+i+"["+fomateIntToStr(player1.getSeedInhouse(i))+"] | "+
                        mappingPosition(i)+"["+fomateIntToStr(player2.getSeedInhouse(mappingPosition(i)))+"] |");
            }
            io.println("+-------+-------+");
            io.println("| P1 " + fomateIntToStr(player1.getSeedInStore()) + " |       |");
            io.println("+---------------+");

        } else {

            fristLine = "| P2 | ";
            for (int i = houseSize; i > 0 ; i--) {
                fristLine = fristLine + i + "[" + fomateIntToStr(player2.getSeedInhouse(i)) + "] | ";
            }
            fristLine = fristLine + fomateIntToStr(player1.getSeedInStore()) + " |";

            secondLine = " | P1 |";
            for (int i = houseSize; i > 0 ; i--) {
                secondLine = " | " + i + "[" +  fomateIntToStr(player1.getSeedInhouse(i)) + "]" + secondLine;
            }
            secondLine = "| " + fomateIntToStr(player2.getSeedInStore()) + secondLine ;

            io.println("+----+"+new String(new char[houseSize]).replace("\0", "-------+")+"----+");
            io.println(fristLine);
            io.println("|    |"+new String(new char[houseSize-1]).replace("\0", "-------+")+"-------|    |");
            io.println(secondLine);
            io.println("+----+"+new String(new char[houseSize]).replace("\0", "-------+")+"----+");

        }


    }

    public void printWinnerInfo(Board board) {
        io.println("Game over");
        printBoardInfo(board);
        io.println("\tplayer 1:"+ board.getP1Score());
        io.println("\tplayer 2:"+ board.getP2Score());
        if (board.getP1Score() == board.getP2Score()){
            io.println("A tie!");
        }else {
            io.println("Player " + board.checkWinner() + " wins!");
        }


    }

    public void printQuit(Board board) {
        io.println("Game over");
        printBoardInfo(board);

    }

    public void printHouseEmpty(Board board) {
        io.println("House is empty. Move again.");
    }

    public void printLoadErr(Board board){
        io.println("No saved game");
    }

    public void robotMoveAndReason(Board board, int move, String reason) {
        io.println("Player P2 (Robot) chooses house #" + move + reason);
    }
}
