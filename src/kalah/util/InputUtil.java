package kalah.util;

import com.qualitascorpus.testsupport.IO;
import kalah.entity.Board;

import java.util.Arrays;
import java.util.List;

//Responsible for receiving legitimate input
public class InputUtil {

    public static int getPlayerChoice(IO io, Board board) {
        int result;
        int lower = 1;
        int upper = ConfigUtil.getMaxHouseSize();
        List<String> chioceList = Arrays.asList(new String[]{"n","s","l","q"});

        while(true) {
            io.println("Player P" + board.whoIsTurn());
            io.println("    (1-6) - house number for move" );
            io.println("    N - New game");
            io.println("    S - Save game");
            io.println("    L - Load game");
            io.println("    q - Quit");

            String input = io.readFromKeyboard("Choice:");
            if (chioceList.contains(input)) {
                result = -chioceList.indexOf(input);
                break;
            }

            try {
                result = Integer.parseInt(input);
                if (result >= lower && result <= upper) {
                    break;
                }
            } catch (NumberFormatException var9) {
            }
        }
        return result;
    }

}
