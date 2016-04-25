/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.metier;

/**
 *
 * @author clement
 */
public class Authentification {
    private String login;
    private String pass;

    public Authentification(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
    
}
