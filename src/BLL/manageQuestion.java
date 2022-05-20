package BLL;

import BLL.DTO.OptionsQuestionDTO;
import BLL.DTO.YesNoQuestionDTO;
import DAL.connectSQL;

public class manageQuestion {
    public manageQuestion() {
        conSQL_question = new connectSQL();
    }

    private void sortQuestion(String[][] str_data, int row, int column) {
        for (int i = 0; i < str_data.length; i++) {
            for (int j = i + 1; j <= str_data.length - 1; j++) {
                if (Integer.parseInt(str_data[i][0]) > Integer.parseInt(str_data[j][0])) {
                    String[][] strArr_tempt = new String[1][column];

                    for (int a = 0; a < column; a++) {
                        strArr_tempt[0][a] = str_data[i][a];
                        str_data[i][a] = str_data[j][a];
                        str_data[j][a] = strArr_tempt[0][a];
                    }
                }
            }
        }
    }

    private String[][] mertArray(String[][] strArr1, String[][] strArr2) {
        int row = strArr1.length + strArr2.length;
        int column = 8;

        String data[][] = new String[row][column];

        int i_row = 0;
        int i_column = 0;

        int i_dataRow = -1;
        int i_dataColumn = -1;

        for (; i_row < strArr1.length; i_row++) {
            i_dataRow++;
            i_dataColumn = -1;
            for (i_column = 0; i_column < column; i_column++) {
                data[i_dataRow][++i_dataColumn] = strArr1[i_row][i_column];
            }
        }

        for (i_row = 0; i_row < strArr2.length; i_row++) {
            i_dataRow++;
            i_dataColumn = -1;
            for (i_column = 0; i_column < column; i_column++) {
                data[i_dataRow][++i_dataColumn] = strArr2[i_row][i_column];
            }
        }

        sortQuestion(data, row, column);

        return data;
    }

    public String[][] getAllQuestions() {
        pQDTO_optionsQuestion = new OptionsQuestionDTO(conSQL_question.getOptionsQuestion(),
                conSQL_question.getColumn());
        ynQDTO_yesNoQuestion = new YesNoQuestionDTO(conSQL_question.getYesNoQuestion(), conSQL_question.getColumn());

        return mertArray(pQDTO_optionsQuestion.getOptionsQuestion(), ynQDTO_yesNoQuestion.getYesNoQuestion());
    }

    private connectSQL conSQL_question;
    private OptionsQuestionDTO pQDTO_optionsQuestion;
    private YesNoQuestionDTO ynQDTO_yesNoQuestion;
}
