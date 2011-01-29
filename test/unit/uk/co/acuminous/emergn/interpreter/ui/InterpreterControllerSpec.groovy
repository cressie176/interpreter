package uk.co.acuminous.emergn.interpreter.ui

import grails.plugin.spock.ControllerSpec
import uk.co.acuminous.emergn.interpreter.core.NumericInterpreter
import javax.servlet.http.HttpServletResponse

class InterpreterControllerSpec extends ControllerSpec {

    def "Index page is rendered"() {

        when:
            controller.index()

        then:
            renderArgs.view == 'index'

    }

    def "Numbers are interpretted"() {

        given:
            NumericInterpreter numericInterpreter = Mock()
            controller.numericInterpreter = numericInterpreter

        when:
            controller.params.number = "$number"
            controller.interpret()

        then:
            1 * numericInterpreter.interpret(number) >> text
            controller.response.contentAsString == text
            renderArgs.contentType == 'text/plain'        

        where:
            number | text
            0      | 'zero'
            1234   | 'one thousand two hundred and thirty four'
    }

    def "Errors are handled gracefully"() {

        given:
            NumericInterpreter numericInterpreter = Mock()
            controller.numericInterpreter = numericInterpreter
            String number = '123'

        when:
            controller.params.number = number
            controller.interpret()

        then:
            1 * numericInterpreter.interpret(_) >> { throw new NumberFormatException() }
            controller.response.status == HttpServletResponse.SC_BAD_REQUEST
            controller.response.contentAsString == "Failed to interpret: $number"
            renderArgs.contentType == 'text/plain'
    }

}
