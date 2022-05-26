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
    private void connetToSQL() {

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

    public Boolean addYesNoQuestion(YesNoQuestion yNoQuestion, String id) {
        String str_InsertQuestion = "insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ("
                + id + ",'" + yNoQuestion.getLevelQuestion()
                + "',N'" + yNoQuestion.getContentQuestion()
                + "','" + yNoQuestion.getAnswerQuestion()
                + "','" + yNoQuestion.getSubjectQuestion() + "','" + yNoQuestion.getLectureQuestion() + "','Yes/No')";
        String str_InsertYnQuestion = "insert into YesNoQuestion (Id,Options) values (" + id
                + ",N'a.Đúng b.Sai')";
        connetToSQL();
        try {
            statement = connection.createStatement();

            statement.executeUpdate(str_InsertQuestion);

            statement.executeUpdate(str_InsertYnQuestion);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        closeConnectSQL();
        return true;
    }

    public Boolean addOptionsQuestion(OptionsQuestion optionsQuestion, String id) {
        String str_InsertQuestion = "insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture,TypeQues) values ("
                + id
                + ",'" + optionsQuestion.getLevelQuestion()
                + "',N'" + optionsQuestion.getContentQuestion()
                + "','" + optionsQuestion.getAnswerQuestion()
                + "','" + optionsQuestion.getSubjectQuestion()
                + "','" + optionsQuestion.getLectureQuestion()
                + "','Options') ";

        String str_InsertOpQuestion = "insert into OptionsQuestion (Id,Options) values ("
                + id
                + ",N'" + optionsQuestion.getOptionsQuestion()
                + "')";
        connetToSQL();
        try {
            statement = connection.createStatement();

            statement.executeUpdate(str_InsertQuestion);

            statement.executeUpdate(str_InsertOpQuestion);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        closeConnectSQL();
        return true;

    }

    public Boolean deleteYesNoQuestion(YesNoQuestion yesNoQuestion) {
        connetToSQL();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("delete from YesNoQuestion where id='" + yesNoQuestion.getIdQuestion() + "'");
            statement.executeUpdate("delete from Questions where id='" + yesNoQuestion.getIdQuestion() + "'");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
        closeConnectSQL();
        return true;

    }

    public Boolean deleteOptionsQuestion(OptionsQuestion optionsQuestion) {
        connetToSQL();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("delete from OptionsQuestion where id='" + optionsQuestion.getIdQuestion() + "'");
            statement.executeUpdate("delete from Questions where id='" + optionsQuestion.getIdQuestion() + "'");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
        closeConnectSQL();
        return true;

    }

    // public OptionsQuestion getSearchOptionsQuestion(String Id) {
    // OptionsQuestion ques = null;
    // }
    public int getCountQuesionsByLevel(String level) {
        int row = 0;
        String str_query = "select qs.Id "
                + "from Questions qs, Levels lv "
                + "where lv.LvName =N'" + level + "'" + " and qs.LevelQues = lv.Id";

        connetToSQL();
        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(str_query);

            while (resultSet.next())
                row++;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        closeConnectSQL();

        return row;
    }

    public int getColumn() {
        return column;
    }



    public String isLogin(String username, String pass){
        String str_user = null;
        connetToSQL();
        try {
            statement = connection.createStatement();
            for(int i = 0; i < 2; i++){
                if(i>0 && str_user == null){
                    resultSet = statement.executeQuery( "select * from LectureLogin where userLecture = '"+username+"' and pass = '"+pass+"'");
                    while(resultSet.next())
                        str_user = resultSet.getString(1).trim();
                }
                resultSet = statement.executeQuery( "select * from StudentsLogin where userStudent = '"+username+"' and pass = '"+pass+"'");
                while(resultSet.next())
                    str_user = resultSet.getString(1).trim();
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            
        }

        return str_user;
    }

    private PreparedStatement ps = null;


    public ArrayList<TimeExam> getTimeExam() {
        ArrayList<TimeExam> arL_timeExam = new ArrayList<>();
        String str_query = "select * from TimeExam";

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

    public ArrayList<String> getAllIdQuestion(String lv) {
        ArrayList<String> arrL_Id = new ArrayList<>();
        String str_query = "select qs.Id, qs.TypeQues "
                + "from Questions qs, Levels lv "
                + "where lv.LvName = N'" + lv + "' and qs.LevelQues = lv.Id";

        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_query);

            while (resultSet.next()) {
                arrL_Id.add(resultSet.getString(1) + "-" + resultSet.getString(2));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        closeConnectSQL();

        return arrL_Id;
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
                // TODO: handle exception
            }
        }

        closeConnectSQL();

        return arL_detailExam;
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

    public OptionsQuestion getOptionQuestionByID(String Id) {
        OptionsQuestion optionsQuestion = null;
        String str_query = "";
        connetToSQL();
        str_query = "select qs.Content, opQ.Options "
                + "from Questions qs, OptionsQuestion opQ "
                + "where qs.Id = opQ.Id and qs.Id = '" + Id + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_query);
            resultSet.next();
            if (resultSet.getRow() > 0) {
                optionsQuestion = new OptionsQuestion(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return optionsQuestion;
    }

    public YesNoQuestion getYesNoQuestionById(String Id) {
        YesNoQuestion ynQuestion = null;
        String str_query = "";
        connetToSQL();
        str_query = "select qs.Content, ynQ.Options "
                + "from Questions qs, YesNoQuestion ynQ "
                + "where qs.Id = ynQ.Id and qs.Id = '" + Id + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_query);
            resultSet.next();
            if (resultSet.getRow() > 0) {
                ynQuestion = new YesNoQuestion(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ynQuestion;
    }

    public void saveExam(String IdExam, ArrayList<DetailExam> detailExams, String TimeName) {
        connetToSQL();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("insert into Exam (Id,TimeName) values (" + IdExam + ",N'" + TimeName + "')");

            for (DetailExam d : detailExams) {
                statement.executeUpdate("insert into DetailExam (Id,IdQues) values ('" + d.getIdExam() + "','"
                        + d.getIdQuestion() + "')");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        closeConnectSQL();
    }

    public void saveExam(ArrayList<Exam> arL_exam) {
        connetToSQL();
        for (Exam exam : arL_exam) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate("delete from DetailExam where Id = '" + exam.getID() + "'");
                statement.executeUpdate("delete from Exam where Id = '" + exam.getID() + "'");

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        closeConnectSQL();

        for (Exam exam : arL_exam) {
            saveExam(exam.getID(), exam.getDetailExams(), exam.getTimeExam().getNameExam());
        }
    }

    public ArrayList<String> getAllIdExam() {
        connetToSQL();
        ArrayList<String> arL_tempt = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Id from Exam");

            while (resultSet.next())
                arL_tempt.add(resultSet.getString(1));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        closeConnectSQL();

        return arL_tempt;
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
            // TODO: handle exception
        }

        return arL_detailExam;
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

            if(!resultSet.next())
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

    public String getAnswer(String Id) {
        String str = null;
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Id,Answer from Questions where Id='" + Id + "'");
            resultSet.next();
            str = resultSet.getString(2);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        closeConnectSQL();
        return str;
    }

    public ArrayList<String> getAllIdExamPaper() {
        ArrayList<String> arL_tempt = new ArrayList<>();
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Id from ExamPapers");

            while (resultSet.next())
                arL_tempt.add(resultSet.getString(1));
        } catch (Exception e) {
            // TODO: handle exception
        }
        closeConnectSQL();

        return arL_tempt;
    }

    public void SaveExamPaper(ExamPaper examPaper) {
        connetToSQL();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(
                    "insert into ExamPapers(IdStudent,IdExam,Id,NumberRight,NumberWrong,Mark) values ('"
                            + examPaper.getIdStudent() + "','" + examPaper.getIdExam() + "','" + examPaper.getId()
                            + "'," + examPaper.getNumberRight() + "," + examPaper.getNumberWrong() + ","
                            + examPaper.getMark() + ")");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        closeConnectSQL();
    }

    public void SaveDetailExamPaper(ArrayList<DetailExamPaper> arL_detailExamPaper) {
        connetToSQL();
        try {
            statement = connection.createStatement();
            for (DetailExamPaper detailExamPaper : arL_detailExamPaper)
                statement.executeUpdate("insert into DetailExamPaper(Id,IdQues,Answer) values ('"
                        + detailExamPaper.getId() + "','" + detailExamPaper.getIdQuestion() + "','"
                        + detailExamPaper.getAnswer() + "')");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        closeConnectSQL();
    }

    public ExamPaper getExamPaperById(String Id) {
        connetToSQL();
        ExamPaper examPaper = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from ExamPapers where Id = '" + Id + "'");

            resultSet.next();
            examPaper = new ExamPaper(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getInt(4), resultSet.getInt(5), resultSet.getFloat(6));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return examPaper;
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
            // TODO: handle exception
            e.printStackTrace();
        }

        return arL_String;
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

    public Lecture getLectureById (String Id){
        
        Lecture l = null;
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Lectures where Id='" + Id + "'");
            resultSet.next();
            l = new Lecture(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        } catch (Exception e) {
            //TODO: handle exception
        }
        closeConnectSQL();

        return l;
    }

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private int column = 0;

    /*
     * ******************
     * Setting sql sever*
     * *****************
     */
    private String host_name = "LAPTOP-9Q1U79UH\\THANHLUC";
    private String user = "sa";
    private String password = "123";

}
