package GUI.MakingExamClasses;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BLL.manageQuestion;
import BLL.manageSubject;
import BLL.Exam.DetailExam;
import BLL.Exam.Exam;
import BLL.Question.OptionsQuestion;
import BLL.Question.YesNoQuestion;
import BLL.Subject.Subject;
import GUI.CommonClasses.Parameter;

public class paneExam extends JPanel implements Parameter {
    public paneExam(Exam Exams) {
        parameter();
        exam = Exams;

        devidePage();
    }

    @Override
    public void parameter() {
        // TODO Auto-generated method stub
        setLayout(null);

        int_widthExam = 559;
        int_heightExam = 1100;

        setSize(int_widthExam, int_heightExam);
        setBackground(Color.BLACK);

        int_widthLbTimeExam = 250;
        int_heightLbTimeExam = 25;

        int_widthLbNameExam = 100;
        int_heightLbNameExam = 25;

        int_widthLbTimeTest = 150;
        int_heightLbTimeTest = 25;

        int_widthLbSubject = 200;
        int_heightLbSubject = 25;

        int_widthLbCredit = 200;
        int_heightLbCredit = 25;

        int_widthLbIdExam = 200;
        int_heightLIdbExam = 25;

        int_widthLbDetailExam = this.getWidth();
        int_heightLbDetailExam = 50;

        int_widthLbOptionsExam = this.getWidth();
        int_heightLbOptionsExam = 70;

        int_widthPage = 559;
        int_heightPage = 1000;

        int_widthBtnPage = 50;
        int_heightBtnPage = 25;

        int_widthBtnSave = 100;
        int_heightBtnSave = 25;
    }

    public void displayPageExam(int index, boolean save) {
        removeAll();

        displayInfoExam();
        displaySubject();
        displayDetailExam(index);
        displayButton();

        if (!save)
            displaySaveButton();

        this.repaint();
    }

    private void devidePage() {
        int page = exam.getDetailExams().size() / 10;
        if (page == 0)
            page = 1;
        pn_page = new JPanel[page];
        btn_Page = new JButton[page];
        for (int i_page = 0; i_page < page; i_page++) {
            pn_page[i_page] = new JPanel();
            pn_page[i_page].setSize(int_widthPage, int_heightPage);
            pn_page[i_page].setOpaque(true);
            pn_page[i_page].setBackground(Color.white);
            pn_page[i_page].setLayout(null);

            btn_Page[i_page] = new JButton(String.valueOf(i_page + 1));
            btn_Page[i_page].setSize(int_widthBtnPage, int_heightBtnPage);
        }
    }

    private void displayInfoExam() {
        lb_timeExam = new JLabel(exam.getTimeExam().getNameExam());
        lb_timeExam.setSize(int_widthLbTimeExam, int_heightLbTimeExam);
        posInScreen.CUSTOM_CHILD_PARENT(lb_timeExam, this, 1, 1);
        this.add(lb_timeExam);

        lb_nameExam = new JLabel("HỌC KÌ: " + exam.getTimeExam().getTimeName());
        lb_nameExam.setSize(int_widthLbNameExam, int_heightLbNameExam);
        posInScreen.CUSTOM_CHILD_PARENT(lb_nameExam, this, 45, 1);
        this.add(lb_nameExam);

        lb_timeTest = new JLabel("THỜI GIAN: " + exam.getTimeExam().getTimeTest() + " PHÚT");
        lb_timeTest.setSize(int_widthLbTimeTest, int_heightLbTimeTest);
        posInScreen.CUSTOM_CHILD_PARENT(lb_timeTest, this, 70, 1);
        this.add(lb_timeTest);

        lb_idExam = new JLabel("Mã đề: " + exam.getID());
        lb_idExam.setSize(int_widthLbIdExam, int_heightLIdbExam);
        posInScreen.CUSTOM_CHILD_PARENT(lb_idExam, this, 1, 7);
        this.add(lb_idExam);
    }

    private void displaySubject() {
        manageSubject mns_manageSubject = new manageSubject();
        Subject sj_subjecTest = mns_manageSubject.getSubjectByQuestion(exam.getDetailExams().get(0).getIdQuestion());
        lb_Subject = new JLabel("Môn: " + sj_subjecTest.getName());
        lb_Subject.setSize(int_widthLbSubject, int_heightLbSubject);
        posInScreen.CUSTOM_CHILD_PARENT(lb_Subject, this, 10, 5);
        this.add(lb_Subject);

        lb_Credit = new JLabel("Số tín chỉ: " + String.valueOf(sj_subjecTest.getCredit()));
        lb_Credit.setSize(int_widthLbCredit, int_heightLbCredit);
        posInScreen.CUSTOM_CHILD_PARENT(lb_Credit, this, 55, 5);
        this.add(lb_Credit);
    }

