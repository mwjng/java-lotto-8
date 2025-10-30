package lotto.dto;

import java.util.List;
import lotto.domain.Lottos;

public record LottosResponse(List<LottoResponse> lottos) {

    public static LottosResponse from(Lottos lottos) {
        List<LottoResponse> lottoResponses = lottos.getLottos().stream()
                .map(LottoResponse::from)
                .toList();

        return new LottosResponse(lottoResponses);
    }
}
