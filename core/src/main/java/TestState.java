
import graphics.gamestate.GameState;
import graphics.gamestate.GameStateManager;
import java.util.Random;
import org.lwjgl.opengl.GL11;

public class TestState extends GameState {

    private double rotation = 0;
    private float width = 100f;
    private float height = 100f;
    private float x = 800f / 2;
    private float y = 600f / 2;
    float x1, x2, x3, x4;
    float y1, y2, y3, y4;
    double rad1, rad2, rad3, rad4;
    Random r;
    float c1, c2, c3;
    int c1m, c2m, c3m;
    float cspeed1, cspeed2, cspeed3;

    public TestState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        System.out.println("Initing");
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        r = new Random();
        c1 = r.nextFloat();
        c2 = r.nextFloat();
        c3 = r.nextFloat();
        c1m = r.nextBoolean() == true ? 1 : -1;
        c2m = r.nextBoolean() == true ? 1 : -1;
        c3m = r.nextBoolean() == true ? 1 : -1;
        cspeed1 = r.nextFloat();
        cspeed2 = r.nextFloat();
        cspeed3 = r.nextFloat();
    }

    @Override
    public void input() {
    }

    @Override
    public void update(float dt) {
        System.out.println("Updating, dt=" + dt);
        rotation += 45 * dt;
        rad1 = Math.toRadians(rotation);
        rad2 = Math.toRadians(rotation + 90);
        rad3 = Math.toRadians(rotation + 180);
        rad4 = Math.toRadians(rotation + 270);
        x1 = x - (float) Math.cos(rad1) * width;
        y1 = y - (float) Math.sin(rad1) * height;
        x2 = x - (float) Math.cos(rad2) * width;
        y2 = y - (float) Math.sin(rad2) * height;
        x3 = x - (float) Math.cos(rad3) * width;
        y3 = y - (float) Math.sin(rad3) * height;
        x4 = x - (float) Math.cos(rad4) * width;
        y4 = y - (float) Math.sin(rad4) * height;
        c1 += c1m * cspeed1 * dt;
        c2 += c2m * cspeed2 * dt;
        c3 += c3m * cspeed3 * dt;
        if (c1 > 1f || c1 < 0) {
            c1m *= -1;
        }
        if (c2 > 1f || c2 < 0) {
            c2m *= -1;
        }
        if (c3 > 1f || c3 < 0) {
            c3m *= -1;
        }
    }

    @Override
    public void render() {
        System.out.println("Rendering");
        //GL11.glColor3f(1f, 0f, 0f);
        GL11.glColor3f(c1, c2, c3);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x2, y2);
        GL11.glVertex2f(x3, y3);
        GL11.glVertex2f(x4, y4);
        GL11.glEnd();
    }

    @Override
    public void dispose() {
        System.out.println("Disposing");
    }

}
