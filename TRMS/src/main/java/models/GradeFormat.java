package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gradeformat")
public class GradeFormat {
    @Id
    @Column(name = "g_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int grade_id;

    private String cutoff_grade;
    private String format;

    public GradeFormat() {
    }

    public GradeFormat(int grade_id) {
        this.grade_id = grade_id;
    }

    public GradeFormat(String cutoff_grade, String format) {
        this.cutoff_grade = cutoff_grade;
        this.format = format;
    }

    public GradeFormat(int grade_id, String cutoff_grade, String format) {
        this.grade_id = grade_id;
        this.cutoff_grade = cutoff_grade;
        this.format = format;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public String getcutoff_grade() {
        return cutoff_grade;
    }

    public void setcutoff_grade(String cutoff_grade) {
        this.cutoff_grade = cutoff_grade;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeFormat that = (GradeFormat) o;
        return getGrade_id() == that.getGrade_id() && Objects.equals(cutoff_grade, that.cutoff_grade) && Objects.equals(getFormat(), that.getFormat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGrade_id(), cutoff_grade, getFormat());
    }

    @Override
    public String toString() {
        return "GradeFormat{" +
                "grade_id=" + grade_id +
                ", cutoff_grade='" + cutoff_grade + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
