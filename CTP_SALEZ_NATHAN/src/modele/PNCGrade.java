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
public class PNCGrade extends PNC {
       
    Grade grade;
    
    
    int nbHeuresVol;

    
    /**
     * 
     * @param nom
     * @param prenom
     * @param nbHeuresVol si négatif, alors l'attribut associé vaudra 0
     */
    public PNCGrade(String nom, String prenom, int nbHeuresVol) {
        super(nom, prenom);
        if( nbHeuresVol >= 0)
            this.nbHeuresVol = nbHeuresVol;
        else
            this.nbHeuresVol = 0;
        
        this.grade = Grade.getGrade(this.nbHeuresVol);
    }

    
    @Override
    public String toString() {
        return "PNCGrade{" + super.toString() + ", grade=" + grade.toString() + ", nbHeuresVol=" + nbHeuresVol + '}';
    }
    
    
    /**
     * augmente le grade du PNC si son nombre d'heures le permet.
     */
    public void miseAJourGrade()
    {
        if( this.nbHeuresVol > this.grade.getNbHeuresMaximum() )
            this.grade = this.grade.getSuivant();
    }
    
    
    /**
     * Met à jour le nombre d'heures de vol du PNC.
     * @param v 
     */
    public void effectuerVol(Vol v)
    {
        if( v == null)
            return;
        
        this.nbHeuresVol += v.getDuree();
        
        this.miseAJourGrade();
    }
    
    
    public static void testQ5()
    {
        PNCGrade p1 = new PNCGrade("NOM","PRENOM",20000);
        PNCGrade p2 = new PNCGrade("NOM","PRENOM",-5);
        
        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }
    
    
    public static void main(String[] args) {
        testQ5();
    }
    
    
    
    
    
    
    
    
    
}
