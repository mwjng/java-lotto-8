package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersRequestTest {

    @DisplayName("입력 문자열을 파싱하여 WinningNumbersRequest를 생성한다")
    @Test
    void 입력_문자열을_파싱하여_WinningNumbersRequest를_생성한다() {
        // given
        String inputWinningNumbers = "1,2,3,4,5,6";

        // when
        WinningNumbersRequest winningNumbersRequest = WinningNumbersRequest.from(inputWinningNumbers);

        // then
        assertThat(winningNumbersRequest.winningNumbers())
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("입력 문자열에 숫자가 아닌 값이 있으면 예외가 발생한다")
    @Test
    void 입력_문자열에_숫자가_아닌_값이_있으면_예외가_발생한다() {
        // given
        String inputWinningNumbers = "1,2,a,4,5,c";

        // when & then
        assertThatThrownBy(() -> WinningNumbersRequest.from(inputWinningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호는 숫자를 입력해야 합니다");
    }
}