/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class Detail {
    
    private String user;
    private String name;
    
    public Detail(){
        user="tcfood";
        name="TC Food";
    }
    
    public Detail(String us, String na){
        this.user=us;
        this.name=na;
    }

    public Detail(Detail detail){
        this.user=detail.user;
        this.name=detail.name;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

