package com.example.elemei.view.event;

/**
 * Date:2021/4/6
 * Author:fanshaofeng
 */
public class Login {

    private boolean login;

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public Login(boolean login){
        this.login=login;
    }
}
