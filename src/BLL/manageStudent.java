package BLL;

import java.util.ArrayList;

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

    public ArrayList<String> getAllIdStudent() {
        return msd_manageStudentDAL.getAllIdStudent();
    }

    private manageStudentDAL msd_manageStudentDAL;
}
