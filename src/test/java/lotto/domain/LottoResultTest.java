package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoResultTest {

    @DisplayName("LottoResult를 생성한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 3, 6})
    void lottoResult를_생성한다(int matchCount) {
        // given
        boolean matchBonus = false;

        // when
        LottoResult lottoResult = LottoResult.of(matchCount, matchBonus);

        // then
        assertThat(lottoResult).isNotNull();
    }

    @DisplayName("matchCount가 0보다 작으면 예외가 발생한다")
    @Test
    void matchCount가_0보다_작으면_예외가_발생한다() {
        // given
        int matchCount = -1;
        boolean matchBonus = false;

        // when & then
        assertThatThrownBy(() -> LottoResult.of(matchCount, matchBonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("매칭 개수는 0 이상 6 이하여야 합니다");
    }

    @DisplayName("matchCount가 6보다 크면 예외가 발생한다")
    @Test
    void matchCount가_6보다_크면_예외가_발생한다() {
        // given
        int matchCount = 7;
        boolean matchBonus = false;

        // when & then
        assertThatThrownBy(() -> LottoResult.of(matchCount, matchBonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("매칭 개수는 0 이상 6 이하여야 합니다");
    }

    @DisplayName("당첨 결과에 맞는 Rank를 반환한다")
    @Test
    void 당첨_결과에_맞는_Rank를_반환한다() {
        // given
        LottoResult lottoResult = LottoResult.of(5, true);

        // when
        Optional<Rank> rank = lottoResult.toRank();

        // then
        assertThat(rank).isPresent().get()
                .isEqualTo(Rank.SECOND);
    }
}