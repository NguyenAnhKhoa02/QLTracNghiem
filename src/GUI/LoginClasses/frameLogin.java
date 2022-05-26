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

import BLL.manageStudentLogin;


public class frameLogin extends JFrame implements ActionListener {

    private JTextField tf_inputName;
    private JPasswordField pf_inputPassword;
    private JCheckBox showPassword;
    private JLabel	lb_User, lb_Password;
    private JLabel lb_nameTitle;
    private static JButton buttonLogin;
    private JButton buttonExit;
    private manageStudentLogin msl_manageStudentLogin;
    private frameStudents fs_frameStudent;

    public frameLogin() {
        setTitle("Đăng nhập");
        addControls();
        addActionEvent();

    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        if(evt.getSource() == buttonLogin){
            msl_manageStudentLogin = new manageStudentLogin();
            String Id = msl_manageStudentLogin.isLogin(tf_inputName.getText(), pf_inputPassword.getText());
            if(Id != null){
                dispose();
                fs_frameStudent = new frameStudents(Id);
                fs_frameStudent.showWindow();
            }else if(tf_inputName.getText().equals("") && pf_inputPassword.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Vui lòng điền thông tin đăng nhập!", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
                tf_inputName.requestFocus();
            }
            else{
                JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
                tf_inputName.requestFocus();
            }
        }
        
        if (evt.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                pf_inputPassword.setEchoChar((char)0);
            } else {
                pf_inputPassword.setEchoChar('*');
            }
        }   
        
        if(evt.getSource() == buttonExit){
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc thoát ra?", "Xác nhận"
            , JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                System.exit(0); //Nếu chọn YES
            }
        }
    }

    public void addActionEvent(){
        buttonLogin.addActionListener(this);
        showPassword.addActionListener(this);
        buttonExit.addActionListener(this);
    }

    public void addControls(){

        JPanel jpTitle = new JPanel();
    //    jpTitle.setBorder(BorderFactory.createLineBorder(Color.red)); //Tạo viền bảng
        jpTitle.add(lb_nameTitle = new JLabel("ĐĂNG NHẬP", JLabel.CENTER));
        lb_nameTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lb_nameTitle.setForeground(Color.BLUE);
        add(lb_nameTitle, BorderLayout.NORTH); //Căn tiêu đề phía trên cùng (NORTH : Phía bắc)

        JPanel jpLeft = new JPanel();
    //    jpLeft.setBorder(BorderFactory.createLineBorder(Color.red));
        ImageIcon imageLeft = new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\QLTracNghiem\\src\\images\\account.png");
        jpLeft.add(new JLabel(imageLeft));
        add(jpLeft, BorderLayout.WEST); //Căn ảnh bên trái (WEST: phía Tây)

        JPanel jpBottom = new JPanel();
        jpBottom.setBorder(BorderFactory.createLineBorder(Color.gray));
        buttonLogin = new JButton("Đăng nhập");
        buttonExit = new JButton("Thoát");
        ImageIcon imageLogin = new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\QLTracNghiem\\src\\images\\login.png");
        ImageIcon imageExit = new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\QLTracNghiem\\src\\images\\exit.png");
        buttonLogin.setIcon(imageLogin);
        buttonExit.setIcon(imageExit);
        buttonLogin.setFont(new Font("Times New Roman",	Font.BOLD,	12));
        buttonExit.setFont(new Font("Times New Roman",	Font.BOLD,	12));
        buttonLogin.setMnemonic(KeyEvent.VK_ENTER); //Nhấn phím Alt + Enter để đăng nhập thay cho thao tác Click chuột
        jpBottom.add(buttonLogin);
        jpBottom.add(buttonExit);
        add(jpBottom, BorderLayout.SOUTH);

        JPanel jpCenter = new JPanel();
        jpCenter.add(lb_User=new JLabel("Tên đăng nhập"));
        lb_User.setFont(new Font("Times New Roman",	Font.BOLD,	14));
        jpCenter.add(tf_inputName=new JTextField(20));
        jpCenter.add(lb_Password=new JLabel("Mật khẩu"));
        lb_Password.setFont(new Font("Times New Roman", Font.BOLD, 14));
        jpCenter.add(pf_inputPassword=new JPasswordField(20));
        jpCenter.add(showPassword = new JCheckBox("Hiển thị mật khẩu"));
        showPassword.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lb_Password.setPreferredSize(lb_User.getPreferredSize());
        add(jpCenter,BorderLayout.CENTER);
    }
    
    public void showWindow(){
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false); //Không thể thay đổi kích cỡ Frame
    }
    
      public static void main(String[] args) {
          frameLogin f = new frameLogin();
          f.showWindow();
      }
}
