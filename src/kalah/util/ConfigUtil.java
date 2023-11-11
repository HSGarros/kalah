package kalah.util;

//global setting
public class ConfigUtil {

    private static int MaxHouseSize;
    private static int InitSeedNum;
    private static boolean RobotFlag;
    private static boolean Vertical;


    static {
        MaxHouseSize = 6;
        InitSeedNum = 4;
        RobotFlag = false;
        Vertical = true;
    }

    public static int getMaxHouseSize() { return MaxHouseSize; }

    public static int getInitSeedNum() { return InitSeedNum; }

    public static boolean getRobotFlag() { return RobotFlag; }

    public static boolean getVertical() { return Vertical; }

    public static void setMaxHouseSize(int maxHouseSize) { MaxHouseSize = maxHouseSize; }

    public static void setInitSeedNum(int initSeedNum) { InitSeedNum = initSeedNum; }

    public static void setRobotFlag(boolean robotFlag) { RobotFlag = robotFlag; }

    public static void setVertical(boolean vertical) { ConfigUtil.Vertical = vertical; }
}
