package uk.co.acuminous.emergn.interpreter.core

import spock.lang.Specification

import static uk.co.acuminous.emergn.interpreter.core.Quantifier.*
import spock.lang.Unroll

class QuantifierSpec extends Specification {

    @Unroll
    def "Quantifier extracts numbers correctly"() {

        expect:
            quantifier.extract(987654321) == value

        where:
            quantifier          | value
            hundredsOfMillions  | 9
            tensOfMillions      | 87
            hundredsOfThousands | 6
            tensOfThousands     | 54
            hundreds            | 3
            tens                | 21


    }

}
