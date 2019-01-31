/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author user
 */
public enum Grade {
    STAGIAIRE(2000),
    ECHELON1(10000),
    ECHELON2(30000),
    EXCEPTIONNEL(50000),
    HORSCLASSE(Integer.MAX_VALUE);
    
    private int nbHeuresMaximum;

    private Grade(int nbHeuresMaximum) {
        switch(nbHeuresMaximum)
        {
            case 2000 :
            case 10000 :
            case 30000 :
            case 50000 :
            case Integer.MAX_VALUE :
                this.nbHeuresMaximum = nbHeuresMaximum;
            break;
            
            default : System.err.println("Nombre d'heures invalide.");
        }
    }

    public int getNbHeuresMaximum() {
        return nbHeuresMaximum;
    }
    
    
    
/**
 * Renvoie le grade associé au nombre d'heures donné en paramètre.
 * @param nbHeures
 * @return 
 */
    public static Grade getGrade(int nbHeures) {
        if( nbHeures < 2000)
            return STAGIAIRE;
        
        if( 2000 <= nbHeures && nbHeures < 10000)
            return ECHELON1;
        
        if( 10000 <= nbHeures && nbHeures < 30000)
            return ECHELON2;
        
        if( 30000 <= nbHeures && nbHeures < 50000)
            return EXCEPTIONNEL;
        
        return HORSCLASSE;
    }
    
    
    /**
     * Renvoie le grade suivant le grade actuel de l'énumération
     * @return 
     */
    public Grade getSuivant()
    {
        if( this.nbHeuresMaximum == 2000)
            return ECHELON1;
        
        if( this.nbHeuresMaximum == 10000)
            return ECHELON2;
        
        if( this.nbHeuresMaximum == 30000)
            return EXCEPTIONNEL;
        
        return HORSCLASSE;
    }

    @Override
    public String toString() {
        switch( this.nbHeuresMaximum )
        {
            case 2000 : 
                return  "Grade{ STAGIAIRE " + "nbHeuresMaximum=" + nbHeuresMaximum + '}';
            
            case 10000 :
                return  "Grade{ ECHELON 1 " + "nbHeuresMaximum=" + nbHeuresMaximum + '}';
                
            case 30000 :
                return  "Grade{ ECHELON 2 " + "nbHeuresMaximum=" + nbHeuresMaximum + '}';
                
            case 50000 :
                return  "Grade{ EXCEPTIONNEL " + "nbHeuresMaximum=" + nbHeuresMaximum + '}';
                
            default :
                return  "Grade{ HORS CLASSE " + "nbHeuresMaximum=" + nbHeuresMaximum + '}';
        }
    }
    
    
    
    
    
    
    
    
    
}
