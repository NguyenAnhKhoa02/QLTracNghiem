package DAL;

import BLL.Lecture.Lecture;

public class manageLectureDAL extends connectSQL {
    public manageLectureDAL() {
    }

    public String getNameLectureById(String Id) {
        String str_name = "";
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select FullName from Lectures where Id='" + Id + "'");

            resultSet.next();
            str_name = resultSet.getNString(1);
        } catch (Exception e) {
            // TODO: handle exception
        }
        closeConnectSQL();
        return str_name;
    }

    public Lecture getLectureById(String Id) {
        String str_query = "select l.Id, l.FullName, f.NameField, l.Position "
                + "from Lectures l, Fields f "
                + "where l.IdField = f.Id and l.Id = '" + Id + "' ";
        Lecture l = null;
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_query);
            resultSet.next();
            l = new Lecture(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4));
        } catch (Exception e) {
            // TODO: handle exception
        }
        closeConnectSQL();

        return l;
    }
}
