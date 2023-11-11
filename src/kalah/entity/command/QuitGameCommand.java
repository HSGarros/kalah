package kalah.entity.command;

import kalah.entity.Board;
import kalah.entity.Memento;

public class QuitGameCommand implements Command{
    Board board = null;
    Memento memento = null;
    CommandHistory commandHistory = null;

    public QuitGameCommand(Board board, CommandHistory commandHistory) {
        this.board = board;
        this.commandHistory = commandHistory;
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
        commandHistory.push(this);
        commandHistory.clean();
        board.quitGame();
    }
}
