package domain;

public class Count {
    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 99;
    private int count;

    public Count(final int count) {
        validate(count);
        this.count = count;
    }

    private void validate(final int count) {
        if (count < MIN_COUNT) {
            throw new IllegalArgumentException("0이하로 주문할 수 없습니다.");
        }
        if (count > MAX_COUNT) {
            throw new IllegalArgumentException("한 메뉴의 최대 주문량은 99입니다.");
        }
    }

    public Count addCount(Count count) {
        final int addResult = this.count + count.getCount();
        return new Count(addResult);
    }

    public int getCount() {
        return count;
    }
}
