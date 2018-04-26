package graphics;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Timer {

    private double lastLoopTime;
    private float timeCount;
    private int fps;
    private int fpsCount;

    public void init() {
        lastLoopTime = getTime();
    }

    public double getTime() {
        return glfwGetTime();
    }

    public float getDelta() {
        double time = getTime();
        float delta = (float) (time - lastLoopTime);
        lastLoopTime = time;
        timeCount += delta;
        return delta;
    }

    public void update() {
        fpsCount++;
        if (timeCount > 1f) {
            fps = fpsCount;
            fpsCount = 0;
            timeCount -= 1f;
        }
    }

    public int getFPS() {
        return fps > 0 ? fps : fpsCount;
    }

    public double getLastLoopTime() {
        return lastLoopTime;
    }
}
