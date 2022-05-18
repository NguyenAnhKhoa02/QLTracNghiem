package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.util.ElementScanner14;

import BLL.Question.OptionsQuestion;
import BLL.Question.Question;
import BLL.Question.YesNoQuestion;

public class connectSQL {
    public connectSQL() {

    }

    // *************************************************************************************************************
    private void connetToSQL() {
        String url = "jdbc:sqlserver://DESKTOP-F7JKQMS\\SQLEXPRESS;databaseName=ManageQuiz;encrypt=false;";
        String account = "sa";
        String password = "admin";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, account, password);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void closeConnectSQL() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] setQuery(String questionName) {
        String[] str_tempt = new String[1];

        switch (questionName) {
            case "options":
                str_tempt[0] = "select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, oQ.Options "
                        + "from Questions qs, Levels lv,Subjects sbj, Lectures lc, OptionsQuestion oQ "
                        + "where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and oQ.Id = qs.Id";
                break;

            case "yes/no":
                str_tempt[0] = "select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, ynQ.Options "
                        + "from Questions qs, Levels lv,Subjects sbj, Lectures lc, YesNoQuestion ynQ "
                        + "where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and ynQ.Id = qs.Id";
                break;

            case "both":
                str_tempt = new String[2];
                str_tempt[0] = "select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, oQ.Options "
                        + "from Questions qs, Levels lv,Subjects sbj, Lectures lc, OptionsQuestion oQ "
                        + "where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and oQ.Id = qs.Id";
                str_tempt[1] = "select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, ynQ.Options "
                        + "from Questions qs, Levels lv,Subjects sbj, Lectures lc, YesNoQuestion ynQ "
                        + "where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and ynQ.Id = qs.Id";
                break;

            default:
                break;
        }

        return str_tempt;
    }

    private String[] setQuerySearch(String Id) {
        String[] strArr_tempt = new String[2];

        strArr_tempt[0] = "select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, oQ.Options "
                + "from Questions qs, Levels lv,Subjects sbj, Lectures lc, OptionsQuestion oQ "
                + "where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and oQ.Id = qs.Id and qs.Id = '"
                + Id + "'";

        strArr_tempt[1] = "select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, ynQ.Options "
                + "from Questions qs, Levels lv,Subjects sbj, Lectures lc, YesNoQuestion ynQ "
                + "where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and ynQ.Id = qs.Id and qs.Id = '"
                + Id + "'";

        return strArr_tempt;
    }

    private ArrayList<?> setArrList(String questionName) {
        if (questionName.equals("options"))
            return new ArrayList<OptionsQuestion>();
        else if (questionName.equals("yes/no"))
            return new ArrayList<YesNoQuestion>();
        else
            return new ArrayList<Question>();
    }

    // *********************************************************************************************************************

    public ArrayList<?> getAllQuestions(String questionName) {

        arrL_allQuestion = setArrList(questionName);
        String[] strArr_query = setQuery(questionName);
        connetToSQL();
        for (String str_query : strArr_query) {
            // *****************************************************************
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(str_query);
                column = resultSet.getMetaData().getColumnCount();
                while (resultSet.next()) {
                    Question ques = new Question(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8));

                    arrL_allQuestion.add(ques);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // **********************************************************************
        }
        closeConnectSQL();

        return arrL_allQuestion;
    }

    public ArrayList<Question> getQuestion(String Id) {
        ArrayList<Question> arrL_tempt = new ArrayList<>();
        String[] strArr_query = setQuerySearch(Id);
        connetToSQL();
        for (String str_query : strArr_query) {
            // *****************************************************************
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(str_query);
                column = resultSet.getMetaData().getColumnCount();
                while (resultSet.next()) {
                    Question ques = new Question(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8));

                    arrL_tempt.add(ques);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (arrL_tempt.size() > 0)
                return arrL_tempt;
            // **********************************************************************
        }
        closeConnectSQL();

        return arrL_tempt;
    }

    public int getColumn() {
        return column;
    }

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private int column = 0;
    private ArrayList arrL_allQuestion;

    public String OPTIONS_QUESTION = "options";
    public String YESNO_QUESTION = "yes/no";
    public String BOTH = "both";
}
