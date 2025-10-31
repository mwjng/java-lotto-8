package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PrizeAmountTest {

    @DisplayName("PrizeAmount를 생성한다")
    @ParameterizedTest
    @ValueSource(longs = {0, 2000000000})
    void prizeAmount를_생성한다(long amount) {
        // when
        PrizeAmount prizeAmount = PrizeAmount.of(amount);

        // then
        assertThat(prizeAmount.getAmount()).isEqualTo(amount);
    }

    @DisplayName("상금이 음수면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(longs = {-1, -150000, -500000000})
    void 상금이_음수면_예외가_발생한다(long amount) {
        // when & then
        assertThatThrownBy(() -> PrizeAmount.of(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상금은 음수일 수 없습니다.");
    }
}