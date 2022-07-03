package survey;

import java.util.List;

//설문 항목에 대한 답변과 응답자 정보를 함께 담는 클래스
public class AnsweredData {

    private List<String> response;
    private Respondent res;

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

    public Respondent getRes() {
        return res;
    }

    public void setRes(Respondent res) {
        this.res = res;
    }
}
