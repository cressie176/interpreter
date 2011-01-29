package uk.co.acuminous.emergn.interpreter.core

public enum Quantifier {

    tens(2, 2, 'ten'),
    hundreds(3, 1, 'hundred'),
    tensOfThousands(5, 2, 'thousand'),
    hundredsOfThousands(6, 1, 'hundred'),
    tensOfMillions(8, 2, 'million'),
    hundredsOfMillions(9, 1, 'hundred')

    final Integer rightOffset
    final Integer numDigits
    final String code

    Quantifier(Integer rightOffset, Integer numDigits, String code) {
        this.rightOffset = rightOffset
        this.numDigits = numDigits
        this.code = code
    }

    Integer extract(Integer number) {
        Integer m = 10 ** rightOffset
        Integer d = 10 ** (rightOffset - numDigits)
        return (number % m) / d
    }
}