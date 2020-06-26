package domain.menu;

import java.util.Objects;

public class Menu {
    private static final int MIN_NUMBER = 1;
    private static final int MIN_PRICE = 0;

    private final int number;
    private final String name;
    private final Category category;
    private final int price;

    public Menu(final int number, final String name, final Category category, final int price) {
        validateNumber(number);
        validateName(name);
        Objects.requireNonNull(category, "카테고리가 null입니다.");
        validatePrice(price);
        this.number = number;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    private void validateNumber(final int number) {
        if (number < MIN_NUMBER) {
            throw new IllegalArgumentException("메뉴 번호는 양수만 입력 가능합니다.\n" +
                    "입력된 수: " + number);
        }
    }

    private void validateName(final String name) {
        Objects.requireNonNull(name, "메뉴명이 null입니다.");
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("메뉴명은 빈 문자열로만 이루어 질 수 없습니다.\n");
        }
    }

    private void validatePrice(final int price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException("가격은 0 또는 양수로 구성되어야 합니다.\n" +
                    "입력된 수: " + price);
        }
    }

    public boolean isSameNumber(final int number) {
        return this.number == number;
    }

    public boolean isChicken() {
        return category.isChicken();
    }

    public int multiply(final int count) {
        return price * count;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return category + " " + number + " - " + name + " : " + price + "원";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Menu menu = (Menu)o;
        return number == menu.number &&
                price == menu.price &&
                Objects.equals(name, menu.name) &&
                category == menu.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, category, price);
    }
}
