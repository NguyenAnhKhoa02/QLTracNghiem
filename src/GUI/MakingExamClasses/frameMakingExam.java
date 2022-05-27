package GUI.MakingExamClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import BLL.manageExam;
import BLL.manageQuestion;
import BLL.manageSubject;
import BLL.manageTimeExam;
import BLL.randomQuestion;
import BLL.TimeExam.TimeExam;
import DAL.connectSQL;
import GUI.CommonClasses.Parameter;
import GUI.MakingExamClasses.paneInfoQuestion.recommendPanel;

public class frameMakingExam extends JFrame implements Parameter, ActionListener, KeyListener {
    public frameMakingExam() {
        parameter();

        mnQ_manageQuestion = new manageQuestion();

        makingRootPane();
        makingTitle();
        makingPaneInfoQuesiton();
        makingTimeExam();
        makingSubject();
        makingSetting();
        makingButton();

        setVisible(true);
    }

    private void makingRootPane() {
        jlP_root = new JLayeredPane();
        jlP_root.setPreferredSize(new Dimension(int_widthJlpRoot, int_heightJlpRoot));
        posInScreen.FULL(jlP_root, this);

        jlP_pane1 = new JLayeredPane();
        jlP_pane1.setPreferredSize(new Dimension(int_widthJlpPane1, int_heightJlpPane1));

        JScrollPane scrollPane = new JScrollPane(jlP_pane1);
        scrollPane.setPreferredSize(new Dimension(jlP_pane1.getWidth(), jlP_pane1.getHeight()));
        posInScreen.FULL(scrollPane, jlP_root);

        jlp_paneDisplay = new JLayeredPane();
        jlp_paneDisplay.setSize(int_widthJlpPaneDisplay, int_heightJlpPaneDisplay);
        posInScreen.CUSTOM_CHILD_PARENT(jlp_paneDisplay, jlP_pane1, 0, 0);
        jlP_pane1.add(jlp_paneDisplay);

        jlP_root.add(scrollPane, JLayeredPane.PALETTE_LAYER);
        add(jlP_root, JLayeredPane.DEFAULT_LAYER);
    }

    private void makingTitle() {
        lb_Title = new JLabel("TẠO ĐỀ THI", JLabel.CENTER);
        lb_Title.setSize(int_widthLbTitle, int_heightLbTitle);
        lb_Title.setFont(new Font("Time new roman", Font.BOLD, 25));
        posInScreen.CUSTOM_WITH_PERCENT(lb_Title, 0, 0);

        jlp_paneDisplay.add(lb_Title);
    }

    private void makingPaneInfoQuesiton() {
        piq_paneInfoQuestion = new paneInfoQuestion(mnQ_manageQuestion);

        posInScreen.CUSTOM_WITH_PERCENT(piq_paneInfoQuestion, 0, 0);
        jlP_pane1.add(piq_paneInfoQuestion, JLayeredPane.PALETTE_LAYER);

        rcp_recommendPanel = piq_paneInfoQuestion.new recommendPanel();
        posInScreen.CUSTOM_WITH_PERCENT(rcp_recommendPanel, 0, 28);
        jlP_pane1.add(rcp_recommendPanel, JLayeredPane.PALETTE_LAYER);
        isVisibleRecommendPanel = false;
        rcp_recommendPanel.setVisible(isVisibleRecommendPanel);

        piq_paneInfoQuestion.getButton().addActionListener(this);
        rcp_recommendPanel.getButtonRecommend().addActionListener(this);
    }

