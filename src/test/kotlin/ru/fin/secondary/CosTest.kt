package ru.fin.secondary

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.mockito.AdditionalMatchers.eq
import org.mockito.Matchers.anyDouble
import org.mockito.Mockito
import org.mockito.Mockito.mock
import ru.fin.function.elementary.Sin
import ru.fin.function.secondary.impl.CosImpl

class CosTest {
    private val elementarySin = mock(Sin::class.java)
    @ParameterizedTest
    @CsvFileSource(resources = ["/secondary/cos.csv"], numLinesToSkip = 1)
    fun cosTest(
        x: Double,
        sin: Double,
        cos: Double,
        error: Double
    ) {
        Mockito.`when`(elementarySin.sin(eq(x + Math.PI / 2, error), anyDouble())).thenReturn(cos)
        Assertions.assertEquals(cos, CosImpl(elementarySin).cos(x, error), error)
    }
}