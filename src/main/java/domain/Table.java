package domain;

public class Table {
    private static final int MIN_NUMBER = 1;

    private final int number;

    public Table(final int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(final int number) {
        if (number < MIN_NUMBER) {
            throw new IllegalArgumentException("테이블 번호는 양수만 입력 가능합니다.\n" +
                    "입력된 수: " + number);
        }
    }

    public boolean isSameNumber(final int number) {
        return this.number == number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
