package graphics.gamestate;

public abstract class GameState {

    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    protected abstract void init();

    protected abstract void input();

    protected abstract void update(float dt);

    protected abstract void render();

    protected abstract void dispose();
}
