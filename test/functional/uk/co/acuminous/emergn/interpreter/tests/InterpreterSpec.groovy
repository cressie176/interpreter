package uk.co.acuminous.emergn.interpreter.tests

import uk.co.acuminous.emergn.interpreter.pages.InterpreterPage
import uk.co.acuminous.emergn.interpreter.geb.FunctionalSpec

class InterpreterSpec extends FunctionalSpec {

    def "Test numbers are interpreted"() {

        given:
            to InterpreterPage

        when:
            number = '1234'
            number.jquery.trigger('keyup')

        then:
            waitFor {
                text.text() != ''
            }

            text.text() == 'one thousand two hundred and thirty four'
    }

    def "Test errors are reported"() {
        given:
            to InterpreterPage

        when:
            number = 'a'
            number.jquery.trigger('keyup')

        then:
            waitFor {
                text.text() != ''
            }

            text.text() == 'Failed to interpret: a'
    }
}
