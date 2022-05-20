package BLL.Question;

public class OptionsQuestion extends Question {

    public OptionsQuestion(String Id, String Level, String Content, String Answer, String Subject, String Lecture,
            String Type,
            String Options) {
        super(Id, Level, Content, Answer, Subject, Lecture, Type);
        str_options = Options;
    }

    private boolean isOptions(String str_tempt) {
        for (String str : strArr_options)
            if (str_tempt.equalsIgnoreCase(str) && !str.equals("a."))
                return true;

        return false;
    }

    private String convertOptions() {
        String str_tempt = "";
        String[] strArr_tempt = str_options.split(" ");

        for (int i = 0; i < strArr_tempt.length; i++) {
            if (isOptions(strArr_tempt[i])) {
                str_tempt += "\n" + strArr_tempt[i];
                continue;
            }
            if (strArr_tempt[i].equals("a."))
                str_tempt += strArr_tempt[i];
            else
                str_tempt += strArr_tempt[i] + " ";
        }

        return str_tempt;
    }

    public String getOptionsQuestion() {
        return convertOptions();
    }

    String str_options;
    String[] strArr_options = { "a.", "b.", "c.", "d." };
}
