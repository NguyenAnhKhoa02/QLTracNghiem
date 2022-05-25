package BLL.Question;

public class OptionsQuestion extends Question {
    public OptionsQuestion(String Id, String Level, String Content, String Answer, String Subject, String Lecture,
            String Type,
            String Options) {
        super(Id, Level, Content, Answer, Subject, Lecture, Type);
        str_options = Options;
    }

    public OptionsQuestion(String Content, String Options) {
        super(Content);
        str_options = Options;
    }

    private boolean isOptions(String str_tempt) {
        for (String str : strArr_options)
            if (str_tempt.equalsIgnoreCase(str) && !str.equals("a."))
                return true;

        return false;
    }

    public OptionsQuestion() {
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

    public String getOptionQuestionToSQL() {
        return convetOption_();

    }

    private String convetOption_() {
        String str_tempt = "";
        String[] s;
        String[] strArr_tempt = str_options.split("\n");

        for (String str : strArr_tempt) {
            s = str.trim().split("\\.", 2);
            str_tempt += s[0] + ". " + s[1] + " ";
        }

        return str_tempt;
    }

    public void setStr_options(String str_options) {
        this.str_options = str_options;
    }

    String str_options;
    String[] strArr_options = { "a.", "b.", "c.", "d." };
}
