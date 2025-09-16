package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    @Test
    void samletDosis1Dag() {

        //Arrange
        DagligSkaev dst = new DagligSkaev(LocalDate.of(2025,9,15), LocalDate.of(2025,9,20));
        dst.opretDosis(LocalTime.of(10,50), 5);
        dst.opretDosis(LocalTime.of(16,50), 5);
        //Act
        double actual = dst.samletDosis();
        double expected = 60;
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void samletDosisFlereDage() {

        //Arrange
        DagligSkaev dst = new DagligSkaev(LocalDate.of(2025,9,15), LocalDate.of(2025,9,15));
        dst.opretDosis(LocalTime.of(10,50), 5);
        dst.opretDosis(LocalTime.of(16,50), 5);
        //Act
        double actual = dst.samletDosis();
        double expected = 10;
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void doegnDosis1Dag() {

        //Arrange
        DagligSkaev dst = new DagligSkaev(LocalDate.of(2025,9,14), LocalDate.of(2025,9,14));
        dst.opretDosis(LocalTime.of(9,30), 2);
        dst.opretDosis(LocalTime.of(15,00), 1);
        dst.opretDosis(LocalTime.of(21,00), 4);
        //Act
        double actual = dst.doegnDosis();
        double expected = 7;
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void doegnDosisFlereDage() {

        //Arrange
        DagligSkaev dst = new DagligSkaev(LocalDate.of(2025,9,14), LocalDate.of(2025,9,17));
        dst.opretDosis(LocalTime.of(9,30), 1);
        dst.opretDosis(LocalTime.of(15,00), 4);
        dst.opretDosis(LocalTime.of(21,00), 1);
        //Act
        double actual = dst.doegnDosis();
        double expected = 6;
        //Assert
        assertEquals(expected, actual);
    }
}