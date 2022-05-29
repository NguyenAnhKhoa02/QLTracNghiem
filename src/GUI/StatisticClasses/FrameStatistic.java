package GUI.StatisticClasses;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import GUI.CommonClasses.Parameter;

public class FrameStatistic extends JFrame implements Parameter {
    public FrameStatistic() {
        parameter();

        makingTabbedPane();

        setVisible(true);
    }

    private void makingTabbedPane() {
        psr_paneStaticRank = new paneStaticRank(int_widthJtpTabbedPane, int_heightJtpTabbedPane);
        psm_paneStaticMark = new paneStaticMark(int_widthJtpTabbedPane, int_heightJtpTabbedPane);

        jtp_tabbedPane = new JTabbedPane();
        jtp_tabbedPane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        jtp_tabbedPane.setSize(int_widthJtpTabbedPane, int_widthJtpTabbedPane);
        posInScreen.FULL(jtp_tabbedPane, this);
        jtp_tabbedPane.add("Xếp loại", psr_paneStaticRank);
        jtp_tabbedPane.add("Điểm", psm_paneStaticMark);

        add(jtp_tabbedPane);
    }

    @Override
    public void parameter() {
        // TODO Auto-generated method stub
        int_heightFrame = 500;
        int_widthFrame = 800;
        setSize(int_widthFrame, int_heightFrame);
        posInScreen.CENTER(this);

        int_widthJtpTabbedPane = this.getWidth();
        int_heightJtpTabbedPane = this.getHeight();
    }

    private int int_widthFrame;
    private int int_heightFrame;

    private JTabbedPane jtp_tabbedPane;
    private int int_widthJtpTabbedPane;
    private int int_heightJtpTabbedPane;

    private paneStaticRank psr_paneStaticRank;
    private paneStaticMark psm_paneStaticMark;
}
