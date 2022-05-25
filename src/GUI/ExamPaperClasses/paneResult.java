package GUI.ExamPaperClasses;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import BLL.manageDetailExam;
import BLL.manageDetailExamPaper;
import BLL.manageExamPaper;
import BLL.manageQuestion;
import BLL.ExamPaper.ExamPaper;
import GUI.CommonClasses.Parameter;

public class paneResult extends JPanel implements Parameter {
    public paneResult(String IdExam) {
        parameter();

        mep_manageExamPaper = new manageExamPaper();
        ep_examPaper = mep_manageExamPaper.getExamPaperById(IdExam);

        displayAnswer();
        displayResult();
    }

    private void displayAnswer() {
        mdep_manageDetailExamPaper = new manageDetailExamPaper();
        mde_manageDetailExam = new manageDetailExam();
        ArrayList<String> arl_idQues = mde_manageDetailExam.getAllAnswerDetailExamById(ep_examPaper.getIdExam());
        lb_answer = new JLabel[arl_idQues.size()];

        mq_manageQuestion = new manageQuestion();
        int index = 0;
        int x = 15;
        int y = 10;
        lb_answerWord = new JLabel("Đáp án");
        lb_answerWord.setSize(int_widthLbAnswerWord, int_heightLbAnswerWord);
        posInScreen.CUSTOM_CHILD_PARENT(lb_answerWord, this, 40, 0);
        add(lb_answerWord);

        for (String str : arl_idQues) {
            lb_answer[index] = new JLabel((index + 1) + "." + str);
            lb_answer[index].setSize(int_widthLbAnswer, int_heightLbAnswer);
            posInScreen.CUSTOM_CHILD_PARENT(lb_answer[index], this, x, y);
            add(lb_answer[index]);

            if ((index + 1) % 5 == 0 && index > 0) {
                y += 8;
                x = 15;
            } else {
                x += 15;
            }
            index++;
        }
    }

    private void displayResult() {
        lb_mark = new JLabel("Điểm: " + ep_examPaper.getMark());
        lb_mark.setSize(int_widthLbMark, int_heightLbMark);
        posInScreen.CUSTOM_CHILD_PARENT(lb_mark, this, 40, 50);
        add(lb_mark);

        lb_numberRight = new JLabel("Số câu đúng: " + ep_examPaper.getNumberRight());
        lb_numberRight.setSize(int_widthLbNumberRight, int_heightLbNumberRight);
        posInScreen.PARENT_CHILD_VERTICAL(lb_numberRight, lb_mark, 40);
        add(lb_numberRight);

        lb_numberWrong = new JLabel("Số câu sai: " + ep_examPaper.getNumberWrong());
        lb_numberWrong.setSize(int_widthLbNumberWrong, int_widthLbNumberWrong);
        posInScreen.PARENT_CHILD_VERTICAL(lb_numberWrong, lb_numberRight, 125);
        add(lb_numberWrong);
    }

    @Override
    public void parameter() {
        // TODO Auto-generated method stub
        int_heightPane = 500;
        int_widthPane = 300;
        setSize(int_widthPane, int_heightPane);
        setOpaque(true);
        setBackground(Color.white);
        setLayout(null);

        int_widthLbAnswer = 50;
        int_heightLbAnswer = 25;

        int_widthLbAnswerWord = 100;
        int_heightLbAnswerWord = 25;

        int_widthLbMark = 200;
        int_heightLbMark = 25;

        int_widthLbNumberRight = 200;
        int_heightLbNumberRight = 25;

        int_widthLbNumberWrong = 200;
        int_heightLbNumberWrong = 25;
    }

    private int int_widthPane;
    private int int_heightPane;

    private JLabel lb_answerWord;
    private JLabel[] lb_answer;
    private int int_widthLbAnswer;
    private int int_heightLbAnswer;
    private int int_widthLbAnswerWord;
    private int int_heightLbAnswerWord;
    private manageExamPaper mep_manageExamPaper;
    private manageDetailExamPaper mdep_manageDetailExamPaper;
    private manageDetailExam mde_manageDetailExam;
    private ExamPaper ep_examPaper;
    private manageQuestion mq_manageQuestion;

    private JLabel lb_mark;
    private int int_widthLbMark;
    private int int_heightLbMark;

    private JLabel lb_numberRight;
    private int int_widthLbNumberRight;
    private int int_heightLbNumberRight;

    private JLabel lb_numberWrong;
    private int int_widthLbNumberWrong;
    private int int_heightLbNumberWrong;
}
