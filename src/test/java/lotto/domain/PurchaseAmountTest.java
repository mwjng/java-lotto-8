package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @DisplayName("구입금액을 입력받는다")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "50000", "88000", "9990000"})
    void 구입금액을_입력받는다(String inputPurchaseAmount) {
        // when
        PurchaseAmount purchaseAmount = PurchaseAmount.from(inputPurchaseAmount);

        // then
        assertThat(purchaseAmount).isNotNull();
    }

    @DisplayName("구입금액으로 입력한 값이 숫자가 아니면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"!!!", "()", "money"})
    void 구입금액으로_입력한_값이_숫자가_아니면_예외가_발생한다(String inputPurchaseAmount) {
        // when & then
        assertThatThrownBy(() -> PurchaseAmount.from(inputPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 숫자를 입력해야 합니다.");
    }

    @DisplayName("구입금액이 1000원 미만이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"-1000", "0", "500", "999"})
    void 구입금액이_1000원_미만이면_예외가_발생한다(String inputPurchaseAmount) {
        // when & then
        assertThatThrownBy(() -> PurchaseAmount.from(inputPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 최소 1000원 이상 입력해야 합니다.");
    }

    @DisplayName("구입금액이 1000원 단위가 아니면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1100", "3333", "5500", "99900"})
    void 구입금액이_1000원_단위가_아니면_예외가_발생한다(String inputPurchaseAmount) {
        // when & then
        assertThatThrownBy(() -> PurchaseAmount.from(inputPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 단위로 입력해야 합니다.");
    }
}