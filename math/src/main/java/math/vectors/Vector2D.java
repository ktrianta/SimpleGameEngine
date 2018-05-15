package math.vectors;

public final class Vector2D {
    public float x;
    public float y;

    public Vector2D() {}

    public Vector2D(float pX, float pY) {
        x = pX;
        y = pY;
    }

    public boolean contentEquals(Vector2D vec) {
        return vec != null &&
                Float.floatToIntBits(x) == Float.floatToIntBits(vec.x) &&
                Float.floatToIntBits(y) == Float.floatToIntBits(vec.y);
    }

    public Vector2D add(Vector2D vec) {
        return new Vector2D(x+vec.x, y+vec.y);
    }

    public Vector2D add(float con) {
        return new Vector2D(x+con, y+con);
    }

    public Vector2D add(float xcon, float ycon) {
        return new Vector2D(x+xcon, y+ycon);
    }

    public Vector2D sub(Vector2D vec) {
        return new Vector2D(x-vec.x, y-vec.y);
    }

    public Vector2D sub(float con) {
        return new Vector2D(x-con, y-con);
    }

    public Vector2D sub(float xcon, float ycon) {
        return new Vector2D(x-xcon, y-ycon);
    }

    public Vector2D mult(float con) {
        return new Vector2D(x*con, y*con);
    }

    public Vector2D div(float con) {
        return new Vector2D(x/con, y/con);
    }

    public float product(Vector2D vec) {
        return x*vec.x + y*vec.y;
    }

    public Vector2D crossProduct() {
        return new Vector2D(y, -x);
    }

    public float magnitude() {
        return (float) Math.hypot(x, y);
    }

    public Vector2D normalize() {
        float mag = magnitude();
        return new Vector2D(x/mag, y/mag);
    }
}
