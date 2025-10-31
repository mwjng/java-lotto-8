package lotto.view.display;

import lotto.domain.Rank;

public enum RankDisplay {

    FIFTH(Rank.FIFTH, "3개 일치 (%,d원) - %d개"),
    FOURTH(Rank.FOURTH, "4개 일치 (%,d원) - %d개"),
    THIRD(Rank.THIRD, "5개 일치 (%,d원) - %d개"),
    SECOND(Rank.SECOND, "5개 일치, 보너스 볼 일치 (%,d원) - %d개"),
    FIRST(Rank.FIRST, "6개 일치 (%,d원) - %d개");

    private final Rank rank;
    private final String display;

    RankDisplay(Rank rank, String display) {
        this.rank = rank;
        this.display = display;
    }

    public Rank getRank() {
        return rank;
    }

    public String getDisplay(int rankCount) {
        return display.formatted(rank.getPrizeAmount(), rankCount);
    }
}
