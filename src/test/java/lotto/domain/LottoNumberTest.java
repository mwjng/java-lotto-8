package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("로또번호를 생성한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 20, 30, 45})
    void 로또번호를_생성한다(int givenNumber) {
        // when
        LottoNumber lottoNumber = LottoNumber.of(givenNumber);

        // then
        assertThat(lottoNumber.getNumber()).isEqualTo(givenNumber);
    }

    @DisplayName("로또번호가 1보다 작으면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-100, -10, -1, 0})
    void 로또번호가_1보다_작으면_예외가_발생한다(int givenNumber) {
        // when & then
        assertThatThrownBy(() -> LottoNumber.of(givenNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @DisplayName("로또번호가 45보다 크면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {46, 47, 80, 100})
    void 로또번호가_45보다_크면_예외가_발생한다(int givenNumber) {
        // when & then
        assertThatThrownBy(() -> LottoNumber.of(givenNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }
}