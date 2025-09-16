package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    private ArrayList<Dosis> doser = new ArrayList<>();


    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
    }

    public DagligSkaev(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    /**
     * Samlet dosis for alle doser i perioden.
     * Doserne i listen doser er de daglige doser.
     * Kræver: at der er tilføjet mindst en dosis til doser.
     */
    @Override
    public double samletDosis() {
        double samletDosis = 0;
        for (Dosis d : doser) {
            samletDosis += d.getAntal();
        }
        long daysBetween = ChronoUnit.DAYS.between(getStartDen(), getSlutDen()) + 1;
        return samletDosis * daysBetween;

    }

    /**
     * Døgn dosis er den gennemsnitlige dosis pr. dag i hele perioden.
     * Kræver: at der er tilføjet mindst en dosis til doser.
     */
    @Override
    public double doegnDosis() {

        long daysBetween = ChronoUnit.DAYS.between(getStartDen(), getSlutDen()) + 1;
        return samletDosis() / daysBetween;

    }

    @Override
    public String getType() {
        return "Daglig Skæv";
    }


    public ArrayList<Dosis> getDoser() {
        return new ArrayList<>(doser);
    }

    public void opretDosis(LocalTime tid, double antal) {

        Dosis dosis = new Dosis(tid, antal);
        if (!doser.contains(dosis)) {
            doser.add(dosis);
        }

    }


}
