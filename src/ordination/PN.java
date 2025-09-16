package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PN extends Ordination {

    private double antalEnheder;
    private int antalGangeGivet = 0;

    public PN(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
    }

    public PN(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }


    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {


        if (givesDen.isBefore(getStartDen()) || givesDen.isAfter(getSlutDen())) {
            return false;
        }

        antalGangeGivet++;
        return true;

    }

    public double doegnDosis() {


        long antalDage = ChronoUnit.DAYS.between(getStartDen(), getSlutDen()) + 1; // +1 fordi begge dage er inklusive
        return (samletDosis() / antalDage);

    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {

        return getAntalGangeGivet() * antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {

        return antalGangeGivet;
    }


    public double getAntalEnheder() {
        return antalEnheder;
    }


    public void setAntalEnheder(double antalEnheder) {
        this.antalEnheder = antalEnheder;
    }

}
