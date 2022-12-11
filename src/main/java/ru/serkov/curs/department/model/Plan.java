package ru.serkov.curs.department.model;

import java.time.LocalDate;
import java.util.Date;

import static org.apache.coyote.http11.Constants.a;

public class Plan implements Model{
    private int id;
    private int id_article;
    private LocalDate start_date;
    private final LocalDate today = LocalDate.now();
    private LocalDate finish_date;
    private int count;
    private boolean state;

    public Plan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(LocalDate finish_date) {
        this.finish_date = finish_date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public static Appearance getPlanAppearance(
            int id, Date start_date,
            Date finish_date,
            int count,
            int progress,
            boolean state,
            String articleName,
            String subjectName,
            String departmentName,
            String teacherName) {
        return new Appearance(id,
                start_date,
                finish_date,
                count,
                progress,
                state,
                articleName,
                subjectName,
                departmentName,
                teacherName
                );
    }

    public static Appearance getPlanAppearance() {
        return new Appearance();
    }

    public int getProgress() {

        if (finish_date.isBefore(today)) return 100;
        if(start_date.isAfter(today)) return 0;
        if (start_date.getYear()==finish_date.getYear()) {
            return (today.getDayOfYear() - start_date.getDayOfYear()) * 100 / (finish_date.getDayOfYear() - start_date.getDayOfYear());
        }
        return (int) ((today.toEpochDay() - start_date.toEpochDay()) * 100 / (finish_date.toEpochDay() - start_date.toEpochDay()));

    }
    public boolean getState(){
        if (finish_date.isBefore(today)) return true;
        return false;
    }

    public static class Appearance implements Model{
        private int id;
        private Date start_date;
        private Date finish_date;
        private int count;
        private int progress;
        private boolean state;
        private String articleName;
        private String subjectName;
        private String departmentName;
        private String teacherName;

        public Appearance(int id, Date start_date,
                          Date finish_date,
                          int count,
                          int progress,
                          boolean state,
                          String articleName,
                          String subjectName,
                          String departmentName,
                          String teacherName) {
            this.id = id;
            this.start_date = start_date;
            this.finish_date = finish_date;
            this.count = count;
            this.progress = progress;
            this.state = state;
            this.articleName = articleName;
            this.subjectName = subjectName;
            this.departmentName = departmentName;
            this.teacherName = teacherName;
        }

        public Appearance() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getStart_date() {
            return start_date;
        }

        public void setStart_date(Date start_date) {
            this.start_date = start_date;
        }

        public Date getFinish_date() {
            return finish_date;
        }

        public void setFinish_date(Date finish_date) {
            this.finish_date = finish_date;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public String getArticleName() {
            return articleName;
        }

        public void setArticleName(String articleName) {
            this.articleName = articleName;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }
    }
}
