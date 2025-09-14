package ordination;

import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination {
    // TODO✅
    private Dosis[] doser = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
    }

    public DagligFast(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    /**
     * Opretter en dosis med det angivne tidspunkt og antal.
     * Dosis placeres i doser[] afhængigt af tidspunktet:
     * Fra kl.6 - kl. 10: doser[0] morgen
     * Kl. 10-14: doser[1] middag
     * Kl. 14-23: doser[2] aften
     * Fra kl. 23 - kl.6: doser[3] nat
     * Hvis der allerede findes en dosis på det angivne tidspunkt, overskrives denne.
     * Returnerer den oprettede dosis.
     * Kræver: antal > 0
     */
    public Dosis opretDosis(LocalTime tid, double antal) {

        Dosis dosis = new Dosis(tid, antal);

        if (tid.getHour() >= 6 && tid.getHour() < 10) {
            doser[0] = dosis;
        } else if (tid.getHour() >= 10 && tid.getHour() < 14) {
            doser[1] = dosis;
        } else if (tid.getHour() >= 14 && tid.getHour() < 23) {
            doser[2] = dosis;
        } else {
            doser[3] = dosis;
        }

        return dosis;
    }

    public Dosis[] getDoser() {
        return doser;
    }


    @Override
    public double samletDosis() {

        double samletDosis = 0;

        for (Dosis dosis : doser) {
            if (dosis != null) {
                samletDosis += dosis.getAntal();
            }
        }
        return samletDosis * antalDage();

    }

    @Override
    public double doegnDosis() {

        return samletDosis() / antalDage();
    }

    @Override
    public String getType() {
        return "Daglig Fast";
    }
}
