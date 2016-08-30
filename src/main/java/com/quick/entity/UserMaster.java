package com.quick.entity;
// Generated 27 Apr, 2013 12:52:34 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * UserMaster generated by hbm2java
 */
public class UserMaster  implements java.io.Serializable {


     private String username;
     private int prn;
     private String password;
     private String name;
     private Long creationdate;
     private Long mobile;
     private Set<Sub> subs = new HashSet<Sub>(0);
     private Set<StudentMaster> studentMasters = new HashSet<StudentMaster>(0);
     private Set<UserRoles> userRoleses = new HashSet<UserRoles>(0);
     private Set<TeacherStddivSub> teacherStddivSubs = new HashSet<TeacherStddivSub>(0);
     private Set<Std> stds = new HashSet<Std>(0);
     private Set<QualificationMaster> qualificationMasters = new HashSet<QualificationMaster>(0);
     private Set<TeacherMaster> teacherMasters = new HashSet<TeacherMaster>(0);

    public UserMaster() {
    }

	
    public UserMaster(String username, int prn) {
        this.username = username;
        this.prn = prn;
    }
    public UserMaster(String username, int prn, String password, String name, Long creationdate, Long mobile, Set<Sub> subs, Set<StudentMaster> studentMasters, Set<UserRoles> userRoleses, Set<TeacherStddivSub> teacherStddivSubs, Set<Std> stds, Set<QualificationMaster> qualificationMasters, Set<TeacherMaster> teacherMasters) {
       this.username = username;
       this.prn = prn;
       this.password = password;
       this.name = name;
       this.creationdate = creationdate;
       this.mobile = mobile;
       this.subs = subs;
       this.studentMasters = studentMasters;
       this.userRoleses = userRoleses;
       this.teacherStddivSubs = teacherStddivSubs;
       this.stds = stds;
       this.qualificationMasters = qualificationMasters;
       this.teacherMasters = teacherMasters;
    }
   
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getPrn() {
        return this.prn;
    }
    
    public void setPrn(int prn) {
        this.prn = prn;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getCreationdate() {
        return this.creationdate;
    }
    
    public void setCreationdate(Long creationdate) {
        this.creationdate = creationdate;
    }
    
    public Long getMobile() {
        return this.mobile;
    }
    
    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Set<Sub> getSubs() {
        return this.subs;
    }
    
    public void setSubs(Set<Sub> subs) {
        this.subs = subs;
    }
    public Set<StudentMaster> getStudentMasters() {
        return this.studentMasters;
    }
    
    public void setStudentMasters(Set<StudentMaster> studentMasters) {
        this.studentMasters = studentMasters;
    }
    public Set<UserRoles> getUserRoleses() {
        return this.userRoleses;
    }
    
    public void setUserRoleses(Set<UserRoles> userRoleses) {
        this.userRoleses = userRoleses;
    }
    public Set<TeacherStddivSub> getTeacherStddivSubs() {
        return this.teacherStddivSubs;
    }
    
    public void setTeacherStddivSubs(Set<TeacherStddivSub> teacherStddivSubs) {
        this.teacherStddivSubs = teacherStddivSubs;
    }
       
    public Set<Std> getStds() {
        return this.stds;
    }
    
    public void setStds(Set<Std> stds) {
        this.stds = stds;
    }

    public Set<QualificationMaster> getQualificationMasters() {
        return this.qualificationMasters;
    }
    
    public void setQualificationMasters(Set<QualificationMaster> qualificationMasters) {
        this.qualificationMasters = qualificationMasters;
    }
    public Set<TeacherMaster> getTeacherMasters() {
        return this.teacherMasters;
    }
    
    public void setTeacherMasters(Set<TeacherMaster> teacherMasters) {
        this.teacherMasters = teacherMasters;
    }




}


