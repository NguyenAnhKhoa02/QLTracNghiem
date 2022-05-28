package DAL;

import java.lang.reflect.Array;
import java.util.ArrayList;

import BLL.Exam.DetailExam;
import BLL.Exam.Exam;
import BLL.Subject.Subject;
import BLL.TimeExam.TimeExam;

public class manageExamDAL extends connectSQL {
    public manageExamDAL() {

    }

    public void saveExam(String IdExam, ArrayList<DetailExam> detailExams, String TimeName, String Semester) {
        connetToSQL();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("insert into Exam (Id,TimeName,Semester) values (" + IdExam + ",N'" + TimeName
                    + "',N'" + Semester + "')");

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
            saveExam(exam.getID(), exam.getDetailExams(), exam.getTimeExam().getNameExam(),
                    exam.getTimeExam().getTimeName());
        }
    }

    public ArrayList<String> getAllExamBySubject(String subject) {
        String sql = "select distinct e.Id " + "from Exam e, DetailExam d, Questions q, Subjects s "
                + "where e.Id = d.Id and d.IdQues = q.Id and q.IdSubject = s.Id and s.Id = '" + subject
                + "' and e.Semester = 'HKII'";
        connetToSQL();
        ArrayList<String> arL_tempt = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
                arL_tempt.add(resultSet.getString(1));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        closeConnectSQL();

        return arL_tempt;
    }

    public ArrayList<String> getAllIdExamBySubject(String subject) {
        String str_query = "select distinct e.Id " + "from DetailExam de, Questions qs, Exam e, Subjects sb "
                + "where de.Id = e.Id and qs.Id = de.IdQues and qs.IdSubject = sb.Id and sb.Id='" + subject.toString()
                + "'";

        connetToSQL();
        ArrayList<String> arL_tempt = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_query);

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

    public ArrayList<String> getAllIdExamDisplay() {
        ArrayList<String> arrayList = new ArrayList<>();
        TimeExam timeExam = null;
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Id from Exam where Semester = 'HKII'");

            while (resultSet.next()) {
                arrayList.add(resultSet.getString(1));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        closeConnectSQL();
        return arrayList;
    }
}
