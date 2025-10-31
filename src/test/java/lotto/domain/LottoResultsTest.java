package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultsTest {

    @DisplayName("LottoResults를 생성한다")
    @Test
    void lottoResults를_생성한다() {
        // given
        LottoResult first = LottoResult.of(6, false);
        LottoResult second = LottoResult.of(5, true);
        LottoResult third = LottoResult.of(5, false);
        List<LottoResult> givenLottoResults = List.of(first, second, third);

        // when
        LottoResults lottoResults = LottoResults.of(givenLottoResults);

        // then
        assertThat(lottoResults).isNotNull();
    }

    @DisplayName("당첨 결과를 집계한다")
    @Test
    void 당첨_결과를_집계한다() {
        // given
        LottoResult first = LottoResult.of(6, false);
        LottoResult second = LottoResult.of(5, true);
        LottoResult third = LottoResult.of(5, false);
        LottoResult anotherThird = LottoResult.of(5, false);

        LottoResults lottoResults = LottoResults.of(List.of(first, second, third, anotherThird));

        // when
        Map<Rank, Integer> rankCount = lottoResults.countByRank();

        // then
        assertThat(rankCount).hasSize(3)
                .containsEntry(Rank.FIRST, 1)
                .containsEntry(Rank.SECOND, 1)
                .containsEntry(Rank.THIRD, 2);
    }
}