package lotto.view.display;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankDisplayTest {

    @DisplayName("당첨 결과를 출력한다")
    @ParameterizedTest
    @CsvSource(value = {
            "FIFTH : 2 : 3개 일치 (5,000원) - 2개",
            "FOURTH : 3 : 4개 일치 (50,000원) - 3개",
            "THIRD : 1 : 5개 일치 (1,500,000원) - 1개",
            "SECOND : 1 : 5개 일치, 보너스 볼 일치 (30,000,000원) - 1개",
            "FIRST : 0 : 6개 일치 (2,000,000,000원) - 0개"
    }, delimiter = ':')
    void 당첨_결과를_출력한다(RankDisplay rankDisplay, int rankCount, String expectedMessage) {
        // when
        String result = rankDisplay.getDisplay(rankCount);

        // then
        assertThat(result).isEqualTo(expectedMessage);
    }
}