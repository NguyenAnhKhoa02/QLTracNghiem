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
import java.awt.event.KeyListener;

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

import BLL.manageLecture;
import BLL.manageLogin;
import BLL.manageStudent;

public class frameLogin extends JFrame implements ActionListener, KeyListener {

    private JTextField tf_inputName;
    private JPasswordField pf_inputPassword;
    private JCheckBox showPassword;
    private JLabel lb_User, lb_Password;
    private JLabel lb_nameTitle;
    private static JButton buttonLogin;
    private JButton buttonExit;
    private manageLogin ml_manageLogin;
    private manageStudent ms_manageStudent;
    private manageLecture ml_manageLecture;
    private frameStudents fs_frameStudent;
    private frameLecture fl_frameLecture;

    public frameLogin() {
        setTitle("Đăng nhập");
        addControls();
        addActionEvent();

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        if (evt.getSource() == buttonLogin) {
            accessLogin();
        }

        if (evt.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                pf_inputPassword.setEchoChar((char) 0);
            } else {
                pf_inputPassword.setEchoChar('*');
            }
        }

        if (evt.getSource() == buttonExit) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc thoát ra?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0); // Nếu chọn YES
            }
        }
    }

    public void addActionEvent() {
        buttonLogin.addActionListener(this);
        showPassword.addActionListener(this);
        buttonExit.addActionListener(this);
    }

    public void addControls() {

        JPanel jpTitle = new JPanel();
        jpTitle.setBackground(Color.white);
        // jpTitle.setBorder(BorderFactory.createLineBorder(Color.red)); //Tạo viền bảng
        jpTitle.add(lb_nameTitle = new JLabel("ĐĂNG NHẬP", JLabel.CENTER));
        lb_nameTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lb_nameTitle.setForeground(Color.BLUE);
        add(jpTitle, BorderLayout.NORTH); // Căn tiêu đề phía trên cùng (NORTH : Phía bắc)

        JPanel jpLeft = new JPanel();
        jpLeft.setBackground(Color.white);
        // jpLeft.setBorder(BorderFactory.createLineBorder(Color.red));
        ImageIcon imageLeft = new ImageIcon("..\\QLTracNghiem\\src\\images\\account.png");
        jpLeft.add(new JLabel(imageLeft));
        add(jpLeft, BorderLayout.WEST); // Căn ảnh bên trái (WEST: phía Tây)

        JPanel jpBottom = new JPanel();
        jpBottom.setBackground(Color.white);
        jpBottom.setBorder(BorderFactory.createLineBorder(Color.gray));
        buttonLogin = new JButton("Đăng nhập");
        buttonExit = new JButton("Thoát");
        ImageIcon imageLogin = new ImageIcon("..\\QLTracNghiem\\src\\images\\login.png");
        ImageIcon imageExit = new ImageIcon("..\\QLTracNghiem\\src\\images\\exit.png");
        buttonLogin.setIcon(imageLogin);
        buttonExit.setIcon(imageExit);
        buttonLogin.setFont(new Font("Times New Roman", Font.BOLD, 12));
        buttonExit.setFont(new Font("Times New Roman", Font.BOLD, 12));
        // buttonLogin.setMnemonic(KeyEvent.VK_ENTER); // Nhấn phím Alt + Enter để đăng
        // nhập thay cho thao tác Click chuột
        jpBottom.add(buttonLogin);
        jpBottom.add(buttonExit);
        add(jpBottom, BorderLayout.SOUTH);

        JPanel jpCenter = new JPanel();
        jpCenter.setBackground(Color.white);
        jpCenter.add(lb_User = new JLabel("Tên đăng nhập"));
        lb_User.setFont(new Font("Times New Roman", Font.BOLD, 14));
        jpCenter.add(tf_inputName = new JTextField(20));
        tf_inputName.addKeyListener(this);
        jpCenter.add(lb_Password = new JLabel("Mật khẩu"));
        lb_Password.setFont(new Font("Times New Roman", Font.BOLD, 14));
        jpCenter.add(pf_inputPassword = new JPasswordField(20));
        pf_inputPassword.addKeyListener(this);
        jpCenter.add(showPassword = new JCheckBox("Hiển thị mật khẩu"));
        showPassword.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lb_Password.setPreferredSize(lb_User.getPreferredSize());
        add(jpCenter, BorderLayout.CENTER);
    }

    private void accessLogin() {
        ml_manageLogin = new manageLogin();
        String Id = ml_manageLogin.isLogin(tf_inputName.getText(), pf_inputPassword.getText());

        ms_manageStudent = new manageStudent();
        ml_manageLecture = new manageLecture();
        if (Id != null) {
            dispose();

            if (ms_manageStudent.getStudentById(Id) != null) {
                fs_frameStudent = new frameStudents(ms_manageStudent.getStudentById(Id));
                fs_frameStudent.showWindow();
            }

            if (ml_manageLecture.getLectureById(Id) != null) {
                fl_frameLecture = new frameLecture(ml_manageLecture.getLectureById(Id));
                fl_frameLecture.showWindow();
            }
        } else if (tf_inputName.getText().equals("") && pf_inputPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền thông tin đăng nhập!", "Lỗi đăng nhập",
                    JOptionPane.ERROR_MESSAGE);
            tf_inputName.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!", "Lỗi đăng nhập",
                    JOptionPane.ERROR_MESSAGE);
            tf_inputName.requestFocus();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyChar() == 10) {
            accessLogin();
        }
    }

    public void showWindow() {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false); // Không thể thay đổi kích cỡ Frame
    }
}
