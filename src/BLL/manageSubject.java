package BLL;

import BLL.DTO.SubjectDTO;
import BLL.Subject.Subject;
import DAL.connectSQL;
import DAL.manageSubjectDAL;

public class manageSubject {
    public manageSubject() {
        msd_manageSubjectDAL = new manageSubjectDAL();
    }

    public String[] getStrArrSubject() {
        return new SubjectDTO(msd_manageSubjectDAL.getSubject()).getStrArrSubject();
    }

    public Subject getSubjectByQuestion(String Id) {
        return msd_manageSubjectDAL.getSubjectByQuestion(Id);
    }

    private manageSubjectDAL msd_manageSubjectDAL;
}
