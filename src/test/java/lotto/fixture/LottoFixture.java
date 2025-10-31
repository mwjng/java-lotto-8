package lotto.fixture;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public final class LottoFixture {

    private LottoFixture() {
    }

    public static List<LottoNumber> lottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::of)
                .toList();
    }

    public static Lotto lotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = lottoNumbers(numbers);
        return Lotto.of(lottoNumbers);
    }
}
