package BLL;

import java.util.ArrayList;
import java.util.Random;

public class randomIdExamPaper {
    public randomIdExamPaper() {

    }

    public String getRandomIdExamPaper() {
        String str = "";
        random = new Random();
        ArrayList<String> arL_idExamPaper = new manageExamPaper().getAllIdExamPaper();

        boolean isDone = false;
        while (!isDone) {
            for (int i = 0; i < 3; i++) {
                str += random.nextInt(10);
            }

            int index = 0;
            for (String str_tempt : arL_idExamPaper) {
                if (str_tempt.equalsIgnoreCase(arL_idExamPaper.get(index))) {
                    continue;
                }
                index++;
            }

            isDone = true;
        }

        return str;
    }

    private Random random;
}
