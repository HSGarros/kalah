package kalah.robot;

import com.qualitascorpus.testsupport.IO;
import kalah.util.ConfigUtil;
import kalah.util.OutputUtil;
import kalah.entity.Board;
import kalah.entity.Move;
import kalah.entity.Player;

public class RobotAgent {
    private static final int houseSize;
    private Player player1;
    private Player player2;

    IO io;
    Board kalahBoard;

    static {
        houseSize = ConfigUtil.getMaxHouseSize();
    }

    public RobotAgent(IO io, Board kalahBoard) {
        this.io = io;
        this.kalahBoard = kalahBoard;
        player1 = kalahBoard.getPlayer1();
        player2 = kalahBoard.getPlayer2();
    }

    public int mappingPosition(int position){
        return houseSize - position + 1;
    }
    public void doBestMove(){

        boolean findBestMoveflag;
        OutputUtil outputUtil = new OutputUtil();
        outputUtil.setIo(io);


        findBestMoveflag = tryFindExtraMove();
        if (findBestMoveflag) {
            outputUtil.printBoardInfo(kalahBoard);
            return;
        }

        findBestMoveflag = tryFindCapture();
        if (findBestMoveflag) {
            outputUtil.printBoardInfo(kalahBoard);
            return;
        }

        tryFindLegalMove();
        outputUtil.printBoardInfo(kalahBoard);
    }

    private boolean tryFindExtraMove(){
        int houseChoice;
        String reason;
        Move move;
        OutputUtil outputUtil = new OutputUtil();
        outputUtil.setIo(io);


        for (int position = 1; position <= houseSize ; position++) {
            if (player2.getSeedInhouse(position) + position == houseSize + 1){
                houseChoice = position;
                reason = " because it leads to an extra move";
                move = new Move(kalahBoard, houseChoice);
                move.executeMove();
                outputUtil.robotMoveAndReason(kalahBoard, houseChoice, reason);
                return true;
            }
        }
        return false;
    }

    private boolean tryFindCapture(){
        int endPosition;
        int houseChoice;
        String reason;
        Move move;
        OutputUtil outputUtil = new OutputUtil();
        outputUtil.setIo(io);


        for (int position = 1; position <= houseSize ; position++) {
            if (player2.getSeedInhouse(position) == 0) continue;

            endPosition = (player2.getSeedInhouse(position) + position);
            if ( endPosition % (2 * houseSize + 1) > 0 && endPosition % (2 * houseSize + 1) <= houseSize) {
                if (player2.getSeedInhouse(endPosition % (2 * houseSize + 1)) == 0) {
                    if (endPosition > (2 * houseSize + 1) || player1.getSeedInhouse(mappingPosition(endPosition)) != 0){
                        houseChoice = position;
                        reason = " because it leads to a capture";
                        move = new Move(kalahBoard, houseChoice);
                        move.executeMove();
                        outputUtil.robotMoveAndReason(kalahBoard, houseChoice, reason);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean tryFindLegalMove(){
        int houseChoice;
        String reason;
        Move move;
        OutputUtil outputUtil = new OutputUtil();
        outputUtil.setIo(io);


        for (int position = 1; position <= houseSize ; position++) {
            if (player2.getSeedInhouse(position) != 0){
                houseChoice = position;
                reason = " because it is the first legal move";
                move = new Move(kalahBoard, houseChoice);
                move.executeMove();
                outputUtil.robotMoveAndReason(kalahBoard, houseChoice, reason);
                return true;
            }
        }
        return false;
    }


}
