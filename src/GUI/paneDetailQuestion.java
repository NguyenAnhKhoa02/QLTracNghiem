package GUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import GUI.CommonClasses.Parameter;

public class paneDetailQuestion extends JPanel implements Parameter {

    public paneDetailQuestion() {
        parameter();
        makingId();
        makingLevel();
        makingSubject();
        makingLecture();
        makingType();
        makingAnswer(OPTIONS_QUESTION);
        makingContent();
        makingOptions(OPTIONS_QUESTION, "");

    }

    private void makingId() {
        tf_Id = new JTextField();
        tf_Id.setSize(int_widthTfId, int_heightTfId);
        tf_Id.setBackground(color);
        posInScreen.CUSTOM_WITH_PERCENT(tf_Id, 2, 2);

        lb_titleId = new JLabel();
        lb_titleId.setSize(int_widthLbId, int_heightLbId);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_titleId, tf_Id);

        addComponentToPanel(tf_Id, lb_titleId);
        // tf_Id.setVisible(false);
        // lb_titleId.setVisible(false);
    }

    private void makingLevel() {

        String[] strArr_level = { "Nhận biết", "Thông hiểu", "Vận dụng thấp", "Vận dụng cao" };

        cb_Level = new JComboBox<>(strArr_level);
        cb_Level.setSize(int_widthCbLevel, int_heightCbLevel);
        cb_Level.setBackground(color);
        posInScreen.CUSTOM_WITH_PERCENT(cb_Level, 5, 2);

        lb_titleLevel = new JLabel("Mức độ");
        lb_titleLevel.setSize(int_widthLbTitleLevel, int_heightLbTitleLevel);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_titleLevel, cb_Level);

        addComponentToPanel(cb_Level, lb_titleLevel);
    }

    private void makingSubject() {
        String[] strArr_Subject = { "Lập trình java", "Lập trình python", "Hệ điều hành mở nguồn mở" };

        cb_Subject = new JComboBox<>(strArr_Subject);
        cb_Subject.setSize(int_widthCbSubject, int_heightCbSubject);
        cb_Subject.setBackground(color);
        posInScreen.CUSTOM_WITH_PERCENT(cb_Subject, 20, 2);

        lb_titleSubject = new JLabel("Môn học");
        lb_titleSubject.setSize(int_widthLbTitleSubject, int_heightLbTitleSubject);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_titleSubject, cb_Subject);

        addComponentToPanel(cb_Subject, lb_titleSubject);
    }

    private void makingLecture() {
        tf_Lecture = new JTextField();
        tf_Lecture.setSize(int_widthTfLecture, int_heightTfLecture);
        tf_Lecture.setBackground(color);
        posInScreen.CUSTOM_WITH_PERCENT(tf_Lecture, 46, 2);

        lb_titleLecture = new JLabel("Giảng viên");
        lb_titleLecture.setSize(int_widthLbLecture, int_heightLbLecture);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_titleLecture, tf_Lecture);

        addComponentToPanel(tf_Lecture, lb_titleLecture);
    }

    private void makingType() {
        String[] strArr_Type = { "Câu hỏi chọn lựa", "Câu hỏi đúng sai" };

        cb_Type = new JComboBox<>(strArr_Type);
        cb_Type.setSize(int_widthCbType, int_heightCbType);
        cb_Type.setBackground(color);
        posInScreen.CUSTOM_WITH_PERCENT(cb_Type, 10, 10);

        lb_titleType = new JLabel("Loại");
        lb_titleType.setSize(int_widthLbTitleType, int_heightLbTitleType);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_titleType, cb_Type);

        addComponentToPanel(cb_Type, lb_titleType);
    }

    private void makingAnswer(String typeQuestion) {
        String[] strArr_options = { "" };
        if (typeQuestion.equals("options")) {
            strArr_options = new String[4];
            strArr_options[0] = "a";
            strArr_options[1] = "b";
            strArr_options[2] = "c";
            strArr_options[3] = "d";
        }

        if (typeQuestion.equals("yes/no")) {
            strArr_options = new String[2];
            strArr_options[0] = "a";
            strArr_options[1] = "b";
        }

        cb_Answer = new JComboBox<>(strArr_options);
        cb_Answer.setSize(int_widthCbAnswer, int_heightCbAnswer);
        cb_Answer.setBackground(color);
        posInScreen.CUSTOM_WITH_PERCENT(cb_Answer, 40, 10);

        lb_titleAnswer = new JLabel("Đáp án");
        lb_titleAnswer.setSize(int_widthLbTitleAnswer, int_heightLbTitleAnswer);
        posInScreen.PARENT_CHILD_HORIZONTAL(lb_titleAnswer, cb_Answer);

        addComponentToPanel(cb_Answer, lb_titleAnswer);
    }

    private void makingContent() {
        ta_Content = new JTextArea();
        ta_Content.setSize(int_widthTaContent, int_heightTaContent);
        posInScreen.CUSTOM_WITH_PERCENT(ta_Content, 1, 20);

        lb_titleContent = new JLabel("Câu hỏi");
        lb_titleContent.setSize(int_widthLbTitleContent, int_heightLbTitleContent);
        posInScreen.PARENT_CHILD_VERTICAL(lb_titleContent, ta_Content);

        addComponentToPanel(ta_Content, lb_titleContent);
    }

    private void makingOptions(String typeQuestion, String options) {
        ta_Options = new JTextArea();
        ta_Options.setSize(int_widthTaOptions, int_heightTaOptions);
        posInScreen.CUSTOM_WITH_PERCENT(ta_Options, 1, 40);
        if (options == null)
            options = "";

        if (typeQuestion.equals("yes/no")) {
            String s = "a.Đúng\nb.Sai";
            ta_Options.setText(s);
            ta_Options.setEditable(false);
        } else {
            if (options.equals(""))
                ta_Options.setText("a.\nb.\nc.\nd.");
            else
                ta_Options.setText(options);

            ta_Options.setEditable(true);
        }

        lb_titleOptions = new JLabel("Lựa chọn");
        lb_titleOptions.setSize(int_widthLbTitleOptions, int_heightLbTitleOptions);
        posInScreen.PARENT_CHILD_VERTICAL(lb_titleOptions, ta_Options);

        addComponentToPanel(ta_Options, lb_titleOptions);
    }

    private void addComponentToPanel(Component... co) {
        for (Component c : co) {
            add(c);
        }
    }

    // ********************************************************************
    public JTextField getId() {
        return tf_Id;
    }

    public JComboBox<String> getLevel() {
        return cb_Level;
    }

    public JComboBox<String> getSubject() {
        return cb_Subject;
    }

    public JTextField getLecture() {
        return tf_Lecture;
    }

    public JComboBox<String> getType() {
        return cb_Type;
    }

    public JComboBox<String> getAnswer() {
        return cb_Answer;
    }

    public JTextArea getContent() {
        return ta_Content;
    }

    public JTextArea getOptions() {
        return ta_Options;
    }

    public void changeStatus(String typeQuestion, String options, String Answer) {
        makingAnswer(typeQuestion);
        makingOptions(typeQuestion, options);
        getAnswer().setSelectedItem(Answer);
    }

    @Override
    public void parameter() {
        setLayout(null);

        int_widthTfId = 0;
        int_heightTfId = 0;

        int_widthLbId = 0;
        int_heightLbId = 0;

        int_widthCbLevel = 110;
        int_heightCbLevel = 25;

        int_widthLbTitleLevel = 50;
        int_heightLbTitleLevel = 25;

        int_widthCbSubject = 250;
        int_heightCbSubject = 25;

        int_widthLbTitleSubject = 60;
        int_heightLbTitleSubject = 25;

        int_widthTfLecture = 150;
        int_heightTfLecture = 25;

        int_widthLbLecture = 50;
        int_heightLbLecture = 25;

        int_widthLbLecture = 70;
        int_heightLbTitleLevel = 25;

        int_widthCbType = 150;
        int_heightCbType = 25;

        int_widthLbTitleType = 35;
        int_heightLbTitleType = 25;

        int_widthCbAnswer = 70;
        int_heightCbAnswer = 25;

        int_widthLbTitleAnswer = 50;
        int_heightLbTitleAnswer = 25;

        int_widthTaContent = parameterScreen.SCREEN_WIDTH * 57 / 100;
        int_heightTaContent = 100;

        int_widthLbTitleContent = 50;
        int_heightLbTitleContent = 25;

        int_widthTaOptions = parameterScreen.SCREEN_WIDTH * 57 / 100;
        int_heightTaOptions = 150;

        int_widthLbTitleOptions = 60;
        int_heightLbTitleOptions = 25;

        color = Color.WHITE;
    }

    private JLabel lb_titleId;
    private JTextField tf_Id;
    private int int_widthTfId;
    private int int_heightTfId;
    private int int_widthLbId;
    private int int_heightLbId;

    private JLabel lb_titleLevel;
    private JComboBox<String> cb_Level;
    private int int_widthCbLevel;
    private int int_heightCbLevel;
    private int int_widthLbTitleLevel;
    private int int_heightLbTitleLevel;

    private JLabel lb_titleSubject;
    private JComboBox<String> cb_Subject;
    private int int_widthCbSubject;
    private int int_heightCbSubject;
    private int int_widthLbTitleSubject;
    private int int_heightLbTitleSubject;

    private JLabel lb_titleLecture;
    private JTextField tf_Lecture;
    private int int_widthTfLecture;
    private int int_heightTfLecture;
    private int int_widthLbLecture;
    private int int_heightLbLecture;

    private JLabel lb_titleType;
    private JComboBox<String> cb_Type;
    private int int_widthCbType;
    private int int_heightCbType;
    private int int_widthLbTitleType;
    private int int_heightLbTitleType;

    private JLabel lb_titleAnswer;
    private JComboBox<String> cb_Answer;
    private int int_widthCbAnswer;
    private int int_heightCbAnswer;
    private int int_widthLbTitleAnswer;
    private int int_heightLbTitleAnswer;

    private JLabel lb_titleContent;
    private JTextArea ta_Content;
    private int int_widthTaContent;
    private int int_heightTaContent;
    private int int_heightLbTitleContent;
    private int int_widthLbTitleContent;

    private JLabel lb_titleOptions;
    private JTextArea ta_Options;
    private int int_widthTaOptions;
    private int int_heightTaOptions;
    private int int_heightLbTitleOptions;
    private int int_widthLbTitleOptions;

    private Color color;

    public static String OPTIONS_QUESTION = "options";
    public static String YESNO_QUESTION = "yes/no";

}
