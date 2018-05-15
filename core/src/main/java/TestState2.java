
import graphics.Graphics;
import graphics.gamestate.GameState;
import graphics.gamestate.GameStateManager;
import java.nio.IntBuffer;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.system.MemoryUtil;

public class TestState2 extends GameState {

    private IntBuffer width, height;
    private long window;

    public TestState2(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    protected void init() {
        width = MemoryUtil.memAllocInt(1);
        height = MemoryUtil.memAllocInt(1);
        window = Graphics.window;
    }

    @Override
    protected void input() {
    }

    @Override
    protected void update(float dt) {
        float ratio;

        /* Get width and height to calcualte the ratio */
        glfwGetFramebufferSize(window, width, height);
        ratio = width.get() / (float) height.get();

        /* Rewind buffers for next get */
        width.rewind();
        height.rewind();

        /* Set ortographic projection */
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-ratio, ratio, -1f, 1f, 1f, -1f);
        glMatrixMode(GL_MODELVIEW);

        /* Rotate matrix */
        glLoadIdentity();
        glRotatef((float) glfwGetTime() * 50f, 0f, 0f, 1f);

        /* Poll Events */
        glfwPollEvents();

    }

    @Override
    protected void render() {
        /* Set viewport and clear screen */
        glViewport(0, 0, width.get(), height.get());
        glClear(GL_COLOR_BUFFER_BIT);

        /* Render triangle */
        glBegin(GL_TRIANGLES);
        glColor3f(1f, 0f, 0f);
        glVertex3f(-0.6f, -0.4f, 0f);
        glColor3f(0f, 1f, 0f);
        glVertex3f(0.6f, -0.4f, 0f);
        glColor3f(0f, 0f, 1f);
        glVertex3f(0f, 0.6f, 0f);
        glEnd();

        /* Flip buffers for next loop */
        width.flip();
        height.flip();
    }

    @Override
    protected void dispose() {
        MemoryUtil.memFree(width);
        MemoryUtil.memFree(height);
    }
}
