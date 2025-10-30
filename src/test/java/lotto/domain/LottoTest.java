package lotto.domain;

import static lotto.fixture.LottoFixture.lottoNumbers;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다")
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        // given
        List<LottoNumber> lottoNumbers = lottoNumbers(List.of(1, 2, 3, 4, 5, 6, 7));

        // when & then
        assertThatThrownBy(() -> Lotto.of(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 번호의 개수가 6개보다 작으면 예외가 발생한다")
    @Test
    void 로또_번호의_개수가_6개보다_작으면_예외가_발생한다() {
        // given
        List<LottoNumber> lottoNumbers = lottoNumbers(List.of(1, 2, 3, 4, 5));

        // when & then
        assertThatThrownBy(() -> Lotto.of(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        // given
        List<LottoNumber> lottoNumbers = lottoNumbers(List.of(1, 2, 3, 4, 5, 5));

        // when & then
        assertThatThrownBy(() -> Lotto.of(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또에 중복된 숫자가 있으면 안됩니다.");
    }
}
