package ru.fin.elementary

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import ru.fin.function.elementary.impl.SinImpl

class SinTest {
    @ParameterizedTest
    @CsvFileSource(resources = ["/elementary/sin.csv"], numLinesToSkip = 1)
    fun sinTest(
        x: Double,
        sin: Double,
        error: Double
    ) {
        Assertions.assertEquals(sin, SinImpl().sin(x, error), error)
    }
}