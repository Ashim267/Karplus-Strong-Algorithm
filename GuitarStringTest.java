// File name: GitarStringTest
// Author: Ashim Chand
// userid: chanda
// Email: ashimchand@vanderbilt.edu
// Class: CS2201
// Honor Statement: Will not use unfair means
// Assignment Number: 6
// Description: Gitar String Implimentation Test
// Last Changed: 3/26/2024

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuitarStringTest {

    // TODO
    // Dear CS2201 student... at this point you should be comfortable creating JUnit
    // tests for the code that you write. Here is a starter file containing a single
    // simple test. Please enhance this test to be complete, and then create additional
    // tests to completely test the rest of the GuitarString class.
    //
    // Note on testing the GuitarString class: after a pluck, the string's ring
    // buffer will contain random data. You will not know what those values are since
    // they are random. However, you can verify that they are all in the expected
    // range. And that after a BUNCH of tic's the values should be approaching zero (ie,
    // they are getting very small).

    @Test
    public void testConstructor() {
        double testFreq = 1000;
        GuitarString myString = new GuitarString(testFreq);
        assertEquals(testFreq, myString.getFrequency(), 0.00001);
        assertThrows(IllegalArgumentException.class, () -> new GuitarString(-1));
    }


    @Test
    public void testPluck() {
        GuitarString string = new GuitarString(50.0);
        string.pluck();
        assertTrue(string.sample() >= -0.5 && string.sample() <= 0.5);
    }

    @Test
    public void testTic() {
        GuitarString string = new GuitarString(50.0);
        string.pluck();
        double sampleBefore = string.sample();
        string.tic();
        double sampleAfter = string.sample();
        assertNotEquals(sampleBefore, sampleAfter);
    }

    @Test
    public void testSample() {
        GuitarString string = new GuitarString(50.0);
        string.pluck();
        assertNotNull(string.sample());
    }

    @Test
    public void testGetTime() {
        GuitarString string = new GuitarString(50.0);
        assertEquals(0, string.getTime());
        string.tic();
        assertEquals(1, string.getTime());
    }



}
