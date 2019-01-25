package game_engine;

public class GameHasEndedException extends RuntimeException {
    GameHasEndedException(String message) {
        super(message);
    }
}
