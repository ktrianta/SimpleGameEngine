
import graphics.Application;

public class TestGame extends Application {

    public TestGame() {
        super();
    }

    @Override
    public void init() {
        System.out.println("Initing");
    }

    @Override
    public void update(float dt) {
        System.out.println("Updating, dt=" + dt);
    }

    @Override
    public void render() {
        System.out.println("Rendering");
    }

    @Override
    public void dispose() {
        System.out.println("Disposing");
    }

}
