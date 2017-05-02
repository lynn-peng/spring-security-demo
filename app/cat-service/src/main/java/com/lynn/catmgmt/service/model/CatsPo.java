package com.lynn.catmgmt.service.model;

public class CatsPo {

    private String formalName;

    private String nickname;

    private int age;

    private boolean isMale;

    public String getFormalName() {
        return formalName;
    }

    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean isMale) {
        this.isMale = isMale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CatsPo user = (CatsPo) o;
        if (formalName != null ? !formalName.equals(user.formalName) : user.formalName != null)
            return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null)
            return false;

        return age == user.age && isMale == user.isMale;

    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (formalName != null ? formalName.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (isMale ? 0 : 1);
        return result;
    }

}
