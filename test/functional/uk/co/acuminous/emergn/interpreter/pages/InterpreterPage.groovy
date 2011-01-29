package uk.co.acuminous.emergn.interpreter.pages

import geb.Page

class InterpreterPage extends Page {

    static url = 'interpreter/index'

    static at = { title == 'Interpreter'}

    static content = {        
        number { $('#number') }
        text { $('#text') }
    }

}
