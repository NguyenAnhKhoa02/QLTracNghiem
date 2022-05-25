package BLL.TimeExam;

import java.util.TimerTask;

public class TimeExam {
    public TimeExam(String IdName, String TimeName, int TimeTest) {
        str_IdName = IdName;
        str_TimeName = TimeName;
        int_TimeTest = TimeTest;
    }

    public String getNameExam() {
        return str_IdName;
    }

    public String getTimeName() {
        return str_TimeName;
    }

    public int getTimeTest() {
        return int_TimeTest;
    }

    private String str_IdName;
    private String str_TimeName;
    private int int_TimeTest;
}
