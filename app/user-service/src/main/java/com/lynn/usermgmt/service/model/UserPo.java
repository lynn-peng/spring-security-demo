package com.lynn.usermgmt.service.model;

public class UserPo {

    private String loginName;

    private String displayName;

    private String password;

    private String address;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserPo user = (UserPo) o;
        if (loginName != null ? !loginName.equals(user.loginName) : user.loginName != null)
            return false;
        if (displayName != null ? !displayName.equals(user.displayName) : user.displayName != null)
            return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        return address != null ? address.equals(user.address) : user.address == null;

    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

}
