package com.xyz.groupby.dos;

public class UserDO {

    private StudentDO studentDO;

    private String sex;

    public UserDO(StudentDO studentDO, String sex) {
        this.studentDO = studentDO;
        this.sex = sex;
    }

    public StudentDO getStudentDO() {
        return studentDO;
    }

    public void setStudentDO(StudentDO studentDO) {
        this.studentDO = studentDO;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "studentDO=" + studentDO +
                ", sex='" + sex + '\'' +
                '}';
    }
}
