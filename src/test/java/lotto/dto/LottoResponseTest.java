package lotto.dto;

import static lotto.fixture.LottoFixture.lotto;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResponseTest {

    @DisplayName("Lotto를 오름차순으로 정렬하여 LottoResponse를 생성한다")
    @Test
    void Lotto를_오름차순으로_정렬하여_LottoResponse를_생성한다() {
        // given
        Lotto lotto = lotto(List.of(31, 45, 40, 1, 18, 11));

        // when
        LottoResponse response = LottoResponse.from(lotto);

        // then
        assertThat(response.lotto())
                .containsExactly("1", "11", "18", "31", "40", "45");
    }
}