/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.HashSet;

/**
 *
 * @author user
 */
public class PNCAffectAeroports extends PNC {

    
    /**
     * On choisit un HashSet car on a définit les méthodes hashcode() et equals() pour les aéroports.
     * Cela garantit l'absence de doublons dans la collection et la recherche d'un aéroport sera plus rapide.
     * 
     */
    private HashSet<Aeroport> ensAeroportsAutorises;
    
    
    
    public PNCAffectAeroports(String nom, String prenom) {
        super(nom, prenom);
        this.ensAeroportsAutorises = new HashSet<>();
    }
    
    
    /**
     * 
     * @param code
     * @return true si l'aéroport avec le code code est autorisé, false sinon.
     */
    public boolean aeroportAutorise(String code)
    {
        if( code == null)
            return false;
        
        if( ensAeroportsAutorises.contains( new Aeroport(code,code,0)) )
            return true;
        else
            return false;
        
    }
    
    
    /**
     * Ajoute l'aéroport a aux aéroports autorisés.
     * @param a 
     */
    public void ajouterAeroport(Aeroport a)
    {
        if( a == null )
            return;
        
        if( !aeroportAutorise(a.getCode()) ) // si a est déjà autorisé alors on ne l'ajoute pas
        {
            this.ensAeroportsAutorises.add(a);
        }
    }

    
    @Override
    public boolean estCompatibleAvec(Vol v) {
        if( v == null)
            return false;
        
        if( aeroportAutorise( v.getaArrivee().getCode()) 
                && aeroportAutorise( v.getaDepart().getCode() ))
            return true;
        else
            return false;
    }
    
    
    
    
    
    public static void testQ6()
    {
        PNCAffectAeroports hot1 = new PNCAffectAeroports("bb", "bb");
        PNCAffectAeroports hot2 = new PNCAffectAeroports("cc", "cc");
        PNCAffectAeroports hot3 = new PNCAffectAeroports("dd", "dd");
        Aeroport lille = new Aeroport("LIL","Lille Lesquin",1);
        Aeroport paris = new Aeroport("CDG","Paris Charles de Gaulle",1);
        
        hot1.ajouterAeroport(paris);
        hot2.ajouterAeroport(lille);
        hot3.ajouterAeroport(paris);
        hot3.ajouterAeroport(lille);
        hot3.ajouterAeroport(paris);
        
        System.out.println( hot1.aeroportAutorise("CDG")); // 
        System.out.println( hot2.aeroportAutorise("CDG"));
        System.out.println( hot3.aeroportAutorise("???"));
        
    }
    
    
    
    public static void main(String[] args) {
        testQ6();
    }
    
    
    
}
