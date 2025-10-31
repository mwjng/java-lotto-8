package lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Rank {

    FIFTH("5등", 5_000L) {
        @Override
        public boolean supports(int matchCount, boolean matchBonus) {
            return matchCount == 3;
        }
    },
    FOURTH("4등", 50_000L) {
        @Override
        public boolean supports(int matchCount, boolean matchBonus) {
            return matchCount == 4;
        }
    },
    THIRD("3등", 1_500_000L) {
        @Override
        public boolean supports(int matchCount, boolean matchBonus) {
            return matchCount == 5 && !matchBonus;
        }
    },
    SECOND("2등", 30_000_000L) {
        @Override
        public boolean supports(int matchCount, boolean matchBonus) {
            return matchCount == 5 && matchBonus;
        }
    },
    FIRST("1등", 2_000_000_000L) {
        @Override
        public boolean supports(int matchCount, boolean matchBonus) {
            return matchCount == 6;
        }
    };

    private final String description;
    private final PrizeAmount prizeAmount;

    Rank(String description, long amount) {
        this.description = description;
        this.prizeAmount = PrizeAmount.of(amount);
    }

    public static Optional<Rank> from(int matchCount, boolean matchBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.supports(matchCount, matchBonus))
                .findFirst();
    }

    public long getPrizeAmount() {
        return prizeAmount.getAmount();
    }

    public abstract boolean supports(int matchCount, boolean matchBonus);
}
