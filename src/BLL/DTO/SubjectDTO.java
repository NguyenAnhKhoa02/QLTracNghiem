package BLL.DTO;

import java.util.ArrayList;

import BLL.Subject.Subject;

public class SubjectDTO {
    public SubjectDTO(ArrayList<Subject> arrayList) {
        arrL_Subject = arrayList;
    }

    public String[] getStrArrSubject() {
        String[] strArr = new String[arrL_Subject.size()];
        int index = 0;

        for (Subject subject : arrL_Subject) {
            strArr[index] = subject.getId() + " - " + subject.getName();
            index++;
        }

        return strArr;
    }

    private ArrayList<Subject> arrL_Subject;
}
