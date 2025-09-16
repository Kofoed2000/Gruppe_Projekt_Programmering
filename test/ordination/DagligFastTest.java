package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    void opretDosis_1_Enhed() {
        DagligFast df = new DagligFast(LocalDate.of(2025, 9, 15), LocalDate.of(2025, 9, 15));
        Dosis dosis1 = df.opretDosis(LocalTime.of(8, 0), 1.0);
        Dosis dosis2 = df.opretDosis(LocalTime.of(13, 0), 1.0);
        Dosis dosis3 = df.opretDosis(LocalTime.of(18, 0), 1.0);
        Dosis dosis4 = df.opretDosis(LocalTime.of(1, 0), 1.0);

        Dosis[] doser = df.getDoser();

        assertEquals(dosis1, doser[0]);
        assertEquals(dosis2, doser[1]);
        assertEquals(dosis3, doser[2]);
        assertEquals(dosis4, doser[3]);

        assertEquals(LocalTime.of(8, 0), doser[0].getTid());
        assertEquals(1.0, doser[0].getAntal());

        assertEquals(LocalTime.of(13, 0), doser[1].getTid());
        assertEquals(1.0, doser[1].getAntal());

        assertEquals(LocalTime.of(18, 0), doser[2].getTid());
        assertEquals(1.0, doser[2].getAntal());

        assertEquals(LocalTime.of(1, 0), doser[3].getTid());
        assertEquals(1.0, doser[3].getAntal());

    }

    @Test
    void opretDosisFlereEnheder() {
        DagligFast df = new DagligFast(LocalDate.of(2025, 9, 15), LocalDate.of(2025, 9, 15));
        Dosis dosis1 = df.opretDosis(LocalTime.of(8, 0), 2.0);
        Dosis dosis2 = df.opretDosis(LocalTime.of(13, 0), 3.0);
        Dosis dosis3 = df.opretDosis(LocalTime.of(18, 0), 4.0);
        Dosis dosis4 = df.opretDosis(LocalTime.of(1, 0), 5.0);

        Dosis[] doser = df.getDoser();

        assertEquals(dosis1, doser[0]);
        assertEquals(dosis2, doser[1]);
        assertEquals(dosis3, doser[2]);
        assertEquals(dosis4, doser[3]);

        assertEquals(LocalTime.of(8, 0), doser[0].getTid());
        assertEquals(2.0, doser[0].getAntal());

        assertEquals(LocalTime.of(13, 0), doser[1].getTid());
        assertEquals(3.0, doser[1].getAntal());

        assertEquals(LocalTime.of(18, 0), doser[2].getTid());
        assertEquals(4.0, doser[2].getAntal());

        assertEquals(LocalTime.of(1, 0), doser[3].getTid());
        assertEquals(5.0, doser[3].getAntal());

    }


    @Test
    void samletDosisFlereDage() {
        DagligFast df = new DagligFast(LocalDate.of(2025, 9, 15), LocalDate.of(2025, 9, 20));
        df.opretDosis(LocalTime.of(8, 0), 2.0);
        df.opretDosis(LocalTime.of(13, 0), 2.0);
        df.opretDosis(LocalTime.of(18, 0), 2.0);
        df.opretDosis(LocalTime.of(1, 0), 2.0);
        double actual = df.samletDosis();
        double expected = 48.0;
        assertEquals(expected, actual);
    }

    @Test
    void samletDosis_1_Dag() {
        DagligFast df = new DagligFast(LocalDate.of(2025, 9, 15), LocalDate.of(2025, 9, 15));
        df.opretDosis(LocalTime.of(8, 0), 2.0);
        df.opretDosis(LocalTime.of(13, 0), 2.0);
        df.opretDosis(LocalTime.of(18, 0), 2.0);
        df.opretDosis(LocalTime.of(1, 0), 2.0);
        double actual = df.samletDosis();
        double expected = 8;
        assertEquals(expected, actual);
    }

    @Test
    void doegnDosis() {
        //return samletDosis() / antalDage();
        DagligFast df = new DagligFast(LocalDate.of(2025, 9, 15), LocalDate.of(2025, 9, 20));
        df.opretDosis(LocalTime.of(8, 0), 2.0);
        df.opretDosis(LocalTime.of(13, 0), 2.0);
        df.opretDosis(LocalTime.of(18, 0), 2.0);
        df.opretDosis(LocalTime.of(1, 0), 2.0);
        double actual = df.doegnDosis();
        double expected = 8.0;
        assertEquals(expected, actual);

    }


}