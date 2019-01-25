package game_engine;

public class MoveOutOfBoundsException extends RuntimeException {
    MoveOutOfBoundsException(String message) {
        super(message);
    }
}
