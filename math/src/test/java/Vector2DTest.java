import math.vectors.Vector2D;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;


@RunWith(Theories.class)
public class Vector2DTest {
    private static final int REPETITIONS = 5;

    @DataPoints
    public static float coordinates[] = {0.0f, -93.643f, 0.999999f, Float.MAX_VALUE, Float.MIN_VALUE,
            Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY};

    @Theory
    public void testCreation(float x, float y) {
        Vector2D vec = new Vector2D(x, y);
        Assert.assertEquals(vec.x, x, 0);
        Assert.assertEquals(vec.y, y, 0);
    }

    @Theory
    public void testContentEqualsIsReflexive(float x, float y) {
        Vector2D vec = new Vector2D(x, y);
        Assert.assertThat(vec.contentEquals(vec), is(true));
    }

    @Theory
    public void testContentEqualsReturnsTrue(float x1, float y1, float x2, float y2) {
        Vector2D vec1 = new Vector2D(x1, y1);
        Vector2D vec2 = new Vector2D(x2, y2);
        Assume.assumeTrue(x1 == x2 && y1 == y2);
        Assert.assertThat(vec1.contentEquals(vec2), is(true));
    }

    @Theory
    public void testContentEqualsReturnsFalse(float x1, float y1, float x2, float y2) {
        Vector2D vec1 = new Vector2D(x1, y1);
        Vector2D vec2 = new Vector2D(x2, y2);
        Assume.assumeTrue(x1 != x2 || y1 != y2);
        Assert.assertThat(vec1.contentEquals(vec2), is(false));
    }

    @Theory
    public void testContentEqualsIsSymmetric(float x1, float y1, float x2, float y2) {
        Vector2D vec1 = new Vector2D(x1, y1);
        Vector2D vec2 = new Vector2D(x2, y2);
        Assume.assumeThat(vec1.contentEquals(vec2), is(true));
        Assert.assertThat(vec2.contentEquals(vec1), is(true));
    }

    @Theory
    public void testContentEqualsIsTransitive(float x1, float y1, float x2, float y2, float x3, float y3) {
        Vector2D vec1 = new Vector2D(x1, y1);
        Vector2D vec2 = new Vector2D(x2, y2);
        Vector2D vec3 = new Vector2D(x3, y3);
        Assume.assumeThat(vec1.contentEquals(vec2), is(true));
        Assume.assumeThat(vec2.contentEquals(vec3), is(true));
        Assert.assertThat(vec1.contentEquals(vec3), is(true));
    }

    @Theory
    public void testContentEqualsWithNull(float x, float y) {
        Vector2D vec = new Vector2D(x, y);
        Assert.assertThat(vec.contentEquals(null), is(false));
    }

    @Theory
    public void testContentEqualsIsConsistent() {
        Vector2D vec1 = new Vector2D(coordinates[1], coordinates[3]);
        Vector2D vec2 = new Vector2D(coordinates[1], coordinates[3]);
        Vector2D vec3 = new Vector2D(coordinates[1], coordinates[4]);
        boolean[] resultsTrue = new boolean[REPETITIONS];
        boolean[] resultsFalse = new boolean[REPETITIONS];

        for (int run = 0; run < REPETITIONS; run++) {
            resultsTrue[run] = vec1.contentEquals(vec2);
            resultsFalse[run] = vec1.contentEquals(vec3);
        }

        Assert.assertArrayEquals(resultsTrue, new boolean[]{true, true, true, true, true});
        Assert.assertArrayEquals(resultsFalse, new boolean[]{false, false, false, false, false});
    }
}
