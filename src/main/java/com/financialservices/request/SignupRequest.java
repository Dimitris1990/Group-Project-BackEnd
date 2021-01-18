package com.financialservices.request;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String fname;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lname;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

 
    private Date creation;

 
    private Boolean actv;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getCreationDateTime() {
        return creation;
    }

    public void setCreationDateTime(Date creation) {
        this.creation = creation;
    }

    public Boolean getActv() {
        return actv;
    }

    public void setActv(Boolean actv) {
        this.actv = actv;
    }

}
