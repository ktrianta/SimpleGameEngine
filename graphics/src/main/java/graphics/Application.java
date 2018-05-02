package graphics;

import graphics.gamestate.GameStateManager;

public abstract class Application {

    protected GameStateManager gsm;

    protected Application() {
        gsm = new GameStateManager();
    }

    protected abstract void init();

    protected abstract void input();

    protected abstract void update(float dt);

    protected abstract void render();

    protected abstract void dispose();

}
