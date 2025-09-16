package ordination;

import controller.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {

        @Test
    void givDosisIndenForInterval1() {

        Laegemiddel laegemiddel1 = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");

        //Arrange
        PN pn1 = new PN(LocalDate.of(2025,9,10),LocalDate.of(2025,9,19), laegemiddel1);

        //Act
        boolean actual = pn1.givDosis(LocalDate.of(2025,9,12));
        boolean expected = true;

        //Assert
        assertEquals(expected, actual);
        assertEquals(1,pn1.getAntalGangeGivet());
    }
        @Test
    void givDosisIndenForInterval2() {

        Laegemiddel laegemiddel1 = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");

        //Arrange
        PN pn2 = new PN(LocalDate.of(2025,9,10),LocalDate.of(2025,9,19), laegemiddel1);

        //Act
        boolean actual = pn2.givDosis(LocalDate.of(2025,9,19));
        boolean expected = true;

        //Assert
        assertEquals(expected, actual);
        assertEquals(1,pn2.getAntalGangeGivet());
    }
        @Test
    void givDosisUdenForInterval() {

        Laegemiddel laegemiddel1 = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");

        //Arrange
        PN pn3 = new PN(LocalDate.of(2025,9,10),LocalDate.of(2025,9,19), laegemiddel1);

        //Act
        boolean actual = pn3.givDosis(LocalDate.of(2025,10,5));
        boolean expected = false;

        //Assert
        assertEquals(expected, actual);
        assertEquals(0,pn3.getAntalGangeGivet());
    }


    @Test
    void doegnDosis1Gang() {
        Laegemiddel laegemiddel1 = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");

        //Arrange
        PN pn4 = new PN(LocalDate.of(2025,9,8),LocalDate.of(2025,9,13), laegemiddel1);
        pn4.givDosis(LocalDate.of(2025,9,9));
        pn4.setAntalEnheder(4.5);

        //Act
        double actual = pn4.doegnDosis();
        double expected = 0.75;

        //Assert
        assertEquals(expected, actual);
        assertEquals(1,pn4.getAntalGangeGivet());

    }
        @Test
    void doegnDosisEnOgEnHalvGang() {
        Laegemiddel laegemiddel1 = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");

        //Arrange
        PN pn4 = new PN(LocalDate.of(2025,9,2),LocalDate.of(2025,9,21), laegemiddel1);
        pn4.setAntalDoser(10);
        pn4.setAntalEnheder(1.5);

        //Act
        double actual = pn4.doegnDosis();
        double expected = 0.75;

        //Assert
        assertEquals(expected, actual);
        assertEquals(10,pn4.getAntalGangeGivet());
    }

        @Test
    void samletDosis17Doser() {
        Laegemiddel laegemiddel1 = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");

        //Arrange
        PN pn5 = new PN(LocalDate.of(2025,9,2),LocalDate.of(2025,9,21), laegemiddel1);
        pn5.setAntalDoser(17);
        pn5.setAntalEnheder(5.58);

        //Act
        double actual = pn5.samletDosis();
        double expected = 94.86;

        //Assert
        assertEquals(expected, actual);
        assertEquals(17,pn5.getAntalGangeGivet());
    }
        @Test
    void samletDosis550Doser() {
        Laegemiddel laegemiddel1 = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");

        //Arrange
        PN pn6 = new PN(LocalDate.of(2025,9,2),LocalDate.of(2025,9,21), laegemiddel1);
        pn6.setAntalDoser(550);
        pn6.setAntalEnheder(12.2);

        //Act
        double actual = pn6.samletDosis();
        double expected = 6710;

        //Assert
        assertEquals(expected, actual);
    }

}