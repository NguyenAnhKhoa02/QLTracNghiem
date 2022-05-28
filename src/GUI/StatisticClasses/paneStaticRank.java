package GUI.StatisticClasses;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BLL.manageExamPaper;
import BLL.manageStudent;
import BLL.manageTimeExam;
import GUI.CommonClasses.Parameter;

public class paneStaticRank extends JPanel implements Parameter, ActionListener {
    public paneStaticRank(int parentWidth, int parentHeight) {
        int_widthPanel = parentHeight;
        int_heightPanel = parentHeight;

        mep_manageExamPaper = new manageExamPaper();
        ms_manageStudent = new manageStudent();
        mte_manageTimeExam = new manageTimeExam();

        parameter();
        makingTimeExam();
        makingStatiMarkc();
    }

    private void makingStatiMarkc() {
        lb_veryGood = new JLabel("Giỏi");
        lb_veryGood.setSize(int_widthLbRank, int_heightLbRank);
        posInScreen.CUSTOM_CHILD_PARENT(lb_veryGood, this, 2, 15);
        add(lb_veryGood);

        lb_Good = new JLabel("Khá");
        lb_Good.setSize(int_widthLbRank, int_heightLbRank);
        posInScreen.PARENT_CHILD_VERTICAL(lb_Good, lb_veryGood, 90);
        add(lb_Good);

        lb_Average = new JLabel("Trung bình");
        lb_Average.setSize(int_widthLbRank, int_heightLbRank);
        posInScreen.PARENT_CHILD_VERTICAL(lb_Average, lb_Good, 90);
        add(lb_Average);

        lb_Poor = new JLabel("Yếu");
        lb_Poor.setSize(int_widthLbRank, int_heightLbRank);
        posInScreen.PARENT_CHILD_VERTICAL(lb_Poor, lb_Average, 90);
        add(lb_Poor);

        setBar();

        jpn_barVeryGood = new JPanel();
        jpn_barVeryGood.setSize(int_widthJpnBarVeryGood, int_heightJpbBar);
        jpn_barVeryGood.setBackground(Color.red);
        posInScreen.CUSTOM_CHILD_PARENT(jpn_barVeryGood, this, 23, 15);
        add(jpn_barVeryGood);

        jpn_barGood = new JPanel();
        jpn_barGood.setSize(int_widthJpnBarGood, int_heightJpbBar);
        jpn_barGood.setBackground(Color.red);
        posInScreen.CUSTOM_CHILD_PARENT(jpn_barGood, this, 23, 27);
        add(jpn_barGood);

        jpn_barAvarage = new JPanel();
        jpn_barAvarage.setSize(int_widthJpnBarAvarage, int_heightJpbBar);
        jpn_barAvarage.setBackground(Color.red);
        posInScreen.CUSTOM_CHILD_PARENT(jpn_barAvarage, this, 23, 40);
        add(jpn_barAvarage);

        jpn_barPoor = new JPanel();
        jpn_barPoor.setSize(int_widthJpnBarPoor, int_heightJpbBar);
        jpn_barPoor.setBackground(Color.red);
        posInScreen.CUSTOM_CHILD_PARENT(jpn_barPoor, this, 23, 53);
        add(jpn_barPoor);
    }

    private void setBar() {
        // mep_manageExamPaper
        ArrayList<String> arL_idStudent = ms_manageStudent.getAllIdStudent();
        int mark = 0;
        for (String str : arL_idStudent) {
            mark = mep_manageExamPaper.getAverageMark(str, jcb_Semester.getSelectedItem().toString());
            if (mark >= 8) {
                int_widthJpnBarVeryGood += mark * 7;
            } else if (mark >= 6 && mark < 8) {
                int_widthJpnBarGood += mark * 7;
            } else if (mark >= 4 && mark < 6) {
                int_widthJpnBarAvarage += mark * 7;
            } else {
                int_widthJpnBarPoor += mark * 7;
            }
        }
    }

    private void makingTimeExam() {
        jcb_NameExam = new JComboBox<>(mte_manageTimeExam.getTimeExam());
        jcb_NameExam.setSize(int_widthJcbNameExam, int_heightJcbNameExam);
        posInScreen.CUSTOM_CHILD_PARENT(jcb_NameExam, this, 3, 3);
        add(jcb_NameExam);

        String[] strArr_Semester = { "HKI", "HKII" };
        jcb_Semester = new JComboBox<>(strArr_Semester);
        jcb_Semester.setSelectedIndex(1);
        jcb_Semester.setSize(int_widthJcbSemester, int_heightJcbSemester);
        posInScreen.CUSTOM_CHILD_PARENT(jcb_Semester, this, 60, 3);
        add(jcb_Semester);
        jcb_Semester.addActionListener(this);
    }

    @Override
    public void parameter() {
        // TODO Auto-generated method stub
        setLayout(null);
        setSize(int_widthPanel, int_heightPanel);

        int_widthLbRank = 100;
        int_heightLbRank = 25;

        int_heightJpbBar = int_heightLbRank;

        int_widthJcbNameExam = 250;
        int_heightJcbNameExam = 25;

        int_widthJcbSemester = 100;
        int_heightJcbSemester = 25;
    }

    private int int_widthPanel;
    private int int_heightPanel;

    private JLabel lb_veryGood, lb_Good, lb_Average, lb_Poor;
    private JPanel jpn_barVeryGood, jpn_barGood, jpn_barAvarage, jpn_barPoor;
    private int int_widthLbRank;
    private int int_heightLbRank;
    private int int_widthJpnBarVeryGood;
    private int int_widthJpnBarGood;
    private int int_widthJpnBarAvarage;
    private int int_widthJpnBarPoor;
    private int int_heightJpbBar;

    private manageExamPaper mep_manageExamPaper;
    private manageStudent ms_manageStudent;
    private manageTimeExam mte_manageTimeExam;

    private JComboBox<String> jcb_NameExam;
    private int int_widthJcbNameExam;
    private int int_heightJcbNameExam;

    private JComboBox<String> jcb_Semester;
    private int int_widthJcbSemester;
    private int int_heightJcbSemester;

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == jcb_Semester) {
            remove(jpn_barVeryGood);
            remove(jpn_barGood);
            remove(jpn_barAvarage);
            remove(jpn_barPoor);
            remove(lb_veryGood);
            remove(lb_Good);
            remove(lb_Average);
            remove(lb_Poor);
            jpn_barVeryGood = null;
            jpn_barGood = null;
            jpn_barAvarage = null;
            jpn_barPoor = null;
            lb_Average = null;
            lb_Good = null;
            lb_Poor = null;
            lb_veryGood = null;
            int_widthJpnBarAvarage = int_widthJpnBarGood = int_widthJpnBarPoor = int_widthJpnBarVeryGood = 0;
            repaint();
            makingStatiMarkc();
        }
    }
}
