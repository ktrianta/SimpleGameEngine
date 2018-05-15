
import graphics.Application;
import java.util.Random;
import org.lwjgl.opengl.GL11;

public class TestGame extends Application {

    float accum = 1f;
    float c1, c2, c3;
    Random r;

    public TestGame() {
        super();
    }

    @Override
    public void init() {
        r = new Random();
        gsm.add("test", new TestState(gsm));
        gsm.change("test");
        gsm.init();
    }

    @Override
    protected void input() {
        gsm.input();
    }

    @Override
    public void update(float dt) {
        accum += dt;
        if (accum >= 1f) {
            accum -= 1f;
            c1 = r.nextFloat();
            c2 = r.nextFloat();
            c3 = r.nextFloat();
        }
        gsm.update(dt);
    }

    @Override
    public void render() {
        GL11.glClearColor(c1, c2, c3, 0);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        gsm.render();
    }

    @Override
    public void dispose() {
        gsm.dispose();
    }
}
