import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RadixSortTest {

    @Test
    void testRadixSortWithPositiveNumbers() {
        int[] input = {170, 45, 75, 90, 802, 24, 2, 66};
        int[] expected = {2, 24, 45, 66, 75, 90, 170, 802};

        Radix.radixsort(input, input.length);
        assertArrayEquals(expected, input, "The sorted array does not match the expected output");
    }

    @Test
    void testRadixSortWithAllEqualNumbers() {
        int[] input = {5, 5, 5, 5, 5};
        int[] expected = {5, 5, 5, 5, 5};

        Radix.radixsort(input, input.length);
        assertArrayEquals(expected, input, "The sorted array does not match the expected output");
    }

    @Test
    void testRadixSortWithSingleElement() {
        int[] input = {42};
        int[] expected = {42};

        Radix.radixsort(input, input.length);
        assertArrayEquals(expected, input, "The sorted array does not match the expected output");
    }

    @Test
    void testRadixSortWithAlreadySortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        Radix.radixsort(input, input.length);
        assertArrayEquals(expected, input, "The sorted array does not match the expected output");
    }

    @Test
    void testRadixSortWithDescendingArray() {
        int[] input = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        Radix.radixsort(input, input.length);
        assertArrayEquals(expected, input, "The sorted array does not match the expected output");
    }

    @Test
    void testRadixSortWithEmptyArray() {
        int[] input = {};
        int[] expected = {};

        Radix.radixsort(input, input.length);
        assertArrayEquals(expected, input, "The sorted array does not match the expected output");
    }
}
