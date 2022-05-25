package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        String url = "jdbc:sqlserver://" + host_name_1
                + ";databaseName=ManageQuiz;encrypt=false;"
                + "user=" + user + ";"
                + "password=" + password_1 + ";";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ko kết nối sql sever");
        }
    }

    private void closeConnectSQL() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // *********************************************************************************************************************

    public ArrayList<OptionsQuestion> getOptionsQuestion() {
        ArrayList<OptionsQuestion> arrL_tempt = new ArrayList<>();
        String str_Query = "select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, oQ.Options "
                + "from Questions qs, Levels lv,Subjects sbj, Lectures lc, OptionsQuestion oQ "
                + "where qs.LevelQues = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and oQ.Id = qs.Id";
        connetToSQL();
        // *****************************************************************
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_Query);
            column = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                OptionsQuestion ques = new OptionsQuestion(
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
        closeConnectSQL();

        return arrL_tempt;
    }

    public ArrayList<YesNoQuestion> getYesNoQuestion() {
        ArrayList<YesNoQuestion> arrL_tempt = new ArrayList<>();
        String str_Query = "select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, ynQ.Options "
                + "from Questions qs, Levels lv,Subjects sbj, Lectures lc, YesNoQuestion ynQ "
                + "where qs.LevelQues = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and ynQ.Id = qs.Id";
        connetToSQL();
        // *****************************************************************
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_Query);
            column = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                YesNoQuestion ques = new YesNoQuestion(
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
        closeConnectSQL();

        return arrL_tempt;
    }

    public Boolean updateQuestion() {

        return null;

    }

    public Boolean updateYesNoQuestion(YesNoQuestion yNoQuestion, boolean isChange) {
        String str_Update = "update Questions "
                + "set Questions.Content = N'" + yNoQuestion.getContentQuestion() + "' "
                + ", Questions.LevelQues=N'" + yNoQuestion.getLevelQuestion() + "' "
                + ", Questions.Answer=N'" + yNoQuestion.getAnswerQuestion() + "' "
                + ", Questions.TypeQues=N'Yes/No' "
                + "where Questions.Id=" + yNoQuestion.getIdQuestion() + "";
        String str_UpdateOp = " update OptionsQuestion "
                + "set OptionsQuestion.Options=N'a.Đúng b.Sai'";
        String str_Clear = "Delete from OptionsQuestion where Id=" + yNoQuestion.getIdQuestion();
        String str_Insert = "insert into YesNoQuestion (Id,Options) values (" + yNoQuestion.getIdQuestion()
                + ",N'a.Đúng b.Sai')";
        connetToSQL();
        try {
            statement = connection.createStatement();
            if (isChange) {
                statement.executeUpdate(str_Clear);
                statement.executeUpdate(str_Insert);
            }
            statement.executeUpdate(str_Update);
            statement.executeUpdate(str_UpdateOp);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        closeConnectSQL();
        return true;
    }

    public Boolean updateOptionsQuestion(OptionsQuestion optionsQuestion, boolean isChange) {
        String str_Update = "update Questions "
                + "set Questions.Content = N'" + optionsQuestion.getContentQuestion() + "' "
                + ", Questions.LevelQues=N'" + optionsQuestion.getLevelQuestion() + "' "
                + ", Questions.Answer=N'" + optionsQuestion.getAnswerQuestion() + "' "
                + ", Questions.TypeQues=N'Options' "
                + "where Questions.Id=" + optionsQuestion.getIdQuestion() + "";
        String str_UpdateOp = " update OptionsQuestion "
                + "set OptionsQuestion.Options=N'" + optionsQuestion.getOptionQuestionToSQL() + "'";
        String str_Clear = "Delete from YesNoQuestion where Id=" + optionsQuestion.getIdQuestion();
        String str_Insert = "insert into OptionsQuestion (Id,Options) values (" + optionsQuestion.getIdQuestion()
                + ",N'" + optionsQuestion.getOptionsQuestion()
                + "')";
        connetToSQL();
        try {
            statement = connection.createStatement();
            if (isChange) {
                statement.executeUpdate(str_Clear);
                statement.executeUpdate(str_Insert);
            }
            statement.executeUpdate(str_Update);
            statement.executeUpdate(str_UpdateOp);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        closeConnectSQL();
        return true;

    }

    public OptionsQuestion getSearchOptionsQuestion(String Id) {
        OptionsQuestion ques = null;

        String str_Query = "select qs.Id, lv.LvName, qs.Content, qs.Answer, sbj.NameSubject,lc.FullName,qs.TypeQues, oQ.Options "
                + "from Questions qs, Levels lv,Subjects sbj, Lectures lc, OptionsQuestion oQ "
                + "where qs.Id = lv.Id and qs.IdLecture = lc.Id and qs.IdSubject = sbj.Id and oQ.Id = qs.Id and qs.Id = "
                + Id;
        connetToSQL();
        // *****************************************************************
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_Query);
            column = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                ques = new OptionsQuestion(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // **********************************************************************
        closeConnectSQL();

        return ques;
    }

    public int getColumn() {
        return column;
    }

    private PreparedStatement ps = null;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private int column = 0;

    /*
     * ******************
     * Setting sql sever*
     * ******************
     */
    private String host_name = "DESKTOP-F7JKQMS\\SQLEXPRESS";
    private String host_name_1 = "QUYQUY";
    private String user = "sa";
    private String password = "admin";
    private String password_1 = "0977";
}
