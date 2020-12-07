package com.sibedge.ergo.util;

import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("a sql wildcard converter")
class WildcardConverterTest {

    @ParameterizedTest
    @DisplayName("converts wildcard-less string")
    @MethodSource("wildcardPairs")
    public void testSimpleText(String wildcardBefore, String wildcardAfter) {
        // given

        // when
        var convertedText = WildcardConverter.asSqlLikeWildcard(wildcardBefore);

        // then
        Assertions.assertThat(convertedText).isEqualTo(wildcardAfter);
    }

    static Stream<Arguments> wildcardPairs() {
        return Stream.of(
                Arguments.arguments("", ""),
                Arguments.arguments("simmple text", "simmple text"),
                Arguments.arguments("*ous", "%ous"),
                Arguments.arguments("com?", "com_"),
                Arguments.arguments("*", "%"),
                Arguments.arguments("?", "_"),
                Arguments.arguments("**", "%%"),
                Arguments.arguments("*?*", "%_%"),
                Arguments.arguments("*%?", "%\\%_"),
                Arguments.arguments("_", "\\_"),
                Arguments.arguments("people * use? *", "people % use_ %"),
                Arguments.arguments("80-90% simple_text", "80-90\\% simple\\_text"),
                Arguments.arguments("80-90% simple_tex? *our", "80-90\\% simple\\_tex_ %our")
        );
    }
};
