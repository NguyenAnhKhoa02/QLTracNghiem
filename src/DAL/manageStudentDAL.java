package DAL;

import java.util.ArrayList;

import BLL.Student.Student;

public class manageStudentDAL extends connectSQL {
    public manageStudentDAL() {

    }

    public Student getStudentById(String Id) {
        Student student = null;
        String str_query = "select s.Id, s.FullName,s.Birthday,f.NameField "
                + "from Students s, Fields f "
                + "where s.IdField = f.Id and s.Id = '" + Id + "'";
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_query);

            if (!resultSet.next())
                return null;

            student = new Student(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        closeConnectSQL();

        return student;
    }

    public ArrayList<String> getAllIdStudent() {
        ArrayList<String> arL_id = new ArrayList<>();
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Students");

            while (resultSet.next()) {
                arL_id.add(resultSet.getString(1).trim());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        closeConnectSQL();

        return arL_id;
    }
}
