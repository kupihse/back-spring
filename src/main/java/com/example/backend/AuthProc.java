package com.example.backend;

import javax.accessibility.AccessibleHyperlink;

// Единичный юзер
public class AuthProc {
    private String logname;
    private String password;
    private String ID;

    public AuthProc() {
        logname="undef";
        password="undef";
        ID="undef";
    }
    public AuthProc(String logname, String password){
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

        AuthProc authProc = (AuthProc) o;

        if (!logname.equals(authProc.logname)) return false;
        return password.equals(authProc.password);
    }
}
