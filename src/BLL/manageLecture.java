package BLL;

import BLL.Lecture.Lecture;
import DAL.connectSQL;
import DAL.manageLectureDAL;

public class manageLecture {
    public manageLecture() {
        mld_manageLectureDAL = new manageLectureDAL();
    }

    public String getNameLectureById(String Id) {
        return mld_manageLectureDAL.getNameLectureById(Id);
    }

    public Lecture getLectureById(String Id) {
        return mld_manageLectureDAL.getLectureById(Id);
    }

    private manageLectureDAL mld_manageLectureDAL;
}
