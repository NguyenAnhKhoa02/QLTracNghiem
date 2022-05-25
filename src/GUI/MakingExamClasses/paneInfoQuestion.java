package GUI.MakingExamClasses;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import BLL.manageQuestion;
import GUI.CommonClasses.Parameter;

public class paneInfoQuestion extends JLayeredPane implements Parameter {
    paneInfoQuestion(manageQuestion manageQuestion) {
        this.mnQ_manageQuestion = manageQuestion;
        parameter();
        makingTitle();
        makingInfo();
        makingButton();
    }

    private void makingTitle() {
        jlb_Title = new JLabel("THÔNG TIN CÂU HỎI", JLabel.CENTER);
        jlb_Title.setFont(new Font("Time new roman", Font.BOLD, 15));
        jlb_Title.setSize(int_widthJlbTitle, int_heightJlbTitle);
        posInScreen.CUSTOM_CHILD_PARENT(jlb_Title, this, 0, 0);
        this.add(jlb_Title);
    }

    private void makingInfo() {
        String str_tempt;
        str_tempt = "Nhận biết: " + mnQ_manageQuestion.getCountQuestions("Nhận biết");
        jlb_Level1 = new JLabel(str_tempt);
        jlb_Level1.setSize(int_widthJlbLevel, int_heightJlbLevel);
        posInScreen.CUSTOM_CHILD_PARENT(jlb_Level1, this, 5, 15);
        add(jlb_Level1);

        str_tempt = "Thông hiểu: " + mnQ_manageQuestion.getCountQuestions("Thông hiểu");
        jlb_Level2 = new JLabel(str_tempt);
        jlb_Level2.setSize(int_widthJlbLevel, int_heightJlbLevel);
        posInScreen.CUSTOM_CHILD_PARENT(jlb_Level2, this, 5, 35);
        add(jlb_Level2);

        str_tempt = "Vận dụng thấp: " + mnQ_manageQuestion.getCountQuestions("Vận dụng thấp");
        jlb_Level3 = new JLabel(str_tempt);
        jlb_Level3.setSize(int_widthJlbLevel, int_heightJlbLevel);
        posInScreen.CUSTOM_CHILD_PARENT(jlb_Level3, this, 5, 55);
        add(jlb_Level3);

        str_tempt = "Vận dụng cao: " + mnQ_manageQuestion.getCountQuestions("Vận dụng cao");
        jlb_Level4 = new JLabel(str_tempt);
        jlb_Level4.setSize(int_widthJlbLevel, int_heightJlbLevel);
        posInScreen.CUSTOM_CHILD_PARENT(jlb_Level4, this, 5, 75);
        add(jlb_Level4);
    }

    private void makingButton() {
        btn_recommendPane = new JButton("Xem gợi ý");
        btn_recommendPane.setSize(int_widthBtnRecommendPane, int_heightBtnRecommendPane);
        posInScreen.CUSTOM_CHILD_PARENT(btn_recommendPane, this, 0, 87);
        btn_recommendPane.setFocusable(false);
        add(btn_recommendPane);
    }

    public JButton getButton() {
        return btn_recommendPane;
    }

    public boolean isCountQuestion(int numberQues, int... rateLV) {
        if (rateLV[0] * numberQues / 100 > mnQ_manageQuestion.getCountQuestions("Nhận biết"))
            return false;

        if (rateLV[1] * numberQues / 100 > mnQ_manageQuestion.getCountQuestions("Thông hiểu"))
            return false;

        if (rateLV[2] * numberQues / 100 > mnQ_manageQuestion.getCountQuestions("Vận dụng thấp"))
            return false;

        if (rateLV[3] * numberQues / 100 > mnQ_manageQuestion.getCountQuestions("Vận dụng cao"))
            return false;

        return true;
    }

    @Override
    public void parameter() {
        // TODO Auto-generated method stub
        setOpaque(true);
        setBackground(Color.WHITE);

        int_heightPane = 200;
        int_widthPane = 200;

        setSize(int_widthPane, int_heightPane);

        int_heightJlbTitle = 25;
        int_widthJlbTitle = this.getWidth();

        int_widthJlbLevel = 150;
        int_heightJlbLevel = 25;

        int_widthBtnRecommendPane = this.getWidth();
        int_heightBtnRecommendPane = 25;
    }

    public class recommendPanel extends JLayeredPane implements Parameter {
        public recommendPanel() {
            parameter();
            disPlayNumberRecommend();
            displayPercentRecommend();
            makingButton();
        }

        @Override
        public void parameter() {
            int_widthRecommendPane = 200;
            int_heightRecommendPane = 200;
            this.setSize(int_widthRecommendPane, int_heightRecommendPane);
            this.setOpaque(true);
            this.setBackground(Color.WHITE);

            int_widthLbMaxQuestions = 200;
            int_heightLbMaxQuestion = 25;

            int_widthLbPercentLevel = 200;
            int_heightLbPercentLevel = 25;

            int_widthBtnAddRecommend = this.getWidth();
            int_heightBtnAddRecommend = 25;
        }

