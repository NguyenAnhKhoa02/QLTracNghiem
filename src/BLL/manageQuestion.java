package BLL;

import java.util.ArrayList;
import DAL.connectSQL;

public class manageQuestion {
    manageQuestion() {
        conSQL_question = new connectSQL();
    }

    public ArrayList<?> getAllQuesitons() {
        return conSQL_question.getAllQuestions(conSQL_question.BOTH);
    }

    public ArrayList<?> getOptionsQuestions() {
        return conSQL_question.getAllQuestions(conSQL_question.OPTIONS_QUESTION);
    }

    public ArrayList<?> getYesNoQuestions() {
        return conSQL_question.getAllQuestions(conSQL_question.YESNO_QUESTION);
    }

    private connectSQL conSQL_question;

}
