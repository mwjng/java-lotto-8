package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RanksTest {

    @DisplayName("당첨 결과를 집계한다")
    @Test
    void 당첨_결과를_집계한다() {
        // given
        Ranks ranks = Ranks.of(List.of(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.THIRD));

        // when
        Map<Rank, Integer> rankCount = ranks.countByRank();

        // then
        assertThat(rankCount).hasSize(3)
                .containsEntry(Rank.FIRST, 1)
                .containsEntry(Rank.SECOND, 1)
                .containsEntry(Rank.THIRD, 2);
    }
}