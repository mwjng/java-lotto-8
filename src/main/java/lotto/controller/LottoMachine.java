package lotto.controller;

import lotto.domain.PurchaseAmount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachine {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        PurchaseAmount purchaseAmount = readInputPurchaseAmount();
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
}
