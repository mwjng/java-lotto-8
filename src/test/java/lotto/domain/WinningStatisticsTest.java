package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    @DisplayName("WinningStatistics를 생성한다")
    @Test
    void WinningStatistics를_생성한다() {
        // given
        Map<Rank, Integer> rankCount = Map.of(Rank.FIFTH, 2, Rank.SECOND, 3, Rank.THIRD, 1);

        // when
        WinningStatistics winningStatistics = WinningStatistics.of(rankCount);

        // then
        assertThat(winningStatistics.getRankCount()).isEqualTo(rankCount);
    }

    @DisplayName("수익률을 계산한다")
    @Test
    void 수익률을_계산한다() {
        // given
        PurchaseAmount purchaseAmount = PurchaseAmount.of(10000);
        Map<Rank, Integer> rankCount = Map.of(Rank.FIFTH, 2, Rank.SECOND, 3, Rank.THIRD, 1);
        WinningStatistics winningStatistics = WinningStatistics.of(rankCount);

        // when
        double profitRate = winningStatistics.calculateProfitRate(purchaseAmount);

        // then
        assertThat(profitRate).isCloseTo(915100.0, within(0.0001));
    }
}