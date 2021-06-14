package com.thethelafaltein.casino.model;

public class Player {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String number;
    private String email;
    private Integer age;
    private Double balance;

    public Player() {
    }

    public Player(Long id, String username, String name, String surname, String number, String email, Integer age, Double balance) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.email = email;
        this.age = age;
        this.balance = balance;
    }

    public Player(String username, String name, String surname, String number, String email, Integer age, Double balance) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.email = email;
        this.age = age;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void updateBalance(Double amount){
        this.balance+=amount;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", funds=" + balance +
                '}';
    }
}
