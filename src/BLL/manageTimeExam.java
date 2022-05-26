package BLL;

import BLL.DTO.TimeExamDTO;
import BLL.TimeExam.TimeExam;
import DAL.connectSQL;
import DAL.manageTimeExamDAL;

public class manageTimeExam {
    public manageTimeExam() {
        mted_manageTimeExamDAL = new manageTimeExamDAL();
        ted_timeExamDTO = new TimeExamDTO(mted_manageTimeExamDAL.getTimeExam());
    }

    public TimeExam getTimeExamObject() {
        return mted_manageTimeExamDAL.getTimeExam().get(0);
    }

    public String[] getTimeExam() {
        return ted_timeExamDTO.getStrArrTimeExam();
    }

    public String[] getTime() {
        return ted_timeExamDTO.getIntArrTime();
    }

    public String[] getTimeName() {
        return ted_timeExamDTO.getStrArrTimeName();
    }

    private TimeExamDTO ted_timeExamDTO;
    private manageTimeExamDAL mted_manageTimeExamDAL;
}
