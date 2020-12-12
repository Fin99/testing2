package ru.fin.secondary

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.mockito.AdditionalMatchers
import org.mockito.Matchers
import org.mockito.Mockito
import ru.fin.function.elementary.Sin
import ru.fin.function.secondary.impl.CosImpl

class CotTest {
    private val elementarySin = Mockito.mock(Sin::class.java)
    @ParameterizedTest
    @CsvFileSource(resources = ["/secondary/cot.csv"], numLinesToSkip = 1)
    fun cotTest(
        x: Double,
        sin: Double,
        cos: Double,
        cot: Double,
        error: Double
    ) {
        Mockito.`when`(elementarySin.sin(AdditionalMatchers.eq(x, error), Matchers.anyDouble())).thenReturn(sin)
        Mockito.`when`(elementarySin.sin(AdditionalMatchers.eq(x + Math.PI / 2, error), Matchers.anyDouble())).thenReturn(cos)
        Assertions.assertEquals(cos, CosImpl(elementarySin).cos(x, error), error)
    }
}