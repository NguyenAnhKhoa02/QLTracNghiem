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

import BLL.Exam.DetailExam;
import BLL.Exam.Exam;
import BLL.ExamPaper.DetailExamPaper;
import BLL.ExamPaper.ExamPaper;
import BLL.Lecture.Lecture;
import BLL.Question.OptionsQuestion;
import BLL.Question.Question;
import BLL.Question.YesNoQuestion;
import BLL.Student.Student;
import BLL.Subject.Subject;
import BLL.TimeExam.TimeExam;

public class connectSQL {
    public connectSQL() {

    }

    // *************************************************************************************************************
    protected void connetToSQL() {

        String url = "jdbc:sqlserver://" + host_name
                + ";databaseName=ManageQuiz;encrypt=false;"
                + "user=" + user + ";"
                + "password=" + password + ";";

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

    public void closeConnectSQL() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // *********************************************************************************************************************

    public int getColumn() {
        return column;
    }

    private PreparedStatement ps = null;

    protected Connection connection = null;
    protected Statement statement = null;
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;
    protected int column = 0;

    /*
     * ******************
     * Setting sql sever*
     * *****************
     */
    private String host_name = "DESKTOP-F7JKQMS\\SQLEXPRESS";
    private String user = "sa";
    private String password = "admin";

}
