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
public class LieuExercice {
    private String type_code;
    private String type_libelle;

    public String getType_code() {
        return type_code;
    }

    public String getType_libelle() {
        return type_libelle;
    }

    public String getType_lieu() {
        return type_lieu;
    }

    public LieuExercice(String type_code, String type_libelle, String type_lieu) {
        this.type_code = type_code;
        this.type_libelle = type_libelle;
        this.type_lieu = type_lieu;
    }
    private String type_lieu;


}
