package lotto.domain;

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

    public static Lottos createRandomLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = Lotto.createRandomLotto();
            lottos.add(lotto);
        }
        return of(lottos);
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }
}
