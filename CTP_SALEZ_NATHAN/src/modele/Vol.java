/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author user
 */
public class Vol implements Comparable<Vol> {
    
    Aeroport aDepart;
    
    
    Aeroport aArrivee;
    
    
    HashSet<PNC> equipage;
    
    
    Date dateDepart;
    
    
    Date dateArrivee;

    
    /**
     * Si les données en paramètre ne sont pas cohérentes, un message d'erreur s'affiche.
     * Néanmoins, l'objet vol sera créé.
     * @param aDepart si vaut null, alors l'attribut associé sera construit par : new Aeroport("LIL", "Lille Lesquin", 0);
     * @param aArrivee si vaut null, alors l'attribut associé sera construit par : new Aeroport("LIL", "Lille Lesquin", 0);
     * @param dateDepart
     * @param dateArrivee 
     */
    public Vol(Aeroport aDepart, Aeroport aArrivee, Date dateDepart, Date dateArrivee) {
        
        buildAirports(aDepart, aArrivee);
        buildDates(dateDepart, dateArrivee);
        equipage = new HashSet<>();
        
    }
    
    
    /**
     * Utilisé dans le constructeur par données.
     * Visibilité private, car l'utilisateur n'a pas à modifier les attributs concernés quand il veut.
     * @param aDepart
     * @param aArrivee 
     */
    private void buildAirports(Aeroport aDepart, Aeroport aArrivee)
    {
        if( aDepart != null)
            this.aDepart = aDepart;
        else
        {
            this.aDepart = new Aeroport("LIL", "Lille Lesquin", 0);
            System.err.println("Attention, aDepart vaut null ==> utilisation du constructeur donné dans la Javadoc.");
        }
        
        if( aArrivee != null)
            this.aArrivee = aArrivee;
        else
        {
            this.aArrivee = new Aeroport("LIL", "Lille Lesquin", 0);
            System.err.println("Attention, aArrivee vaut null ==> utilisation du constructeur donné dans la Javadoc.");
        }
        
        if( this.aDepart.equals(this.aArrivee))
        {
            System.err.println("Attention, aéroports de départ et d'arrivée identiques !");
        }
    }
    
    
    /**
     * Utilisé dans le constructeur par données.
     * Visibilité private, car l'utilisateur n'a pas à modifier les attributs concernés quand il veut.
     * Si les formats sont incohérents, un message d'avertissement apparait.
     * @param dateDepart si vaut null, l'attribut associé vaudra la date correspondant à "01/01/1970 00:00"
     * @param dateArrivee si vaut null, l'attribut associé vaudra la date correspondant à "01/01/1970 00:00"
     */
    private void buildDates(Date dateDepart, Date dateArrivee)
    {        
        if( dateDepart != null)
            this.dateDepart = dateDepart;
        else
            this.dateDepart = getDefaultDate();
        
        if( dateArrivee != null)
            this.dateArrivee = dateArrivee;
        else
            this.dateArrivee = getDefaultDate();    // 2 appels possibles, pour éviter que dateArrivee et dateDepart pointent sur la même date.
        
        if( dateArrivee.before(dateDepart) )
        {
            System.err.println("La date d'arrivée est avant la date de départ.");
        }
        else if( dateArrivee.equals(dateDepart) )
        {
            System.err.println("La date d'arrivée correspond à la date de départ.");
        }
    }

    
    /**
     * on compare d'abord selon :
     * date de depart CROISSANT
     * date d'arrivée CROISSANT
     * code aéroport départ CROISSANT
     * code aéroport arrivée CROISSANT
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Vol o) {
        
        // date de depart CROISSANT
        int cmpDateDepart = this.dateDepart.compareTo(o.dateDepart);
        if( cmpDateDepart != 0)
            return cmpDateDepart;
        else
        {
            // date d'arrivée CROISSANT
            int cmpDateArrivee = this.dateArrivee.compareTo(o.dateArrivee);
            if( cmpDateArrivee != 0)
                return cmpDateArrivee;
            else
            {
                // code aéroport départ CROISSANT
                int cmpAeroportDepart = this.aDepart.compareTo(o.aDepart);
                if( cmpAeroportDepart != 0)
                    return cmpAeroportDepart;
                else
                {
                    // code aéroport arrivée CROISSANT
                    int cmpAeroportArrivee = this.aArrivee.compareTo(o.aArrivee);
                    return cmpAeroportArrivee;
                }
            }
        }
    }
    
    
    
    
    
    /**
     * Utilisé dans lé méthode buildDates
     * Visibilité private, car inutile pour l'utilisateur. 
     * Peut arrêter le programme si problème de format.
     * @return la date correspondant à "01/01/1970 00:00"
     */
    private static Date getDefaultDate()
    {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.FRANCE);
        Date dDefaut = null;
        try
        {
            dDefaut = format.parse("01/01/1970 00:00");
        }
        catch( ParseException e)
        {
            System.err.println("Format de la date par défaut non respectée.");
            System.exit(-1);
        }
        