    private String convertToHTML(String options) {
        String str_tempt = "<html>";
        for (String str : options.split("\n")) {
            str_tempt += str + "<br/>";
        }
        str_tempt += "</html>";

        return str_tempt;
    }

    private void displayDetailExam(int index) {
        manageQuestion mnQ_manageQuestion = new manageQuestion();

        int i_end = index * 10 + 1;
        int i_start = i_end - 10 - 1;

        if (i_end > exam.getDetailExams().size()) {
            i_end = exam.getDetailExams().size();
        }

        lb_contentExam = new JLabel[i_end];
        lb_optionsExam = new JLabel[i_end];
        int y = 0;
        for (; i_start < i_end; i_start++) {
            y += 9;

            OptionsQuestion opq_optionQuestion = mnQ_manageQuestion
                    .getOptionsQuestionById(exam.getDetailExams().get(i_start).getIdQuestion());
            YesNoQuestion ynq_yesNoQuestion = mnQ_manageQuestion
                    .getYesNoQuestionById(exam.getDetailExams().get(i_start).getIdQuestion());
            if (opq_optionQuestion != null) {
                lb_contentExam[i_start] = new JLabel(i_start + 1 + "." + opq_optionQuestion.getContentQuestion());
                lb_optionsExam[i_start] = new JLabel(convertToHTML(opq_optionQuestion.getOptionsQuestion()));

                lb_contentExam[i_start].setSize(int_widthLbDetailExam, int_heightLbDetailExam);
                lb_optionsExam[i_start].setSize(int_widthLbOptionsExam, int_heightLbOptionsExam);

                posInScreen.CUSTOM_CHILD_PARENT(lb_contentExam[i_start], pn_page[index - 1], 0, y);
                posInScreen.PARENT_CHILD_VERTICAL(lb_optionsExam[i_start], lb_contentExam[i_start], 100);
            }

            if (ynq_yesNoQuestion != null) {
                lb_contentExam[i_start] = new JLabel(i_start + 1 + "." + ynq_yesNoQuestion.getContentQuestion());
                lb_optionsExam[i_start] = new JLabel(convertToHTML(ynq_yesNoQuestion.getOptionsQuestion()));

                lb_contentExam[i_start].setSize(int_widthLbDetailExam, int_heightLbDetailExam);
                lb_optionsExam[i_start].setSize(int_widthLbOptionsExam, int_heightLbOptionsExam);

                posInScreen.CUSTOM_CHILD_PARENT(lb_contentExam[i_start], pn_page[index - 1], 0, y);
                posInScreen.PARENT_CHILD_VERTICAL(lb_optionsExam[i_start], lb_contentExam[i_start], 80);
            }
            // System.out.println(pn_page[index - 1]);
            pn_page[index - 1].add(lb_contentExam[i_start]);
            pn_page[index - 1].add(lb_optionsExam[i_start]);
        }

        add(pn_page[index - 1]);
        posInScreen.CUSTOM_CHILD_PARENT(pn_page[index - 1], this, 0, 0);
    }

    private void displayButton() {
        posInScreen.CENTER_CUSTOM_Y_CHILDREN_PARENT_ARRAY_COMPONENT(btn_Page, this, 10, 92);

        for (JButton btn : btn_Page) {
            add(btn);
        }
    }

    private void displaySaveButton() {
        btn_Save = new JButton("Save");
        btn_Save.setSize(int_widthBtnSave, int_heightBtnSave);
        posInScreen.BOTTOM_CENTER_CHILD_PARENT(btn_Save, this);
        add(btn_Save);
    }

    public JButton getSaveButton() {
        return btn_Save;
    }

    public JButton[] getAllButton() {
        return btn_Page;
    }

    public String getIdExam() {
        return exam.getID();
    }

    public ArrayList<DetailExam> getDeatilExam() {
        return exam.getDetailExams();
    }

    public String getIdTimeName() {
        return exam.getTimeExam().getNameExam();
    }

    public class chechBoxAnswer extends JPanel implements Parameter {
        public chechBoxAnswer() {
            parameter();
            displayQuestionNumber();
        }

        private int getNumberOptions(String Idques) {
            if (new manageQuestion().getTypeById(Idques).equalsIgnoreCase("Options"))
                return 4;
            return 2;
        }

