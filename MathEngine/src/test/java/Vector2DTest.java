import core.Vector2D;

import org.junit.Assert;
import org.junit.Test;

public class Vector2DTest {

    @Test
    public void testCreation() {
        Vector2D vec;

        vec = new Vector2D(0, 0);
        Assert.assertEquals(vec.x, 0, 0);
        Assert.assertEquals(vec.y, 0, 0);
        vec = new Vector2D(5.5f, 0);
        Assert.assertEquals(vec.x, 5.5f, 0);
        Assert.assertEquals(vec.y, 0, 0);
        vec = new Vector2D(5.5f, 5.5f);
        Assert.assertEquals(vec.x, 5.5f, 0);
        Assert.assertEquals(vec.y, 5.5f, 0);
        vec = new Vector2D(0, -5.5f);
        Assert.assertEquals(vec.x, 0, 0);
        Assert.assertEquals(vec.y, -5.5f, 0);
        vec = new Vector2D(5.511f, -5.5f);
        Assert.assertEquals(vec.x, 5.511f, 0);
        Assert.assertEquals(vec.y, -5.5f, 0);
        vec = new Vector2D(Float.MAX_VALUE, 0);
        Assert.assertEquals(vec.x, Float.MAX_VALUE, 0);
        Assert.assertEquals(vec.y, 0, 0);
        vec = new Vector2D(5.789f, Float.MIN_VALUE);
        Assert.assertEquals(vec.x, 5.789f, 0);
        Assert.assertEquals(vec.y, Float.MIN_VALUE, 0);
        vec = new Vector2D(Float.MIN_VALUE, Float.MAX_VALUE);
        Assert.assertEquals(vec.x, Float.MIN_VALUE, 0);
        Assert.assertEquals(vec.y, Float.MAX_VALUE, 0);
    }

    /**
     * Tests the equals method using the Assert.assertEquals method.
     */
    @Test
    public void testEquality() {
        Assert.assertEquals(new Vector2D(0,0), new Vector2D(0,0));
        Assert.assertEquals(new Vector2D(55.5678f,0), new Vector2D(55.5678f,0));
        Assert.assertEquals(new Vector2D(800f,-55.545678f), new Vector2D(800f,-55.545678f));
        Assert.assertEquals(new Vector2D(0,Float.MAX_VALUE), new Vector2D(0, Float.MAX_VALUE));
        Assert.assertEquals(new Vector2D(-0.9999f, Float.MIN_VALUE), new Vector2D(-0.9999f, Float.MIN_VALUE));
        Assert.assertEquals(new Vector2D(Float.MAX_VALUE, Float.MIN_VALUE), new Vector2D(Float.MAX_VALUE, Float.MIN_VALUE));
        Assert.assertEquals(new Vector2D(-1.2345678f, Float.POSITIVE_INFINITY), new Vector2D(-1.2345678f, Float.POSITIVE_INFINITY));
        Assert.assertEquals(new Vector2D(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY), new Vector2D(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY));
    }
}
