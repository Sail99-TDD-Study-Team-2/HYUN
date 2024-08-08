package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    /*
     *
     * [ 유료 서비스 비용 만료일 결정 규칙 ]
     * 1. 서비스를 사용하려면 매달 1만 원 선불 납부.
     *      납부일 기준으로 한 달 뒤가 서비스 만료일(한달뒤라는 기준이 애매한것같다...)
     * 2. 2개월 이상 요금을 납부할 수 있다.
     * 3. 10만 원 납부시 서비스 1년 제공
     *
     */

    // 1. 쉬운 구현
    @Test
    @DisplayName("만원_납부하면_한달뒤_만료")
    void pay_10_000_won() {
        // 예시 1
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019,4,1)
        );

        // 예시 2 (동일 조건의 예 추가하면서 일반화)
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024,8,8))
                        .payAmount(10_000).build(),
                LocalDate.of(2024,9,8)
        );
    }

    /*
     * 2. 예외상황 처리
     * - 납부일이 2024-01-31 & 납부액 1만원 -> 만료일 2024-02-29
     * - 납부일이 2024-05-31 & 납부액 1만원 -> 만료일 2024-06-30
     * - 납부일이 2025-01-31 & 납부액 1만원 -> 만료일 2024-02-28
     */
    @Test
    @DisplayName("납부일과_한달_뒤_일자가_같지않음")
    void plus1month_isNOT_expiryDate() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024,1,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024,2,29)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024,5,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024,6,30)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2025,1,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2025,2,28)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        // 예시1
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2024,1,31))
                .billingDate(LocalDate.of(2024,2,29))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2024,3,31));
        // 예시2
        PayData pd2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2024,1,30))
                .billingDate(LocalDate.of(2024,2,29))
                .payAmount(10_000)
                .build();
        assertExpiryDate(pd2, LocalDate.of(2024,3,30));
        // 예시3
        PayData pd3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2024,5,31))
                .billingDate(LocalDate.of(2024,6,30))
                .payAmount(10_000)
                .build();
        assertExpiryDate(pd3, LocalDate.of(2024,7,31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        // 2만원 납부
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024,3,1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2024,5,1)
        );
        // 3만원 납부
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024,3,1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2024,6,1)
        );
    }

    // 예외 상황 테스트 추가
    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2024,1,31))
                        .billingDate(LocalDate.of(2024,2,29))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2024,4,30)
        );
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2024,1,31))
                        .billingDate(LocalDate.of(2024,2,29))
                        .payAmount(40_000)
                        .build(),
                LocalDate.of(2024,6,30)
        );
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2024,3,31))
                        .billingDate(LocalDate.of(2024,4,30))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2024,7,31)
        );
    }

    @Test
    void 십만원_납부시_1년_제공() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024,1,28))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2025,1,28)
        );
    }

    // 스스로 어른이
    @Test
    void 윤달_마지막날_10만원_납부() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024,2,29))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2025,2,28)
        );
        /*assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023,2,28))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2024,2,29)
        );*/ // 모르겠다... 기준이 이상한것같다... ㅜ
    }

    @Test
    void 십만원_초과_납부하는_경우() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024,8,8))
                        .payAmount(130_000)
                        .build(),
                LocalDate.of(2025,11,8)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023,10,1))
                        .payAmount(250_000)
                        .build(),
                LocalDate.of(2026,3,1)
        );
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate,realExpiryDate);
    }

}
