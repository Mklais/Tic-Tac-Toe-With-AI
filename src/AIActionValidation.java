public class AIActionValidation {
    private boolean validMove = false;
    public boolean isValidMove() {
        return validMove;
    }
    public void setValidMove(boolean validMove) {
        this.validMove = validMove;
    }
    public void aiMoveValidation(AI ai, Field field, int optimizeMe) {
        // This method checks whether the coordinates of the random generator of the AI are free
        // If they aren't free, the validation process makes it generate new coordinates

        int row = ai.getAI_ROW_CORD();
        int col = ai.getAI_COL_CORD();
        if (!(field.getField()[row][col].equals(field.getEMPTY_TILE()))) {
            setValidMove(false);
        } else {
            setValidMove(true);
        }

        optimizeMe++;
    }
}