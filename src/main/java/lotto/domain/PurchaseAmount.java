package lotto.domain;

import java.util.Objects;

public class PurchaseAmount {
    private static final String PURCHASE_AMOUNT_FORMAT_ERROR_MESSAGE = "구입 금액은 숫자를 입력해야 합니다.";
    private static final String PURCHASE_AMOUNT_RANGE_ERROR_MESSAGE = "구입 금액은 최소 %d원 이상 입력해야 합니다.";
    private static final String PURCHASE_AMOUNT_UNIT_ERROR_MESSAGE = "구입 금액은 %d원 단위로 입력해야 합니다.";

    private static final long LOTTO_PRICE = 1000L;
    private static final long AMOUNT_UNIT = 1000L;

    private final long amount;

    private PurchaseAmount(long amount) {
        validateRange(amount);
        validateUnit(amount);
        this.amount = amount;
    }

    public static PurchaseAmount of(long amount) {
        return new PurchaseAmount(amount);
    }

    public static PurchaseAmount from(String inputPurchaseAmount) {
        try {
            long parsedPurchaseAmount = Long.parseLong(inputPurchaseAmount);
            return of(parsedPurchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_FORMAT_ERROR_MESSAGE, e);
        }
    }

    public int toLottoCount() {
        return (int) (amount / LOTTO_PRICE);
    }

    public long getPurchasableAmount() {
        return toLottoCount() * LOTTO_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PurchaseAmount other = (PurchaseAmount) o;
        return amount == other.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    private void validateRange(long amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(
                    PURCHASE_AMOUNT_RANGE_ERROR_MESSAGE.formatted(LOTTO_PRICE)
            );
        }
    }

    private void validateUnit(long amount) {
        if (amount % AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(
                    PURCHASE_AMOUNT_UNIT_ERROR_MESSAGE.formatted(AMOUNT_UNIT)
            );
        }
    }
}
