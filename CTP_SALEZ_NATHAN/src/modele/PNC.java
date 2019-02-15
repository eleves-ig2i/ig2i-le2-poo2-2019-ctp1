/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.TreeSet;

/**
 * désigne un Personnel Navigant Commercial
 * @author user
 */
public class PNC {  // Personnel Navigant Commercial
    
    private String nom;
    
    
    private TreeSet<Vol> ensVols;
    
    
    private String prenom;

    
    /**
     * 
     * @param nom si vaut null, l'attribut associé vaudra "SALEZ"
     * @param prenom si vaut null, l'attribut associé vaudra "NAYSSON"
     */
    public PNC(String nom, String prenom) {
        if( nom != null)
            this.nom = nom;
        else
            this.nom = "SALEZ";
        
        if( prenom != null)
            this.prenom = prenom;
        else
            this.prenom = "NAYSSON";
        
        ensVols = new TreeSet<>();
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nom);
        hash = 29 * hash + Objects.hashCode(this.prenom);
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
        final PNC other = (PNC) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PNC{" + "nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    
    /**
     * Renvoie un booléan qui définit si le Personnel est compatible avec le vol v
     * @param v
     * @return true si le
     */
    public boolean estCompatibleAvec(Vol v)
    {
        if( v != null)
            return true;
        else
            return false;
    }
    
    
    /**
     * Permet se savoir si le PNC peut effectuer le vol passé en paramètre
     * @param v
     * @return 
     */
    public boolean peutEffectuer(Vol v)
    {
        if ( estCompatibleAvec(v) )
        {
            for(Vol vAEffectuer : this.ensVols)
            {
                if( !vAEffectuer.estCompatible(v) ) // si UN SEUL vol n'est pas compatible avec celui en paramètre, on retourne false
                    return false;
            }
            
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * Affecte le PNC au vol v si c'est possible.
     * @param v
     * @return true si l'affectation a eu lieu, false sinon.
     */
    public boolean ajouterVol(Vol v)
    {
        if( this.peutEffectuer(v) )
        {
            this.ensVols.add(v);
            v.ajouterPNC(this);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    public static void testQ2()
    {
        PNC p1 = new PNC(null,null);
        PNC p2 = new PNC("Nom",null);
        PNC p3 = new PNC("SALEZ","NAYSSON");
        
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        
        System.out.println(p1.equals(p2));  // false
        System.out.println(p1.equals(p3));  // true
        System.out.println(p2.equals(p3));  // false
    }
    
    
    public static void testQ14()
    {
        try
        {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);
            Aeroport paris = new Aeroport("CDG", "Paris Charles de Gaulle", 1);
            Aeroport lille = new Aeroport("LIL", "Lille Lesquin", 1);
            Vol v1 = new Vol(paris, lille, format.parse("31/01/2019 11:45"), format.parse("31/01/2019 12:30"));
            Vol v2 = new Vol(paris, lille, format.parse("31/01/2019 17:45"), format.parse("31/01/2019 18:30"));
            Vol v3 = new Vol(lille, paris, format.parse("31/01/2019 12:45"), format.parse("31/01/2019 13:30"));
            Vol v4 = new Vol(paris, lille, format.parse("02/02/2019 11:45"), format.parse("02/02/2019 12:30"));
            Vol v5 = new Vol(lille, paris, format.parse("31/01/2019 15:45"), format.parse("31/01/2019 16:30"));
            PNC pTest = new PNC("SALEZ", "TEST");
            
            boolean succes = pTest.ajouterVol(v1);
            System.out.println(succes); // true
            
            succes = pTest.ajouterVol(v1);
            System.out.println(succes); // false
            
            succes = pTest.ajouterVol(null);
            System.out.println(succes); // false
            
            succes = pTest.ajouterVol(v2); // false car pas assez d'heures pour faire lille -> paris
            System.out.println(succes);
            
            succes = pTest.ajouterVol(v4); // true
            System.out.println(succes);
            
            succes = pTest.ajouterVol(v5); // true
            System.out.println(succes);
            
            succes = pTest.ajouterVol(v2); // false car arrive à paris à 16h30 et doit décoller de paris à 17h45
            System.out.println(succes);
            
            
            
        }
        catch( ParseException e)
        {}
        
    }
    
    public static void main(String[] args) {
        //testQ2();
        testQ14();
    }
    
    
    
    
}
