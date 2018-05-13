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
        return vec != null && x == vec.x && y == vec.y;
    }
}
