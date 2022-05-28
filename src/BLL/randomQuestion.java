package BLL;

import java.util.ArrayList;
import java.util.Random;

import javax.lang.model.util.ElementScanner14;
import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class randomQuestion {
    public randomQuestion(int RateLV1, int RateLV2, int RateLV3, int RateLV4, int numberQuestion, int numberId,
            String subject) {
        manageQuestion = new manageQuestion();
        str_subject = subject;
        int_numQuestionLV1 = RateLV1 * numberQuestion / 100;
        int_numQuestionLV2 = RateLV2 * numberQuestion / 100;
        int_numQuestionLV3 = RateLV3 * numberQuestion / 100;
        int_numQuestionLV4 = RateLV4 * numberQuestion / 100;
        chechQuestion();
        if (int_numQuestionLV1 + int_numQuestionLV2 + int_numQuestionLV3 + int_numQuestionLV4 < numberQuestion) {
            fillQuestion(numberQuestion, int_numQuestionLV1, int_numQuestionLV2, int_numQuestionLV3,
                    int_numQuestionLV4);
        }
    }

    private void chechQuestion() {
        if (int_numQuestionLV1 > manageQuestion.getCountQuestionByLevelAndSubject("Nhận biết", str_subject))
            int_numQuestionLV1 = manageQuestion.getCountQuestionByLevelAndSubject("Nhận biết", str_subject);

        if (int_numQuestionLV2 > manageQuestion.getCountQuestionByLevelAndSubject("Thông hiểu", str_subject))
            int_numQuestionLV2 = manageQuestion.getCountQuestionByLevelAndSubject("Thông hiểu", str_subject);

        if (int_numQuestionLV3 > manageQuestion.getCountQuestionByLevelAndSubject("Vận dụng thấp", str_subject))
            int_numQuestionLV3 = manageQuestion.getCountQuestionByLevelAndSubject("Vận dụng thấp", str_subject);

        if (int_numQuestionLV4 > manageQuestion.getCountQuestionByLevelAndSubject("Vận dụng cao", str_subject))
            int_numQuestionLV4 = manageQuestion.getCountQuestionByLevelAndSubject("Vận dụng cao", str_subject);
    }

    private void fillQuestion(int numberQues, int... intQuestion) {
        int int_allQuestion = 0;
        for (int i : intQuestion) {
            int_allQuestion += i;
        }

        int int_quesNeed = numberQues - int_allQuestion;

        Random rand = new Random();
        int int_randNumber;
        for (int i = 0; i < int_quesNeed; i++) {
            int_randNumber = rand.nextInt(4);
            if (int_randNumber > 0) {
                if (int_randNumber == 1 && int_numQuestionLV1 < manageQuestion
                        .getCountQuestionByLevelAndSubject("Nhận biết", str_subject))
                    int_numQuestionLV1 += 1;
                else if (int_randNumber == 2 && int_numQuestionLV2 < manageQuestion
                        .getCountQuestionByLevelAndSubject("Thông hiểu", str_subject))
                    int_numQuestionLV2 += 1;
                else if (int_randNumber == 3 && int_numQuestionLV3 < manageQuestion
                        .getCountQuestionByLevelAndSubject("Vận dụng thấp", str_subject))
                    int_numQuestionLV3 += 1;
                else if (int_randNumber == 4
                        && int_numQuestionLV4 < manageQuestion.getCountQuestionByLevelAndSubject("Vận dụng cao",
                                str_subject))
                    int_numQuestionLV4 += 1;
                else
                    i -= 1;
            } else
                i -= 1;
        }
    }

    private boolean isSame(ArrayList<Integer> hasNumrand, int numberRand) {
        for (int i : hasNumrand) {
            if (i == numberRand)
                return true;
        }

        return false;
    }

    private String[] randomQuestion(String[] strArr, int numberQues) {
        String[] strArr_random = new String[numberQues];
        Random random = new Random();
        ArrayList<Integer> arL_numberHasRand = new ArrayList<>();
        int int_numRand;
        int index_strArrRandom = 0;
        for (int i = 0; i < numberQues; i++) {
            int_numRand = random.nextInt(strArr.length);
            if (!isSame(arL_numberHasRand, int_numRand)) {
                strArr_random[index_strArrRandom] = strArr[int_numRand];
                index_strArrRandom++;
                arL_numberHasRand.add(int_numRand);
            } else {
                i -= 1;
            }
        }

        return strArr_random;
    }

    public String[] getRandomQuestionLV1() {
        strArr_questions = manageQuestion.getAllIdQuestion("Nhận biết", str_subject);

        return randomQuestion(strArr_questions, int_numQuestionLV1);
    }

    public String[] getRandomQuestionLV2() {
        strArr_questions = manageQuestion.getAllIdQuestion("Thông hiểu", str_subject);

        return randomQuestion(strArr_questions, int_numQuestionLV2);
    }

    public String[] getRandomQuestionLV3() {
        strArr_questions = manageQuestion.getAllIdQuestion("Vận dụng thấp", str_subject);

        return randomQuestion(strArr_questions, int_numQuestionLV3);
    }

    public String[] getRandomQuestionLV4() {
        strArr_questions = manageQuestion.getAllIdQuestion("Vận dụng cao", str_subject);

        return randomQuestion(strArr_questions, int_numQuestionLV4);
    }

    private int int_numQuestionLV1, int_numQuestionLV2, int_numQuestionLV3, int_numQuestionLV4, int_numberQuestion;
    private manageQuestion manageQuestion;
    private String[] strArr_questions;
    private String str_subject;
}
