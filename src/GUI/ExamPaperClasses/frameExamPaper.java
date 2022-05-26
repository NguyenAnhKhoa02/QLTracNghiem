package GUI.ExamPaperClasses;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import BLL.manageDetailExamPaper;
import BLL.manageExam;
import BLL.manageExamPaper;
import BLL.manageStudent;
import BLL.Exam.Exam;
import BLL.Student.Student;
import GUI.CommonClasses.Parameter;
import GUI.MakingExamClasses.paneExam;
import GUI.MakingExamClasses.paneExam.chechBoxAnswer;

public class frameExamPaper extends JFrame implements Parameter, ActionListener, ItemListener {
    public frameExamPaper(String IdStudent) {
        parameter();

        makingRootPane();
        displayInforStudent(IdStudent);
        mne_manageExam = new manageExam();
        exam = mne_manageExam.getRandExam();

        if (exam == null)
            return;
        displayExam();
        displayCheckBoxAnswer();

        if (exam != null) {
            displayButtonAddmitExam();
            setVisible(true);
        }
    }

    public frameExamPaper() {
        parameter();
        mne_manageExam = new manageExam();
        ArrayList<String> arL_exam = mne_manageExam.getAllIdExam();

        if (arL_exam.size() == 0) {
            return;
        }
        int_heightPane1 = arL_exam.size() * 1100;
        int_heightPnDisplay = int_heightPane1;
        makingRootPane();

        int y = 0;
        int index = 0;
        peArr_paneExam = new paneExam[arL_exam.size()];
        for (String e : arL_exam) {
            exam = mne_manageExam.getExamById(e);

            peArr_paneExam[index] = new paneExam(exam);
            peArr_paneExam[index].displayPageExam(1, true);
            peArr_paneExam[index].setOpaque(true);
            peArr_paneExam[index].setBackground(Color.white);
            pn_display.add(peArr_paneExam[index]);

            for (JButton btn : peArr_paneExam[index].getAllButton())
                btn.addActionListener(this);
            posInScreen.CENTER_CUSTOM_Y_CHILDREN_PARENT_ARRAY_COMPONENT(peArr_paneExam[index], pn_display, y);

            index++;
            y += 100 / arL_exam.size();
        }

        setVisible(true);
    }

    private void makingRootPane() {
        pn_root = new JLayeredPane();
        pn_root.setPreferredSize(new Dimension(int_widthPnRoot, int_heightPnRoot));
        posInScreen.FULL(pn_root, this);

        pane1 = new JLayeredPane();
        pane1.setPreferredSize(new Dimension(int_widthPane1, int_heightPane1));

        JScrollPane scrollPane = new JScrollPane(pane1);
        scrollPane.setPreferredSize(new Dimension(int_widthPane1,
                int_heightPane1));
        posInScreen.FULL(scrollPane, pn_root);

        pn_display = new JLayeredPane();
        pn_display.setSize(int_widthPnDisplay, int_heightPnDisplay);
        posInScreen.CUSTOM_CHILD_PARENT(pn_display, pane1, 0, 0);
        pn_display.setOpaque(true);
        pn_display.setBackground(Color.white);

        pane1.add(pn_display);
        pn_root.add(scrollPane, JLayeredPane.DEFAULT_LAYER);
        add(pn_root, JLayeredPane.DEFAULT_LAYER);
    }

    private void displayInforStudent(String IdStudent) {
        mns_manageStudent = new manageStudent();
        student = mns_manageStudent.getStudentById(IdStudent);

        lb_IdStudent = new JLabel("MSSV: " + student.getId());
        lb_IdStudent.setSize(int_widthLbIdStudent, int_heightLbIdStudent);

        lb_FullNameStudent = new JLabel("Họ và tên: " + student.getFullName());
        lb_FullNameStudent.setSize(int_widthLbFullNameStudent, int_heightLbFullNameStudent);

        lb_Birthday = new JLabel("Ngày sinh: " + student.getBirthday());
        lb_Birthday.setSize(int_widthLbBirthday, int_heightLbBirthday);

        lb_Field = new JLabel("Khoa: " + student.getField());
        lb_Field.setSize(int_widthLbField, int_heightLbField);

        posInScreen.CENTER_CUSTOM_Y_CHILDREN_PARENT_ARRAY_COMPONENT(pn_display, 0, 1, lb_IdStudent, lb_FullNameStudent,
                lb_Birthday, lb_Field);
        pn_display.add(lb_IdStudent);
        pn_display.add(lb_FullNameStudent);
        pn_display.add(lb_Birthday);
        pn_display.add(lb_Field);
    }

