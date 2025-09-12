package ordination;

import java.time.LocalDate;

public class DagligFast extends Ordination{

    private Dosis[] antalDosis = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
    }

    public DagligFast(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    public Dosis[] getDosis() {
        return antalDosis;
    }

    @Override
    public double samletDosis() {
        return 0;
    }

    @Override
    public double doegnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return "";
    }
    // TODO

}
