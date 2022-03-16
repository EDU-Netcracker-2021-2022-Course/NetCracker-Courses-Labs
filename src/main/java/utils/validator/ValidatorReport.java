package utils.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorReport {
    private String status;
    private List<String> errorDescriptionList = new ArrayList<>();
    private List<String> errorVariableNameList = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getErrorDescriptionList() {
        return errorDescriptionList;
    }

    public List<String> getErrorVariableNameList() {
        return errorVariableNameList;
    }

    public void addErrorDescription(String description) {
        errorDescriptionList.add(description);
    }

    public void addErrorVariableName(String variableName) {
        errorVariableNameList.add(variableName);
    }

    @Override
    public String toString() {
        return "ValidatorReport{" +
                "status='" + status + '\'' +
                ", errorDescriptionList=" + errorDescriptionList +
                ", errorVariableNameList=" + errorVariableNameList +
                '}';
    }
}
