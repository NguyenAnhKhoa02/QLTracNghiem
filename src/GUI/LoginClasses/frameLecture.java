package GUI.LoginClasses;

import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import BLL.manageExam;
import BLL.manageLecture;
import BLL.Lecture.Lecture;
import GUI.frameListQuestion;
import GUI.ExamPaperClasses.frameExamPaper;
import GUI.MakingExamClasses.frameMakingExam;
import GUI.StatisticClasses.FrameStatistic;

public class frameLecture extends JFrame implements ActionListener, WindowListener {

    private JLabel lb_LectureTitle, lb_LectureId, lb_LectureFullName, lb_LecturePosition, lb_LectureIdField;
    private JLabel lb_Images;
    private JTextField jt_LectureId, jt_LectureFullName, jt_LecturePosition, jt_LectureIdField;
    private JButton buttonManageQ, buttonLogOut, buttonMakingExam, buttonViewExamPaper, buttonStatisitc;
    private Lecture lecture;
    private manageLecture ms_managelecture;
    private frameListQuestion flq_frameListQuestion;
    private frameMakingExam fme_frameMakingExam;
    private frameExamPaper fep_frameExamPaper;
    private FrameStatistic fs_frameStatistic;

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == buttonManageQ) {
            setVisible(false);
            flq_frameListQuestion = new frameListQuestion(lecture.getStr_Id());
            flq_frameListQuestion.addWindowListener(this);
        }

        if (lecture.getStr_Position().equals("Trưởng khoa")) {
            if (evt.getSource() == buttonMakingExam) {
                setVisible(false);
                fme_frameMakingExam = new frameMakingExam();
                fme_frameMakingExam.addWindowListener(this);
            }

            if (evt.getSource() == buttonViewExamPaper) {
                fep_frameExamPaper = new frameExamPaper();
                if (fep_frameExamPaper.getExam() != null) {
                    setVisible(false);
                    fep_frameExamPaper.addWindowListener(this);
                } else {
                    JOptionPane.showMessageDialog(this, "Không có đề thi nào được tạo", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if (evt.getSource() == buttonStatisitc) {
                fs_frameStatistic = new FrameStatistic();
                fs_frameStatistic.addWindowListener(this);
            }
        }

        if (evt.getSource() == buttonLogOut) {
            dispose();
            new frameLogin().showWindow();
        }
    }

    public frameLecture(Lecture lecture) {
        super("Thông tin");
        ms_managelecture = new manageLecture();
        this.lecture = lecture;

        addControls();
        displayInfolecture();
        addActionEvent();
    }

    public void addActionEvent() {
        buttonManageQ.addActionListener(this);
        buttonLogOut.addActionListener(this);
    }

    private void disableTetField(JTextField... co) {
        for (JTextField c : co) {
            c.setEditable(false);
        }
    }

    private void displayInfolecture() {

        JPanel jp_LectureInfor = new JPanel();
        jp_LectureInfor.setBackground(Color.white);

        jp_LectureInfor.add(lb_LectureId = new JLabel("Mã giảng viên: "));
        lb_LectureId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        jt_LectureId = new JTextField(20);
        jt_LectureId.setText(lecture.getStr_Id());
        jp_LectureInfor.add(jt_LectureId);

        jp_LectureInfor.add(lb_LectureFullName = new JLabel("Họ tên: "));
        lb_LectureFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        jt_LectureFullName = new JTextField(20);
        jt_LectureFullName.setText(lecture.getStr_Name());
        jp_LectureInfor.add(jt_LectureFullName);

        jp_LectureInfor.add(lb_LecturePosition = new JLabel("Chức vụ: "));
        lb_LecturePosition.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        jt_LecturePosition = new JTextField(20);
        jt_LecturePosition.setText(lecture.getStr_Position());
        jp_LectureInfor.add(jt_LecturePosition);

        jp_LectureInfor.add(lb_LectureIdField = new JLabel("Khoa: "));
        lb_LectureIdField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        jt_LectureIdField = new JTextField(20);
        jt_LectureIdField.setText(lecture.getStr_Filed());
        jp_LectureInfor.add(jt_LectureIdField);

        lb_LectureFullName.setPreferredSize(lb_LectureId.getPreferredSize());
        lb_LecturePosition.setPreferredSize(lb_LectureId.getPreferredSize());
        lb_LectureIdField.setPreferredSize(lb_LectureId.getPreferredSize());
        add(jp_LectureInfor, BorderLayout.CENTER);

        disableTetField(jt_LectureId, jt_LectureFullName, jt_LecturePosition, jt_LectureIdField);
    }

    private void addControls() {
        JPanel jp_LectureTitle = new JPanel();
        jp_LectureTitle.setBackground(Color.white);
        jp_LectureTitle.add(lb_LectureTitle = new JLabel("THÔNG TIN GIẢNG VIÊN", JLabel.CENTER));
        lb_LectureTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lb_LectureTitle.setForeground(Color.BLUE);
        add(jp_LectureTitle, BorderLayout.NORTH);

        JPanel jp_Images = new JPanel();
        jp_Images.setBackground(Color.white);
        ImageIcon img_Infor = new ImageIcon(
                "..\\QLTracNghiem\\src\\images\\lectureinfor.png");
        jp_Images.add(lb_Images = new JLabel(img_Infor));
        add(jp_Images, BorderLayout.WEST);

        JPanel jp_DoTest = new JPanel();
        jp_DoTest.setBackground(Color.white);

        if (lecture.getStr_Position().equals("Trưởng khoa")) {
            buttonMakingExam = new JButton("Ra đề thi");
            ImageIcon img_MakingExam = new ImageIcon(
                    "..\\QLTracNghiem\\src\\images\\makingexam.png");

            buttonMakingExam.setIcon(img_MakingExam);
            buttonMakingExam.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            buttonMakingExam.addActionListener(this);
            jp_DoTest.add(buttonMakingExam);

            buttonViewExamPaper = new JButton("Xem đề thi");
            ImageIcon img_ViewTest = new ImageIcon(
                    "..\\QLTracNghiem\\src\\images\\viewtest.png");

            buttonViewExamPaper.setIcon(img_ViewTest);
            buttonViewExamPaper.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            buttonViewExamPaper.addActionListener(this);
            jp_DoTest.add(buttonViewExamPaper);

            buttonStatisitc = new JButton("Thống kê");
            ImageIcon img_Statisitc = new ImageIcon(
                    "..\\QLTracNghiem\\src\\images\\statisitc.png");

            buttonStatisitc.setIcon(img_Statisitc);
            buttonStatisitc.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            buttonStatisitc.addActionListener(this);
            jp_DoTest.add(buttonStatisitc);
        }

        buttonLogOut = new JButton("Đăng xuất");
        buttonManageQ = new JButton("Quản lý câu hỏi");
        ImageIcon img_DoTest = new ImageIcon(
                "..\\QLTracNghiem\\src\\images\\managequestion.png");
        ImageIcon img_LogOut = new ImageIcon(
                "..\\QLTracNghiem\\src\\images\\logout.png");
        buttonLogOut.setIcon(img_LogOut);
        buttonManageQ.setIcon(img_DoTest);
        buttonLogOut.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        buttonManageQ.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        jp_DoTest.add(buttonManageQ);
        jp_DoTest.add(buttonLogOut);
        add(jp_DoTest, BorderLayout.SOUTH);
    }

    public void showWindow() {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(620, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false); // Không thể thay đổi kích cỡ Frame

    }
    // public static void main(String[] args) {
    // frameLecture f = new frameLecture("1");
    // f.showWindow();
    // }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == flq_frameListQuestion) {
            setVisible(true);
        }

        if (e.getSource() == fme_frameMakingExam) {
            setVisible(true);
        }

        if (e.getSource() == fep_frameExamPaper) {
            setVisible(true);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }
}
