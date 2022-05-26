package BLL;

import BLL.DTO.OptionsQuestionDTO;
import BLL.DTO.QuestionDTO;
import BLL.DTO.YesNoQuestionDTO;
import BLL.Question.OptionsQuestion;
import BLL.Question.YesNoQuestion;
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

    public Boolean updateYesNoQuestion(YesNoQuestion yNoQuestion, boolean isChange) {
        return conSQL_question.updateYesNoQuestion(yNoQuestion, isChange);
    }

    public Boolean updateOptionsQuestion(OptionsQuestion optionsQuestion, boolean isChange) {
        return conSQL_question.updateOptionsQuestion(optionsQuestion, isChange);
    }

    public Boolean addYesNoQuestion(YesNoQuestion yNoQuestion) {
        return conSQL_question.addYesNoQuestion(yNoQuestion, String.valueOf(getAllCountQuestions() + 1));
    }

    public Boolean addOptionsQuestion(OptionsQuestion optionsQuestion) {
        return conSQL_question.addOptionsQuestion(optionsQuestion, String.valueOf(getAllCountQuestions() + 1));
    }

    public Boolean deleteYesNoQuestion(YesNoQuestion yNoQuestion) {
        return conSQL_question.deleteYesNoQuestion(yNoQuestion);
    }

    public Boolean deleteOptionsQuestion(OptionsQuestion optionsQuestion) {
        return conSQL_question.deleteOptionsQuestion(optionsQuestion);
    }

    public int getCountQuestions(String level) {
        return conSQL_question.getCountQuesionsByLevel(level);
    }

    public int getAllCountQuestions() {
        int int_sum = 0;
        String[] strArr_Levels = { "Nhận biết", "Thông hiểu", "Vận dụng thấp", "Vận dụng cao" };

        for (String str : strArr_Levels) {
            int_sum += getCountQuestions(str);
        }

        return int_sum;
    }

    public String[] getAllIdQuestion(String lv) {
        return new QuestionDTO(conSQL_question.getAllIdQuestion(lv)).getStrArrQuestion();
    }

    public String getAnswer(String Id) {
        return conSQL_question.getAnswer(Id);
    }

    public OptionsQuestion getOptionsQuestionById(String Id) {
        return conSQL_question.getOptionQuestionByID(Id);
    }

    public YesNoQuestion getYesNoQuestionById(String Id) {
        return conSQL_question.getYesNoQuestionById(Id);

    }

    private connectSQL conSQL_question;
    private OptionsQuestionDTO pQDTO_optionsQuestion;
    private YesNoQuestionDTO ynQDTO_yesNoQuestion;
    private YesNoQuestion yesNoQuestion;
}
