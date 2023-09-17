public class Field {
    private final String FIELD_SEPERATOR = "-";
    private final String SIDE_TILE = "|";
    private final String EMPTY_TILE = " ";
    private final String EMPTY_CELL = "_";
    private final String PLAYER_X_SYMBOL = "X";
    private final String PLAYER_O_SYMBOL = "O";
    private final int FIELD_ROW = 3;
    private final int FIELD_COL = 3;
    private String[][] field;
    public String[][] getField() {
        return field;
    }
    public String getEMPTY_TILE() {
        return EMPTY_TILE;
    }
    public String getPLAYER_X_SYMBOL() {
        return PLAYER_X_SYMBOL;
    }
    public String getPLAYER_O_SYMBOL() {
        return PLAYER_O_SYMBOL;
    }
    public void initializeField() {
        field = new String[FIELD_ROW][FIELD_COL]; // Initialize the field array
        createField();
    }
    private void createField() {
        // Creating an empty field

        for (int row = 0; row < FIELD_ROW; row++) {
            for (int col = 0; col < FIELD_COL; col++) {
                field[row][col] = EMPTY_TILE;
            }
        }
    }
    public void printField() {
        // Printing a field

        System.out.println("---------");
        for (int row = 0; row < FIELD_ROW; row++) {
            System.out.print("| ");
            for (int col = 0; col < FIELD_COL; col++) {
                System.out.print(field[row][col] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}