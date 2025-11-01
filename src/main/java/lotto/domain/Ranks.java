package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ranks {
    private final List<Rank> ranks;

    private Ranks(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public static Ranks of(List<Rank> ranks) {
        return new Ranks(ranks);
    }

    public Map<Rank, Integer> countByRank() {
        return ranks.stream()
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
    }

    public List<Rank> getRanks() {
        return List.copyOf(ranks);
    }
}
