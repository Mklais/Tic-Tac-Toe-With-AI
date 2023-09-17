public class UserActionValidation {
    public boolean initialStateValidation(String initialCells) {
        if (initialCells.length() == 9) {
            return true;
        } else {
            return false;
        }
    }
    public boolean coordinateValidation(UserInterface ui, String playerCoords, Field field, Game game) {
        String[] numberStrings = playerCoords.split(" ");

        if (!coordinateFormatValidation(numberStrings, ui)) {
            return false;
        } else if (!coordinateLocationValidation(numberStrings, ui, field, game)) {
            return false;
        } else {
            return true;
        }
    }
    private boolean coordinateFormatValidation(String[] numberStrings, UserInterface ui) {
        int[] intCoords = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            try {
                intCoords[i] = Integer.parseInt(numberStrings[i]);
                if (intCoords[i] > 3 || intCoords[i] < 1) {
                    ui.coordinatesRangeValidationError();
                    return false;
                }
            } catch (Exception e) {
                ui.coordinatesFormatValidationError();
                return false;
            }
        }
        return true;
    }
    private boolean coordinateLocationValidation(String[] numberStrings, UserInterface ui, Field field, Game game) {
        int rowCord = Integer.parseInt(numberStrings[0]) - 1;
        int colCord = Integer.parseInt(numberStrings[1]) - 1;

        if (field.getField()[rowCord][colCord] != field.getEMPTY_TILE()) {
            ui.occupiedCellValidationError();
            return false;
        } else {
            game.setROW_CORD(rowCord);
            game.setCOL_CORD(colCord);
            return true;
        }
    }
}