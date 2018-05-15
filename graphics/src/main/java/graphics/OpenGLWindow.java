package graphics;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
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
        // setting error callback to default system error output
        errorCallback = GLFWErrorCallback.createPrint(System.err);
        glfwSetErrorCallback(errorCallback);

        // initialize the GLFW library
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // setting window options
        glfwWindowHint(GLFW_RESIZABLE, config.resizeable ? 1 : 0);
        glfwWindowHint(GLFW_FOCUSED, config.focused ? 1 : 0);

        // creating the GLFW window
        String windowTitle = config.title + " " + config.versionMajor + "." + config.versionMinor + "." + config.versionBuild;
        window = glfwCreateWindow(config.width, config.height, windowTitle, NULL, NULL);

        // check if window creation failed
        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }
        Graphics.window = window;

        /* Center the window on screen */
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        if (vidMode != null) {
            glfwSetWindowPos(window, (vidMode.width() - 640) / 2, (vidMode.height() - 480) / 2);
        }

        // TODO add input processing
        keyCallback = new GLFWKeyCallback() {
            // TMP escape button exits
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                    glfwSetWindowShouldClose(window, true);
                }
            }
        };
        glfwSetKeyCallback(window, keyCallback);

        // make the OpenGL context of the window current on the calling thread
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        // enable v-sync
        glfwSwapInterval(config.vsync ? 1 : 0);

        // create application clock-timer
        timer = new Timer();
        Graphics.timer = timer;
        timer.init();
        Graphics.application = application;
        application.init();
    }

    private void loop() {
        float delta; // time from last frame
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
            Thread.yield(); // TODO change?
            now = timer.getTime();
        }
    }

}
