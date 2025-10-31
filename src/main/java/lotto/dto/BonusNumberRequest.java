package lotto.dto;

import lotto.domain.LottoNumber;

public record BonusNumberRequest(int bonusNumber) {

    private static final String BONUS_NUMBER_FORMAT_ERROR_MESSAGE = "보너스 번호는 숫자를 입력해야 합니다.";

    public static BonusNumberRequest from(String inputBonusNumber) {
        try {
            int parsedBonusNumber = Integer.parseInt(inputBonusNumber);
            return new BonusNumberRequest(parsedBonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(BONUS_NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    public LottoNumber toLottoNumber() {
        return LottoNumber.of(bonusNumber);
    }
}
