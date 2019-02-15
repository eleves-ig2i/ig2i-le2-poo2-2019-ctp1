/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Aeroport implements Comparable<Aeroport> {
    
    
    /**
     * Deux aéroports ne peuvent pas avoir le même code.
     * On ne gère pas l'unicité de code dans cette classe.
     * Contient EN GENERAL 3 caractères.
     */
    private String code;
    
    
    private String nom;
    
    
    private int zone;

    
    /**
     * Constructeur par données.
     * @param code s'il vaut null, l'attribut associé vaudra "ERREUR"
     * @param nom s'il vaut null, l'attribut associé vaudra "Lille Lesquin"
     * @param zone 
     */
    public Aeroport(String code, String nom, int zone) {
        if( code != null)
            this.code = code;
        else
            this.code = "ERREUR";
        
        if( nom != null)
            this.nom = nom;
        else
            this.nom = "Lille Lesquin";
        
        this.zone = zone;
    }
    
    
    /**
     * Constructeur utilisé pour la question 7, pour renvoyer un aéroport.
     * @param other si vaut null alors code vaut "ERREUR", nom vaut "Lille Lesquin" et zone vaut 0
     */
    public Aeroport(Aeroport other)
    {
        if( other == null)
        {
            this.code = "ERREUR";
            this.nom = "Lille Lesquin";
            this.zone = 0;
        }
        else
        {
            this.code = other.code;
            this.nom = other.nom;
            this.zone = other.zone;
        }
    }

    /**
     * Les aéroports sont comparés suivant leur code.
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Aeroport o) {
        if( o == null)
            return 1;
        
        return this.code.compareTo(o.code);
    }
    
    
    

    public String getCode() {
        return code;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aeroport other = (Aeroport) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aeroport{" + "code=" + code + ", nom=" + nom + ", zone=" + zone + '}';
    }
    
    
    /**
     * Test de la question 1
     */
    public static void testQ1()
    {
        Aeroport a1 = new Aeroport("LIL","Lille Lesquin",1);
        Aeroport a2 = new Aeroport("LIL","Paris CDG",-1);
        Aeroport a3 = new Aeroport(null,null,4);
        System.out.println(a1.toString());
        System.out.println(a2.toString());
        System.out.println(a3.toString());
        
        System.out.println( a1.equals(a2)); // true
        System.out.println( a1.equals(a3)); // false
    }
    
    
    public static void testQ9()
    {
        Aeroport lille = new Aeroport("LIL","Lille Lesquin",1);
        Aeroport paris = new Aeroport("CDG","Paris Charles de Gaulle",1);
        Aeroport marseille = new Aeroport("MRS","Marseille Provence",2);
        Aeroport lille2 = new Aeroport("LIL","Lille Lesquin 2",1);
        
        System.out.println( lille.compareTo(paris)); // doit etre positif
        System.out.println( lille.compareTo(marseille)); // doit etre negatif
        System.out.println( lille.compareTo(lille2)); // doit etre nul
    }
    
    public static void main(String[] args) {
        //testQ1();
        testQ9();
    }
    
    
    
}
