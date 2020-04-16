package com.xyz.groupby.dos;


import java.util.Objects;

public class StudentDO {

    private Integer studentId;

    private String studentName;

    public StudentDO(Integer studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDO studentDO = (StudentDO) o;
        return Objects.equals(studentId, studentDO.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "StudentDO{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
