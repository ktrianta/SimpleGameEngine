
import graphics.Application;

public class TestGame2 extends Application {

    public TestGame2() {
        super();
    }

    @Override
    protected void init() {
        gsm.add("test", new TestState2(gsm));
        gsm.change("test");
        gsm.init();
    }

    @Override
    protected void input() {
        gsm.input();
    }

    @Override
    protected void update(float dt) {
        gsm.update(dt);
    }

    @Override
    protected void render() {
        gsm.render();
    }

    @Override
    protected void dispose() {
        gsm.dispose();
    }
}
