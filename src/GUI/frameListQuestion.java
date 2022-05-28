package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import BLL.manageLecture;
import BLL.manageQuestion;
import BLL.Question.Question;
import GUI.CommonClasses.*;

public class frameListQuestion extends JFrame
        implements Parameter, MouseListener, ActionListener, KeyListener, WindowListener {
    public frameListQuestion(String IdLecture) {
        parameter();
        str_idLecture = IdLecture;

        mnQ_questions = new manageQuestion();

        lb_titleFrame = new JLabel("DANH SÁCH CÂU HỎI", JLabel.CENTER);
        lb_titleFrame.setFont(new Font("Time new roman", Font.BOLD, 50));
        lb_titleFrame.setSize(int_widthLbTitleFrame, int_heightLbTitleFrame);
        posInScreen.CUSTOM_WITH_PERCENT(lb_titleFrame, 0, 0);
        add(lb_titleFrame);

        makingTable();
        displayAddButton();
        displaySearch();
        setVisible(true);
    }

    @Override
    public void parameter() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        int_heightTbListQuestion = parameterScreen.SCREEN_HEIGHT * 72 / 100;
        int_widthTbListQuestion = parameterScreen.SCREEN_WIDTH;
        int_heightRowTbListQuestion = 30;

        int_widthLbTitleFrame = parameterScreen.SCREEN_WIDTH;
        int_heightLbTitleFrame = 100;

        int_widthBtnAddQuestion = 150;
        int_heightBtnAddQuestion = 50;

        int_widthLbSearch = 75;
        int_heightLbSearch = 25;

        int_widthJtfSearch = 200;
        int_heightJtfSearch = 30;
    }

    private void setParameterTable(JTable jTable, int... width) {
        int index = 0;
        for (int w : width) {
            jTable.getColumnModel().getColumn(index).setPreferredWidth(w);
            ++index;
        }
    }

    private void makingTable() {
        data = mnQ_questions.getAllQuestions();
        String[] column = { "Id", "Mức độ", "Nội dung", "Câu trả lời", "Môn học", "Giảng viên", "Loại câu hỏi" };

        tb_listQuestion = new JTable(data, column);
        tb_listQuestion.setRowHeight(int_heightRowTbListQuestion);
        setParameterTable(tb_listQuestion, 0, 50, 500, 10, 150, 150);
        tb_listQuestion.addMouseListener(this);

        scrollPane = new JScrollPane(tb_listQuestion);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(int_widthTbListQuestion, int_heightTbListQuestion);
        posInScreen.CUSTOM_WITH_PERCENT(scrollPane, 0, 25);
        add(scrollPane);
    }

    private void displayAddButton() {
        btn_AddQuestion = new JButton("Thêm câu hỏi");
        btn_AddQuestion.setSize(int_widthBtnAddQuestion, int_heightBtnAddQuestion);
        posInScreen.CUSTOM_WITH_PERCENT(btn_AddQuestion, 87, 18);
        this.add(btn_AddQuestion);
        btn_AddQuestion.addActionListener(this);
    }

    private void displaySearch() {
        jtf_Search = new JTextField();
        jtf_Search.setSize(int_widthJtfSearch, int_heightJtfSearch);
        posInScreen.CUSTOM_WITH_PERCENT(jtf_Search, 6, 20);
        add(jtf_Search);
        jtf_Search.addKeyListener(this);

        lb_Search = new JLabel("Tìm kiếm");
        lb_Search.setFont(new Font("Time new roman", Font.BOLD, 15));
        lb_Search.setSize(int_widthLbSearch, int_heightLbSearch);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_Search, jtf_Search);
        add(lb_Search);
    }

    private void makingSearchTable(String[][] strArr_data) {
        String[] column = { "Id", "Mức độ", "Nội dung", "Câu trả lời", "Môn học", "Giảng viên", "Loại câu hỏi" };

        tb_listQuestion = new JTable(strArr_data, column);
        tb_listQuestion.setRowHeight(int_heightRowTbListQuestion);
        setParameterTable(tb_listQuestion, 0, 50, 500, 10, 150, 150);
        tb_listQuestion.addMouseListener(this);

        scrollPane = new JScrollPane(tb_listQuestion);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(int_widthTbListQuestion, int_heightTbListQuestion);
        posInScreen.CUSTOM_WITH_PERCENT(scrollPane, 0, 25);
        add(scrollPane);
    }

    private JTable tb_listQuestion;
    private manageQuestion mnQ_questions;
    private int int_widthTbListQuestion;
    private int int_heightTbListQuestion;
    private int int_heightRowTbListQuestion;

    private JLabel lb_titleFrame;
    private int int_widthLbTitleFrame;
    private int int_heightLbTitleFrame;
    private JScrollPane scrollPane;

    private String[][] data;

    private frameDetailQuestion fDQ_detaulQuestion;

    private JButton btn_AddQuestion;
    private int int_widthBtnAddQuestion;
    private int int_heightBtnAddQuestion;
    private String str_idLecture;
    private JLabel lb_Search;
    private JTextField jtf_Search;
    private int int_widthLbSearch;
    private int int_heightLbSearch;
    private int int_widthJtfSearch;
    private int int_heightJtfSearch;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    private int getIndex(String options) {
        if (options.equalsIgnoreCase("a.Đúng\nb.Sai"))
            return 1;
        else
            return 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == tb_listQuestion && SwingUtilities.isLeftMouseButton(e)) {
            int int_indexSelected = tb_listQuestion.getSelectedRow();

            fDQ_detaulQuestion = new frameDetailQuestion(data[int_indexSelected][7],
                    data[int_indexSelected][3]);
            fDQ_detaulQuestion.getPanelDetailQuestion().getId().setText(data[int_indexSelected][0]);
            fDQ_detaulQuestion.getPanelDetailQuestion().getId().setEditable(false);
            fDQ_detaulQuestion.getPanelDetailQuestion().getLevel()
                    .setSelectedItem(data[int_indexSelected][1]);

            fDQ_detaulQuestion.getPanelDetailQuestion().getContent().setText(data[int_indexSelected][2]);

            fDQ_detaulQuestion.getPanelDetailQuestion().getSubject().setSelectedItem(data[int_indexSelected][4]);

            fDQ_detaulQuestion.getPanelDetailQuestion().getLecture().setText(data[int_indexSelected][5]);
            fDQ_detaulQuestion.getPanelDetailQuestion().getLecture().setEditable(false);

            fDQ_detaulQuestion.getPanelDetailQuestion().getType()
                    .setSelectedIndex(getIndex(data[int_indexSelected][7]));
            fDQ_detaulQuestion.initialize_type = fDQ_detaulQuestion.getPanelDetailQuestion().getType()
                    .getSelectedIndex();

            fDQ_detaulQuestion.getPanelDetailQuestion().getOptions().setText(data[int_indexSelected][7]);

            fDQ_detaulQuestion.getPanelDetailQuestion().getAnswer().setSelectedItem(data[int_indexSelected][3].trim());

            fDQ_detaulQuestion.getControllerButton().setText("Sửa");
            fDQ_detaulQuestion.getControllerButton().addActionListener(this);
            fDQ_detaulQuestion.addWindowListener(this);
        }

        if (SwingUtilities.isRightMouseButton(e) && e.getSource() == tb_listQuestion) {
            tb_listQuestion.setRowSelectionInterval(tb_listQuestion.rowAtPoint(e.getPoint()),
                    tb_listQuestion.rowAtPoint(e.getPoint()));
            int int_indexSelected = tb_listQuestion.getSelectedRow();

            fDQ_detaulQuestion = new frameDetailQuestion(data[int_indexSelected][7],
                    data[int_indexSelected][3]);
            fDQ_detaulQuestion.getPanelDetailQuestion().getId().setText(data[int_indexSelected][0]);
            fDQ_detaulQuestion.getPanelDetailQuestion().getId().setEditable(false);
            fDQ_detaulQuestion.getPanelDetailQuestion().getLevel()
                    .setSelectedItem(data[int_indexSelected][1]);

            fDQ_detaulQuestion.getPanelDetailQuestion().getContent().setText(data[int_indexSelected][2]);

            fDQ_detaulQuestion.getPanelDetailQuestion().getSubject().setSelectedItem(data[int_indexSelected][4]);

            fDQ_detaulQuestion.getPanelDetailQuestion().getLecture().setText(data[int_indexSelected][5]);
            fDQ_detaulQuestion.getPanelDetailQuestion().getLecture().setEditable(false);

            fDQ_detaulQuestion.getPanelDetailQuestion().getType()
                    .setSelectedIndex(getIndex(data[int_indexSelected][7]));
            fDQ_detaulQuestion.initialize_type = fDQ_detaulQuestion.getPanelDetailQuestion().getType()
                    .getSelectedIndex();

            fDQ_detaulQuestion.getPanelDetailQuestion().getOptions().setText(data[int_indexSelected][7]);

            fDQ_detaulQuestion.getPanelDetailQuestion().getAnswer().setSelectedItem(data[int_indexSelected][3].trim());

            fDQ_detaulQuestion.getPanelDetailQuestion().getLevel().setEnabled(false);
            fDQ_detaulQuestion.getPanelDetailQuestion().getSubject().setEnabled(false);
            fDQ_detaulQuestion.getPanelDetailQuestion().getContent().setEditable(false);
            fDQ_detaulQuestion.getPanelDetailQuestion().getType().setEnabled(false);
            fDQ_detaulQuestion.getPanelDetailQuestion().getAnswer().setEnabled(false);
            fDQ_detaulQuestion.getPanelDetailQuestion().getOptions().setEditable(false);

            fDQ_detaulQuestion.getControllerButton().setText("Xóa");
            fDQ_detaulQuestion.getControllerButton().addActionListener(this);
            fDQ_detaulQuestion.addWindowListener(this);
        }
    }

    private boolean isOk() {
        if (fDQ_detaulQuestion.getPanelDetailQuestion().getType().getSelectedIndex() == 0) {
            String str_tempt = "a.\nb.\nc.\nd.";
            if (fDQ_detaulQuestion.getPanelDetailQuestion().getOptions().getText().equals(str_tempt))
                return false;
        }

        if (fDQ_detaulQuestion.getPanelDetailQuestion().getContent().getText().equals(""))
            return false;

        if (fDQ_detaulQuestion.getPanelDetailQuestion().getAnswer().getSelectedItem() == null)
            return false;

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (fDQ_detaulQuestion != null) {

            if (fDQ_detaulQuestion.getControllerButton().getText().equals("Sửa")) {
                fDQ_detaulQuestion.updateSQL(null);
                fDQ_detaulQuestion.dispose();
                fDQ_detaulQuestion.addWindowListener(this);
                getContentPane().remove(scrollPane);
                repaint();
                makingTable();
            }

            if (fDQ_detaulQuestion.getControllerButton().getText().equals("Thêm")) {
                if (isOk()) {
                    fDQ_detaulQuestion.updateSQL(str_idLecture);
                    fDQ_detaulQuestion.dispose();
                    fDQ_detaulQuestion.addWindowListener(this);
                    getContentPane().remove(scrollPane);
                    repaint();
                    makingTable();
                } else {
                    JOptionPane.showMessageDialog(fDQ_detaulQuestion, "Không đủ dữ liệu", "Thôn báo",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

            if (fDQ_detaulQuestion.getControllerButton().getText().equals("Xóa")) {
                fDQ_detaulQuestion.updateSQL(null);
                fDQ_detaulQuestion.dispose();
                fDQ_detaulQuestion.addWindowListener(this);
                getContentPane().remove(scrollPane);
                repaint();
                makingTable();
            }

        }

        if (e.getSource() == btn_AddQuestion) {
            tb_listQuestion.getSelectionModel().clearSelection();

            fDQ_detaulQuestion = new frameDetailQuestion();
            fDQ_detaulQuestion.getControllerButton().setText("Thêm");
            fDQ_detaulQuestion.getControllerButton().addActionListener(this);

            fDQ_detaulQuestion.getPanelDetailQuestion().getLecture()
                    .setText(new manageLecture().getNameLectureById(str_idLecture));
            fDQ_detaulQuestion.getPanelDetailQuestion().getLecture().setEditable(false);
            fDQ_detaulQuestion.addWindowListener(this);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    // public static void main(String[] args) {
    // frameListQuestion a = new frameListQuestion();
    // }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        String str_search = jtf_Search.getText() + e.getKeyChar();
        ArrayList<String> arL_search = new ArrayList<>();
        for (int i = 0; i < tb_listQuestion.getRowCount(); i++) {
            if ((tb_listQuestion.getValueAt(i, 2).toString().toLowerCase()).startsWith(str_search.toLowerCase())) {
                arL_search.add(tb_listQuestion.getValueAt(i, 0).toString());
            }
        }

        String[][] strArr = new String[arL_search.size()][7];
        int index = 0;
        for (String str : arL_search) {
            for (int i = 0; i < tb_listQuestion.getRowCount(); i++) {
                if (str.equalsIgnoreCase(tb_listQuestion.getValueAt(i, 0).toString())) {
                    for (int j = 0; j <= 7; j++) {
                        if (j == 7) {
                            index++;
                            break;
                        }

                        strArr[index][j] = tb_listQuestion.getValueAt(i, j).toString();
                    }
                }
            }
        }

        getContentPane().remove(scrollPane);
        repaint();
        if (arL_search.size() == 0)
            makingTable();
        else
            makingSearchTable(strArr);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == fDQ_detaulQuestion)
            fDQ_detaulQuestion = null;
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

    // public static void main(String[] args) {
    // frameListQuestion a = new frameListQuestion("1");
    // }

}
