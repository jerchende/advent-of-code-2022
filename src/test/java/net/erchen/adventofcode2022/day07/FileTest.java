package net.erchen.adventofcode2022.day07;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @Test
    void shouldInitByInput() {
        var file = File.fromInput("123 abc");

        assertThat(file.name()).isEqualTo("abc");
        assertThat(file.size()).isEqualTo(123L);
    }

}