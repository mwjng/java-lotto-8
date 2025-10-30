package lotto.domain;

import static lotto.domain.Lotto.LOTTO_NUMBER_COUNT;
import static lotto.domain.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static lotto.domain.LottoNumber.MINIMUM_LOTTO_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public static Lottos create(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = createLotto();
            lottos.add(lotto);
        }
        return of(lottos);
    }

    private static Lotto createLotto() {
        List<Integer> generatedNumbers = Randoms.pickUniqueNumbersInRange(
                MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER, LOTTO_NUMBER_COUNT
        );
        List<LottoNumber> lottoNumbers = generatedNumbers.stream()
                .map(LottoNumber::of)
                .toList();

        return Lotto.of(lottoNumbers);
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }
}
