package org.nightdivers.kupica;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

/*
    PR Test 시 Test Report 가 잘 나오는지 확인하기 위한 항상 실패하는 테스트
    Test Report 가 정상적으로 나오면 바로 삭제될 예정
 */
@ActiveProfiles("test")
public class TestResultTest {

    @DisplayName("PR Test 시 Test Report 가 잘 나오는지 확인하기 위한 항상 실패하는 테스트 1")
    @Test
    public void testAlwaysFailure1() {
        assert false;
    }

    @DisplayName("PR Test 시 Test Report 가 잘 나오는지 확인하기 위한 항상 실패하는 테스트 2")
    @Test
    public void testAlwaysFailure2() {
        int i = 5;
        assert i == 4;
    }

    @DisplayName("PR Test 시 Test Report 가 잘 나오는지 확인하기 위한 항상 실패하는 테스트 3")
    @Test
    public void testAlwaysFailure3() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        assert integers.size() == 4;
    }
}
