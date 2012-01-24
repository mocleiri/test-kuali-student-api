package org.kuali.student.process.poc.context;

public class DirectRuleCheckContext extends CheckContext {

    private String atpKey;
    private String studentId;

    public DirectRuleCheckContext() {
    }

    public String getAtpKey() {
        return atpKey;
    }

    public void setAtpKey(String atpKey) {
        this.atpKey = atpKey;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    
}