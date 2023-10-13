package RUBank;

public enum Campus {
    NEW_BRUNSWICK(0),
    NEWARK(1),
    CAMDEN(2);

    private final int campusCode;

    Campus(int campusCode) {
        this.campusCode = campusCode;
    }

    public int getCampusCode() {
        return campusCode;
    }
}