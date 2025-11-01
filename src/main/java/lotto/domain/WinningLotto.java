package lotto.domain;

import java.util.List;
import java.util.Optional;

public class WinningLotto {
    private static final String BONUS_NUMBER_DUPLICATE_ERROR_MESSAGE = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    private WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        validateNoDuplicate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(Lotto winningNumbers, LottoNumber bonusNumber) {
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    public List<Rank> matchAll(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(this::match)
                .flatMap(Optional::stream)
                .toList();
    }

    private void validateNoDuplicate(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
    }

    private Optional<Rank> match(Lotto lotto) {
        int matchCount = winningNumbers.countMatch(lotto);
        boolean matchBonus = lotto.contains(bonusNumber);

        return Rank.from(matchCount, matchBonus);
    }
}
