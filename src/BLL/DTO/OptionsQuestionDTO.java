package BLL.DTO;

import java.util.ArrayList;

import BLL.Question.OptionsQuestion;

public class OptionsQuestionDTO {
    public OptionsQuestionDTO(ArrayList<OptionsQuestion> arrL_optionsQuestion, int column) {
        this.arrL_optionsQuestion = arrL_optionsQuestion;
        this.column = column;
    }

    private String[][] convertArrOptionsQuestionListToArr() {
        int row = arrL_optionsQuestion.size();
        int col = column;

        String[][] str_tempt = new String[row][col];
        for (int i_row = 0; i_row < row; i_row++) {
            int j_col = 0;
            str_tempt[i_row][j_col] = arrL_optionsQuestion.get(i_row).getIdQuestion();
            str_tempt[i_row][++j_col] = arrL_optionsQuestion.get(i_row).getLevelQuestion();
            str_tempt[i_row][++j_col] = arrL_optionsQuestion.get(i_row).getContentQuestion();
            str_tempt[i_row][++j_col] = arrL_optionsQuestion.get(i_row).getAnswerQuestion();
            str_tempt[i_row][++j_col] = arrL_optionsQuestion.get(i_row).getSubjectQuestion();
            str_tempt[i_row][++j_col] = arrL_optionsQuestion.get(i_row).getLectureQuestion();
            str_tempt[i_row][++j_col] = arrL_optionsQuestion.get(i_row).getTypeQuestion();
            str_tempt[i_row][++j_col] = arrL_optionsQuestion.get(i_row).getOptionsQuestion();
        }

        return str_tempt;
    }

    public String[][] getOptionsQuestion() {
        return convertArrOptionsQuestionListToArr();
    }

    private ArrayList<OptionsQuestion> arrL_optionsQuestion;
    int column;
}
