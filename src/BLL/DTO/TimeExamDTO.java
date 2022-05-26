package BLL.DTO;

import java.util.ArrayList;

import BLL.TimeExam.TimeExam;

public class TimeExamDTO {
    public TimeExamDTO(ArrayList<TimeExam> timeExams) {
        arL_timeExam = timeExams;
    }

    public String[] getStrArrTimeExam() {
        String[] strArr_timeExam = new String[arL_timeExam.size()];
        int index = 0;
        for (TimeExam str : arL_timeExam) {
            strArr_timeExam[index] = str.getNameExam();
            index++;
        }

        return strArr_timeExam;
    }

    public String[] getStrArrTimeName() {
        String[] strArr_timeExam = new String[arL_timeExam.size()];
        int index = 0;
        for (TimeExam str : arL_timeExam) {
            strArr_timeExam[index] = str.getTimeName();
            index++;
        }

        return strArr_timeExam;
    }

    public String[] getIntArrTime() {
        String[] intArr_Time = new String[arL_timeExam.size()];
        int index = 0;
        for (TimeExam timeExam : arL_timeExam) {
            intArr_Time[index] = Integer.toString(timeExam.getTimeTest());
            index++;
        }

        return intArr_Time;
    }

    private ArrayList<TimeExam> arL_timeExam;
}
