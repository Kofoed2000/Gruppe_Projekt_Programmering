package ordination;

import java.util.ArrayList;

public class Patient {
    private String cprnr;
    private String navn;
    private double vaegt;
    private ArrayList<Ordination> ordinationer = new ArrayList<>();


    public Patient(String cprnr, String navn, double vaegt) {
        this.cprnr = cprnr;
        this.navn = navn;
        this.vaegt = vaegt;
    }

    public String getCprnr() {
        return cprnr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getVaegt(){
        return vaegt;
    }

    public void setVaegt(double vaegt){
        this.vaegt = vaegt;
    }


    /**
     * Returnerer en liste over patientens ordinationer.
     * Kræver: Metoden kræver ingen forudsætninger.
     */
    public ArrayList<Ordination> getOrdinationer() {

        return new ArrayList<Ordination>(ordinationer);

    }

    /**
     * Tilføjer en ordination til patientens liste af ordinationer, hvis den ikke allerede findes i listen.
     * Kræver: Ordinationen må ikke være null.
     */
    public void addOrdination(Ordination o) {

        if (!ordinationer.contains(o)) {
            ordinationer.add(o);
        }

    }


    @Override
    public String toString(){
        return navn + "  " + cprnr;
    }

}
