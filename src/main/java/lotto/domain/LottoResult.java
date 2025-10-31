package lotto.domain;

import java.util.Optional;

public class LottoResult {
    private static final String MATCH_COUNT_RANGE_ERROR_MESSAGE = "매칭 개수는 %d 이상 %d 이하여야 합니다";

    private static final int MINIMUM_MATCH_COUNT = 0;
    private static final int MAXIMUM_MATCH_COUNT = 6;

    private final int matchCount;
    private final boolean matchBonus;

    private LottoResult(int matchCount, boolean matchBonus) {
        validateRange(matchCount);
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
    }

    public static LottoResult of(int matchCount, boolean matchBonus) {
        return new LottoResult(matchCount, matchBonus);
    }

    public Optional<Rank> toRank() {
        return Rank.from(matchCount, matchBonus);
    }

    private void validateRange(int matchCount) {
        if (isOutOfRange(matchCount)) {
            throw new IllegalArgumentException(
                    MATCH_COUNT_RANGE_ERROR_MESSAGE.formatted(MINIMUM_MATCH_COUNT, MAXIMUM_MATCH_COUNT)
            );
        }
    }

    private boolean isOutOfRange(int matchCount) {
        return matchCount < MINIMUM_MATCH_COUNT
                || matchCount > MAXIMUM_MATCH_COUNT;
    }
}
