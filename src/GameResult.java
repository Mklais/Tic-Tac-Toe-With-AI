public class GameResult {
    public void printResult(UserInterface ui, Field field, Game game) {
        if (checkwin(field, game)) {
            String message = ui.gameWin();
            System.out.println(game.getPLAYER_TURN() + message);
            game.setFINISHED(true);
        } else if (checkDraw(field)) {
            ui.draw();
            game.setFINISHED(true);
        } else {
            game.setFINISHED(false);
        }
    }
    private boolean checkwin(Field field, Game game) {
        for (int i = 0; i < 3; i++) {
            if (checkRowWin(field, i, game)) {
                return true;
            }
            if (checkColWin(field, i, game)) {
                return true;
            }
        }
        return checkDiagonalWin(field, game);
    }
    private boolean checkDiagonalWin(Field field, Game game) {
        return checkLeftDiagonal(field, game) || checkRightDiagonal(field, game);
    }
    private boolean checkRightDiagonal(Field field, Game game) {
        return field.getField()[0][2].equals(game.getPLAYER_TURN()) && field.getField()[1][1].equals(game.getPLAYER_TURN()) && field.getField()[2][0].equals(game.getPLAYER_TURN());
    }
    private boolean checkLeftDiagonal(Field field, Game game) {
        return field.getField()[0][0].equals(game.getPLAYER_TURN()) && field.getField()[1][1].equals(game.getPLAYER_TURN()) && field.getField()[2][2].equals(game.getPLAYER_TURN());
    }
    private boolean checkColWin(Field field, int i, Game game) {
        return field.getField()[0][i].equals(game.getPLAYER_TURN()) && field.getField()[1][i].equals(game.getPLAYER_TURN()) && field.getField()[2][i].equals(game.getPLAYER_TURN());
    }
    private boolean checkRowWin(Field field, int i, Game game) {
        return field.getField()[i][0].equals(game.getPLAYER_TURN()) && field.getField()[i][1].equals(game.getPLAYER_TURN()) && field.getField()[i][2].equals(game.getPLAYER_TURN());
    }
    private boolean checkDraw(Field field) {
        // Checking if the game has resulted in a draw

        for (int row= 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (field.getField()[row][col].equals(" ")) {
                    return false; // At least one empty cell, game is not draw
                }
            }
        }
        return true; // All cells filled, game is draw
    }
}