public class AI {
    private int AI_ROW_CORD = 0;
    private int AI_COL_CORD = 0;
    private final int MIN = 1;
    private final int MAX = 3;

    public int getAI_ROW_CORD() {
        return AI_ROW_CORD;
    }
    public void setAI_ROW_CORD(int AI_ROW_CORD) {
        this.AI_ROW_CORD = AI_ROW_CORD;
    }
    public int getAI_COL_CORD() {
        return AI_COL_CORD;
    }
    public void setAI_COL_CORD(int AI_COL_CORD) {
        this.AI_COL_CORD = AI_COL_CORD;
    }

    public void aiMakesMove(UserInterface ui, Field field, AIActionValidation aiValidation, Game game, AI ai, AITool aiTool, Player player) {
        if (player.getDifficulty().equals("easy")) {
            easyDifficulty(game, aiTool, ui);
        } else if (player.getDifficulty().equals("medium")) {
            mediumDifficulty(game, aiTool, ui, field);
        }
    }
    private void easyDifficulty(Game game, AITool aiTool, UserInterface ui) {
        aiTool.easyDifficultyMove(game);
        ui.aiEasyLevel();
    }
    private void mediumDifficulty(Game game, AITool aiTool, UserInterface ui, Field field) {
        aiTool.mediumDifficultyMove(game, field);
        ui.aiMediumLevel();
    }
}