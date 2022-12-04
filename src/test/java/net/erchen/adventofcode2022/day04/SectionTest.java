package net.erchen.adventofcode2022.day04;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SectionTest {

    @Test
    void shouldInitFromInput() {
        var section = Section.fromInput("2-4");
        assertThat(section.from()).isEqualTo(2);
        assertThat(section.to()).isEqualTo(4);
    }

    @CsvSource({
            "2-4,6-8,false",
            "2-3,4-5,false",
            "5-7,7-9,false",
            "2-8,3-7,true",
            "6-6,4-6,false",
            "2-6,4-8,false",
            "4-6,6-6,true"
    })
    @ParameterizedTest
    void shouldFullyOverlaps(String section1S, String section2S, boolean isFullyOverlapped) {
        var section1 = Section.fromInput(section1S);
        var section2 = Section.fromInput(section2S);

        assertThat(section2.fullyOverlaps(section1)).isEqualTo(isFullyOverlapped);
    }


    @CsvSource({
            "2-4,6-8,false",
            "2-3,4-5,false",
            "5-7,7-9,true",
            "2-8,3-7,true",
            "6-6,4-6,true",
            "2-6,4-8,true",
            "4-6,6-6,true",
            "1-1,1-1,true",
            "1-1,2-2,false",
            "3-3,2-2,false",
            "3-3,2-3,true",
    })
    @ParameterizedTest
    void shouldPartiallyOverlaps(String section1S, String section2S, boolean isPartiallyOverlapped) {
        var section1 = Section.fromInput(section1S);
        var section2 = Section.fromInput(section2S);

        assertThat(section2.partiallyOverlaps(section1)).isEqualTo(isPartiallyOverlapped);
    }

}