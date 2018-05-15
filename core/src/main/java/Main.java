
import graphics.OpenGLWindow;
import graphics.OpenGLWindowConfiguration;

public class Main {

    public static void main(String[] args) {
        OpenGLWindowConfiguration c = new OpenGLWindowConfiguration();
        c.title = "Testing opengl window 2";
        c.versionBuild = 2;
        OpenGLWindow w = new OpenGLWindow(new TestGame2(), c);
        w.start();
    }
}
