package kalah.entity.command;

import com.qualitascorpus.testsupport.IO;
import kalah.entity.Board;
import kalah.entity.Memento;
import kalah.util.OutputUtil;

public class NewGameCommand implements Command{
    Board board = null;
    Memento memento = null;
    CommandHistory commandHistory = null;
    OutputUtil outputUtil;

    public NewGameCommand(Board board, CommandHistory commandHistory,IO io) {
        this.board = board;
        this.commandHistory = commandHistory;
        outputUtil = new OutputUtil();
        outputUtil.setIo(io);
    }

    @Override
    public void saveBackup() {
        this.memento = board.save();
    }

    @Override
    public void undo() {

    }

    @Override
    public void execute() {

        commandHistory.push(this);
        commandHistory.clean();
        board.restart();
        outputUtil.printBoardInfo(board);
    }
}
