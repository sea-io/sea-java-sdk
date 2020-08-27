package org.gmdev.jargs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArgsTest {

    Args underTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void itShouldBeValidIfNoArguments() throws ParseException, ArgsException {
        // Given
        String schema = "";
        String[] args = {};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThat(underTest.isValid()).isTrue();
        assertThat(underTest.usage()).isBlank();
    }

    @Test
    void itShouldThrowIfSchemaElementIsNoALetter() {
        // Given

        String schema = "1";
        String[] args = {"-1"};

        // When
        // Then
        assertThatThrownBy(() -> new Args(schema, args))
                .isInstanceOf(ParseException.class)
                .hasMessageContaining(
                        String.format("Bad character: %s in Args format: %s", "1", schema));
    }


    @Test
    void itShouldSetBooleanValue() throws ParseException, ArgsException {
        // Given
        String schema = "l";
        String[] args = {"-l"};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThat(underTest.getBoolean('l')).isTrue();
        assertThat(underTest.isValid()).isTrue();
    }

    @Test
    void itShouldNotSetBooleanValue() throws Exception {
        // Given
        String schema = "l";
        String[] args = {"-x"};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThat(underTest.getBoolean('x')).isFalse();
        assertThat(underTest.isValid()).isFalse();
        assertThat(underTest.errorMessage()).contains("unexpected");
    }

    @Test
    void itShouldSetStringValue() throws ParseException, ArgsException {
        // Given
        String name = "gians";
        String schema = "n*";
        String[] args = {"-n", name};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThat(underTest.getString('n')).isEqualTo(name);
        assertThat(underTest.isValid()).isTrue();
    }

    @Test
    void itShouldNotSetStringValue() throws Exception {
        // Given
        String name = "gians";
        String schema = "n*";
        String[] args = {"-x", name};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThat(underTest.getString('x')).isBlank();
        assertThat(underTest.isValid()).isFalse();
        assertThat(underTest.errorMessage()).contains("unexpected");
    }

    @Test
    void itShouldBeNotValidIfStringIsMissing() throws Exception {
        // Given
        String schema = "n*";
        String[] args = {"-n"};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThat(underTest.errorMessage())
                .isEqualTo(String.format("Could not find string parameter for -%c", 'n'));
    }

    @Test
    void itShouldSetIntegerValue() throws ParseException, ArgsException {
        // Given
        String giveNumber1 = "99";
        String giveNumber2 = "100";
        int expectedNumber1 = 99;
        int expectedNumber2 = 100;
        String schema = "n#,m#";
        String[] args = {"-n", giveNumber1, "-m", giveNumber2};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThat(underTest.getInt('n')).isEqualTo(expectedNumber1);
        assertThat(underTest.getInt('m')).isEqualTo(expectedNumber2);
        assertThat(underTest.isValid()).isTrue();
    }

    @Test
    void itShouldNotSetIntegerValue() throws Exception {
        String number = "99";
        String schema = "n#";
        String[] args = {"-x", number};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThat(underTest.getInt('x')).isEqualTo(0);
        assertThat(underTest.isValid()).isFalse();
        assertThat(underTest.errorMessage()).contains("unexpected");
    }

    @Test
    void itShouldBeNotValidIfIntegerIsMissing() throws Exception {
        // Given
        String schema = "n#";
        String[] args = {"-n"};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThat(underTest.errorMessage())
                .isEqualTo(String.format("Could not find integer parameter for -%c", 'n'));
    }

    @Test
    void itShouldBeNotValidIfIntegerIsNotANumber() throws Exception {
        // Given
        String number = "a";
        String schema = "n#";
        String[] args = {"-n", number};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThat(underTest.errorMessage())
                .isEqualTo(String.format("Invalid integer parameter for -%c", 'n'));
    }

    @Test
    void itShouldReturnTheCorrectArgumentsNumber() throws ParseException, ArgsException {
        // Given
        String schema = "f*,s*";
        String[] args = {"-f", "first", "-s", "second"};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThat(underTest.cardinality()).isEqualTo(2);
        assertThat(underTest.usage()).contains(schema);
        assertThat(underTest.has('f')).isTrue();
    }

    @Test
    void itShouldThrowWhenCallErrorMessageWithNoErrors() throws Exception {
        String schema = "";
        String[] args = {};

        // When
        underTest = new Args(schema, args);

        // Then
        assertThatThrownBy(() -> underTest.errorMessage())
                .isInstanceOf(Exception.class)
                .hasMessageContaining("TILT: Should not get here");
    }

    @Test
    void itShouldReturnBlankErrorMessageIfTestErrorCodeIsSet() throws Exception {
        // Given
        String schema = "";
        String[] args = {};

        // When
        underTest = new Args(schema, args);
        underTest.setErrorCode(Args.ErrorCode.TEST_CODE);

        // Then
        assertThat(underTest.errorMessage()).isBlank();
    }
}