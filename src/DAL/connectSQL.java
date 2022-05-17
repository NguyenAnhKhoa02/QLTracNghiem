package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class connectSQL {
    connectSQL() {

    }

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

    public ArrayList<HashMap<String, String>> getAllQuestions(String questionName) {
        arrL_allQuestion = new ArrayList<>();
        String[] strArr_query = setQuery(questionName);
        connetToSQL();
        for (String str_query : strArr_query) {
            // *****************************************************************
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(str_query);

                while (resultSet.next()) {
                    HM_question = new HashMap<String, String>();
                    HM_question.put("Id", resultSet.getString("Id"));
                    HM_question.put("Lv", resultSet.getString("LvName"));
                    HM_question.put("Content", resultSet.getString("Content"));
                    HM_question.put("Answer", resultSet.getString("Answer"));
                    HM_question.put("NameSubject", resultSet.getString("NameSubject"));
                    HM_question.put("FullName", resultSet.getString("FullName"));
                    HM_question.put("TypeQues", resultSet.getString("TypeQues"));
                    HM_question.put("Options", resultSet.getString("Options"));

                    arrL_allQuestion.add(HM_question);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // **********************************************************************
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrL_allQuestion;
    }

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private HashMap<String, String> HM_question;
    private ArrayList<HashMap<String, String>> arrL_allQuestion;

    public String OPTIONS_QUESTION = "options";
    public String YESNO_QUESTION = "yes/no";
    public String BOTH = "both";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        connectSQL c = new connectSQL();
        c.getAllQuestions(c.BOTH);
        System.out.println("a");
    }
}