    private void makingTimeExam() {
        manageTimeExam mte_manageTimeExam = new manageTimeExam();

        jcb_TimeExam = new JComboBox<>(mte_manageTimeExam.getTimeExam());
        jcb_TimeExam.setSize(int_widthJcbTimeExam, int_heightJcbTimeExam);
        jcb_TimeExam.setBackground(Color.WHITE);
        posInScreen.CUSTOM_CHILD_PARENT(jcb_TimeExam, jlp_paneDisplay, 25, 4);
        jlp_paneDisplay.add(jcb_TimeExam);

        lb_TimeExam = new JLabel("Kỳ thi");
        lb_TimeExam.setSize(int_widthLbTimeExam, int_heightLbTimeExam);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_TimeExam, jcb_TimeExam);
        jlp_paneDisplay.add(lb_TimeExam);

        jcb_TimeName = new JComboBox<>(mte_manageTimeExam.getTimeName());
        jcb_TimeName.setSize(int_widthJcbTimeName, int_heightJcbTimeName);
        jcb_TimeName.setBackground(Color.WHITE);
        posInScreen.CUSTOM_CHILD_PARENT(jcb_TimeName, jlp_paneDisplay, 50, 4);
        jlp_paneDisplay.add(jcb_TimeName);

        lb_TimeName = new JLabel("Học kì");
        lb_TimeName.setSize(int_widthLbTimeName, int_heightLbTimeName);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_TimeName, jcb_TimeName);
        jlp_paneDisplay.add(lb_TimeName);

        jcb_Time = new JComboBox<>(mte_manageTimeExam.getTime());
        jcb_Time.setSize(int_widthJcbTime, int_heightJcbTime);
        jcb_Time.setBackground(Color.white);
        posInScreen.CUSTOM_CHILD_PARENT(jcb_Time, jlp_paneDisplay, 65, 4);
        jlp_paneDisplay.add(jcb_Time);

        lb_Time = new JLabel("Thời gian");
        lb_Time.setSize(int_widthLbTime, int_heightLbTime);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_Time, jcb_Time);
        jlp_paneDisplay.add(lb_Time);
    }

    private void makingSubject() {
        jcb_Subject = new JComboBox<>(new manageSubject().getStrArrSubject());
        jcb_Subject.setSize(int_widthJcbSubject, int_heightJcbSubject);
        posInScreen.CUSTOM_CHILD_PARENT(jcb_Subject, jlp_paneDisplay, 75, 4);
        jcb_Subject.setBackground(Color.WHITE);
        jlp_paneDisplay.add(jcb_Subject);

        lb_Subject = new JLabel("Môn");
        lb_Subject.setSize(int_widthLbSubject, int_heightLbSubject);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_Subject, jcb_Subject);
        jlp_paneDisplay.add(lb_Subject);
    }

    private void makingSetting() {
        jtf_Level1 = new JTextField();
        jtf_Level1.setSize(int_widthJtfLevel, int_heightJtfLevel);
        posInScreen.CUSTOM_CHILD_PARENT(jtf_Level1, jlp_paneDisplay, 25, 8);
        jtf_Level1.addKeyListener(this);
        jlp_paneDisplay.add(jtf_Level1);

        lb_RateLevel1 = new JLabel("Nhận biết");
        lb_RateLevel1.setSize(int_widthLbRateLevel1, int_heightLbRateLevel1);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_RateLevel1, jtf_Level1);
        jlp_paneDisplay.add(lb_RateLevel1);

        jtf_Level2 = new JTextField();
        jtf_Level2.setSize(int_widthJtfLevel, int_heightJtfLevel);
        posInScreen.CUSTOM_CHILD_PARENT(jtf_Level2, jlp_paneDisplay, 40, 8);
        jtf_Level2.addKeyListener(this);
        jlp_paneDisplay.add(jtf_Level2);

        lb_RateLevel2 = new JLabel("Thông hiểu");
        lb_RateLevel2.setSize(int_widthLbRateLevel2, int_heightLbRateLevel2);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_RateLevel2, jtf_Level2);
        jlp_paneDisplay.add(lb_RateLevel2);

        jtf_Level3 = new JTextField();
        jtf_Level3.setSize(int_widthJtfLevel, int_heightJtfLevel);
        posInScreen.CUSTOM_CHILD_PARENT(jtf_Level3, jlp_paneDisplay, 55, 8);
        jtf_Level3.addKeyListener(this);
        jlp_paneDisplay.add(jtf_Level3);

        lb_RateLevel3 = new JLabel("Vận dụng thấp");
        lb_RateLevel3.setSize(int_widthLbRateLevel3, int_heightLbRateLevel3);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_RateLevel3, jtf_Level3);
        jlp_paneDisplay.add(lb_RateLevel3);

        jtf_Level4 = new JTextField();
        jtf_Level4.setSize(int_widthJtfLevel, int_heightJtfLevel);
        posInScreen.CUSTOM_CHILD_PARENT(jtf_Level4, jlp_paneDisplay, 70, 8);
        jtf_Level4.addKeyListener(this);
        jlp_paneDisplay.add(jtf_Level4);

        lb_RateLevel4 = new JLabel("Vận dụng thấp");
        lb_RateLevel4.setSize(int_widthLbRateLevel4, int_heightLbRateLevel4);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_RateLevel4, jtf_Level4);
        jlp_paneDisplay.add(lb_RateLevel4);

        jtf_NumberQuestion = new JTextField();
        jtf_NumberQuestion.setSize(int_widthJtfNumberQuestion, int_heightJtfNumberQuestion);
        posInScreen.CUSTOM_CHILD_PARENT(jtf_NumberQuestion, jlp_paneDisplay, 80, 8);
        jtf_NumberQuestion.addKeyListener(this);
        jlp_paneDisplay.add(jtf_NumberQuestion);

        lb_NumberQuestion = new JLabel("Số câu");
        lb_NumberQuestion.setSize(int_widthLbNumberQuestion, int_heightLbNumberQuestion);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_NumberQuestion, jtf_NumberQuestion);
        jlp_paneDisplay.add(lb_NumberQuestion);

        jtf_NumberId = new JTextField();
        jtf_NumberId.setSize(int_widthJtfNumberId, int_heightJtfNumberId);
        posInScreen.CUSTOM_CHILD_PARENT(jtf_NumberId, jlp_paneDisplay, 90, 8);
        jtf_NumberId.addKeyListener(this);
        jlp_paneDisplay.add(jtf_NumberId);

        lb_NumberId = new JLabel("Số đề");
        lb_NumberId.setSize(int_widthLbNumberId, int_heightLbNumberId);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_NumberId, jtf_NumberId);
        jlp_paneDisplay.add(lb_NumberId);
    }

    private void makingButton() {
        btn_makingExam = new JButton("Tạo đề");
        btn_makingExam.setSize(int_widthBtnMakingExam, int_heightBtnMakingExam);
        btn_makingExam.setFocusable(false);
        btn_makingExam.setEnabled(false);
        posInScreen.CUSTOM_CHILD_PARENT(btn_makingExam, jlp_paneDisplay, 30, 10);
        jlp_paneDisplay.add(btn_makingExam);

        btn_makingExam.addActionListener(this);
    }

    private void panePageExam(int index) {
        pe_paneExam = new paneExam(mnE_manageExam.getAllExam().get(index - 1));
        pe_paneExam.displayPageExam(1, isSaveIdExam[int_indexIdExam]);
        posInScreen.CUSTOM_CHILD_PARENT(pe_paneExam, jlp_paneDisplay, 30, 15);
        jlp_paneDisplay.add(pe_paneExam, JLayeredPane.PALETTE_LAYER);

        for (JButton btn : pe_paneExam.getAllButton()) {
            btn.addActionListener(this);
        }

        if (pe_paneExam.getSaveButton() != null)
            pe_paneExam.getSaveButton().addActionListener(this);
    }

    private void showAllButtonIDExam() {
        if (btn_numberExam != null) {
            for (JButton btn : btn_numberExam)
                jlp_paneDisplay.remove(btn);
        }

        btn_numberExam = new JButton[mnE_manageExam.getAllExam().size()];
        for (int i = 0; i < mnE_manageExam.getAllExam().size(); i++) {
            btn_numberExam[i] = new JButton(String.valueOf(i + 1));
            btn_numberExam[i].setSize(int_widthNumberExam, int_heightNumberExam);
            btn_numberExam[i].addActionListener(this);
        }

        posInScreen.CENTER_CUSTOM_Y_CHILDREN_PARENT_ARRAY_COMPONENT(btn_numberExam, jlp_paneDisplay, 10, 13);

        for (JButton btn : btn_numberExam) {
            jlp_paneDisplay.add(btn, JLayeredPane.PALETTE_LAYER);
        }
    }

    private void showButtonSave() {
        if (btn_Save != null) {
            jlp_paneDisplay.remove(btn_Save);
        }
        btn_Save = new JButton("SaveAll");
        btn_Save.setSize(int_widthBtnSave, int_heightBtnSave);
        posInScreen.CENTER_CUSTOM_Y_CHILDREN_PARENT_ARRAY_COMPONENT(btn_Save, jlp_paneDisplay, 68);
        jlp_paneDisplay.add(btn_Save);

        btn_Save.addActionListener(this);
    }

    @Override
    public void parameter() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        int_widthFrame = parameterScreen.SCREEN_WIDTH;
        int_heightFrame = parameterScreen.SCREEN_HEIGHT;
        setSize(int_widthFrame, int_heightFrame);

        int_widthJlpRoot = this.getWidth();
        int_heightJlpRoot = this.getHeight();

        int_heightLbTitle = 30;
        int_widthLbTitle = parameterScreen.SCREEN_WIDTH;

        int_widthJlpPane1 = this.getWidth();
        int_heightJlpPane1 = this.getHeight() + 800;

        int_widthJcbTimeExam = 250;
        int_heightJcbTimeExam = 25;

        int_widthLbTimeExam = 50;
        int_heightLbTimeExam = 25;

        // int_widthPaneTimeExam = parameterScreen.SCREEN_WIDTH - 200;
        // int_heightPaneTimeExam = 50;

        int_widthJcbTime = 50;
        int_heightJcbTime = 25;

        int_widthLbTime = 70;
        int_heightLbTime = 25;

        int_widthJlpPaneDisplay = this.getWidth();
        int_heightJlpPaneDisplay = this.getHeight() * 3;

        int_widthJcbSubject = 200;
        int_heightJcbSubject = 25;

        int_widthLbSubject = 35;
        int_heightLbSubject = 25;

        int_widthJtfLevel = 50;
        int_heightJtfLevel = 25;

        int_widthLbRateLevel1 = 60;
        int_heightLbRateLevel1 = 25;

        int_widthLbRateLevel2 = 70;
        int_heightLbRateLevel2 = 25;

        int_widthLbRateLevel3 = 90;
        int_heightLbRateLevel3 = 25;

        int_widthLbRateLevel4 = 90;
        int_heightLbRateLevel4 = 25;

        int_widthJtfNumberQuestion = 25;
        int_heightJtfNumberQuestion = 25;

        int_widthLbNumberQuestion = 44;
        int_heightLbNumberQuestion = 25;

        int_heightJtfNumberId = 25;
        int_widthJtfNumberId = 25;

        int_widthLbNumberId = 44;
        int_heightLbNumberId = 25;

        int_widthBtnMakingExam = 500;
        int_heightBtnMakingExam = 50;

        int_widthJcbTimeName = 100;
        int_heightJcbTimeName = 25;

        int_widthLbTimeName = 45;
        int_heightLbTimeName = 25;

        int_widthNumberExam = 50;
        int_heightNumberExam = 25;

        int_widthBtnSave = 100;
        int_heightBtnSave = 25;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == piq_paneInfoQuestion.getButton()) {
            if (!isVisibleRecommendPanel) {
                rcp_recommendPanel.setVisible(true);
                isVisibleRecommendPanel = true;
            } else {
                rcp_recommendPanel.setVisible(false);
                isVisibleRecommendPanel = false;
            }
        }

        if (e.getSource() == rcp_recommendPanel.getButtonRecommend()) {
            jtf_Level1.setText(String.valueOf(rcp_recommendPanel.getRateLevel1()));
            int_RateLv1 = rcp_recommendPanel.getRateLevel1();

            jtf_Level2.setText(String.valueOf(rcp_recommendPanel.getRateLevel2()));
            int_RateLv2 = rcp_recommendPanel.getRateLevel2();

            jtf_Level3.setText(String.valueOf(rcp_recommendPanel.getRateLevel3()));
            int_RateLv3 = rcp_recommendPanel.getRateLevel3();

            jtf_Level4.setText(String.valueOf(rcp_recommendPanel.getRateLevel4()));
            int_RateLv4 = rcp_recommendPanel.getRateLevel4();

            jtf_NumberQuestion.setText(String.valueOf(rcp_recommendPanel.getNumberQuestion()));
            int_numberQues = rcp_recommendPanel.getNumberQuestion();
        }

        if (btn_Save != null) {
            if (e.getSource() == btn_Save) {
                mnE_manageExam.saveToSql(mnE_manageExam.getAllExam());

                for (int i = 0; i < isSaveIdExam.length; i++) {
                    isSaveIdExam[i] = true;
                }
                isSave = true;
                jlp_paneDisplay.remove(pe_paneExam);
                panePageExam(int_indexIdExam + 1);

                jlp_paneDisplay.remove(btn_Save);
                jlp_paneDisplay.repaint();

                btn_Save = null;
            }
        }

        if (e.getSource() == btn_makingExam) {
            if (piq_paneInfoQuestion.isCountQuestion(int_numberQues, int_RateLv1, int_RateLv2, int_RateLv3,
                    int_RateLv4)) {

                isSave = false;

                if (pe_paneExam != null) {
                    jlp_paneDisplay.remove(pe_paneExam);
                    for (JButton btn : btn_numberExam) {
                        jlp_paneDisplay.remove(btn);
                    }
                }

                mnE_manageExam = new manageExam();
                mnE_manageExam.makingNewExam(int_RateLv1, int_RateLv2, int_RateLv3,
                        int_RateLv4, int_numberQues,
                        int_numberId,
                        new TimeExam(jcb_TimeExam.getSelectedItem().toString(),
                                jcb_TimeName.getSelectedItem().toString(),
                                Integer.parseInt(jcb_Time.getSelectedItem().toString())));

                isSaveIdExam = new boolean[mnE_manageExam.getAllExam().size()];
                for (boolean b : isSaveIdExam) {
                    b = false;
                }

                panePageExam(1);
                showAllButtonIDExam();
                showButtonSave();
            } else {
                JOptionPane.showMessageDialog(this, "Không đủ câu hỏi thực hiện yêu cầu!!", "Thông báo",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (pe_paneExam != null) {
            for (JButton btn : pe_paneExam.getAllButton()) {
                if (e.getSource() == btn) {
                    pe_paneExam.displayPageExam(Integer.parseInt(btn.getText()),
                            isSaveIdExam[int_indexIdExam]);
                }
            }

            if (e.getSource() == pe_paneExam.getSaveButton()) {
                mnE_manageExam.saveToSql(pe_paneExam.getIdExam(), pe_paneExam.getDeatilExam(),
                        pe_paneExam.getIdTimeName());
                isSaveIdExam[int_indexIdExam] = true;
                pe_paneExam.remove(pe_paneExam.getSaveButton());
                pe_paneExam.repaint();
            }
        }

        if (btn_numberExam != null) {
            for (JButton btn : btn_numberExam) {
                if (e.getSource() == btn) {
                    int_indexIdExam = Integer.parseInt(btn.getText()) - 1;
                    jlp_paneDisplay.remove(pe_paneExam);
                    panePageExam(Integer.valueOf(btn.getText()));
                    jlp_paneDisplay.repaint();
                }
            }
        }
    }

    private boolean isNumber(int ascii) {
        if (ascii >= 48 && ascii <= 57)
            return true;
        return false;
    }

    private boolean isNumberWhenClear(String str) {
        if (str.equals(""))
            return false;

        for (int i = 0; i < str.length(); i++) {
            if ((int) str.charAt(i) <= 48 || (int) str.charAt(i) >= 57)
                return false;
        }

        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String str = "";

        if (e.getSource() == jtf_Level1) {
            if (isNumber((int) e.getKeyChar())) {
                str += jtf_Level1.getText() + e.getKeyChar();
                int_RateLv1 = Integer.parseInt(str);
            } else
                int_RateLv1 = 0;

            if ((int) e.getKeyChar() == 8) {
                if (isNumberWhenClear(jtf_Level1.getText())) {
                    int_RateLv1 = Integer.parseInt(jtf_Level1.getText());
                }
            }
        }

        if (e.getSource() == jtf_Level2) {
            if (isNumber((int) e.getKeyChar())) {
                str += jtf_Level2.getText() + e.getKeyChar();
                int_RateLv2 = Integer.parseInt(str);
            } else
                int_RateLv2 = 0;

            if ((int) e.getKeyChar() == 8) {
                if (isNumberWhenClear(jtf_Level2.getText())) {
                    int_RateLv2 = Integer.parseInt(jtf_Level2.getText());
                }
            }
        }

        if (e.getSource() == jtf_Level3) {
            if (isNumber((int) e.getKeyChar())) {
                str += jtf_Level3.getText() + e.getKeyChar();
                int_RateLv3 = Integer.parseInt(str);
            } else
                int_RateLv3 = 0;

            if ((int) e.getKeyChar() == 8) {
                if (isNumberWhenClear(jtf_Level3.getText())) {
                    int_RateLv3 = Integer.parseInt(jtf_Level3.getText());
                }
            }
        }

        if (e.getSource() == jtf_Level4) {
            if (isNumber((int) e.getKeyChar())) {
                str += jtf_Level4.getText() + e.getKeyChar();
                int_RateLv4 = Integer.parseInt(str);
            } else {
                int_RateLv4 = 0;
            }

            if ((int) e.getKeyChar() == 8) {
                if (isNumberWhenClear(jtf_Level4.getText())) {
                    int_RateLv4 = Integer.parseInt(jtf_Level4.getText());
                }
            }
        }

        if (e.getSource() == jtf_NumberQuestion) {
            if (isNumber((int) e.getKeyChar())) {
                str += jtf_NumberQuestion.getText() + e.getKeyChar();
                int_numberQues = Integer.parseInt(str);
            } else {
                int_numberQues = 0;
            }

            if ((int) e.getKeyChar() == 8) {
                if (isNumberWhenClear(jtf_NumberQuestion.getText())) {
                    int_numberQues = Integer.parseInt(jtf_NumberQuestion.getText());
                } else {
                    int_numberQues = 0;
                }
            }
        }

        if (e.getSource() == jtf_NumberId) {
            if (isNumber((int) e.getKeyChar())) {
                str += jtf_NumberId.getText() + e.getKeyChar();
                int_numberId = Integer.parseInt(str);
            } else {
                int_numberId = 0;
            }

            if ((int) e.getKeyChar() == 8) {
                if (isNumberWhenClear(jtf_NumberId.getText())) {
                    int_numberId = Integer.parseInt(jtf_NumberId.getText());
                } else {
                    int_numberId = 0;
                }
            }
        }

        int_RateLv = int_RateLv1 + int_RateLv2 + int_RateLv3 + int_RateLv4;
        if (int_RateLv == 100 && int_numberQues > 0 && int_numberId > 0) {
            btn_makingExam.setEnabled(true);
        } else
            btn_makingExam.setEnabled(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    int int_widthFrame;
    int int_heightFrame;

    private JLayeredPane jlP_root;
    private int int_widthJlpRoot;
    private int int_heightJlpRoot;

    private JLayeredPane jlP_pane1;
    private int int_widthJlpPane1;
    private int int_heightJlpPane1;

    private JLayeredPane jlp_paneDisplay;
    private int int_widthJlpPaneDisplay;
    private int int_heightJlpPaneDisplay;

    private JLabel lb_Title;
    private int int_widthLbTitle;
    private int int_heightLbTitle;

    private paneInfoQuestion piq_paneInfoQuestion;

    private recommendPanel rcp_recommendPanel;
    private boolean isVisibleRecommendPanel;

    private manageQuestion mnQ_manageQuestion;

    private JLabel lb_TimeExam;
    private JComboBox<String> jcb_TimeExam;
    private int int_widthJcbTimeExam;
    private int int_heightJcbTimeExam;
    private int int_widthLbTimeExam;
    private int int_heightLbTimeExam;

    private JLabel lb_Time;
    private JComboBox<String> jcb_Time;
    private int int_widthJcbTime;
    private int int_heightJcbTime;
    private int int_widthLbTime;
    private int int_heightLbTime;

    private JLabel lb_TimeName;
    private JComboBox<String> jcb_TimeName;
    private int int_widthJcbTimeName;
    private int int_heightJcbTimeName;
    private int int_widthLbTimeName;
    private int int_heightLbTimeName;

    private JLabel lb_Subject;
    private JComboBox<String> jcb_Subject;
    private int int_widthJcbSubject;
    private int int_heightJcbSubject;
    private int int_widthLbSubject;
    private int int_heightLbSubject;

    private int int_widthJtfLevel;
    private int int_heightJtfLevel;

    private JLabel lb_RateLevel1;
    private JTextField jtf_Level1;
    private int int_widthLbRateLevel1;
    private int int_heightLbRateLevel1;

    private JLabel lb_RateLevel2;
    private JTextField jtf_Level2;
    private int int_widthLbRateLevel2;
    private int int_heightLbRateLevel2;

    private JLabel lb_RateLevel3;
    private JTextField jtf_Level3;
    private int int_widthLbRateLevel3;
    private int int_heightLbRateLevel3;

    private JLabel lb_RateLevel4;
    private JTextField jtf_Level4;
    private int int_widthLbRateLevel4;
    private int int_heightLbRateLevel4;

    private JLabel lb_NumberQuestion;
    private JTextField jtf_NumberQuestion;
    private int int_widthJtfNumberQuestion;
    private int int_heightJtfNumberQuestion;
    private int int_widthLbNumberQuestion;
    private int int_heightLbNumberQuestion;

    private JLabel lb_NumberId;
    private JTextField jtf_NumberId;
    private int int_widthJtfNumberId;
    private int int_heightJtfNumberId;
    private int int_widthLbNumberId;
    private int int_heightLbNumberId;

    private JButton btn_makingExam;
    private int int_widthBtnMakingExam;
    private int int_heightBtnMakingExam;

    private int int_RateLv, int_RateLv1, int_RateLv2, int_RateLv3, int_RateLv4;
    private int int_numberQues;
    private int int_numberId;

    private manageExam mnE_manageExam;

    private paneExam pe_paneExam;

    private JButton[] btn_numberExam;
    private int int_widthNumberExam;
    private int int_heightNumberExam;

    private JButton btn_Save;
    private int int_widthBtnSave;
    private int int_heightBtnSave;

    private boolean isSave;
    private boolean[] isSaveIdExam;

    private int int_indexIdExam;

    // public static void main(String[] args) {
    // frameMakingExam f = new frameMakingExam();
    // }
}
