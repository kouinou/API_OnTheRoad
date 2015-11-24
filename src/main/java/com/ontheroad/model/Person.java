package com.ontheroad.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/**
 * Created by Kouinou on 24/11/2015.
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Person {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String[] citizenships;
    private Map<String, Object> creditCards;
    private int age;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String[] getCitizenships() {
        return citizenships;
    }
    public void setCitizenships(String[] citizenships) {
        this.citizenships = citizenships;
    }

    public Map<String, Object> getCreditCards() {
        return creditCards;
    }
    public void setCreditCards(Map<String, Object> creditCards) {
        this.creditCards = creditCards;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
