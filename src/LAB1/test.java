package LAB1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class test {
	Lab1 t = new Lab1();

	@ParameterizedTest
    @MethodSource("parameterDataProvider")
    void test(String url, int l, String result) throws IOException {
        t.read(url);
        assertEquals(result, t.toString());
    }

    public static Stream<Arguments> parameterDataProvider() {
        return Stream.of(
                Arguments.of("C:\\Users\\sunfu\\Desktop\\codeExample.c", 1, "total num: 35"),
                Arguments.of("C:\\Users\\sunfu\\Desktop\\codeExample.c", 2, "switch num: 2\ncase num: 3 2"),
                Arguments.of("C:\\Users\\sunfu\\Desktop\\codeExample.c", 3, "if-else num: 2"),
                Arguments.of("C:\\Users\\sunfu\\Desktop\\codeExample.c", 4, "if-elseif-else num: 2")
        );
    }
}
