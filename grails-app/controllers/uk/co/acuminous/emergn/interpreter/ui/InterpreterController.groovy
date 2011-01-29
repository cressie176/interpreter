package uk.co.acuminous.emergn.interpreter.ui

import uk.co.acuminous.emergn.interpreter.core.NumericInterpreter
import javax.servlet.http.HttpServletResponse

class InterpreterController {

    NumericInterpreter numericInterpreter

    def index = {
        render(view: 'index')
    }

    def interpret = {
        String content
        try {
            content = numericInterpreter.interpret(Integer.parseInt(params.number))
        } catch (Exception e) {
            content = message(code:'badRequest', args:[params.number])
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)            
        }
        render(text: content, contentType: 'text/plain') // text/plain stops reverse proxy adding a doc type
    }

}
