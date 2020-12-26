package net.theprogrammersworld.herobrine.util;

import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    public static int ranInt(int start, int end) {
        return random.nextInt(end - start) + start;
    }

    public static int ranIntWithNegative(int start, int end) {
        return random.nextBoolean() ? -ranInt(start, end) : ranInt(start, end);
    }
}
