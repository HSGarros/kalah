package kalah.entity;

public class BoardSnapshot implements Memento{
    Board board;
    int[] p1HouseList;

    int p1SeedNum;
    int[] p2HouseList;
    int p2SeedNum;
    String turnFlag;

    public int[] getP1HouseList() {
        return p1HouseList;
    }

    public int getP1SeedNum() {
        return p1SeedNum;
    }

    public int[] getP2HouseList() {
        return p2HouseList;
    }

    public int getP2SeedNum() {
        return p2SeedNum;
    }

    public String getP1TurnFlag() {
        return turnFlag;
    }

    public BoardSnapshot(Board board, Player p1, Player p2, String turnFlag) {
        this.board = board;
        this.p1HouseList = p1.getHouseList().clone();
        this.p2HouseList = p2.getHouseList().clone();
        this.p1SeedNum = p1.getSeedInStore();
        this.p2SeedNum = p2.getSeedInStore();
        this.turnFlag = turnFlag;
    }


    @Override
    public void restore() {
        board.setState(this);
    }
}
