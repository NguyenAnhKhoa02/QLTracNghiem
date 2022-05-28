package DAL;

import java.sql.SQLException;
import java.util.ArrayList;

import BLL.Question.OptionsQuestion;
import BLL.Question.YesNoQuestion;
import BLL.Subject.Subject;

public class manageQuestionDAL extends connectSQL {
    public manageQuestionDAL() {

    }

    public Boolean updateYesNoQuestion(YesNoQuestion yNoQuestion, boolean isChange) {
        String str_Update = "update q "
                + "set q.Content = N'" + yNoQuestion.getContentQuestion() + "' "
                + ", q.LevelQues=N'" + yNoQuestion.getLevelQuestion() + "' "
                + ", q.Answer=N'" + yNoQuestion.getAnswerQuestion() + "' "
                + ", q.TypeQues=N'Yes/No' "
                + ", q.IdSubject=N'" + yNoQuestion.getSubjectQuestion() + "' "
                + "from Questions q inner join Subjects on q.IdSubject=Subjects.Id "
                + "inner join YesNoQuestion on q.Id=YesNoQuestion.Id "
                + "where q.Id='" + yNoQuestion.getIdQuestion() + "'";

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
        String str_Update = "update q "
                + "set q.Content = N'" + optionsQuestion.getContentQuestion() + "' "
                + ", q.LevelQues=N'" + optionsQuestion.getLevelQuestion() + "' "
                + ", q.Answer=N'" + optionsQuestion.getAnswerQuestion() + "' "
                + ", q.TypeQues=N'Options' "
                + ", q.IdSubject=N'" + optionsQuestion.getSubjectQuestion() + "' "
                + "from Questions q inner join Subjects on q.IdSubject=Subjects.Id "
                + "inner join OptionsQuestion on q.Id=OptionsQuestion.Id "
                + "where q.Id='" + optionsQuestion.getIdQuestion() + "' ";
        String str_UpdateOp = "update OptionsQuestion "
                + "set OptionsQuestion.Options=N'" + optionsQuestion.getOptionQuestionToSQL() + "' ";
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
        String str_InsertQuestion = "insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture, TypeQues) values ('"
                + id + "','" + yNoQuestion.getLevelQuestion()
                + "',N'" + yNoQuestion.getContentQuestion()
                + "','" + yNoQuestion.getAnswerQuestion()
                + "','" + yNoQuestion.getSubjectQuestion() + "','" + yNoQuestion.getLectureQuestion() + "','Yes/No')";
        String str_InsertYnQuestion = "insert into YesNoQuestion (Id,Options) values ('" + id
                + "',N'a.Đúng b.Sai')";
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
        String str_InsertQuestion = "insert into Questions(Id,LevelQues,Content,Answer,IdSubject,IdLecture,TypeQues) values ('"
                + id + "' "
                + ",'" + optionsQuestion.getLevelQuestion()
                + "',N'" + optionsQuestion.getContentQuestion()
                + "','" + optionsQuestion.getAnswerQuestion()
                + "','" + optionsQuestion.getSubjectQuestion()
                + "','" + optionsQuestion.getLectureQuestion()
                + "','Options') ";

        String str_InsertOpQuestion = "insert into OptionsQuestion (Id,Options) values ('"
                + id + "'"
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

    public int getCountQuesionsByLevelAndSubject(String level, String subject) {
        int row = 0;
        String str_query = "select qs.Id "
                + "from Questions qs, Levels lv "
                + "where qs.IdSubject = '" + subject + "' and lv.LvName=N'" + level + "' and qs.LevelQues = lv.Id";

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

    public String getCountBySubject(String subJect) {
        int count = 0;
        String str_Qry = "select Questions.Id "
                + "from Questions,Subjects "
                + "where Questions.Idsubject =Subjects.Id and Questions.IdSubject='" + subJect + "'";
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str_Qry);
            while (resultSet.next()) {
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnectSQL();
        return subJect + (count + 1);
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

    public String getTypeById(String Id) {
        String str = null;
        connetToSQL();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select TypeQues from Questions where Id  = '" + Id + "'");

            resultSet.next();
            str = resultSet.getString(1);
        } catch (Exception e) {
            // TODO: handle exception
        }
        closeConnectSQL();

        return str;
    }

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

    public ArrayList<String> getAllIdQuestion(String lv, String subject) {
        ArrayList<String> arrL_Id = new ArrayList<>();
        String str_query = "select qs.Id, qs.TypeQues "
                + "from Questions qs, Levels lv "
                + "where qs.IdSubject = '" + subject + "' and lv.LvName=N'" + lv + "' and qs.LevelQues = lv.Id";

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

    // public static void main(String[] args) {
    // manageQuestionDAL MA = new manageQuestionDAL();
    // String n = "LTPT";
    // System.out.println(MA.getCountBySubject(n));
    // }
}
