package lotto.domain;

import java.util.Map;

public class WinningStatistics {
    private final Map<Rank, Integer> rankCount;

    private WinningStatistics(Map<Rank, Integer> rankCount) {
        this.rankCount = rankCount;
    }

    public static WinningStatistics of(Map<Rank, Integer> rankCount) {
        return new WinningStatistics(rankCount);
    }

    public double calculateProfitRate(PurchaseAmount purchaseAmount) {
        long totalPrizeAmount = rankCount.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrizeAmount() * entry.getValue())
                .sum();

        long purchasableAmount = purchaseAmount.getPurchasableAmount();

        return (double) totalPrizeAmount / purchasableAmount * 100;
    }

    public Map<Rank, Integer> getRankCount() {
        return Map.copyOf(rankCount);
    }
}
