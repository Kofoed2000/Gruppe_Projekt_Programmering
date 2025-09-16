package controller;

import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ControllerTest {

    private Controller controller;
    private LocalDate startDen;
    private LocalDate slutDen;
    private Patient patient;
    private Laegemiddel lægemiddel;

    @BeforeEach
    void setup() {

        controller = Controller.getTestController();
        startDen = LocalDate.of(2025, 9, 10);
        slutDen = LocalDate.of(2025, 9, 20);
        patient = new Patient("121256-0512", "Jane Jensen", 63.4);
        lægemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "ML");

    }


    @Test
    void testOpretPNOrdinationIllegalArgumentException() {

        startDen = LocalDate.of(2025, 9, 20);
        slutDen = LocalDate.of(2025, 9, 10);



        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.opretPNOrdination(startDen, slutDen, patient, lægemiddel, 3));
        assertEquals("Start dato skal være før slut dato👺", exception.getMessage());

    }

    @Test
    void testOpretPNOrdinationFindOrdinationPaaPatient() {

        PN pn = controller.opretPNOrdination(startDen, slutDen, patient, lægemiddel, 5);
        assertEquals(1, patient.getOrdinationer().size());
        assertEquals(pn, patient.getOrdinationer().get(0));

    }

    @Test
    void testOpretDagligFastOrdinationIllegalArgumentException() {

        startDen = LocalDate.of(2025, 9, 20);
        slutDen = LocalDate.of(2025, 9, 10);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.opretDagligFastOrdination(startDen, slutDen, patient, lægemiddel, 1, 2, 3, 4));
        assertEquals("Start dato skal være før slut dato👺", exception.getMessage());

    }


    @Test
    void testOpretDagligFastOrdinationFindOrdinationPaaPatient() {

        DagligFast df = controller.opretDagligFastOrdination(startDen, slutDen, patient, lægemiddel, 1, 2, 3, 4);
        assertEquals(1, patient.getOrdinationer().size());
        assertEquals(df, patient.getOrdinationer().getFirst());


    }

    @Test
    void testOpretDagligSkaevOrdinationIllegalArgumentExceptionDatoer() {

        DagligSkaev ds = controller.opretDagligSkaevOrdination(startDen, slutDen, patient, lægemiddel, new LocalTime[]{LocalTime.of(8, 0)}, new double[]{1.0});

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.opretDagligSkaevOrdination(startDen, slutDen, patient, lægemiddel,
                        new LocalTime[]{LocalTime.of(8, 0)}, new double[]{1.0}));
        assertEquals("Start dato skal være før slut dato👺", exception.getMessage());

    }

    @Test
    void testOpretDagligSkaevOrdinationIllegalArugmentExceptionKlokkesletLængde() {



        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.opretDagligSkaevOrdination(startDen, slutDen, patient, lægemiddel,
                        new LocalTime[]{LocalTime.of(8, 0)}, new double[]{1.0}));
        assertEquals("Antallet af klokkeslæt og antallet af enheder skal være ens👺", exception.getMessage());




    }

    @Test
    void testOpretDagligSkaevOrdinationFindOrdinationPaaPatient() {


    }

    @Test
    void ordinationPNAnvendt() {
    }

    @Test
    void anbefaletDosisPrDoegn() {
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }

}