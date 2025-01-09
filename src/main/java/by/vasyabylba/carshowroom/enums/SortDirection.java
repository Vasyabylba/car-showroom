package by.vasyabylba.carshowroom.enums;

public enum SortDirection {
    ASC,
    DESC;

    public boolean isAscending() {
        return this.equals(ASC);
    }

    public boolean isDescending() {
        return this.equals(DESC);
    }
}