        return dDefaut;
    }

    
    /**
     * Ajoute le PNC à l'équipage du vol, si c'est possible.
     * @param personne
     */
    public void ajouterPNC(PNC personne)
    {
        if( personne == null)
            return;
        
        if( equipage.contains(personne))
            return;
        
        equipage.add(personne);
    }
    
    
    /**
     * @return la durée du vol en heures
     */
    public long getDuree()
    {
        return getDifferenceHeures(dateDepart, dateArrivee);
    }
    
    /**
     * Retourne le nombre d'heures en valeur absolue entre la date d2 et la date d1.
     */
    private static long getDifferenceHeures(Date d1, Date d2)
    {
        long time1 = d1.getTime();
        long time2 = d2.getTime();
        long heureEnMs = 60*60*1000; // nb de millisecondes dans une heure;
        long time1EnH = time1 / heureEnMs;
        long time2EnH = time2 / heureEnMs;
        return Math.abs( time1EnH - time2EnH);
    }
    
    /**
     * 
     * @return un booléan indiquant si l'équipage affecté au vol est complet ou non.
     */
    public boolean equipageComplet()
    {
        long dureeVol = getDuree();
        if( dureeVol < 2)
        {
            return this.equipage.size() >= 2;
        }
        
        if( dureeVol < 4)
        {
            return this.equipage.size() >= 4;
        }
        
        return this.equipage.size() >= 7;
    }
    
    
    /**
     * On renvoie une copie de l'aéroport pour respecter l'encapsulation.
     * @return 
     */
    public Aeroport getaDepart() {
        return new Aeroport(aDepart);
    }

    
    /**
     * On renvoie une copie de l'aéroport pour respecter l'encapsulation.
     * @return 
     */
    public Aeroport getaArrivee() {
        return new Aeroport(aArrivee);
    }
    
    
    /**
     * @param v1
     * @param v2
     * @return la durée en heures entre le vol v1 et le vol 2
     */
    public static long getDureeInterVols(Vol v1, Vol v2)
    {
        if( v1 == null || v2 == null || v1.equals(v2) )
            return 0;
        
        if( v1.compareTo(v2) > 0) // v1 > v2
        {
            return getDifferenceHeures(v1.dateDepart,v2.dateArrivee);
        }
        else
        {
            return getDifferenceHeures(v1.dateArrivee,v2.dateDepart);
        }
    }
    
    /**
     * 
     * @param v
     * @return true si v est compatible avec l'instance actuelle, false sinon.
     */
    public boolean estCompatible(Vol v)
    {
        if( v == null)
            return false;
        else
        {
            long dureeInterVolsEnHeures = getDureeInterVols(this, v);
            // 2 cas suivants l'égalité entre les aéroports
            if( this.aArrivee.equals(v.aDepart) || this.aDepart.equals(v.aArrivee) )
            {
                // meme aeroport => durée = 2 heures
                return dureeInterVolsEnHeures >= 2;
            }
            else
            {
                // aéroports différents => durée = 12 heures
                return dureeInterVolsEnHeures >= 12;
            }
        }
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.aDepart);
        hash = 97 * hash + Objects.hashCode(this.aArrivee);
        hash = 97 * hash + Objects.hashCode(this.dateDepart);
        hash = 97 * hash + Objects.hashCode(this.dateArrivee);
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
        final Vol other = (Vol) obj;
        if (!Objects.equals(this.aDepart, other.aDepart)) {
            return false;
        }
        if (!Objects.equals(this.aArrivee, other.aArrivee)) {
            return false;
        }
        if (!Objects.equals(this.dateDepart, other.dateDepart)) {
            return false;
        }
        if (!Objects.equals(this.dateArrivee, other.dateArrivee)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vol{" + "aDepart=" + aDepart.toString() + ", aArrivee=" + aArrivee.toString() + ", dateDepart=" + dateDepart.toString() + ", dateArrivee=" + dateArrivee.toString() + '}';
    }
    
    
    public static void testQ3()
    {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.FRANCE);
        Aeroport lille = new Aeroport("LIL","Lille Lesquin",1);
        Aeroport paris = new Aeroport("CDG","Paris Charles de Gaulle",-1);
        
        Vol v1 = null;
        Vol v2 = null;
        Vol v3 = null;
        try
        {
            v1 = new Vol(paris,lille,format.parse("31/01/2019 11:45"),format.parse("31/01/2019 12:45")); // aucun message d'erreur.
            v2 = new Vol(lille,paris,format.parse("31/01/2019 11:45"),format.parse("31/01/2019 10:45")); // dates invalides
            v3 = new Vol(lille,lille,format.parse("31/01/2019 11:45"),format.parse("31/01/2019 12:45")); // aéroports invalides
        }
        catch( ParseException e)
        {
            System.err.println("Format de la date par défaut non respectée.");
            System.exit(-1);
        }
        
        System.out.println(v1.toString());
        System.out.println(v2.toString());
        System.out.println(v3.toString());
    }
    
    
    public static void testQ7()
    {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.FRANCE);
        Aeroport lille = new Aeroport("LIL","Lille Lesquin",1);
        Aeroport paris = new Aeroport("CDG","Paris Charles de Gaulle",-1);
        
        PNC p1 = new PNC("SALEZ","NAYSSON");
        PNCGrade p2 = new PNCGrade("NOM","PRENOM",20000);
        PNCAffectAeroports p3 = new PNCAffectAeroports("bb", "bb");
        
        Vol v1 = null;
        
        try
        {
            v1 = new Vol(paris,lille,format.parse("31/01/2019 11:45"),format.parse("31/01/2019 12:45")); // aucun message d'erreur.
        }
        catch( ParseException e)
        {
            System.err.println("Format de la date par défaut non respectée.");
            System.exit(-1);
        }
        
        System.out.println( p1.estCompatibleAvec(v1) ); // true
        System.out.println( p2.estCompatibleAvec(v1)); // true
        System.out.println( p3.estCompatibleAvec(v1)); // false
        
        p3.ajouterAeroport(paris);
        System.out.println( p3.estCompatibleAvec(v1)); // false
        
        p3.ajouterAeroport(lille);
        System.out.println( p3.estCompatibleAvec(v1)); // true
        
    }
    
    
    public static void testQ8()
    {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.FRANCE);
        Aeroport lille = new Aeroport("LIL","Lille Lesquin",1);
        Aeroport paris = new Aeroport("CDG","Paris Charles de Gaulle",-1);
        
        PNC p1 = new PNC("SALEZ","NAYSSON");
        PNCGrade p2 = new PNCGrade("NOM","PRENOM",20000);
        PNCAffectAeroports p3 = new PNCAffectAeroports("bb", "bb");
        PNC p4 = new PNC("SALEZ","NAYSSO");
        p3.ajouterAeroport(paris);
        p3.ajouterAeroport(lille);
        
        Vol v1 = null;
        
        try
        {
            v1 = new Vol(paris,lille,format.parse("31/01/2019 11:45"),format.parse("31/01/2019 14:45")); // aucun message d'erreur.
        }
        catch( ParseException e)
        {
            System.err.println("Format de la date par défaut non respectée.");
            System.exit(-1);
        }
        
        v1.ajouterPNC(p1);
        v1.ajouterPNC(p2);
        v1.ajouterPNC(p3);
        v1.ajouterPNC(p4);
        
        System.out.println( v1.getDuree()); // nb d'heures
        System.out.println( v1.equipageComplet()); // true
        
        
    }
    
    
    public static void testQ10()
    {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.FRANCE);
        Aeroport lille = new Aeroport("LIL","Lille Lesquin",1);
        Aeroport paris = new Aeroport("CDG","Paris Charles de Gaulle",1);
        Aeroport marseille = new Aeroport("MRS","Marseille Provence",2);
        Aeroport lille2 = new Aeroport("LIL","Lille Lesquin 2",1);
        
        PNC p1 = new PNC("SALEZ","NAYSSON");
        PNCGrade p2 = new PNCGrade("NOM","PRENOM",20000);
        PNCAffectAeroports p3 = new PNCAffectAeroports("bb", "bb");
        PNC p4 = new PNC("SALEZ","NAYSSO");
        p3.ajouterAeroport(paris);
        p3.ajouterAeroport(lille);
        
        Vol v1 = null;
        Vol v2 = null;
        Vol v3 = null;
        Vol v4 = null;
        Vol v5 = null;
        Vol v6 = null;
        
        try
        {
            v1 = new Vol(paris,lille,format.parse("31/01/2019 11:45"),format.parse("31/01/2019 14:45")); 
            v2 = new Vol(lille,paris,format.parse("31/01/2019 12:45"),format.parse("31/01/2019 13:45")); // date depart croissant
            v3 = new Vol(paris,lille,format.parse("31/01/2019 11:45"),format.parse("31/01/2019 15:45")); // date arrivee croissant
            v4 = new Vol(lille,paris,format.parse("31/01/2019 11:45"),format.parse("31/01/2019 14:45")); // aéroport départ croissant
            v5 = new Vol(paris,marseille,format.parse("31/01/2019 11:45"),format.parse("31/01/2019 14:45")); // aeroport arrivee croissant
            v6 = new Vol(paris,lille,format.parse("31/01/2019 11:45"),format.parse("31/01/2019 14:45")); // egalite
        }
        catch( ParseException e)
        {
            System.err.println("Format de la date par défaut non respectée.");
            System.exit(-1);
        }
        
        System.out.println(v1.compareTo(v2)); // négatif
        System.out.println(v3.compareTo(v1)); // positif
        System.out.println(v1.compareTo(v4)); // négatif
        System.out.println(v5.compareTo(v1)); // positif
        System.out.println(v6.compareTo(v1)); // nul
        
    }
    
    
    public static void testQ12()
    {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);
        Aeroport paris = new Aeroport("CDG", "Paris Charles de Gaulle", 1);
        Aeroport lille = new Aeroport("LIL", "Lille Lesquin", 1);
        try
        {
            Vol v1 = new Vol(paris, lille, 
                    format.parse("31/01/2019 11:45"), 
                    format.parse("31/01/2019 12:30"));
            Vol v2 = new Vol(paris, lille, 
                    format.parse("31/01/2019 17:45"), 
                    format.parse("31/01/2019 18:30"));
            Vol v3 = new Vol(lille, paris, 
                    format.parse("31/01/2019 12:45"), 
                    format.parse("31/01/2019 13:30"));
            Vol v4 = new Vol(paris, lille, 
                    format.parse("02/02/2019 11:45"), 
                    format.parse("02/02/2019 12:30"));
            Vol v5 = new Vol(lille, paris, 
                    format.parse("31/01/2019 15:45"), 
                    format.parse("31/01/2019 16:30"));
            System.out.println("Compatibilité");
            System.out.println(v1.estCompatible(v2)); // false
            System.out.println(v1.estCompatible(v3)); // false
            System.out.println(v1.estCompatible(v4)); // true
            System.out.println(v1.estCompatible(v5)); // true
            System.out.println(v2.estCompatible(v1)); // false
            System.out.println(v3.estCompatible(v1)); // false
            System.out.println(v4.estCompatible(v1)); // true
            System.out.println(v5.estCompatible(v1)); // true
        }
        catch( ParseException e)
        {
            System.err.println("Format de la date par défaut non respectée.");
            System.exit(-1);
        }
    }
    
    
    public static void main(String[] args) {
        //testQ3();
        //testQ7();
        //testQ8();
        //testQ10();
        testQ12();
    }
    
}
