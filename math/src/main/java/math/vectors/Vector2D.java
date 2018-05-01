package math.vectors;

public class Vector2D {
    public float x;
    public float y;

    public Vector2D(float pX, float pY) {
        // TODO: initialize fields
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;

        // TODO: implement actual comparison
        return false;
    }

    @Override
    public int hashCode() {
        // TODO: implement hashcode
        return 0;
    }
}
