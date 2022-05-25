package BLL.DTO;

import java.util.ArrayList;

public class QuestionDTO {
    public QuestionDTO(ArrayList<String> arrayList) {
        arL_questionLively = arrayList;
    }

    public String[] getStrArrQuestion() {
        String[] strArr_questionLively = new String[arL_questionLively.size()];

        int index = 0;
        for (String str : arL_questionLively) {
            strArr_questionLively[index] = str;
            index++;
        }

        return strArr_questionLively;
    }

    private ArrayList<String> arL_questionLively;
}
