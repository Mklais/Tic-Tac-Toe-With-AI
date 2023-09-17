import java.util.Scanner;

public class Game {
    private GameResult gameResult;
    private Player playerX;
    private Player playerO;
    private String PLAYER_TURN = "X";
    private int ROW_CORD = 0;
    private int COL_CORD = 0;
    private boolean exitCommand = false;
    private boolean FINISHED = false;
    public void setPLAYER_TURN(String PLAYER_TURN) {
        this.PLAYER_TURN = PLAYER_TURN;
    }
    public String getPLAYER_TURN() {
        return PLAYER_TURN;
    }
    public boolean isExitCommand() {
        return exitCommand;
    }
    public void setExitCommand(boolean exitCommand) {
        this.exitCommand = exitCommand;
    }
    public int getROW_CORD() {
        return ROW_CORD;
    }
    public void setROW_CORD(int ROW_CORD) {
        this.ROW_CORD = ROW_CORD;
    }
    public int getCOL_CORD() {
        return COL_CORD;
    }
    public void setCOL_CORD(int COL_CORD) {
        this.COL_CORD = COL_CORD;
    }
    public boolean isFINISHED() {
        return FINISHED;
    }
    public void setFINISHED(boolean FINISHED) {
        this.FINISHED = FINISHED;
    }
    public Game() {
        gameResult = new GameResult();
    }
    public void setGameSettings(UserInterface ui, GameSettingsManager settingsManager, Game game, Field field, AITool aiTool) {
        Scanner input = new Scanner(System.in);

        while (true) {
            ui.commandPrompt();
            String commandInput = input.nextLine();

            String[] commandParts = commandInput.toLowerCase().split(" ");
            String command = commandParts[0];

            switch (command) {
                case "start":
                    if (commandParts.length != 3) {
                        ui.incorrectParameter();
                    } else {
                        setSettings(settingsManager, game, commandParts, field, aiTool);
                        // System.out.println(playerX.getType());
                        // System.out.println(playerX.getDifficulty());
                        // System.out.println(playerO.getType());
                        // System.out.println(playerO.getDifficulty());
                        return;
                    }
                    break;

                case "exit":
                    setExitCommand(true);
                    return;

                default:
                    ui.incorrectParameter();
            }
        }
    }
    private void setSettings(GameSettingsManager settingsManager, Game game, String[] commandParts, Field field, AITool aiTool) {
        settingsManager.resetSettings(game, field, aiTool);
        playerX = settingsManager.createPlayer(commandParts[1], "");
        playerO = settingsManager.createPlayer(commandParts[2], "");
    }
    public void playGame(UserInterface ui, UserActionValidation uav, Field field, Game game, AIActionValidation aiValidation, AI ai, AITool aiTool) {
        if (PLAYER_TURN.equals("X")) {
            playerXMove(ui, uav, field, game, aiValidation, ai, aiTool);
        } else {
            playerOMove(ui, uav, field, game, aiValidation, ai, aiTool);
        }
        processGameData(field, ui, game);
    }
    private void processGameData(Field field, UserInterface ui, Game game) {
        placePlayerOnTheField(field);
        field.printField();
        gameResult.printResult(ui, field, game);
        switchPlayer(field);
    }
    private void playerOMove(UserInterface ui, UserActionValidation uav, Field field, Game game, AIActionValidation aiValidation, AI ai, AITool aiTool) {
        if (playerO.getType().equals("user")) {
            userMakesMove(ui, uav, field, game, aiTool);
        } else if (playerO.getType().equals("ai")) {
            ai.aiMakesMove(ui, field, aiValidation, game, ai, aiTool, playerO);
        }
    }
    private void playerXMove(UserInterface ui, UserActionValidation uav, Field field, Game game, AIActionValidation aiValidation, AI ai, AITool aiTool) {
        if (playerX.getType().equals("user")) {
            userMakesMove(ui, uav, field, game, aiTool);
        } else if (playerX.getType().equals("ai")) {
            ai.aiMakesMove(ui, field, aiValidation, game, ai, aiTool, playerX);
        }
    }
    private void placePlayerOnTheField(Field field) {
        field.getField()[ROW_CORD][COL_CORD] = getPLAYER_TURN();
    }
    private void switchPlayer(Field field) {
        if (getPLAYER_TURN().equals(field.getPLAYER_X_SYMBOL())) {
            setPLAYER_TURN(field.getPLAYER_O_SYMBOL());
        } else if (getPLAYER_TURN().equals(field.getPLAYER_O_SYMBOL())) {
            setPLAYER_TURN(field.getPLAYER_X_SYMBOL());
        }
    }
    private void userMakesMove(UserInterface ui, UserActionValidation uav, Field field, Game game, AITool aiTool) {
        Scanner input = new Scanner(System.in);

        ui.userCoordinates();
        while (true) {
            String playerCoords = input.nextLine();
            if (uav.coordinateValidation(ui, playerCoords, field, game)) {
                aiTool.removeCordUser(playerCoords);
                break;
            }
        }
    }

}