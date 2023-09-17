public class UserInterface {
    public void initialCells() {
        System.out.println("Enter the cells: ");
    }
    public void userCoordinates() {
        System.out.print("Enter the coordinates: ");
    }
    public void coordinatesFormatValidationError() {
        System.out.println("You should enter numbers!");
    }
    public void coordinatesRangeValidationError() {
        System.out.println("Coordinates should be from 1 to 3!");
    }
    public void occupiedCellValidationError() {
        System.out.println("This cell is occupied! Choose another one!");
    }
    public void draw() {
        System.out.println("Draw");
    }
    public String gameWin() {
        String message = " wins";
        return message;
    }
    public void aiEasyLevel() {
        System.out.println("Making move level \"easy\"");
    }
    public void incorrectParameter() {
        System.out.println("Bad parameters!");
    }
    public void commandPrompt() {
        System.out.print("Input command: ");
    }
    public void gameEnd() {
        // This is just for printing head-space
        System.out.println();
    }
    public void aiMediumLevel() {
        System.out.println("Making move level \"medium\"");
    }
}