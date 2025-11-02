package lotto.domain;

import static lotto.domain.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static lotto.domain.LottoNumber.MINIMUM_LOTTO_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class Lotto {
    public static final int LOTTO_NUMBER_COUNT = 6;

    private static final String NUMBER_COUNT_ERROR_MESSAGE = "로또 번호는 %d개여야 합니다.";
    private static final String NUMBER_DUPLICATE_ERROR_MESSAGE = "로또에 중복된 숫자가 있으면 안됩니다.";

    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        validateLength(numbers);
        validateNoDuplicate(numbers);
        this.numbers = numbers;
    }

    public static Lotto of(List<LottoNumber> numbers) {
        return new Lotto(numbers);
    }

    public static Lotto createRandomLotto() {
        List<Integer> generatedNumbers = Randoms.pickUniqueNumbersInRange(
                MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER, LOTTO_NUMBER_COUNT
        );
        List<LottoNumber> lottoNumbers = generatedNumbers.stream()
                .map(LottoNumber::of)
                .toList();

        return of(lottoNumbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public int countMatch(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contains)
                .count();
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }

    private void validateLength(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(
                    NUMBER_COUNT_ERROR_MESSAGE.formatted(LOTTO_NUMBER_COUNT)
            );
        }
    }

    private void validateNoDuplicate(List<LottoNumber> numbers) {
        if (hasDuplicate(numbers)) {
            throw new IllegalArgumentException(NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
    }

    private boolean hasDuplicate(List<LottoNumber> numbers) {
        return numbers.stream()
                .distinct()
                .count() != numbers.size();
    }
}
