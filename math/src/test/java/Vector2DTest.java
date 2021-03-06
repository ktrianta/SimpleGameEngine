import math.vectors.Vector2D;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;


/**
 * Vector2DTest is the class responsible for testing the class Vector2D.
 *
 * Vector2DTest uses both JUnit Tests and Theories.
 *
 * From the JUnit Theories docs (github.com/junit-team/junit4/wiki/Theories):
 * A test captures the intended behavior in one particular scenario. A theory
 * captures some aspect of the intended behavior in possibly infinite numbers
 * of potential scenarios.
 */
@RunWith(Theories.class)
public class Vector2DTest {
    private static final int REPETITIONS = 5;

    @DataPoints
    public static float coordinates[] = {0.0f, -93.643f, 0.999999f, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY};


    /*
     * Constructors tests.
     */

    @Test
    public void testEmptyCreation() {
        Vector2D vec = new Vector2D();
        Assert.assertEquals(vec.x, 0, 0);
        Assert.assertEquals(vec.y, 0, 0);
    }

    @Theory
    public void testCreation(float x, float y) {
        Vector2D vec = new Vector2D(x, y);
        Assert.assertEquals(vec.x, x, 0);
        Assert.assertEquals(vec.y, y, 0);
    }


    /*
     * Method contentEquals tests.
     */

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

    /*
     * Method contentEquals should be an equivalence relation on non-null object references.
     *  * test it is reflexive.
     *  * test it is symmetric.
     *  * test it is transitive.
     *  * test it is consistent.
     *  * test contentEquals(null) returns false.
     */

    @Theory
    public void testContentEqualsIsReflexive(float x, float y) {
        Vector2D vec = new Vector2D(x, y);
        Assert.assertThat(vec.contentEquals(vec), is(true));
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

    @Test
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


    /*
     * Vector2D operations tests.
     */

    @Theory
    public void testAddVector(float x1, float y1, float x2, float y2) {
        Vector2D v1 = new Vector2D(x1, y1);
        Vector2D v2 = new Vector2D(x2, y2);
        Vector2D expectedResult = new Vector2D(x1+x2, y1+y2);

        Assert.assertThat(v1.add(v2).contentEquals(expectedResult), is(true));
    }

    @Theory
    public void testAddConstant(float x, float y, float c) {
        Vector2D v = new Vector2D(x, y);
        Vector2D expectedResult = new Vector2D(x+c, y+c);

        Assert.assertThat(v.add(c).contentEquals(expectedResult), is(true));
    }

    @Theory
    public void testAddConstantXY(float x, float y, float cx, float cy) {
        Vector2D v = new Vector2D(x, y);
        Vector2D expectedResult = new Vector2D(x+cx, y+cy);

        Assert.assertThat(v.add(cx, cy).contentEquals(expectedResult), is(true));
    }

    @Theory
    public void testSubVector(float x1, float y1, float x2, float y2) {
        Vector2D v1 = new Vector2D(x1, y1);
        Vector2D v2 = new Vector2D(x2, y2);
        Vector2D expectedResult = new Vector2D(x1-x2, y1-y2);

        Assert.assertThat(v1.sub(v2).contentEquals(expectedResult), is(true));
    }

    @Theory
    public void testSubConstant(float x, float y, float c) {
        Vector2D v = new Vector2D(x, y);
        Vector2D expectedResult = new Vector2D(x-c, y-c);

        Assert.assertThat(v.sub(c).contentEquals(expectedResult), is(true));
    }

    @Theory
    public void testSubConstantXY(float x, float y, float cx, float cy) {
        Vector2D v = new Vector2D(x, y);
        Vector2D expectedResult = new Vector2D(x-cx, y-cy);

        Assert.assertThat(v.sub(cx, cy).contentEquals(expectedResult), is(true));
    }

    @Theory
    public void testMultConstant(float x, float y, float c) {
        Vector2D v = new Vector2D(x, y);
        Vector2D expectedResult = new Vector2D(x*c, y*c);

        Assert.assertThat(v.mult(c).contentEquals(expectedResult), is(true));
    }

    @Theory
    public void testDivConstant(float x, float y, float c) {
        Vector2D v = new Vector2D(x, y);
        Vector2D expectedResult = new Vector2D(x/c, y/c);

        Assert.assertThat(v.div(c).contentEquals(expectedResult), is(true));
    }

    @Theory
    public void testProduct(float x1, float y1, float x2, float y2) {
        Vector2D v1 = new Vector2D(x1, y1);
        Vector2D v2 = new Vector2D(x2, y2);

        Assert.assertEquals(v1.product(v2), v2.product(v1), 0);
        Assert.assertEquals(v1.product(v2), x1*x2 + y1*y2, 0);
    }

    @Theory
    public void testCrossProduct(float x, float y) {
        Vector2D v = new Vector2D(x, y);
        Vector2D expectedResult = new Vector2D(y, -x);

        Assert.assertThat(v.crossProduct().contentEquals(expectedResult), is(true));
    }

    @Theory
    public void testMagnitude(float x, float y) {
        Vector2D v = new Vector2D(x, y);

        Assert.assertEquals(v.magnitude(), (float) Math.sqrt(x*x + y*y), 0);
    }

    @Theory
    public void testNormalize(float x, float y) {
        Vector2D v = new Vector2D(x, y);
        float mag = (float) Math.sqrt(x*x+y*y);
        Vector2D expectedResult = new Vector2D(x/mag, y/mag);

        Assert.assertThat(v.normalize().contentEquals(expectedResult), is(true));
    }
}
