package kalah.entity.command;

import com.qualitascorpus.testsupport.IO;
import kalah.entity.Board;
import kalah.entity.Memento;
import kalah.util.OutputUtil;

public class MoveGameComand implements Command{
    Board board = null;
    Memento memento = null;
    CommandHistory commandHistory = null;
    OutputUtil outputUtil;
    int houseChoice;

    public MoveGameComand(Board board, CommandHistory commandHistory, int houseChoice, IO io) {
        this.board = board;
        this.commandHistory = commandHistory;
        this.houseChoice = houseChoice;
        outputUtil = new OutputUtil();
        outputUtil.setIo(io);
    }

    @Override
    public void saveBackup() {
        this.memento = board.save();
    }

    @Override
    public void undo() {
        this.memento.restore();
    }

    @Override
    public void execute() {

        saveBackup();
        commandHistory.push(this);

        if (board.tryMove(houseChoice)){

            outputUtil.printBoardInfo(board);
        } else {
            outputUtil.printHouseEmpty(board);
            outputUtil.printBoardInfo(board);
        }

    }
}
