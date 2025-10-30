package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;

    private static final String NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 %d부터 %d 사이의 숫자여야 합니다.";

    private final int number;

    private LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public static LottoNumber of(int number) {
        return new LottoNumber(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber other = (LottoNumber) o;
        return number == other.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    private void validateRange(int number) {
        if (isOutOfRange(number)) {
            throw new IllegalArgumentException(
                    NUMBER_RANGE_ERROR_MESSAGE.formatted(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
            );
        }
    }

    private boolean isOutOfRange(int number) {
        return number < MINIMUM_LOTTO_NUMBER
                || number > MAXIMUM_LOTTO_NUMBER;
    }
}
