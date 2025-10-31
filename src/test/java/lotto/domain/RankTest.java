package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @DisplayName("matchCount와 matchBonus를 받아 적절한 Rank를 반환한다")
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "3, false, FIFTH"
    })
    void matchCount와_matchBonus를_받아_적절한_Rank를_반환한다(int matchCount, boolean matchBonus, Rank expectedRank) {
        // when
        Optional<Rank> rank = Rank.from(matchCount, matchBonus);

        // then
        assertThat(rank).isPresent().get()
                .isEqualTo(expectedRank);
    }

    @DisplayName("당첨이 안되었을 경우에는 빈 값을 반환한다")
    @ParameterizedTest
    @CsvSource({
            "2, false",
            "1, true",
            "1, false"
    })
    void 당첨이_안되었을_경우에는_빈_값을_반환한다(int matchCount, boolean matchBonus) {
        // when
        Optional<Rank> rank = Rank.from(matchCount, matchBonus);

        // then
        assertThat(rank).isEmpty();
    }
}