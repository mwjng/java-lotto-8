package lotto.domain;

import static lotto.fixture.LottoFixture.lotto;
import static lotto.fixture.LottoFixture.lottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    private static Stream<Arguments> lottoContainsProvider() {
        return Stream.of(
                Arguments.of(lotto(List.of(1, 2, 3, 4, 5, 6)), LottoNumber.of(1), true),
                Arguments.of(lotto(List.of(1, 2, 3, 4, 5, 6)), LottoNumber.of(6), true),
                Arguments.of(lotto(List.of(1, 2, 3, 4, 5, 6)), LottoNumber.of(7), false),
                Arguments.of(lotto(List.of(1, 2, 3, 4, 5, 6)), LottoNumber.of(45), false)
        );
    }

    @DisplayName("로또 번호에 특정 번호가 포함되는지 여부를 판단한다")
    @ParameterizedTest
    @MethodSource("lottoContainsProvider")
    void 로또_번호에_특정_번호가_포함되는지_여부를_판단한다(Lotto lotto, LottoNumber lottoNumber, boolean expected) {
        // when
        boolean result = lotto.contains(lottoNumber);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> lottoMatchCountProvider() {
        return Stream.of(
                Arguments.of(lotto(List.of(1, 2, 3, 4, 5, 6)), lotto(List.of(3, 4, 5, 6, 7, 8)), 4),
                Arguments.of(lotto(List.of(2, 3, 4, 5, 6, 7)), lotto(List.of(3, 4, 5, 6, 7, 8)), 5),
                Arguments.of(lotto(List.of(1, 2, 3, 4, 5, 6)), lotto(List.of(7, 8, 9, 10, 11, 12)), 0)
        );
    }

    @DisplayName("일치하는 숫자 개수를 반환한다")
    @ParameterizedTest
    @MethodSource("lottoMatchCountProvider")
    void 일치하는_숫자_개수를_반환한다(Lotto lotto, Lotto otherLotto, int expected) {
        // when
        int countMatch = lotto.countMatch(otherLotto);

        // then
        assertThat(countMatch).isEqualTo(expected);
    }
}
