package com.quick.entity;
// Generated 5 Jun, 2013 6:08:00 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * ExamEntry generated by hbm2java
 */

public class ExamEntry  implements java.io.Serializable {


     private int exId;
     private Std std;
     private Sub sub;
     private String exName;
     private int exType;
     private Date startDt;
     private Date endDt;
     private String fordiv;
     private String topic;
     private String createdBy;
     private Date creationDt;
     private int noOfQuestions;
     private int totalMarks;
     private int passingMarks;
     private int totalStudents;
     private int appearedStudents;
     private int passedStudents;
     private int failedStudents;
     private Set<ExamQuestionsAnswers> examQuestionsAnswerses = new HashSet<ExamQuestionsAnswers>(0);
     private Set<ExamStudentResponse> examStudentResponses = new HashSet<ExamStudentResponse>(0);
     private Set<StudentExamSummary> studentExamSummaries = new HashSet<StudentExamSummary>(0);

    public ExamEntry() {
    }


    public int getExId() {
        return this.exId;
    }
    
    public void setExId(int exId) {
        this.exId = exId;
    }

    public Std getStd() {
        return this.std;
    }
    
    public void setStd(Std std) {
        this.std = std;
    }

    public Sub getSub() {
        return this.sub;
    }
    
    public void setSub(Sub sub) {
        this.sub = sub;
    }
    
  
    public String getExName() {
        return this.exName;
    }
    
    public void setExName(String exName) {
        this.exName = exName;
    }
    
    public int getExType() {
        return this.exType;
    }
    
    public void setExType(int exType) {
        this.exType = exType;
    }
    
    public Date getStartDt() {
        return this.startDt;
    }
    
    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }
   
    
    
    public Date getEndDt() {
        return this.endDt;
    }
    
    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }
    
    public String getFordiv() {
        return this.fordiv;
    }
    
    public void setFordiv(String fordiv) {
        this.fordiv = fordiv;
    }
    
    public String getTopic() {
        return this.topic;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public Date getCreationDt() {
        return this.creationDt;
    }
    
    public void setCreationDt(Date creationDt) {
        this.creationDt = creationDt;
    }
    
    public int getNoOfQuestions() {
        return this.noOfQuestions;
    }
    
    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }
    
    public int getTotalMarks() {
        return this.totalMarks;
    }
    
    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
    
    public int getPassingMarks() {
        return this.passingMarks;
    }
    
    public void setPassingMarks(int passingMarks) {
        this.passingMarks = passingMarks;
    }
    
    public int getTotalStudents() {
        return this.totalStudents;
    }
    
    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }
    
    public int getAppearedStudents() {
        return this.appearedStudents;
    }
    
    public void setAppearedStudents(int appearedStudents) {
        this.appearedStudents = appearedStudents;
    }
    
    public int getPassedStudents() {
        return this.passedStudents;
    }
    
    public void setPassedStudents(int passedStudents) {
        this.passedStudents = passedStudents;
    }
    
   
    public int getFailedStudents() {
        return this.failedStudents;
    }
    
    public void setFailedStudents(int failedStudents) {
        this.failedStudents = failedStudents;
    }
    public Set<ExamQuestionsAnswers> getExamQuestionsAnswerses() {
        return this.examQuestionsAnswerses;
    }
    
    public void setExamQuestionsAnswerses(Set<ExamQuestionsAnswers> examQuestionsAnswerses) {
        this.examQuestionsAnswerses = examQuestionsAnswerses;
    }
    public Set<ExamStudentResponse> getExamStudentResponses() {
        return this.examStudentResponses;
    }
    
    public void setExamStudentResponses(Set<ExamStudentResponse> examStudentResponses) {
        this.examStudentResponses = examStudentResponses;
    }
    public Set<StudentExamSummary> getStudentExamSummaries() {
        return this.studentExamSummaries;
    }
    
    public void setStudentExamSummaries(Set<StudentExamSummary> studentExamSummaries) {
        this.studentExamSummaries = studentExamSummaries;
    }




}


