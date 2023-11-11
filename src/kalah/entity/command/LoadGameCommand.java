package kalah.entity.command;

import com.qualitascorpus.testsupport.IO;
import kalah.controllor.GameControl;
import kalah.entity.Board;
import kalah.entity.Memento;
import kalah.util.OutputUtil;

public class LoadGameCommand implements Command{
    Board board = null;
    Memento memento = null;
    CommandHistory commandHistory = null;
    OutputUtil outputUtil;
    GameControl gameControl;

    public LoadGameCommand(GameControl gameControl, Board board, CommandHistory commandHistory, IO io) {
        this.gameControl = gameControl;
        this.board = board;
        this.commandHistory = commandHistory;
        outputUtil = new OutputUtil();
        outputUtil.setIo(io);
    }

    public Memento getMemento() {
        return memento;
    }

    @Override
    public void saveBackup() {
        this.memento = board.save();
    }

    @Override
    public void undo() { }

    @Override
    public void execute() {

        commandHistory.push(this);
        if (commandHistory.rollback()){
            outputUtil.printBoardInfo(board);
        } else {
            outputUtil.printLoadErr(board);
            outputUtil.printBoardInfo(board);
        }
    }
}
