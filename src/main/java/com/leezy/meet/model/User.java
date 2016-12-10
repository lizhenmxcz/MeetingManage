package com.leezy.meet.model;

/**
 * Created by lizhen on 2016/12/8.
 */
public class User {
    private Long id;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userPwd;
    private Short isDelete;
    private Short isAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    public Short getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Short isAdmin) {
        this.isAdmin = isAdmin;
    }
}
