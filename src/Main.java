public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        UserInterface ui = new UserInterface();
        Game game = new Game();
        UserActionValidation uav = new UserActionValidation();
        AI ai = new AI();
        AIActionValidation aiValidation = new AIActionValidation();
        AITool aiTool = new AITool();
        GameSettingsManager settingsManager = new GameSettingsManager();

        // Initializing the AI
        aiTool.initializeTheOptimizer();
        // Initializing the field
        field.initializeField();


        while (isNotExitCommand(game)) {
            // Initializing game
            game.setGameSettings(ui, settingsManager, game, field, aiTool);
            if (isNotExitCommand(game)) {
                field.printField();
            }

            while (isGameInProgress(game)) {
                game.playGame(ui, uav, field, game, aiValidation, ai, aiTool);

                // For optimization purpose sending the data of table to AI
                aiTool.optimizeTheMove(game);
            }
            ui.gameEnd();
        }
    }

    private static boolean isNotExitCommand(Game game) {
        return !game.isExitCommand();
    }

    private static boolean isGameInProgress(Game game) {
        return !game.isFINISHED() && isNotExitCommand(game);
    }
}