        private void displayQuestionNumber() {
            int lenght = exam.getDetailExams().size();
            lb_questionNumber = new JLabel[lenght];
            int y = 6;
            for (int i = 0; i < lb_questionNumber.length; i++) {
                lb_questionNumber[i] = new JLabel(String.valueOf(i + 1) + ":");
                lb_questionNumber[i].setSize(int_widthLbQuestionNumber, int_heightLbQuestionNumber);
                posInScreen.CUSTOM_CHILD_PARENT(lb_questionNumber[i], this, 1, y);
                add(lb_questionNumber[i]);
                y += 2;
            }

            int x = 18;
            for (JLabel lb : lb_optionsAnswer) {
                lb.setSize(int_widthLbOptionAnswer, int_heightLbOptionAnswer);
                posInScreen.CUSTOM_CHILD_PARENT(lb, this, x, 3);
                add(lb);
                x += 15;
            }

            cb_optionAnswer = new Checkbox[lenght][lb_optionsAnswer.length];
            cbg_checkBoxGroup = new CheckboxGroup[lenght];
            y = 6;
            for (int i = 0; i < lenght; i++) {
                x = 18;
                cb_optionAnswer[i] = new Checkbox[getNumberOptions(exam.getDetailExams().get(i).getIdQuestion())];
                cbg_checkBoxGroup[i] = new CheckboxGroup();
                int index = 0;
                for (Checkbox c : cb_optionAnswer[i]) {
                    c = new Checkbox("", cbg_checkBoxGroup[i], false);
                    c.setSize(int_widthCbOptionAnswer, int_heightCbOptionAnswer);
                    c.setBackground(Color.white);
                    posInScreen.CUSTOM_CHILD_PARENT(c, this, x, y);
                    add(c);
                    cb_optionAnswer[i][index] = c;
                    x += 15;
                    index++;
                }
                y += 2;
            }
        }

        public Checkbox[][] getAllCheckBox() {
            return cb_optionAnswer;
        }

        @Override
        public void parameter() {
            // TODO Auto-generated method stub
            int_widthPane = 200;
            int_heightPane = parameterScreen.SCREEN_HEIGHT;
            setSize(int_widthPane, int_heightPane);
            setLayout(null);
            setOpaque(true);
            setBackground(Color.white);

            int_widthLbQuestionNumber = 20;
            int_heightLbQuestionNumber = 25;

            int_widthLbOptionAnswer = 25;
            int_heightLbOptionAnswer = 25;

            int_widthCbOptionAnswer = 20;
            int_heightCbOptionAnswer = 20;
        }

        int int_widthPane;
        int int_heightPane;

        private JLabel[] lb_questionNumber;
        private int int_widthLbQuestionNumber;
        private int int_heightLbQuestionNumber;

        private JLabel[] lb_optionsAnswer = { new JLabel("A"), new JLabel("B"), new JLabel("C"), new JLabel("D") };
        private int int_widthLbOptionAnswer;
        private int int_heightLbOptionAnswer;

        private Checkbox[][] cb_optionAnswer;
        private int int_widthCbOptionAnswer;
        private int int_heightCbOptionAnswer;

        private CheckboxGroup[] cbg_checkBoxGroup;
    }

    private Exam exam;
    private int int_widthExam;
    private int int_heightExam;

    private JLabel lb_timeExam;
    private int int_widthLbTimeExam;
    private int int_heightLbTimeExam;

    private JLabel lb_nameExam;
    private int int_widthLbNameExam;
    private int int_heightLbNameExam;

    private JLabel lb_timeTest;
    private int int_widthLbTimeTest;
    private int int_heightLbTimeTest;

    private JLabel lb_Subject;
    private int int_widthLbSubject;
    private int int_heightLbSubject;

    private JLabel lb_Credit;
    private int int_widthLbCredit;
    private int int_heightLbCredit;

    private JLabel lb_idExam;
    private int int_widthLbIdExam;
    private int int_heightLIdbExam;

    private JLabel[] lb_contentExam;
    private JLabel[] lb_optionsExam;
    private int int_widthLbDetailExam;
    private int int_heightLbDetailExam;
    private int int_widthLbOptionsExam;
    private int int_heightLbOptionsExam;

    private JPanel[] pn_page;
    private int int_widthPage;
    private int int_heightPage;

    private JButton[] btn_Page;
    private int int_widthBtnPage;
    private int int_heightBtnPage;

    private JButton btn_Save;
    private int int_widthBtnSave;
    private int int_heightBtnSave;
}
