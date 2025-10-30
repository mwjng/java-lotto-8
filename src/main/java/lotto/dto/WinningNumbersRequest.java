package lotto.dto;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public record WinningNumbersRequest(List<Integer> winningNumbers) {

    private static final String WINNING_NUMBERS_DELIMITER = ",";
    private static final String WINNING_NUMBERS_FORMAT_ERROR_MESSAGE = "당첨 번호는 숫자를 입력해야 합니다";

    public static WinningNumbersRequest from(String inputWinningNumbers) {
        try {
            List<Integer> winningNumbers = Arrays.stream(inputWinningNumbers.split(WINNING_NUMBERS_DELIMITER))
                    .map(Integer::parseInt)
                    .toList();

            return new WinningNumbersRequest(winningNumbers);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(WINNING_NUMBERS_FORMAT_ERROR_MESSAGE, e);
        }
    }

    public Lotto toLotto() {
        List<LottoNumber> winningLottoNumbers = winningNumbers.stream()
                .map(LottoNumber::of)
                .toList();

        return Lotto.of(winningLottoNumbers);
    }
}
