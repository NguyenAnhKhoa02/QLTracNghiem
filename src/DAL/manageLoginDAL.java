package DAL;

public class manageLoginDAL extends connectSQL {
    public manageLoginDAL() {

    }

    public String isLogin(String username, String pass) {
        String str_user = null;
        connetToSQL();
        try {
            statement = connection.createStatement();
            for (int i = 0; i < 2; i++) {
                if (i > 0 && str_user == null) {
                    resultSet = statement.executeQuery("select * from LectureLogin where userLecture = '" + username
                            + "' and pass = '" + pass + "'");
                    while (resultSet.next())
                        str_user = resultSet.getString(1).trim();
                }
                resultSet = statement.executeQuery(
                        "select * from StudentsLogin where userStudent = '" + username + "' and pass = '" + pass + "'");
                while (resultSet.next())
                    str_user = resultSet.getString(1).trim();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }

        return str_user;
    }
}
