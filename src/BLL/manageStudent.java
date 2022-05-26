package BLL;

import BLL.Student.Student;
import DAL.connectSQL;
import DAL.manageStudentDAL;

public class manageStudent {
    public manageStudent() {
        msd_manageStudentDAL = new manageStudentDAL();
    }

    public Student getStudentById(String Id) {
        return msd_manageStudentDAL.getStudentById(Id);
    }

    private manageStudentDAL msd_manageStudentDAL;
}
