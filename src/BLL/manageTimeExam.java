package BLL;

import BLL.DTO.TimeExamDTO;
import BLL.TimeExam.TimeExam;
import DAL.connectSQL;

public class manageTimeExam {
    public manageTimeExam() {
        connectSQL = new connectSQL();
        ted_timeExamDTO = new TimeExamDTO(connectSQL.getTimeExam());
    }

    public TimeExam getTimeExamObject() {
        return connectSQL.getTimeExam().get(0);
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

    private connectSQL connectSQL;
    private TimeExamDTO ted_timeExamDTO;
}
