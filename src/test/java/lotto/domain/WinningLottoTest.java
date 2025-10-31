package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.dto.BonusNumberRequest;
import lotto.dto.WinningNumbersRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @DisplayName("WinningLotto를 생성한다")
    @Test
    void winningLotto를_생성한다() {
        // given
        String inputWinningNumbers = "1,2,3,4,5,6";
        String inputBonusNumber = "7";
        Lotto winningNumbers = WinningNumbersRequest.from(inputWinningNumbers).toLotto();
        LottoNumber bonusNumber = BonusNumberRequest.from(inputBonusNumber).toLottoNumber();

        // when
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);

        // then
        assertThat(winningLotto).isNotNull();
    }

    @DisplayName("보너스 번호와 당첨 번호가 중복되면 예외가 발생한다")
    @Test
    void 보너스_번호와_당첨_번호가_중복되면_예외가_발생한다() {
        // given
        String inputWinningNumbers = "1,2,3,4,5,6";
        String inputBonusNumber = "1";
        Lotto winningNumbers = WinningNumbersRequest.from(inputWinningNumbers).toLotto();
        LottoNumber bonusNumber = BonusNumberRequest.from(inputBonusNumber).toLottoNumber();

        // when & then
        assertThatThrownBy(() -> WinningLotto.of(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}