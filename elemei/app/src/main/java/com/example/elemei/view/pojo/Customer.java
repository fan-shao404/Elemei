package com.example.elemei.view.pojo;

/**
 * Date:2021/4/9
 * Author:fanshaofeng
 */
public class Customer {
    private Integer id;
    private String phone;
    private String email;
    private String password;
    private String name;
    private Integer state;

    public Customer(Integer id, String phone, String email, String password, String name, Integer state) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
