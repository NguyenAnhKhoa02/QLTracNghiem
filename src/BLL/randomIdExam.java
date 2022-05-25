package BLL;

import java.util.ArrayList;
import java.util.Random;

import DAL.connectSQL;

public class randomIdExam {
    public randomIdExam(int numberId) {
        ArrayList<String> arL_idExam = new manageExam().getAllIdExam();

        strArr_randomId = new String[numberId];
        random = new Random();
        int index = 0;
        while (numberId > 0) {
            strArr_randomId[index] = "";
            for (int i = 0; i < 3; i++) {
                strArr_randomId[index] += String.valueOf(random.nextInt(10));
            }

            for (String str : arL_idExam) {
                if (strArr_randomId[index].equals(str)) {
                    continue;
                }
            }

            if (index > 0 && !strArr_randomId[index].equals(strArr_randomId[index - 1])) {
                numberId -= 1;
                index++;
            }

            if (index == 0) {
                index++;
                numberId -= 1;
            }
        }
    }

    public randomIdExam(ArrayList<String> arL_idExam) {
        this.arL_idExam = arL_idExam;
    }

    public String getRandExam() {
        random = new Random();
        return arL_idExam.get(random.nextInt(arL_idExam.size()));
    }

    public String[] getIdExam() {
        return strArr_randomId;
    }

    private Random random;
    private String[] strArr_randomId;
    private ArrayList<String> arL_idExam;
}
