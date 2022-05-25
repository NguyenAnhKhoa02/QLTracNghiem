package BLL;

import DAL.connectSQL;

public class manageLecture {
    public manageLecture() {
        connectSQL = new connectSQL();
    }

    public String getNameLectureById(String Id) {
        return connectSQL.getNameLectureById(Id);
    }

    private connectSQL connectSQL;
}
