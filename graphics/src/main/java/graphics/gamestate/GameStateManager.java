package graphics.gamestate;

import java.util.HashMap;
import java.util.Map;

public class GameStateManager {

    private Map<String, GameState> states;
    private GameState currentState;

    public GameStateManager() {
        states = new HashMap<String, GameState>();
        currentState = null;
    }

    public void add(String name, GameState state) {
        states.put(name, state);
    }

    public void change(String name) {
        if (currentState != null) {
            currentState.dispose();
        }
        currentState = states.get(name);
        currentState.init();
    }

    public void input() {
        if (currentState != null) {
            currentState.input();
        }
    }

    public void update(float dt) {
        if (currentState != null) {
            currentState.update(dt);
        }
    }

    public void render() {
        if (currentState != null) {
            currentState.render();
        }
    }

    public void init() {
        if (currentState != null) {
            currentState.init();
        }
    }

    public void dispose() {
        if (currentState != null) {
            currentState.dispose();
        }
    }

}
