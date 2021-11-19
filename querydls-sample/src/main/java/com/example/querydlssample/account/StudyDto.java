package com.example.querydlssample.account;

public class StudyDto {

    private Long id;

    private String keyword;

    private String measureDt;

    private String title;

    private Long user_id;

    private String username;

    public StudyDto() {}

    public StudyDto(Long id, String keyword, String measureDt, String title, Long user_id, String username) {
        this.id = id;
        this.keyword = keyword;
        this.measureDt = measureDt;
        this.title = title;
        this.user_id = user_id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMeasureDt() {
        return measureDt;
    }

    public void setMeasureDt(String measureDt) {
        this.measureDt = measureDt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
