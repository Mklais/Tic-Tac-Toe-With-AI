/**
 * The GameSettingsManager class is responsible for managing game settings,
 * including the creation of Player objects with specific types and difficulties.
 */
public class GameSettingsManager {
    public void resetSettings(Game game, Field field, AITool aiTool) {
        resetGameSettings(game);
        resetFieldSettings(field, aiTool);
    }
    private void resetGameSettings(Game game) {
        game.setFINISHED(false);
        game.setPLAYER_TURN("X");
    }
    private void resetFieldSettings(Field field, AITool aiTool) {
        field.initializeField();
        aiTool.initializeTheOptimizer();
    }
    public Player createPlayer(String type, String difficulty) {
        if (type.equals("user")) {
            difficulty = "user";
        } else {
            difficulty = type;
            type = "ai";
        }
        return new Player(type, difficulty);
    }

}