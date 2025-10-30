package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottosTest {

    @DisplayName("여러 개의 로또를 생성한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 8, 30, 100})
    void 여러_개의_로또를_생성한다(int givenLottoCount) {
        // when
        Lottos lottos = Lottos.create(givenLottoCount);

        // then
        assertThat(lottos.getLottos()).hasSize(givenLottoCount);
    }
}