package GUI.LoginClasses;

import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import BLL.manageStudent;
import BLL.Student.Student;
import GUI.ExamPaperClasses.frameExamPaper;

public class frameStudents extends JFrame implements ActionListener {

    private JLabel lb_StudentsTitle, lb_StudentsId, lb_StudentsFullName, lb_StudentsBirth, lb_StudentsIdField;
    private JLabel lb_Images;
    private JTextField jt_StudentsId, jt_StudentsFullName, jt_StudentsBirth, jt_StudentsIdField;
    private JButton buttonViewTest, buttonDoTest, buttonResult, buttonLogOut;
    private Student student;
    private manageStudent ms_manageStudent;
    private frameExamPaper fep_frameExamPaper;
    private frameChooseSubject fcs_frameChooseSubject;

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == buttonDoTest) {
            fcs_frameChooseSubject = new frameChooseSubject(student.getId());

            for (JButton btn : fcs_frameChooseSubject.getButtonChoose()) {
                btn.addActionListener(this);
            }
        }

        if (evt.getSource() == buttonLogOut) {
            dispose();
            new frameLogin().showWindow();
        }

        if (fcs_frameChooseSubject != null) {
            int index = 0;

            for (JButton btn : fcs_frameChooseSubject.getButtonChoose()) {
                if (evt.getSource() == btn) {
                    fep_frameExamPaper = new frameExamPaper(student.getId(),
                            fcs_frameChooseSubject.getAllSubject()[index].split("-")[0]);

                    if (fep_frameExamPaper.getExam() != null) {
                        setVisible(false);
                        fcs_frameChooseSubject.setVisible(false);
                        fcs_frameChooseSubject = null;
                        fep_frameExamPaper.addWindowListener((WindowListener) new WindowListener() {

                            @Override
                            public void windowOpened(WindowEvent e) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void windowClosing(WindowEvent e) {
                                // TODO Auto-generated method stub
                                setVisible(true);
                                fep_frameExamPaper.dispose();
                                fep_frameExamPaper = null;
                                fcs_frameChooseSubject = null;
                                // fcs_frameChooseSubject = new frameChooseSubject(student.getId());

                                // for (JButton btn : fcs_frameChooseSubject.getButtonChoose()) {
                                // btn.addActionListener((ActionListener) this);
                                // }
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

                        });
                    }

                }

                index++;
            }
        }
    }

    public frameStudents(Student student) {
        super("Th??ng tin");
        ms_manageStudent = new manageStudent();
        this.student = student;

        addControls();
        displayInfoStudent();
        addActionEvent();
    }

    public void addActionEvent() {
        buttonDoTest.addActionListener(this);
        buttonLogOut.addActionListener(this);
    }

    private void disableTetField(JTextField... co) {
        for (JTextField c : co) {
            c.setEditable(false);
        }
    }

    private void displayInfoStudent() {

        JPanel jp_StudentsInfor = new JPanel();
        jp_StudentsInfor.setBackground(Color.white);

        jp_StudentsInfor.add(lb_StudentsId = new JLabel("M?? sinh vi??n: "));
        lb_StudentsId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        jt_StudentsId = new JTextField(20);
        jt_StudentsId.setText(student.getId());
        jp_StudentsInfor.add(jt_StudentsId);

        jp_StudentsInfor.add(lb_StudentsFullName = new JLabel("H??? t??n: "));
        lb_StudentsFullName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        jt_StudentsFullName = new JTextField(20);
        jt_StudentsFullName.setText(student.getFullName());
        jp_StudentsInfor.add(jt_StudentsFullName);

        jp_StudentsInfor.add(lb_StudentsBirth = new JLabel("Ng??y sinh: "));
        lb_StudentsBirth.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        jt_StudentsBirth = new JTextField(20);
        jt_StudentsBirth.setText(student.getBirthday());
        jp_StudentsInfor.add(jt_StudentsBirth);

        jp_StudentsInfor.add(lb_StudentsIdField = new JLabel("Ng??nh h???c: "));
        lb_StudentsIdField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        jt_StudentsIdField = new JTextField(20);
        jt_StudentsIdField.setText(student.getField());
        jp_StudentsInfor.add(jt_StudentsIdField);

        lb_StudentsFullName.setPreferredSize(lb_StudentsId.getPreferredSize());
        lb_StudentsBirth.setPreferredSize(lb_StudentsId.getPreferredSize());
        lb_StudentsIdField.setPreferredSize(lb_StudentsId.getPreferredSize());
        add(jp_StudentsInfor, BorderLayout.CENTER);

        disableTetField(jt_StudentsId, jt_StudentsFullName, jt_StudentsBirth, jt_StudentsIdField);
    }

    private void addControls() {
        JPanel jp_StudentsTitle = new JPanel();
        jp_StudentsTitle.setBackground(Color.white);
        jp_StudentsTitle.add(lb_StudentsTitle = new JLabel("TH??NG TIN SINH VI??N", JLabel.CENTER));
        lb_StudentsTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lb_StudentsTitle.setForeground(Color.BLUE);
        add(jp_StudentsTitle, BorderLayout.NORTH);

        JPanel jp_Images = new JPanel();
        jp_Images.setBackground(Color.white);
        ImageIcon img_Infor = new ImageIcon("..\\QLTracNghiem\\src\\images\\studentsinfor.png");
        jp_Images.add(lb_Images = new JLabel(img_Infor));
        add(jp_Images, BorderLayout.WEST);

        JPanel jp_DoTest = new JPanel();
        jp_DoTest.setBackground(Color.white);

        buttonDoTest = new JButton("L??m b??i thi");
        buttonLogOut = new JButton("????ng xu???t");
        ImageIcon img_DoTest = new ImageIcon("..\\QLTracNghiem\\src\\images\\dotest.png");
        ImageIcon img_LogOut = new ImageIcon("..\\QLTracNghiem\\src\\images\\logout.png");
        buttonLogOut.setIcon(img_LogOut);
        buttonDoTest.setIcon(img_DoTest);
        buttonLogOut.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        buttonDoTest.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        jp_DoTest.add(buttonDoTest);
        jp_DoTest.add(buttonLogOut);
        // buttonViewTest = new JButton("Xem b??i thi");
        // buttonResult = new JButton("Xem k???t qu???");
        // ImageIcon img_ViewTest = new
        // ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\QLTracNghiem\\src\\images\\viewtest.png");
        // ImageIcon img_Result = new
        // ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\QLTracNghiem\\src\\images\\result.png");
        // buttonResult.setIcon(img_Result);
        // buttonViewTest.setFont(new Font("Times New Roman", Font.BOLD, 12));
        // buttonResult.setFont(new Font("Times New Roman", Font.BOLD, 12));
        // buttonViewTest.setIcon(img_ViewTest);
        // buttonResult.setIcon(img_Result);
        // jp_DoTest.add(buttonViewTest);
        // jp_DoTest.add(buttonResult);
        add(jp_DoTest, BorderLayout.SOUTH);
    }

    public void showWindow() {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false); // Kh??ng th??? thay ?????i k??ch c??? Frame

    }
}