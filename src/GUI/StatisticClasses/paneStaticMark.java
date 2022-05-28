package GUI.StatisticClasses;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Painter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BLL.manageExamPaper;
import BLL.manageTimeExam;
import GUI.CommonClasses.Parameter;

public class paneStaticMark extends JPanel implements Parameter, ActionListener {
    public paneStaticMark(int parentWidth, int parentHeight) {
        int_widthPane = parentWidth;
        int_heightPane = parentHeight;
        mep_manageExamPaper = new manageExamPaper();
        mte_manageTimeExam = new manageTimeExam();

        parameter();

        makingTimeExam();
        makingTable();
    }

    private void makingTable() {
        String[] column = { "MSSV", "Họ và tên", "Môn 1", "Môn 2", "Môn 3", "Trung bình", "Xếp loại" };
        String[][] data = mep_manageExamPaper.getArrStastic(jcb_Semester.getSelectedItem().toString());
        tb_statistic = new JTable(data, column);
        tb_statistic.setSize(int_widthPane, int_heightPane);
        scroll = new JScrollPane(tb_statistic);
        scroll.setSize(int_widthPane, int_heightPane);
        posInScreen.CUSTOM_WITH_PERCENT(scroll, 0, 10);
        add(scroll);
    }

    private void makingTimeExam() {
        jcb_NameExam = new JComboBox<>(mte_manageTimeExam.getTimeExam());
        jcb_NameExam.setSize(int_widthJcbNameExam, int_heightJcbNameExam);
        posInScreen.CUSTOM_CHILD_PARENT(jcb_NameExam, this, 5, 5);
        add(jcb_NameExam);

        String[] strArr_Semester = { "HKI", "HKII" };
        jcb_Semester = new JComboBox<>(strArr_Semester);
        jcb_Semester.setSelectedIndex(1);
        jcb_Semester.setSize(int_widthJcbSemester, int_heightJcbSemester);
        posInScreen.CUSTOM_CHILD_PARENT(jcb_Semester, this, 63, 5);
        add(jcb_Semester);
        jcb_Semester.addActionListener(this);
    }

    @Override
    public void parameter() {
        // TODO Auto-generated method stub
        setSize(int_widthPane, int_heightPane);
        setLayout(null);

        int_widthJcbNameExam = 250;
        int_heightJcbNameExam = 25;

        int_widthJcbSemester = 100;
        int_heightJcbSemester = 25;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == jcb_Semester) {
            remove(scroll);
            repaint();
            makingTable();
        }

    }

    private int int_widthPane;
    private int int_heightPane;

    private JTable tb_statistic;
    private JScrollPane scroll;
    private manageExamPaper mep_manageExamPaper;

    private JComboBox<String> jcb_NameExam;
    private int int_widthJcbNameExam;
    private int int_heightJcbNameExam;

    private JComboBox<String> jcb_Semester;
    private int int_widthJcbSemester;
    private int int_heightJcbSemester;

    private manageTimeExam mte_manageTimeExam;
}
