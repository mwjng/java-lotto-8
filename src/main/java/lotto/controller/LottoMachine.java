package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoResults;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.WinningStatistics;
import lotto.dto.BonusNumberRequest;
import lotto.dto.LottosResponse;
import lotto.dto.WinningNumbersRequest;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachine {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        PurchaseAmount purchaseAmount = readInputPurchaseAmount();

        int lottoCount = purchaseAmount.toLottoCount();
        outputView.showPurchasedLottoCountMessage(lottoCount);

        Lottos lottos = Lottos.create(lottoCount);
        showPurchasedLottos(lottos);

        WinningLotto winningLotto = readInputWinningLotto();
        WinningStatistics winningStatistics = createWinningStatistics(lottos, winningLotto);

        showResultStatistics(winningStatistics, purchaseAmount);
    }

    private PurchaseAmount readInputPurchaseAmount() {
        while (true) {
            try {
                outputView.requestInputPurchaseAmount();
                String inputPurchaseAmount = inputView.read();

                return PurchaseAmount.from(inputPurchaseAmount);
            } catch (IllegalArgumentException e) {
                outputView.showErrorMessage(e);
            }
        }
    }

    private void showPurchasedLottos(Lottos lottos) {
        LottosResponse lottosResponse = LottosResponse.from(lottos);
        outputView.showPurchasedLottos(lottosResponse);
    }

    private WinningLotto readInputWinningLotto() {
        Lotto winningNumbers = readInputWinningNumbers();

        while (true) {
            try {
                LottoNumber bonusNumber = readInputBonusNumber();
                return WinningLotto.of(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.showErrorMessage(e);
            }
        }
    }

    private Lotto readInputWinningNumbers() {
        while (true) {
            try {
                outputView.requestInputWinningNumbers();
                String inputWinningNumbers = inputView.read();

                WinningNumbersRequest winningNumbersRequest = WinningNumbersRequest.from(inputWinningNumbers);
                return winningNumbersRequest.toLotto();
            } catch (IllegalArgumentException e) {
                outputView.showErrorMessage(e);
            }
        }
    }

    private LottoNumber readInputBonusNumber() {
        outputView.requestInputBonusNumber();
        String inputBonusNumber = inputView.read();

        BonusNumberRequest bonusNumberRequest = BonusNumberRequest.from(inputBonusNumber);
        return bonusNumberRequest.toLottoNumber();
    }

    private WinningStatistics createWinningStatistics(Lottos lottos, WinningLotto winningLotto) {
        LottoResults lottoResults = matchAll(lottos, winningLotto);
        Map<Rank, Integer> rankCount = lottoResults.countByRank();

        return WinningStatistics.of(rankCount);
    }

    private LottoResults matchAll(Lottos lottos, WinningLotto winningLotto) {
        List<LottoResult> results = lottos.getLottos().stream()
                .map(winningLotto::match)
                .toList();

        return LottoResults.of(results);
    }

    private void showResultStatistics(WinningStatistics winningStatistics, PurchaseAmount purchaseAmount) {
        outputView.showWinningStatistics(winningStatistics.getRankCount());
        showProfitRate(winningStatistics, purchaseAmount);
    }

    private void showProfitRate(WinningStatistics winningStatistics, PurchaseAmount purchaseAmount) {
        double profitRate = winningStatistics.calculateProfitRate(purchaseAmount);
        outputView.showProfitRate(profitRate);
    }
}
