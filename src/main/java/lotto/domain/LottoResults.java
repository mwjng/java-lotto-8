package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LottoResults {
    private final List<LottoResult> results;

    private LottoResults(List<LottoResult> results) {
        this.results = results;
    }

    public static LottoResults of(List<LottoResult> results) {
        return new LottoResults(results);
    }

    public Map<Rank, Integer> countByRank() {
        return results.stream()
                .map(LottoResult::toRank)
                .flatMap(Optional::stream)
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
    }

    public List<LottoResult> getResults() {
        return List.copyOf(results);
    }
}
