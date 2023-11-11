package kalah.entity.command;

import kalah.entity.Memento;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CommandHistory {
    Stack<Command> history;
    Memento backup = null;

    Memento[] ml;



    public CommandHistory() {
        this.history = new Stack<Command>();
    }

    public void push(Command command){
        this.history.push(command);
    }

    public Command pop() { return this.history.pop();}

    public boolean empty() { return this.history.empty(); }

    public boolean rollback(){
        List<Command> list = new LinkedList<Command>();
        Command currentCommand;

        while (!empty()){
            currentCommand = pop();
            list.add(currentCommand);
            if (currentCommand.getClass() == SaveGameCommand.class){
                currentCommand.undo();

                for (Command command: list) {
                    push(command);
                }
                return true;
            }
        }
        for (Command command:
                list) {
            push(command);
        }
        return false;
    }

    public void clean(){
        this.history = new Stack<Command>();
        backup = null;
    }
}
