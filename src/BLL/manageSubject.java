package BLL;

import BLL.DTO.SubjectDTO;
import BLL.Subject.Subject;
import DAL.connectSQL;

public class manageSubject {
    public manageSubject() {
        conSQL_cConnectSQL = new connectSQL();
    }

    public String[] getStrArrSubject() {
        return new SubjectDTO(conSQL_cConnectSQL.getSubject()).getStrArrSubject();
    }

    public Subject getSubjectByQuestion(String Id) {
        return conSQL_cConnectSQL.getSubjectByQuestion(Id);
    }

    private connectSQL conSQL_cConnectSQL;
}
