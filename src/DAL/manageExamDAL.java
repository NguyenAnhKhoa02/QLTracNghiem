package DAL;

import java.util.ArrayList;

import BLL.Exam.DetailExam;
import BLL.Exam.Exam;
import BLL.TimeExam.TimeExam;

public class manageExamDAL extends connectSQL {
    public manageExamDAL() {

    }

    public void saveExam(String IdExam, ArrayList<DetailExam> detailExams, String TimeName) {
        connetToSQL();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("insert into Exam (Id,TimeName) values (" + IdExam + ",N'" + TimeName + "')");

            for (DetailExam d : detailExams) {
                statement.executeUpdate("insert into DetailExam (Id,IdQues) values ('" + d.getIdExam() + "','"
                        + d.getIdQuestion() + "')");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        closeConnectSQL();
    }

    public void saveExam(ArrayList<Exam> arL_exam) {
        connetToSQL();
        for (Exam exam : arL_exam) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate("delete from DetailExam where Id = '" + exam.getID() + "'");
                statement.executeUpdate("delete from Exam where Id = '" + exam.getID() + "'");

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        closeConnectSQL();

        for (Exam exam : arL_exam) {
            saveExam(exam.getID(), exam.getDetailExams(), exam.getTimeExam().getNameExam());
        }
    }

    public ArrayList<String> getAllIdExam() {
        connetToSQL();
        ArrayList<String> arL_tempt = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Id from Exam");

            while (resultSet.next())
                arL_tempt.add(resultSet.getString(1));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        closeConnectSQL();

        return arL_tempt;
    }

    public Exam getExamById(String Id) {
        return new Exam(Id, new manageTimeExamDAL().getTimeExam().get(0),
                new manageDetailExamDAL().getAllDetailExamById(Id));
    }
}
