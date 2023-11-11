package kalah.entity.command;

public class CommandSender {
    Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand(){
        command.execute();

    }
}
