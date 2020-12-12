package ru.fin.elementary

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import ru.fin.function.elementary.impl.LnImpl

class LnTest {
    @ParameterizedTest
    @CsvFileSource(resources = ["/elementary/ln.csv"], numLinesToSkip = 1)
    fun lnTest(
        x: Double,
        ln: Double,
        error: Double
    ) {
        Assertions.assertEquals(ln, LnImpl().ln(x, error), error)
    }
}