package graphics;

public abstract class Application {

    protected GameStateManager gsm;

    public Application() {
        gsm = new GameStateManager();
    }

    protected abstract void init();

    protected abstract void update(float dt);

    protected abstract void render();

    protected abstract void dispose();

}
