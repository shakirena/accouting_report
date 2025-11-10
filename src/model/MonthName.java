package model;

public enum MonthName {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private final int number;

    private MonthName(int value) {
        number = value;
    }

    public int getNumber() {
        return number;
    }

    public static MonthName getMonthName(int value) {
        for (MonthName m : MonthName.values()) {
            if (m.getNumber() == value) {
                return m;
            }
        }
        throw new IllegalArgumentException("Invalid month name: " + value);
    }
}
