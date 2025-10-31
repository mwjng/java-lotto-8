package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.domain.Rank;
import lotto.dto.LottoResponse;
import lotto.dto.LottosResponse;
import lotto.view.display.RankDisplay;

public class OutputView {
    private static final String INPUT_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String LOTTO_COUNT_MESSAGE = "%d개를 구매했습니다.";
    private static final String WINNING_STATISTICS_MESSAGE = "당첨 통계";

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final String LOTTO_NUMBER_FORMAT = "[%s]";
    private static final String STATISTICS_DIVIDER = "---";

    private static final int DEFAULT_RANK_COUNT = 0;

    public void requestInputPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT_MESSAGE);
    }

    public void requestInputWinningNumbers() {
        System.out.println("\n" + INPUT_WINNING_LOTTO_MESSAGE);
    }

    public void requestInputBonusNumber() {
        System.out.println("\n" + INPUT_BONUS_NUMBER_MESSAGE);
    }

    public void showPurchasedLottoCountMessage(int lottoCount) {
        System.out.printf("\n" + LOTTO_COUNT_MESSAGE + "\n", lottoCount);
    }

    public void showPurchasedLottos(LottosResponse lottosResponse) {
        List<LottoResponse> lottoResponses = lottosResponse.lottos();
        lottoResponses.forEach(this::showPurchasedLotto);
    }

    public void showWinningStatisticsMessage() {
        System.out.println("\n" + WINNING_STATISTICS_MESSAGE);
    }

    public void showWinningStatistics(Map<Rank, Integer> rankCount) {
        System.out.println(STATISTICS_DIVIDER);

        Arrays.stream(RankDisplay.values())
                .forEach(rankDisplay -> showRankResult(rankCount, rankDisplay));
    }

    private void showRankResult(Map<Rank, Integer> rankCount, RankDisplay rankDisplay) {
        Rank rank = rankDisplay.getRank();
        int count = rankCount.getOrDefault(rank, DEFAULT_RANK_COUNT);

        System.out.println(rankDisplay.getDisplay(count));
    }

    public void showErrorMessage(Exception e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    private void showPurchasedLotto(LottoResponse lottoResponse) {
        List<String> lotto = lottoResponse.lotto();
        String joinedLotto = String.join(LOTTO_NUMBER_DELIMITER, lotto);

        System.out.printf(LOTTO_NUMBER_FORMAT + "\n", joinedLotto);
    }
}
