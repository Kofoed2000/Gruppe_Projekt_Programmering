package controller;

import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


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

        startDen = LocalDate.of(2025, 9, 15);
        slutDen = LocalDate.of(2025, 9, 10);
        LocalTime[] klokkeslet = new LocalTime[2];
        double[] enheder = new double[2];


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.opretDagligSkaevOrdination(startDen, slutDen, patient, lægemiddel, klokkeslet, enheder));
        assertEquals("Start dato skal være før slutdato👺", exception.getMessage());

    }

    @Test
    void testOpretDagligSkaevOrdinationIllegalArugmentExceptionKlokkesletLængde() {

        LocalTime[] klokkeslet = new LocalTime[6];
        double[] enheder = new double[12];


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.opretDagligSkaevOrdination(startDen, slutDen, patient, lægemiddel,
                        klokkeslet, enheder));
        assertEquals("Antallet af klokkeslæt og antallet af enheder skal være ens👺", exception.getMessage());

    }

    @Test
    void testOpretDagligSkaevOrdinationFindOrdinationPaaPatient() {

        LocalTime[] klokkeslet = new LocalTime[2];
        double[] enheder = new double[2];

        DagligSkaev ds = controller.opretDagligSkaevOrdination(startDen, slutDen, patient, lægemiddel, klokkeslet, enheder);
        assertEquals(1, patient.getOrdinationer().size());
        assertEquals(ds, patient.getOrdinationer().getFirst());

    }

    @Test
    void testOrdinationPNAnvendtIllegalArgumentExceptionGyldighedsperiode() {

        PN pn = controller.opretPNOrdination(startDen, slutDen, patient, lægemiddel, 5);
        LocalDate dato = LocalDate.of(2025, 9, 25);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.ordinationPNAnvendt(pn, dato));
        assertEquals("Datoen er ikke indenfor ordinationens gyldighedsperiode👺", exception.getMessage());
    }

    @Test
    void testOrdinationsPNAnvendtFindPNAnvendtPaaPatient() {

        PN pn = controller.opretPNOrdination(startDen, slutDen, patient, lægemiddel, 5);
        LocalDate dato = LocalDate.of(2025, 9, 15);
        controller.ordinationPNAnvendt(pn, dato);
        assertEquals(1, pn.getAntalGangeGivet());

    }

    @Test
    void anbefaletDosisPrDoegnLetVægt() {

        double vægt = 24.0;
        patient = new Patient("121256-0512", "Jane Jensen", vægt);
        double expectedDosis = vægt * lægemiddel.getEnhedPrKgPrDoegnLet();
        double actualDosis = controller.anbefaletDosisPrDoegn(patient, lægemiddel);
        assertEquals(expectedDosis, actualDosis);

    }

    @Test
    void anbefaletDosisPrDoegnMellemVægt() {

        double vægt1 = 26.0;
        patient = new Patient("121256-0512", "Jane Jensen", vægt1);
        double expectedDosis1 = vægt1 * lægemiddel.getEnhedPrKgPrDoegnNormal();
        double actualDosis1 = controller.anbefaletDosisPrDoegn(patient, lægemiddel);
        assertEquals(expectedDosis1, actualDosis1);

        double vægt2 = 119.0;
        patient = new Patient("121256-0512", "Jane Jensen", vægt2);
        double expectedDosis2 = vægt2 * lægemiddel.getEnhedPrKgPrDoegnNormal();
        double actualDosis2 = controller.anbefaletDosisPrDoegn(patient, lægemiddel);
        assertEquals(expectedDosis2, actualDosis2);

    }

    @Test
    void anbefaletDosisPrDoegnTungVægt() {

        double vægt = 121.0;
        patient = new Patient("121256-0512", "Jane Jensen", vægt);
        double expectedDosis = vægt * lægemiddel.getEnhedPrKgPrDoegnTung();
        double actualDosis = controller.anbefaletDosisPrDoegn(patient, lægemiddel);
        assertEquals(expectedDosis, actualDosis);
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {

        double vægtStart = 30.0;
        double vægtSlut = 75.0;

        Patient patient = controller.opretPatient("121256-0512", "Jane Jensen", 63.4);
        Patient patient1 = controller.opretPatient("070985-1153", "Finn Madsen", 70.0);
        Patient patient2 = controller.opretPatient("050972-1233", "Hans Jørgensen", 60.0);
        Patient patient3 = controller.opretPatient("011064-1522", "Ulla Nielsen", 50.0);

        Laegemiddel lægemiddel1 = new Laegemiddel("Paracetamol", 1, 1.5, 2, "ML");
        Laegemiddel lægemiddel2 = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Laegemiddel lægemiddel3 = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
        Laegemiddel lægemiddel4 = new Laegemiddel("Methotrexat", 0.01, 0.015, 0.2, "Styk");

        controller.opretPNOrdination(startDen, slutDen, patient, lægemiddel1, 5);
        controller.opretPNOrdination(startDen, slutDen, patient1, lægemiddel2, 1);
        controller.opretPNOrdination(startDen, slutDen, patient2, lægemiddel3, 3);
        controller.opretPNOrdination(startDen, slutDen, patient3, lægemiddel4, 4);


        int actual1 = controller.antalOrdinationerPrVægtPrLægemiddel(vægtStart, vægtSlut, lægemiddel1);
        int actual2 = controller.antalOrdinationerPrVægtPrLægemiddel(vægtStart, vægtSlut, lægemiddel2);
        int actual3 = controller.antalOrdinationerPrVægtPrLægemiddel(vægtStart, vægtSlut, lægemiddel3);
        int actual4= controller.antalOrdinationerPrVægtPrLægemiddel(vægtStart, vægtSlut, lægemiddel4);


        assertEquals(1, actual1);
        assertEquals(1, actual2);
        assertEquals(1, actual3);
        assertEquals(1, actual4);

    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddelForkertVægt() {

        //vægtStart må ikke være højere end vægtSlut
        double vægtStart = 50.0;
        double vægtSlut = 25.0;

        Patient patient = controller.opretPatient("121256-0512", "Jane Jensen", 40.4);
        Laegemiddel lægemiddel1 = new Laegemiddel("Paracetamol", 1, 1.5, 2, "ML");
        controller.opretPNOrdination(startDen, slutDen, patient, lægemiddel1, 5);
        int actual = controller.antalOrdinationerPrVægtPrLægemiddel(vægtStart, vægtSlut, lægemiddel1);


        assertNotEquals(1, actual);

    }

}