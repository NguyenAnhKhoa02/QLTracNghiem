package GUI;

import javax.swing.JFrame;

import GUI.CommonClasses.*;

public class frameListQuestion extends JFrame implements Parameter {
    public frameListQuestion() {
        parameter();
    }

    @Override
    public void parameter() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        frameListQuestion a = new frameListQuestion();
    }
}
