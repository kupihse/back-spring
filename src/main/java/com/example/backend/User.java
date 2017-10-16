package com.example.backend;

// Единичный юзер
public class User {
    private String name;
    private String logname;
    private String password;
    private String ID;

    public User() {
        name=null;
        logname=null;
        password=null;
    }
    public User(String name, String logname, String password) {
        this.name = name;
        this.logname = logname;
        this.password = password;
    }
    public String getName(){
        return name;
    }
    public void setLogname(String logname) {
        this.logname = logname;
    }
    public String getLogname() {
        return logname;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!logname.equals(user.logname)) return false;
        return password.equals(user.password);
    }
}
