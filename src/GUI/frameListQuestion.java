package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import BLL.manageQuestion;
import BLL.Question.Question;
import GUI.CommonClasses.*;

public class frameListQuestion extends JFrame implements Parameter, MouseListener {
    public frameListQuestion() {
        parameter();

        mnQ_questions = new manageQuestion();

        lb_titleFrame = new JLabel("DANH SÁCH CÂU HỎI", JLabel.CENTER);
        lb_titleFrame.setFont(new Font("Time new roman", Font.BOLD, 50));
        lb_titleFrame.setSize(int_widthLbTitleFrame, int_heightLbTitleFrame);
        posInScreen.CUSTOM_WITH_PERCENT(lb_titleFrame, 0, 0);
        add(lb_titleFrame);

        makingTable();
        setVisible(true);
    }

    @Override
    public void parameter() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        int_heightTbListQuestion = parameterScreen.SCREEN_HEIGHT * 75 / 100;
        int_widthTbListQuestion = parameterScreen.SCREEN_WIDTH;
        int_heightRowTbListQuestion = 30;

        int_widthLbTitleFrame = parameterScreen.SCREEN_WIDTH;
        int_heightLbTitleFrame = 100;
    }

    private void setParameterTable(JTable jTable, int... width) {
        int index = 0;
        for (int w : width) {
            jTable.getColumnModel().getColumn(index).setPreferredWidth(w);
            ++index;
        }
    }

    private void makingTable() {
        String[][] data = mnQ_questions.getAllQuestions();
        String[] column = { "Id", "Độ khó", "Nội dung", "Câu trả lời", "Môn học", "Giảng viên", "Loại câu hỏi" };

        tb_listQuestion = new JTable(data, column);
        tb_listQuestion.setRowHeight(int_heightRowTbListQuestion);
        setParameterTable(tb_listQuestion, 10, 10, 200, 10, 150, 150);
        tb_listQuestion.addMouseListener(this);

        JScrollPane scrollPane = new JScrollPane(tb_listQuestion);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(int_widthTbListQuestion, int_heightTbListQuestion);
        posInScreen.CUSTOM_WITH_PERCENT(scrollPane, 0, 25);
        add(scrollPane);
    }

    private JTable tb_listQuestion;
    private manageQuestion mnQ_questions;
    private int int_widthTbListQuestion;
    private int int_heightTbListQuestion;
    private int int_heightRowTbListQuestion;

    private JLabel lb_titleFrame;
    private int int_widthLbTitleFrame;
    private int int_heightLbTitleFrame;

    private frameDetailQuestion fDQ_detaulQuestion;

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    private int indexType(String typeQues) {
        int index = 0;
        if (typeQues.equalsIgnoreCase("options"))
            return 0;
        else if (typeQues.equalsIgnoreCase("yes/no"))
            return 1;

        return -1;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == tb_listQuestion) {
            ArrayList<Question> arrL_detailQuestion = mnQ_questions.getQuestion(
                    tb_listQuestion.getModel().getValueAt(tb_listQuestion.getSelectedRow(), 0).toString());
            fDQ_detaulQuestion = new frameDetailQuestion(arrL_detailQuestion.get(0).getOptionsQuestion());

            fDQ_detaulQuestion.getPanelDetailQuestion().getLevel()
                    .setSelectedItem(arrL_detailQuestion.get(0).getLevelQuestion());

            fDQ_detaulQuestion
                    .getPanelDetailQuestion()
                    .getContent()
                    .setText(arrL_detailQuestion.get(0).getContentQuestion());

            fDQ_detaulQuestion.getPanelDetailQuestion().getSubject()
                    .setSelectedItem(arrL_detailQuestion.get(0).getSubjectQuestion());

            fDQ_detaulQuestion.getPanelDetailQuestion().getLecture()
                    .setText(arrL_detailQuestion.get(0).getLectureQuestion());
            fDQ_detaulQuestion.getPanelDetailQuestion().getLecture().setEditable(false);

            fDQ_detaulQuestion.getPanelDetailQuestion().getType()
                    .setSelectedIndex(indexType(arrL_detailQuestion.get(0).getTypeQuestion()));

            fDQ_detaulQuestion.getPanelDetailQuestion().getAnswer()
                    .setSelectedItem(arrL_detailQuestion.get(0).getAnswerQuestion());

            if (indexType(arrL_detailQuestion.get(0).getTypeQuestion()) == 0)
                fDQ_detaulQuestion.getPanelDetailQuestion().getOptions()
                        .setText(arrL_detailQuestion.get(0).getOptionsQuestion());

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        frameListQuestion a = new frameListQuestion();
    }
}
