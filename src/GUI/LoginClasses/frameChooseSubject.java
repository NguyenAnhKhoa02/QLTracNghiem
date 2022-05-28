package GUI.LoginClasses;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

import BLL.manageExamPaper;
import BLL.manageSubject;
import GUI.CommonClasses.Parameter;

public class frameChooseSubject extends JFrame implements Parameter {
    public frameChooseSubject(String IdStudent) {
        str_IdStudent = IdStudent;
        parameter();

        makingButtons();

        setVisible(true);
    }

    private void makingButtons() {
        ms_manageSubject = new manageSubject();
        strArr_subject = ms_manageSubject.getStrArrSubject();
        jbtn_Subject = new JButton[strArr_subject.length];

        jlb_chooseSubject = new JLabel("Chọn môn thi");
        jlb_chooseSubject.setSize(int_widthJlbChooseSubject, int_heightJlbChooseSubject);
        posInScreen.CUSTOM_CHILD_PARENT(jlb_chooseSubject, this, 40, 10);
        add(jlb_chooseSubject);

        int index = 0;
        int x = 20;
        int y = 20;
        for (JButton btn : jbtn_Subject) {
            btn = new JButton(strArr_subject[index].split("-")[1]);
            btn.setSize(int_widthJbtnSubject, int_heightJbtnSubject);
            btn.setFocusable(false);
            posInScreen.CUSTOM_CHILD_PARENT(btn, this, x, y);
            add(btn);

            if (new manageExamPaper().isDoExam(str_IdStudent, strArr_subject[index].split("-")[0])) {
                btn.setEnabled(false);
            }

            jbtn_Subject[index] = btn;

            y += 18;
            index++;
        }
    }

    public JButton[] getButtonChoose() {
        return jbtn_Subject;
    }

    public String[] getAllSubject() {
        return strArr_subject;
    }

    @Override
    public void parameter() {
        // TODO Auto-generated method stub
        int_widthFrame = 500;
        int_heightFrame = 500;
        setSize(int_widthFrame, int_heightFrame);
        posInScreen.CENTER(this);
        setLayout(null);

        int_widthJbtnSubject = 300;
        int_heightJbtnSubject = 50;

        int_widthJlbChooseSubject = 300;
        int_heightJlbChooseSubject = 50;
    }

    private int int_widthFrame;
    private int int_heightFrame;

    private String str_IdStudent;

    private manageSubject ms_manageSubject;

    private JButton[] jbtn_Subject;
    private int int_widthJbtnSubject;
    private int int_heightJbtnSubject;

    private JLabel jlb_chooseSubject;
    private int int_widthJlbChooseSubject;
    private int int_heightJlbChooseSubject;

    private String[] strArr_subject;
}
