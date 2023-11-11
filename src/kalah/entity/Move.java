package kalah.entity;

import kalah.util.ConfigUtil;

public class Move {
    private Board kalahBoard;
    private int houseChoice;
    private Player currentPlayer;
    private static final int houseSize;
    private static final int seedNum;


    static {
        houseSize = ConfigUtil.getMaxHouseSize();
        seedNum = ConfigUtil.getInitSeedNum();
    }

    public Move(Board kalahBoard, int houseChoice) {
        this.kalahBoard = kalahBoard;
        this.houseChoice = houseChoice;
    }

    public void executeMove(){
        int currentPosition;
        currentPlayer = kalahBoard.getNowPlayer();
        currentPosition = this.spreadSeeds();
        this.checkLastSeedPosition(currentPosition);
    }

    private int spreadSeeds(){
        int seedNumber;
        seedNumber = currentPlayer.takeAllSeedsInhouse(houseChoice);
        int currentPosition = houseChoice;

        for (int seedLeft = seedNumber; seedLeft > 0 ; seedLeft--) {
            currentPosition++;
            if ( currentPosition == houseSize + 1 && currentPlayer == kalahBoard.getNowPlayer()){
                currentPosition = 0;
                currentPlayer.addSeedInStore();
                currentPlayer = kalahBoard.getNextPlayer();
            }else if (currentPosition == houseSize + 1 && currentPlayer != kalahBoard.getNowPlayer()){
                currentPosition = 0;
                currentPlayer = kalahBoard.getNowPlayer();
                seedLeft++;
            }else {
                currentPlayer.addSeedInhouse(currentPosition);
            }
        }

        return currentPosition;
    }

    private void checkLastSeedPosition(int finalPosition){

        if (finalPosition != 0) {
            if( currentPlayer == kalahBoard.getNowPlayer() && currentPlayer.getSeedInhouse(finalPosition) == 1  ){
                if (kalahBoard.getNextPlayer().getSeedInhouse(mappingPosition(finalPosition)) != 0 ){
                    kalahBoard.getNowPlayer().addSeedsInStore(kalahBoard.getNowPlayer().takeAllSeedsInhouse(finalPosition) +
                            kalahBoard.getNextPlayer().takeAllSeedsInhouse(mappingPosition(finalPosition)));
                }
            }
            kalahBoard.changeTurn();
        }

    }

    private int mappingPosition(int postion){
        return houseSize - postion + 1;
    }

    public boolean checkPosition(){
        return kalahBoard.checkHouseEmpty(houseChoice);
    }



}
