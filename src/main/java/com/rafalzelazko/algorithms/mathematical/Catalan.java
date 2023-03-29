package com.rafalzelazko.algorithms.mathematical;

public class Catalan {
    /**
     * Computes the Catalan number at the specified position in the sequence
     * <ul>
     * <li> The position must be at least 0 and at most 16
     * </ul>
     *
     * @param position the position
     * @return the Catalan number
     */
    public static int catalan(int position) {
        int catalan = 1;

        for (int index = 2; index <= position; index++) {
            catalan = catalan * (4 * index - 2);
            catalan = catalan / (index + 1);
        }

        return catalan;
    }
}
