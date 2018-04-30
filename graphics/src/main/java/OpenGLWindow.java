
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import static org.lwjgl.system.MemoryUtil.NULL;

public class OpenGLWindow {

    private OpenGLWindowConfiguration config;
    private Timer timer;
    private long window;
    private Application application;
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    public OpenGLWindow(Application app) {
        this(app, new OpenGLWindowConfiguration());
    }

    public OpenGLWindow(Application application, OpenGLWindowConfiguration config) {
        this.application = application;
        this.config = config;
    }

    public void start() {
        init();
        loop();
        dispose();
    }

    private void init() {
        errorCallback = GLFWErrorCallback.createPrint(System.err);
        glfwSetErrorCallback(errorCallback);
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        String windowTitle = config.title + " " + config.versionMajor + "." + config.versionMinor + "." + config.versionBuild;
        window = glfwCreateWindow(config.width, config.height, windowTitle, NULL, NULL);
        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }
        // TODO add input processing
        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                    glfwSetWindowShouldClose(window, true);
                }
            }
        };
        glfwSetKeyCallback(window, keyCallback);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwSwapInterval(config.vsync ? 1 : 0);
        if (config.vsync) {
            glfwSwapInterval(1);
        } else {
            glfwSwapInterval(0);
        }
        timer = new Timer();
        timer.init();
        application.init();
    }

    private void loop() {
        float delta;
        while (!glfwWindowShouldClose(window)) {
            delta = timer.getDelta();
            // TODO input();
            application.update(delta);
            application.render();
            timer.update();
            glfwSwapBuffers(window);
            glfwPollEvents();
            if (!config.vsync) {
                sync(config.fps);
            }
        }
    }

    private void dispose() {
        application.dispose();
        glfwDestroyWindow(window);
        keyCallback.free();
        glfwTerminate();
        errorCallback.free();
    }

    public void sync(int fps) {
        double lastLoopTime = timer.getLastLoopTime();
        double now = timer.getTime();
        float targetTime = 1f / fps;
        while (now - lastLoopTime < targetTime) {
            Thread.yield();
            now = timer.getTime();
        }
    }

}
