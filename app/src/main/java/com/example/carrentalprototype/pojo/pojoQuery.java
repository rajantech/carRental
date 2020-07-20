package com.example.carrentalprototype.pojo;

public class pojoQuery {

    private String queryId;
    private String subject;
    private String userName;
    private String userLastName;
    private String message;
    private String userId;
    private String visit;
    private String userProfilePic;


    public pojoQuery(String userId, String message, String queryId, String userName, String userLastName) {
        this.message = message;
        this.userId = userId;
        this.queryId=queryId;
        this.userName=userName;
        this.userLastName=userLastName;

    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }
}


