package BLL.DTO;

import java.util.ArrayList;

public class ExamDTO {
    public ExamDTO(String[] randomQuestion) {
        arL_DTO = randomQuestion;
    }

    public String[] getStrArrId() {
        String[] strArr_tempt = new String[arL_DTO.length];

        int index = 0;
        for (String str : arL_DTO) {
            strArr_tempt[index] = str.split("-")[0];
            index++;
        }

        return strArr_tempt;
    }

    public String[] getStrArrTypeQues() {
        String[] strArr_tempt = new String[arL_DTO.length];

        int index = 0;
        for (String str : arL_DTO) {
            strArr_tempt[index] = str.split("-")[1];
            index++;
        }

        return strArr_tempt;
    }

    private String[] arL_DTO;
}
