package GUI;

import javax.lang.model.util.ElementScanner14;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import BLL.manageQuestion;
import BLL.Question.OptionsQuestion;
import BLL.Question.Question;
import BLL.Question.YesNoQuestion;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import GUI.CommonClasses.Parameter;

public class frameDetailQuestion extends JFrame implements Parameter, ActionListener {

    public frameDetailQuestion() {
        initialize();
    }

    public frameDetailQuestion(String options, String Answer) {
        initialize();
        this.options = options;
        this.answer = Answer;
        if (options.equalsIgnoreCase("a.Đúng\nb.Sai"))
            this.options = "";
    }

    private void initialize() {
        parameter();

        pDQ_detailQuestion = new paneDetailQuestion();
        pDQ_detailQuestion.setSize(int_widthPaneDetailQuestion, int_heightPaneDetailQuestion);
        posInScreen.CUSTOM_WITH_PERCENT(pDQ_detailQuestion, 0, 0);
        pDQ_detailQuestion.getType().addActionListener(this);
        pDQ_detailQuestion.getAnswer().addActionListener(this);
        add(pDQ_detailQuestion);

        buttonController();

        setVisible(true);
    }

    private void buttonController() {
        btn_Controller = new JButton("Controller");
        btn_Controller.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btn_Controller.setSize(int_widthBtnController, int_heightBtnController);
        btn_Controller.setFocusable(false);
        posInScreen.CUSTOM_CHILD_PARENT(btn_Controller, this, 45, 85);
        add(btn_Controller);
    }

    private void remove(Component... co) {
        for (Component c : co)
            pDQ_detailQuestion.remove(c);
    }

    @Override
    public void parameter() {
        setLayout(null);
        setSize(parameterScreen.SCREEN_WIDTH * 60 / 100, parameterScreen.SCREEN_HEIGHT * 72 / 100);
        posInScreen.CENTER(this);

        int_widthPaneDetailQuestion = this.getWidth();
        int_heightPaneDetailQuestion = this.getHeight() * 85 / 100;

        int_widthBtnController = 100;
        int_heightBtnController = 25;

    }

    public paneDetailQuestion getPanelDetailQuestion() {
        return pDQ_detailQuestion;
    }

    // Phương thức vĩ đại
    public void updateSQL(String Id) {
        if (pDQ_detailQuestion.getType().getSelectedIndex() == initialize_type)
            isChange = false;
        else
            isChange = true;

        if (pDQ_detailQuestion.getType().getSelectedIndex() == 0) {
            optionsQuestion = new OptionsQuestion();
            optionsQuestion.setStr_Id(pDQ_detailQuestion.getId().getText());
            optionsQuestion.setStr_Level(pDQ_detailQuestion.getLevel().getSelectedIndex() + 1 + "");
            optionsQuestion.setStr_Content(pDQ_detailQuestion.getContent().getText());
            optionsQuestion.setStr_Answer(pDQ_detailQuestion.getAnswer().getSelectedItem().toString());
            if (Id == null)
                optionsQuestion.setStr_Lecture(pDQ_detailQuestion.getLecture().getText());

            else
                optionsQuestion.setStr_Lecture(Id);

            if (pDQ_detailQuestion.getSubject().getSelectedIndex() == 0) {
                optionsQuestion.setStr_Subject("LTJV");
            } else if (pDQ_detailQuestion.getSubject().getSelectedIndex() == 1) {
                optionsQuestion.setStr_Subject("LTPT");
            } else if (pDQ_detailQuestion.getSubject().getSelectedIndex() == 2) {
                optionsQuestion.setStr_Subject("MNM");
            }

            if (pDQ_detailQuestion.getType().getSelectedIndex() == 0) {
                optionsQuestion.setStr_Type("Options");
            } else {
                optionsQuestion.setStr_Type("Yes/No");
            }

            optionsQuestion.setStr_options(pDQ_detailQuestion.getOptions().getText());
            if (btn_Controller.getText().equals("Sửa")) {
                manageQuestion.updateOptionsQuestion(optionsQuestion, isChange);
            } else if (btn_Controller.getText().equals("Thêm")) {
                manageQuestion.addOptionsQuestion(optionsQuestion);
            } else {
                manageQuestion.deleteOptionsQuestion(optionsQuestion);
            }
        }
        if (pDQ_detailQuestion.getType().getSelectedIndex() == 1) {
            yesNoQuestion = new YesNoQuestion();
            yesNoQuestion.setStr_Id(pDQ_detailQuestion.getId().getText());
            yesNoQuestion.setStr_Content(pDQ_detailQuestion.getContent().getText());

            yesNoQuestion.setStr_Answer(pDQ_detailQuestion.getAnswer().getSelectedItem().toString());
            if (Id == null)
                yesNoQuestion.setStr_Lecture(pDQ_detailQuestion.getLecture().getText());
            else
                yesNoQuestion.setStr_Lecture(Id);
            if (pDQ_detailQuestion.getSubject().getSelectedIndex() == 0) {
                yesNoQuestion.setStr_Subject("LTJV");
            } else if (pDQ_detailQuestion.getSubject().getSelectedIndex() == 1) {
                yesNoQuestion.setStr_Subject("LTPT");
            } else if (pDQ_detailQuestion.getSubject().getSelectedIndex() == 2) {
                yesNoQuestion.setStr_Subject("MNM");
            }
            if (pDQ_detailQuestion.getType().getSelectedIndex() == 0) {
                yesNoQuestion.setStr_Type("Options");
            } else {
                yesNoQuestion.setStr_Type("Yes/No");
            }

            yesNoQuestion.getOptionsQuestion().toString();
            if (btn_Controller.getText().equals("Sửa")) {
                manageQuestion.updateYesNoQuestion(yesNoQuestion, isChange);
            } else if (btn_Controller.getText().equals("Thêm")) {
                manageQuestion.addYesNoQuestion(yesNoQuestion);
            } else {
                manageQuestion.deleteYesNoQuestion(yesNoQuestion);
            }
        }
    }

    public JButton getControllerButton() {
        return btn_Controller;
    }

    private paneDetailQuestion pDQ_detailQuestion;
    private int int_widthPaneDetailQuestion;
    private int int_heightPaneDetailQuestion;
    private String options;
    private String answer;

    private JButton btn_Controller;
    private int int_widthBtnController;
    private int int_heightBtnController;
    private YesNoQuestion yesNoQuestion;
    private OptionsQuestion optionsQuestion;
    private manageQuestion manageQuestion = new manageQuestion();
    private frameListQuestion frameListQuestion;
    public int initialize_type;

    private boolean isChange;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pDQ_detailQuestion.getType()) {
            if (pDQ_detailQuestion.getType().getSelectedIndex() == 0) {
                remove(pDQ_detailQuestion.getAnswer(), pDQ_detailQuestion.getOptions());
                pDQ_detailQuestion.changeStatus(paneDetailQuestion.OPTIONS_QUESTION, options, answer);
            }
            if (pDQ_detailQuestion.getType().getSelectedIndex() == 1) {
                remove(pDQ_detailQuestion.getAnswer(), pDQ_detailQuestion.getOptions());
                pDQ_detailQuestion.changeStatus(paneDetailQuestion.YESNO_QUESTION, options, answer);
            }
            pDQ_detailQuestion.repaint();
        }

    }
}
