
import graphics.OpenGLWindow;
import graphics.OpenGLWindowConfiguration;

public class Main {

    public static void main(String[] args) {
        OpenGLWindowConfiguration c = new OpenGLWindowConfiguration();
        c.title = "Testing opengl window";
        OpenGLWindow w = new OpenGLWindow(new TestGame(), c);
        w.start();
    }
}
