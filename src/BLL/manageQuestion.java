package BLL;

import BLL.DTO.OptionsQuestionDTO;
import BLL.DTO.QuestionDTO;
import BLL.DTO.YesNoQuestionDTO;
import BLL.Question.OptionsQuestion;
import BLL.Question.YesNoQuestion;
import DAL.connectSQL;
import DAL.manageQuestionDAL;

public class manageQuestion {
    public manageQuestion() {
        mqd_manageQuestion = new manageQuestionDAL();
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
        pQDTO_optionsQuestion = new OptionsQuestionDTO(mqd_manageQuestion.getOptionsQuestion(),
                mqd_manageQuestion.getColumn());
        ynQDTO_yesNoQuestion = new YesNoQuestionDTO(mqd_manageQuestion.getYesNoQuestion(),
                mqd_manageQuestion.getColumn());

        return mertArray(pQDTO_optionsQuestion.getOptionsQuestion(), ynQDTO_yesNoQuestion.getYesNoQuestion());
    }

    public Boolean updateYesNoQuestion(YesNoQuestion yNoQuestion, boolean isChange) {
        return mqd_manageQuestion.updateYesNoQuestion(yNoQuestion, isChange);
    }

    public Boolean updateOptionsQuestion(OptionsQuestion optionsQuestion, boolean isChange) {
        return mqd_manageQuestion.updateOptionsQuestion(optionsQuestion, isChange);
    }

    public Boolean addYesNoQuestion(YesNoQuestion yNoQuestion) {
        return mqd_manageQuestion.addYesNoQuestion(yNoQuestion, String.valueOf(getAllCountQuestions() + 1));
    }

    public Boolean addOptionsQuestion(OptionsQuestion optionsQuestion) {
        return mqd_manageQuestion.addOptionsQuestion(optionsQuestion, String.valueOf(getAllCountQuestions() + 1));
    }

    public Boolean deleteYesNoQuestion(YesNoQuestion yNoQuestion) {
        return mqd_manageQuestion.deleteYesNoQuestion(yNoQuestion);
    }

    public Boolean deleteOptionsQuestion(OptionsQuestion optionsQuestion) {
        return mqd_manageQuestion.deleteOptionsQuestion(optionsQuestion);
    }

    public int getCountQuestions(String level) {
        return mqd_manageQuestion.getCountQuesionsByLevel(level);
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
        return new QuestionDTO(mqd_manageQuestion.getAllIdQuestion(lv)).getStrArrQuestion();
    }

    public String getAnswer(String Id) {
        return mqd_manageQuestion.getAnswer(Id);
    }

    public OptionsQuestion getOptionsQuestionById(String Id) {
        return mqd_manageQuestion.getOptionQuestionByID(Id);
    }

    public YesNoQuestion getYesNoQuestionById(String Id) {
        return mqd_manageQuestion.getYesNoQuestionById(Id);
    }

    public String getTypeById(String Id) {
        return mqd_manageQuestion.getTypeById(Id);
    }

    private OptionsQuestionDTO pQDTO_optionsQuestion;
    private YesNoQuestionDTO ynQDTO_yesNoQuestion;
    private YesNoQuestion yesNoQuestion;
    private manageQuestionDAL mqd_manageQuestion;
}
