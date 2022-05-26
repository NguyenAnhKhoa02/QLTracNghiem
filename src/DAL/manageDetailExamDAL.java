package DAL;

import java.util.ArrayList;

import BLL.Exam.DetailExam;

public class manageDetailExamDAL extends connectSQL {
    public manageDetailExamDAL() {

    }

    public ArrayList<DetailExam> getAllDetailExam(String Id[], String TypeQues[]) {
        ArrayList<DetailExam> arL_detailExam = new ArrayList<>();
        String str_query = null;
        connetToSQL();
        for (int i = 0; i < Id.length; i++) {
            if (TypeQues[i].equalsIgnoreCase("Yes/No")) {
                str_query = "select qs.Id "
                        + "from Questions qs,YesNoQuestion ynQ "
                        + "where  qs.Id = '" + Id[i] + "' and qs.Id = ynQ.Id";
            } else {
                str_query = "select qs.Id "
                        + "from Questions qs,OptionsQuestion opQ "
                        + "where  qs.Id = '" + Id[i] + "' and qs.Id = opQ.Id";
            }

            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(str_query);

                resultSet.next();
                arL_detailExam.add(new DetailExam(resultSet.getString(1), resultSet.getString(2)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return arL_detailExam;
    }

    public ArrayList<DetailExam> getAllDetailExamById(String Id) {
        ArrayList<DetailExam> arL_detailExam = new ArrayList<>();

        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from DetailExam where Id='" + Id + "'");

            while (resultSet.next()) {
                arL_detailExam.add(new DetailExam(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnectSQL();

        return arL_detailExam;
    }

    public ArrayList<String> getAnswerQuesById(String Id) {
        String str_query = "select qs.Answer "
                + "from DetailExam de, Questions qs "
                + "where de.Id='" + Id + "' and de.IdQues = qs.Id";
        ArrayList<String> arL_String = new ArrayList<>();
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_query);

            while (resultSet.next()) {
                arL_String.add(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arL_String;
    }
}