    private void displayExam() {
        pe_paneExam = new paneExam(exam);
        pe_paneExam.displayPageExam(1, true);
        pe_paneExam.setOpaque(true);
        pe_paneExam.setBackground(Color.white);
        posInScreen.CENTER_CUSTOM_Y_CHILDREN_PARENT_ARRAY_COMPONENT(pe_paneExam, pn_display, 5);
        pn_display.add(pe_paneExam);

        for (JButton btn : pe_paneExam.getAllButton())
            btn.addActionListener(this);
    }

    private void displayCheckBoxAnswer() {
        for (JButton btn : pe_paneExam.getAllButton()) {
            btn.addActionListener(this);
        }

        cba_checkBoxAnswer = pe_paneExam.new chechBoxAnswer();
        posInScreen.CUSTOM_CHILD_PARENT(cba_checkBoxAnswer, pn_root, 1, 0);
        pn_root.add(cba_checkBoxAnswer, JLayeredPane.PALETTE_LAYER);

        int index = 0;
        for (int i = 0; i < cba_checkBoxAnswer.getAllCheckBox().length; i++) {
            for (Checkbox c : cba_checkBoxAnswer.getAllCheckBox()[i]) {
                c.addItemListener(this);
            }
        }

        hm_getAnswer = new HashMap<>();
    }

    private void displayButtonAddmitExam() {
        btn_addmitExam = new JButton("Nộp bài");
        btn_addmitExam.setSize(int_widthBtnAddmitExam, int_heightBtnAddmitExam);
        posInScreen.CUSTOM_CHILD_PARENT(btn_addmitExam, this, 3, 90);
        pn_root.add(btn_addmitExam, JLayeredPane.MODAL_LAYER);
        btn_addmitExam.setFocusable(false);
        btn_addmitExam.addActionListener(this);
    }

    private HashMap<String, String> convertAnswer() {
        HashMap<String, String> hm_convert = new HashMap<>(hm_getAnswer.size());
        for (String str : hm_getAnswer.keySet()) {
            switch (hm_getAnswer.get(str)) {
                case "1":
                    hm_convert.put(exam.getDetailExams().get(Integer.parseInt(str) - 1).getIdQuestion(), "A");
                    break;

                case "2":
                    hm_convert.put(exam.getDetailExams().get(Integer.parseInt(str) - 1).getIdQuestion(), "B");
                    break;

                case "3":
                    hm_convert.put(exam.getDetailExams().get(Integer.parseInt(str) - 1).getIdQuestion(), "C");
                    break;

                case "4":
                    hm_convert.put(exam.getDetailExams().get(Integer.parseInt(str) - 1).getIdQuestion(), "D");
                    break;

                default:
                    break;
            }
        }

        return hm_convert;
    }

