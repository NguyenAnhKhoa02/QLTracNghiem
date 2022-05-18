package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;

import BLL.manageQuestion;
import BLL.Question.Question;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import GUI.CommonClasses.Parameter;

public class frameDetailQuestion extends JFrame implements Parameter, ActionListener {

    public frameDetailQuestion() {
        initialize();
    }

    public frameDetailQuestion(String options) {
        initialize();
        this.options = options;

        if (options.equalsIgnoreCase("a.Đúng\nb.Sai\n"))
            this.options = "";
    }

    private void initialize() {
        parameter();

        pDQ_detailQuestion = new paneDetailQuestion();
        pDQ_detailQuestion.setSize(int_widthPaneDetailQuestion, int_heightPaneDetailQuestion);
        posInScreen.CUSTOM_WITH_PERCENT(pDQ_detailQuestion, 0, 0);
        pDQ_detailQuestion.getType().addActionListener(this);
        add(pDQ_detailQuestion);

        buttonController();

        setVisible(true);
    }

    private void buttonController() {
        btn_Controller = new JButton("Sửa");
        btn_Controller.setSize(int_widthBtnController, int_heightBtnController);
        btn_Controller.setFocusable(false);
        posInScreen.BOTTOM_CENTER_CHILD_PARENT(btn_Controller, this);
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
        setResizable(false);
        posInScreen.CENTER(this);

        int_widthPaneDetailQuestion = this.getWidth();
        int_heightPaneDetailQuestion = this.getHeight() * 85 / 100;

        int_widthBtnController = 100;
        int_heightBtnController = 25;

    }

    public paneDetailQuestion getPanelDetailQuestion() {
        return pDQ_detailQuestion;
    }

    private paneDetailQuestion pDQ_detailQuestion;
    private int int_widthPaneDetailQuestion;
    private int int_heightPaneDetailQuestion;
    private String options;

    private JButton btn_Controller;
    private int int_widthBtnController;
    private int int_heightBtnController;

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == pDQ_detailQuestion.getType()) {
            if (pDQ_detailQuestion.getType().getSelectedIndex() == 0) {
                remove(pDQ_detailQuestion.getAnswer(), pDQ_detailQuestion.getOptions());
                pDQ_detailQuestion.changeStatus(pDQ_detailQuestion.OPTIONS_QUESTION, options);
            }

            if (pDQ_detailQuestion.getType().getSelectedIndex() == 1) {
                remove(pDQ_detailQuestion.getAnswer(), pDQ_detailQuestion.getOptions());
                pDQ_detailQuestion.changeStatus(pDQ_detailQuestion.YESNO_QUESTION, options);
            }

            pDQ_detailQuestion.repaint();
        }
    }

}
