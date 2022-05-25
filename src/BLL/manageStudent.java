package BLL;

import BLL.Student.Student;
import DAL.connectSQL;

public class manageStudent {
    public manageStudent() {
        connectSQL = new connectSQL();
    }

    public Student getStudentById(String Id) {
        return connectSQL.getStudentById(Id);
    }

    private connectSQL connectSQL;
}
