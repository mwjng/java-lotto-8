package lotto.domain;

public class PrizeAmount {
    private static final String PRIZE_AMOUNT_RANGE_ERROR_MESSAGE = "상금은 음수일 수 없습니다.";

    private final long amount;

    private PrizeAmount(long amount) {
        validateRange(amount);
        this.amount = amount;
    }

    public static PrizeAmount of(long amount) {
        return new PrizeAmount(amount);
    }

    public long getAmount() {
        return amount;
    }

    private void validateRange(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(PRIZE_AMOUNT_RANGE_ERROR_MESSAGE);
        }
    }
}
