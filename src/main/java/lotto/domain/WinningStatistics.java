package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WinningStatistics {
    private final Map<Rank, Integer> rankCount;

    private WinningStatistics(Map<Rank, Integer> rankCount) {
        this.rankCount = rankCount;
    }

    public static WinningStatistics of(Map<Rank, Integer> rankCount) {
        return new WinningStatistics(rankCount);
    }

    public static WinningStatistics from(List<Rank> ranks) {
        Map<Rank, Integer> rankCount = ranks.stream()
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));

        return of(rankCount);
    }

    public double calculateProfitRate(PurchaseAmount purchaseAmount) {
        long totalPrizeAmount = totalPrizeAmount();
        long purchasableAmount = purchaseAmount.getPurchasableAmount();

        return (double) totalPrizeAmount / purchasableAmount * 100;
    }

    public Map<Rank, Integer> getRankCount() {
        return Map.copyOf(rankCount);
    }

    private long totalPrizeAmount() {
        return rankCount.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrizeAmount() * entry.getValue())
                .sum();
    }
}
