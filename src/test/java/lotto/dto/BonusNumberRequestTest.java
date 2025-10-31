package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberRequestTest {

    @DisplayName("입력 문자열을 받아 보너스 번호를 생성한다")
    @Test
    void 입력_문자열을_받아_보너스_번호를_생성한다() {
        // given
        String inputBonusNumber = "7";

        // when
        BonusNumberRequest bonusNumberRequest = BonusNumberRequest.from(inputBonusNumber);

        // then
        assertThat(bonusNumberRequest).isNotNull();
    }

    @DisplayName("보너스 번호에 숫자가 아닌 값을 입력하면 예외가 발생한다")
    @Test
    void 보너스_번호에_숫자가_아닌_값을_입력하면_예외가_발생한다() {
        // given
        String inputBonusNumber = "bonus";

        // when & then
        assertThatThrownBy(() -> BonusNumberRequest.from(inputBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 숫자를 입력해야 합니다.");
    }
}