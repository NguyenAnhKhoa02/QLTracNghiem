package DAL;

import java.util.ArrayList;

import BLL.TimeExam.TimeExam;

public class manageTimeExamDAL extends connectSQL {
    public manageTimeExamDAL() {

    }

    public ArrayList<TimeExam> getTimeExam() {
        ArrayList<TimeExam> arL_timeExam = new ArrayList<>();
        String str_query = "select * from TimeExam where TimeName='HKII'";

        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_query);
            column = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                arL_timeExam.add(new TimeExam(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeConnectSQL();

        return arL_timeExam;
    }
}
