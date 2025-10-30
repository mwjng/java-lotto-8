package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.dto.LottosResponse;
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
}
