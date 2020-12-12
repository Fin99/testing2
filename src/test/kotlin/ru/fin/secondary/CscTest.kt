package ru.fin.secondary

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.mockito.AdditionalMatchers
import org.mockito.Matchers
import org.mockito.Mockito
import ru.fin.function.elementary.Sin
import ru.fin.function.secondary.impl.CscImpl

class CscTest {
    private val elementarySin = Mockito.mock(Sin::class.java)

    @ParameterizedTest
    @CsvFileSource(resources = ["/secondary/csc.csv"], numLinesToSkip = 1)
    fun cotTest(
        x: Double,
        sin: Double,
        csc: Double,
        error: Double
    ) {
        Mockito.`when`(elementarySin.sin(AdditionalMatchers.eq(x, error), Matchers.anyDouble())).thenReturn(sin)
        Assertions.assertEquals(csc, CscImpl(elementarySin).csc(x, error), error)
    }
}