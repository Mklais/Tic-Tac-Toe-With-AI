import java.util.ArrayList;
import java.util.Random;
public class AITool {
    private ArrayList<String> availableCoordinates = new ArrayList<>();
    private boolean[][] available;
    private final int ROW = 3;
    private final int COL = 3;
    public ArrayList<String> getAvailableCoordinates() {
        return availableCoordinates;
    }
    public void initializeTheOptimizer() {
        // In here we
        // initialize the 2D array that store data about taken tiles in the field
        available = new boolean[ROW][COL];

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                // Setting all the vars in the boolean array to true- to flag that they are empty
                available[row][col] = true;
            }
        }

        // Initializing List which will store the coordinates of available coordinates
        availableCoordinates = new ArrayList<>();

        for (int row = 0; row < available.length; row++) {
            for (int col = 0; col < available[row].length; col++) {
                if (available[row][col] == true) {
                    int rowInt = row + 1;
                    int colInt = col + 1;
                    String coordinateToAdd = rowInt + " " + colInt;
                    availableCoordinates.add(coordinateToAdd);
                }
            }
        }
    }
    public void optimizeTheMove(Game game) {
        // Flagging the occupied tiles from the field
        flagTheOccupiedTiles(game);
        markTheAvailableTiles();
    }
    private void flagTheOccupiedTiles(Game game) {
        // Marking the current move by whichever player

        int row = game.getROW_CORD();
        int col = game.getCOL_CORD();

        available[row][col] = false;
    }
    private void markTheAvailableTiles() {
        // Removing occupied coordinates from the available coordinates list

        for (int row = 0; row < available.length; row++) {
            for (int col = 0; col < available[row].length; col++) {
                if (available[row][col] == false) {
                    int rowInt = row + 1;
                    int colInt = col + 1;
                    String coordinateToRemove = rowInt + " " + colInt;

                    if (availableCoordinates.contains(coordinateToRemove)) {
                        availableCoordinates.remove(coordinateToRemove);
                    }
                }
            }
        }
    }
    /* Easy AI thought process:
    Easy AI difficulty mode will only move randomly
    It will not check for blocking or winning opportunities.

    - easyDifficultyMove is shared by a medium difficulty AI.
     */
    public void easyDifficultyMove(Game game) {
        // This method chooses *RANDOM* move tile for AI && removes it from available list
        Random random = new Random();

        int min = 1;
        int max = availableCoordinates.size();
        int randomCoordinateIndex = random.nextInt(max);

        String coordinate = availableCoordinates.get(randomCoordinateIndex);
        String coordinateParts[] = coordinate.split(" ");

        int row = Integer.parseInt(coordinateParts[0]);
        int col = Integer.parseInt(coordinateParts[1]);

        setRandomAIMove(game, row, col);
        removeCordAI(coordinate);
    }
    /* Medium AI thought process:
    In medium difficulty mode the AI will check first to block the opponent from winning with the next move. If opponent has 2 in a row
    the AI will move to block.
    If that is not the case the AI will check if it can win itself (as in has 1 missing from win). Moves to win
    If all else is false, it will move randomly within the available tiles.
     */
    public void mediumDifficultyMove(Game game, Field field) {
        if (checkToWin(game, field)) {
        } else if (checkToBlock(game, field)) {
        } else {
            easyDifficultyMove(game);
        }
    }
    private boolean checkToWin(Game game, Field field) {
        // Checking to see if AI has a chance to win with next turn, if so, it places the third tile
        String playerSymbol = game.getPLAYER_TURN();

        if (checkRow(game, field, playerSymbol)) {
            return true;
        } else if (checkColumn(game, field, playerSymbol)) {
            return true;
        } else if (checkDiagonals(game, field, playerSymbol)) {
            return true;
        } else {
            return false;
        }
    }
    private boolean checkToBlock(Game game, Field field) {
        // Checking to see if opponent has 2 tiles next to each other and has a chance to win - AI blocks it
        String opponentSymbol = getOpponentSymbol(game);

        if (checkRow(game, field, opponentSymbol)) {
            return true;
        } else if (checkColumn(game, field, opponentSymbol)) {
            return true;
        } else if (checkDiagonals(game, field, opponentSymbol)) {
            return true;
        }
        return false;
    }
    private String getOpponentSymbol(Game game) {
        String opponentSymbol;
        if (game.getPLAYER_TURN().equals("X")) {
            opponentSymbol = "O";
        } else {
            opponentSymbol = "X";
        }
        return opponentSymbol;
    }
    private boolean checkRow(Game game, Field field, String playerSymbol) {
        boolean opportunity = false;

        for (int row = 0; row < field.getField().length; row++) {
            // If there is a match in the row, we increase the flag var
            int match = 0;
            boolean isEmptyTile = false;
            int emptyRow = 0;
            int emptyCol = 0;

            for (int col = 0; col < field.getField()[row].length; col++) {
                // If the field tile equals the target symbol, we flag the match
                if (field.getField()[row][col].equals(playerSymbol)) {
                    match++;
                }
                // If the field tile equals **empty tile** - we also flag it
                if (field.getField()[row][col].equals(field.getEMPTY_TILE())) {
                    emptyRow = row;
                    emptyCol = col;
                    isEmptyTile = true;
                }
                // If a match is found, we set it in the Game class
                if (match == 2 && isEmptyTile) {
                    setOpportunityAndRemoveCord(game, emptyRow, emptyCol);
                    opportunity = true;
                    break;
                }
            }
        }
        return opportunity;
    }

    private boolean checkColumn(Game game, Field field, String playerSymbol) {
        boolean opportunity = false;

        for (int col = 0; col < field.getField()[0].length; col++) {
            int match = 0;
            boolean isEmptyTile = false;
            int emptyRow = 0;

            for (int row = 0; row < field.getField().length; row++) {
                if (field.getField()[row][col].equals(playerSymbol)) {
                    match++;
                }
                if (field.getField()[row][col].equals(field.getEMPTY_TILE())) {
                    emptyRow = row;
                    isEmptyTile = true;
                }

                if (match == 2 && isEmptyTile) {
                    setOpportunityAndRemoveCord(game, emptyRow, col);
                    opportunity = true;
                    break;
                }
            }
        }
        return opportunity;
    }
    private boolean checkDiagonals(Game game, Field field, String playerSymbol) {
        // A playerSymbol variable can be used for both checking to block or to win
        // Returning false, when it is not possible to block nor win

        if (checkLeftDiagonal(game, field, playerSymbol)) {
            return true;
        } else if (checkRightDiagonal(game, field, playerSymbol)) {
            return true;
        } else {
            return false;
        }
    }
    private boolean checkLeftDiagonal(Game game, Field field, String playerSymbol) {
        boolean opportunity = false;
        boolean isEmptyTile = false;
        int match = 0;
        int emptyRow = 0;
        int emptyCol = 0;

        // Check the left diagonal
        for (int row = 0; row < field.getField().length; row++) {
            if (field.getField()[row][row].equals(playerSymbol)) {
                match++;
            } else if (field.getField()[row][row].equals(field.getEMPTY_TILE())) {
                emptyRow = row;
                emptyCol = row;
                isEmptyTile = true;
            }
        }

        // If two matches and one empty tile are found, set AI move
        if (match == 2 && isEmptyTile) {
            setOpportunityAndRemoveCord(game, emptyRow, emptyCol);
            opportunity = true;
        }
        return opportunity;
    }
    private boolean checkRightDiagonal(Game game, Field field, String playerSymbol) {
        boolean opportunity = false;
        int match = 0;
        boolean isEmptyTile = false;
        int emptyRow = 0;
        int emptyCol = 0;

        // Check the right diagonal
        for (int row = 0; row < field.getField().length; row++) {
            int col = field.getField().length - 1 - row; // Calculate column for secondary diagonal
            if (field.getField()[row][col].equals(playerSymbol)) {
                match++;
            } else if (field.getField()[row][col].equals(field.getEMPTY_TILE())) {
                emptyRow = row;
                emptyCol = col;
                isEmptyTile = true;
            }
        }

        // If two matches and one empty tile are found, set AI move
        if (match == 2 && isEmptyTile) {
            setOpportunityAndRemoveCord(game, emptyRow, emptyCol);
            opportunity = true;
        }
        return opportunity;
    }
    private void setRandomAIMove(Game game, int row, int col) {
        game.setROW_CORD(row - 1);
        game.setCOL_CORD(col - 1);
    }
    private void setOpportunityAndRemoveCord(Game game, int row, int col){
        game.setROW_CORD(row);
        game.setCOL_CORD(col);

        int removeRow = game.getROW_CORD() + 1;
        int removeCol = game.getCOL_CORD() + 1;

        StringBuilder sb = new StringBuilder();
        sb.append(removeRow + " " + removeCol);
        String coordinate = sb.toString();
        removeCordUser(coordinate);
    }
    private void removeCordAI(String coordinate) {
        availableCoordinates.remove(coordinate);
    }
    public void removeCordUser(String coordinate) {
        availableCoordinates.remove(coordinate);
    }
}