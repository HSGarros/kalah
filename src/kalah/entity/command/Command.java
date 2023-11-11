package kalah.entity.command;

public interface Command {

    public void saveBackup();

    public void undo();

    public void execute();
}
