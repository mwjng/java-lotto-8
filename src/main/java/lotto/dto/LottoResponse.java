package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;

public record LottoResponse(List<String> lotto) {

    public static LottoResponse from(Lotto lotto) {
        List<String> lottoNumbers = lotto.getNumbers().stream()
                .sorted()
                .map(String::valueOf)
                .toList();

        return new LottoResponse(lottoNumbers);
    }
}
