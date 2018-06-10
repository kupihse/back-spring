package com.example.backend.models;

import java.util.*;

// Единичный юзер
public class User {
    private String name;
//    private String idProduct;
    private String login;
    private String password;
    private Set<String> products = new HashSet<>();
    private List<String> wishlist = new ArrayList<>();
    private String token;

    private boolean confirmed;

    public User() {
        name="";
        login ="";
        password="";
    }
    public User(String login, String password){
        name="unknown";
        this.login = login;
        this.password = password;
    }

    public User(String name, String logname, String password) {
        this.name = name;
        this.login = logname;
        this.password = password;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNotConfirmed() {
        this.confirmed = false;
    }

    public void confirm() {
        this.confirmed = true;
    }

    public void addProduct(String product) { products.add(product); }

    public Set<String> getProducts() { return products; }

    public List<String> getWishlist(){ return wishlist; }

    public void addToWishlist(String id) {
        wishlist.add(id);
    }

    public void removeFromWishlist(String id) {
        wishlist.remove(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;
        return password.equals(user.password);
    }

    @Override
    public String toString() {
        return "login: " + getLogin() + " pass: " + getPassword() + " token: " + token;
    }



    // + test
    public String token() {
        if (token == null) {
            token = UUID.randomUUID().toString();
        }
        return token;
    }
}
