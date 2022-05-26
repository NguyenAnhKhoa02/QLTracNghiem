package DAL;

import java.util.ArrayList;

import BLL.Subject.Subject;

public class manageSubjectDAL extends connectSQL {
    public manageSubjectDAL() {

    }

    public ArrayList<Subject> getSubject() {
        ArrayList<Subject> arL_Subject = new ArrayList<>();
        String str_query = "Select Id,NameSubject from Subjects";

        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_query);

            while (resultSet.next()) {
                arL_Subject.add(new Subject(resultSet.getString(1), resultSet.getString(2)));
            }

        } catch (Exception e) {
            // TODO: handle exception

            e.printStackTrace();
        }
        closeConnectSQL();

        return arL_Subject;
    }

    public Subject getSubjectByQuestion(String Id) {
        String query = "select sbj.Id,sbj.NameSubject, sbj.Credit "
                + "from Questions qs, Subjects sbj "
                + "where  qs.Id = '" + Id + "' and qs.IdSubject = sbj.Id";

        Subject subject = null;
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();

            subject = new Subject(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
        } catch (Exception e) {
            // TODO: handle exception
        }

        closeConnectSQL();

        return subject;
    }
}
