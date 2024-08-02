package chap01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {

    @Test
    void subString() {
        String str = "abcd";
        assertEquals("cd", str.substring(2, 4));
    }
}
