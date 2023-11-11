package kalah.controllor;

import com.qualitascorpus.testsupport.IO;
import kalah.entity.command.*;
import kalah.entity.Board;
import kalah.entity.command.CommandHistory;
import kalah.robot.RobotAgent;
import kalah.util.ConfigUtil;
import kalah.util.InputUtil;
import kalah.util.OutputUtil;

//Responsible for the control of the entire game
public class GameControl {
    private Board kalahBoard;
    private int houseChoice;
    private IO io;
    private CommandHistory commandHistory;

    OutputUtil outputUtil;

    public GameControl() {
        houseChoice = -1;
        kalahBoard = new Board();
        commandHistory = new CommandHistory();
    }
    public GameControl(int[] p1SeedList,int p1SeedInStore,int[] p2SeedList,int p2SeedInStore, boolean player1Start) {
        houseChoice = -1;
        commandHistory = new CommandHistory();
        kalahBoard = new Board(p1SeedList, p1SeedInStore, p2SeedList, p2SeedInStore);
        if (!player1Start) kalahBoard.changeTurn();

    }

//Responsible for the logic of the entire game, receiving input, controlling the board to accept moves, printing results
    public void startGame(IO io, boolean vertical, boolean bmf){
        this.io = io;

        this.setConfig(vertical, bmf, io);

        this.runGame();

        this.endPrint();

    }

    private void setConfig(boolean vertical, boolean bmf ,IO io){
        ConfigUtil.setVertical(vertical);
        if (vertical){
            ConfigUtil.setRobotFlag(false);
        } else {
            ConfigUtil.setRobotFlag(bmf);
        }
        outputUtil = new OutputUtil();
        outputUtil.setIo(io);

    }

    private void runGame(){
        outputUtil.printBoardInfo(kalahBoard);
        RobotAgent robotAgent = new RobotAgent(io, kalahBoard);

        while (!kalahBoard.checkFinish() && !kalahBoard.getQuitFlag()){
            if (ConfigUtil.getRobotFlag() && kalahBoard.whoIsTurn() == "2") {
                robotAgent.doBestMove();
            } else {
                doUserChoice();
            }
        }
    }

    private void doUserChoice(){
        CommandSender sender = new CommandSender();
        houseChoice = InputUtil.getPlayerChoice(io, kalahBoard);
        if (houseChoice < 1) {
            if (houseChoice == 0) {
                sender.setCommand(new NewGameCommand(kalahBoard,commandHistory,io));
            } else if (houseChoice == -1) {
                sender.setCommand(new SaveGameCommand(kalahBoard,commandHistory,io));
            } else if (houseChoice == -2) {
                sender.setCommand(new LoadGameCommand(this, kalahBoard,commandHistory,io));
            } else {
                sender.setCommand(new QuitGameCommand(kalahBoard,commandHistory));
            }
        } else {
            sender.setCommand(new MoveGameComand(kalahBoard,commandHistory, houseChoice,io));

        }
        sender.executeCommand();
    }


    private void endPrint(){

        if (kalahBoard.getQuitFlag()){
            outputUtil.printQuit( kalahBoard);
        }else {
            outputUtil.printWinnerInfo(kalahBoard);
        }
    }



}
