import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrProcessorTest {
    ArrProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new ArrProcessor();
    }

    @Test
    void getMin() {
        double[] arr = {1, -100, -10};
        double expected = -10;
        assertEquals(expected, processor.getMin(arr));
    }

    @Test
    void getSumOfSegment() {
        double[][] arr = {{1, 2, 3}, {-50, -100, -50}, {-50, -50, -50}};
        double expected = 6;
        assertEquals(expected, processor.getSumOfSegment(arr));
    }

    @Test
    void getSumOfSegment1() {
        double[][] arr = {{1, 2, 3}, {-50, 10, -50}, {-50, -50, -50}};
        double expected = 16;
        assertEquals(expected, processor.getSumOfSegment(arr));
    }
}