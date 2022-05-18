package GUI;

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
        posInScreen.FULL(pDQ_detailQuestion, this);
        pDQ_detailQuestion.getType().addActionListener(this);

        add(pDQ_detailQuestion);
        setVisible(true);
    }

    private void remove(Component... co) {
        for (Component c : co)
            pDQ_detailQuestion.remove(c);
    }

    @Override
    public void parameter() {
        // TODO Auto-generated method stub
        setLayout(null);
        setSize(parameterScreen.SCREEN_WIDTH * 60 / 100, parameterScreen.SCREEN_HEIGHT * 72 / 100);
        posInScreen.CENTER(this);
    }

    public paneDetailQuestion getPanelDetailQuestion() {
        return pDQ_detailQuestion;
    }

    private paneDetailQuestion pDQ_detailQuestion;
    private String options;

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
