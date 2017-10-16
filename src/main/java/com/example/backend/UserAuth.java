package com.example.backend;

// Единичный юзер
public class UserAuth {
    private String logname;
    private String password;
    private String ID;

    public UserAuth() {
        logname="undef";
        password="undef";
        ID="undef";
    }
    public UserAuth(String logname, String password){
        this.logname=logname;
        this.password=password;
    }
    public void setId(String ID) {
        this.ID = ID;
    }
    public void setLogname(String logname) {

        this.logname = logname;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuth userAuth = (UserAuth) o;

        if (!logname.equals(userAuth.logname)) return false;
        return password.equals(userAuth.password);
    }
}
