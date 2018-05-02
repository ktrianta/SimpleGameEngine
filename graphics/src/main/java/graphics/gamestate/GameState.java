package graphics.gamestate;

public abstract class GameState {

    private GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void init();

    public abstract void input();

    public abstract void update(float dt);

    public abstract void render();

    public abstract void dispose();
}
