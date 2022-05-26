package BLL;

import BLL.Lecture.Lecture;
import DAL.connectSQL;

public class manageLecture {
    public manageLecture() {
        connectSQL = new connectSQL();
    }

    public String getNameLectureById(String Id) {
        return connectSQL.getNameLectureById(Id);
    }

    public Lecture getLectureById(String Id){
        return connectSQL.getLectureById(Id);
    }

    private connectSQL connectSQL;
}