        private void disPlayNumberRecommend() {
            int_numberQuestion = mnQ_manageQuestion.getAllCountQuestions();
            int_numberQuestion = int_numberQuestion - int_numberQuestion % 10;
            lb_maxQuestions = new JLabel("Số câu hỏi: " + int_numberQuestion);
            lb_maxQuestions.setSize(int_widthLbMaxQuestions, int_heightLbMaxQuestion);
            posInScreen.CUSTOM_CHILD_PARENT(lb_maxQuestions, this, 0, 0);
            add(lb_maxQuestions);
        }

        private void displayPercentRecommend() {
            int_percentLevel1 = (int) ((float) mnQ_manageQuestion.getCountQuestions("Nhận biết")
                    / (float) int_numberQuestion * 100);
            lb_PercentLevel1 = new JLabel("Nhận biết: " + int_percentLevel1 + "%");
            lb_PercentLevel1.setSize(int_widthLbPercentLevel, int_heightLbPercentLevel);
            posInScreen.CUSTOM_CHILD_PARENT(lb_PercentLevel1, this, 0, 10);
            add(lb_PercentLevel1);

            int_percentLevel2 = (int) ((float) mnQ_manageQuestion.getCountQuestions("Thông hiểu")
                    / (float) int_numberQuestion * 100);
            lb_PercentLevel2 = new JLabel("Thông hiểu: " + int_percentLevel2 + "%");
            lb_PercentLevel2.setSize(int_widthLbPercentLevel, int_heightLbPercentLevel);
            posInScreen.CUSTOM_CHILD_PARENT(lb_PercentLevel2, this, 0, 30);
            add(lb_PercentLevel2);

            int_percentLevel3 = (int) ((float) mnQ_manageQuestion.getCountQuestions("Vận dụng thấp")
                    / (float) int_numberQuestion * 100);
            lb_PercentLevel3 = new JLabel("Vận dụng thấp: " + int_percentLevel3 + "%");
            lb_PercentLevel3.setSize(int_widthLbPercentLevel, int_heightLbPercentLevel);
            posInScreen.CUSTOM_CHILD_PARENT(lb_PercentLevel3, this, 0, 50);
            add(lb_PercentLevel3);

            int_percentLevel4 = 100 - int_percentLevel1 - int_percentLevel2 - int_percentLevel3;
            lb_PercentLevel4 = new JLabel("Vận dụng cao: " + int_percentLevel4 + "%");
            lb_PercentLevel4.setSize(int_widthLbPercentLevel, int_heightLbPercentLevel);
            posInScreen.CUSTOM_CHILD_PARENT(lb_PercentLevel4, this, 0, 70);
            add(lb_PercentLevel4);
        }

        private void makingButton() {
            btn_addRecommend = new JButton("Áp dụng gợi ý");
            btn_addRecommend.setSize(int_widthBtnAddRecommend, int_heightBtnAddRecommend);
            btn_addRecommend.setFocusable(false);
            posInScreen.BOTTOM_CENTER_CHILD_PARENT(btn_addRecommend, this);
            add(btn_addRecommend);
        }

        public JButton getButtonRecommend() {
            return btn_addRecommend;
        }

        public int getRateLevel1() {
            return int_percentLevel1;
        }

        public int getRateLevel2() {
            return int_percentLevel2;
        }

        public int getRateLevel3() {
            return int_percentLevel3;
        }

        public int getRateLevel4() {
            return int_percentLevel4;
        }

        public int getNumberQuestion() {
            return int_numberQuestion;
        }

        private int int_widthRecommendPane;
        private int int_heightRecommendPane;

        private JLabel lb_maxQuestions;
        private int int_widthLbMaxQuestions;
        private int int_heightLbMaxQuestion;

        private int int_widthLbPercentLevel;
        private int int_heightLbPercentLevel;
        private JLabel lb_PercentLevel1, lb_PercentLevel2, lb_PercentLevel3, lb_PercentLevel4;

        private JButton btn_addRecommend;
        private int int_widthBtnAddRecommend;
        private int int_heightBtnAddRecommend;

        private int int_percentLevel1, int_percentLevel2, int_percentLevel3, int_percentLevel4;
        private int int_numberQuestion;
    }

    private int int_widthPane;
    private int int_heightPane;

    private JLabel jlb_Title;
    private int int_widthJlbTitle;
    private int int_heightJlbTitle;

    private int int_widthJlbLevel;
    private int int_heightJlbLevel;
    private JLabel jlb_Level1, jlb_Level2, jlb_Level3, jlb_Level4;

    private JButton btn_recommendPane;
    private int int_widthBtnRecommendPane;
    private int int_heightBtnRecommendPane;

    private manageQuestion mnQ_manageQuestion;
}