    private boolean isDone() {
        String questionNotDone = null;
        for (int i = 0; i < exam.getDetailExams().size(); i++) {
            if (hm_getAnswer.get(String.valueOf(i + 1)) == null) {
                if (questionNotDone != null)
                    questionNotDone += ",";

                if (questionNotDone == null)
                    questionNotDone = "";

                questionNotDone += String.valueOf(i + 1);
            }
        }

        if (questionNotDone != null) {
            JOptionPane.showMessageDialog(this, "Câu " + questionNotDone + " chưa làm", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (hm_getAnswer.size() == 0) {
            JOptionPane.showMessageDialog(this, "Làm bài trước khi nộp", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        return true;
    }

    public Exam getExam() {
        return exam;
    }

    @Override
    public void parameter() {
        setLayout(null);
        int_widthFrame = parameterScreen.SCREEN_WIDTH;
        int_heightFrame = parameterScreen.SCREEN_HEIGHT;
        setSize(int_widthFrame, int_heightFrame);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        int_widthPnRoot = this.getWidth();
        int_heightPnRoot = this.getHeight();

        int_widthLbIdStudent = 100;
        int_heightLbIdStudent = 25;

        int_widthLbFullNameStudent = 180;
        int_heightLbFullNameStudent = 25;

        int_widthLbBirthday = 180;
        int_heightLbBirthday = 25;

        int_widthLbField = 200;
        int_heightLbField = 25;

        int_widthPane1 = this.getWidth() * 90 / 100;
        int_heightPane1 = this.getHeight() * 2;

        int_widthPnDisplay = this.getWidth();
        int_heightPnDisplay = int_heightPane1;

        int_widthBtnAddmitExam = 100;
        int_heightBtnAddmitExam = 50;

        isAdmit = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (pe_paneExam != null) {
            for (JButton btn : pe_paneExam.getAllButton()) {
                if (e.getSource() == btn) {
                    pe_paneExam.displayPageExam(Integer.parseInt(btn.getText()), true);
                }
            }
        }

        if (e.getSource() == btn_addmitExam) {
            if (isAdmit)
                JOptionPane.showMessageDialog(this, "Đã nộp bài", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            if (isDone() && !isAdmit) {
                mep_manageExamPaper = new manageExamPaper();
                mep_manageExamPaper.makingExamPaper(exam, convertAnswer(), student.getId());

                pr_paneResule = new paneResult(mep_manageExamPaper.getCurrentStringIdExamPaper());
                posInScreen.CUSTOM_CHILD_PARENT(pr_paneResule, pn_root, 75, 0);
                pn_root.add(pr_paneResule, JLayeredPane.MODAL_LAYER);

                isAdmit = true;
            }
        }

        if (peArr_paneExam != null) {
            for (paneExam p : peArr_paneExam) {
                for (JButton btn : p.getAllButton()) {
                    if (e.getSource() == btn) {
                        p.displayPageExam(Integer.valueOf(btn.getText()), true);
                    }
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        int indexSelect;
        for (int i = 0; i < cba_checkBoxAnswer.getAllCheckBox().length; i++) {
            indexSelect = 0;
            for (Checkbox c : cba_checkBoxAnswer.getAllCheckBox()[i]) {
                if (e.getSource() == c) {
                    hm_getAnswer.put(String.valueOf(i + 1), String.valueOf(indexSelect + 1));
                }
                indexSelect++;
            }
        }
    }

    private int int_widthFrame;
    private int int_heightFrame;

    private JLayeredPane pn_root, pane1, pn_display;
    private int int_widthPnRoot;
    private int int_heightPnRoot;
    private int int_widthPane1;
    private int int_heightPane1;
    private int int_widthPnDisplay;
    private int int_heightPnDisplay;

    private manageStudent mns_manageStudent;
    private Student student;

    private manageExam mne_manageExam;
    private Exam exam;

    private paneExam pe_paneExam;
    private chechBoxAnswer cba_checkBoxAnswer;

    private manageExamPaper mep_manageExamPaper;

    private JLabel lb_IdStudent, lb_FullNameStudent, lb_Birthday, lb_Field;
    private int int_widthLbIdStudent;
    private int int_heightLbIdStudent;
    private int int_widthLbFullNameStudent;
    private int int_heightLbFullNameStudent;
    private int int_widthLbBirthday;
    private int int_heightLbBirthday;
    private int int_widthLbField;
    private int int_heightLbField;

    private JButton btn_addmitExam;
    private int int_widthBtnAddmitExam;
    private int int_heightBtnAddmitExam;

    private HashMap<String, String> hm_getAnswer;
    private paneResult pr_paneResule;

    private paneExam[] peArr_paneExam;

    private boolean isAdmit;

    // public static void main(String[] args) {
    // String[] a = { "1" };
    // frameExamPaper f = new frameExamPaper(a);
    // }
}
