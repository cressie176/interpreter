package uk.co.acuminous.emergn.interpreter.core

import grails.plugin.spock.IntegrationSpec
import spock.lang.Unroll

import org.codehaus.groovy.grails.commons.GrailsApplication
import uk.co.acuminous.emergn.interpreter.core.NumericInterpreter

class ConfigurationSpec extends IntegrationSpec {

    GrailsApplication grailsApplication

    @Unroll
    def "Test some whacky numbers are translated correctly"() {

        given:
            NumericInterpreter numericInterpreter = grailsApplication.mainContext.getBean('numericInterpreter')

        expect:
            numericInterpreter.interpret(number) == text

        where:
            number    | text
                    0 | 'zero'
                    1 | 'one'
                   10 | 'ten'
                   11 | 'eleven'
                   99 | 'ninety nine'
                  100 | 'one hundred'
                  101 | 'one hundred and one'
                  110 | 'one hundred and ten'
                  901 | 'nine hundred and one'
                 1000 | 'one thousand'
                 1001 | 'one thousand and one'
                 1010 | 'one thousand and ten'
                 1100 | 'one thousand one hundred'
                 1101 | 'one thousand one hundred and one'
                 9999 | 'nine thousand nine hundred and ninety nine'
                10000 | 'ten thousand'
                10001 | 'ten thousand and one'
                10100 | 'ten thousand one hundred'
                11111 | 'eleven thousand one hundred and eleven'
                99999 | 'ninety nine thousand nine hundred and ninety nine'
               100000 | 'one hundred thousand'
               100001 | 'one hundred thousand and one'
               100100 | 'one hundred thousand one hundred'
               100101 | 'one hundred thousand one hundred and one'
               101101 | 'one hundred and one thousand one hundred and one'
               111000 | 'one hundred and eleven thousand'
              1001000 | 'one million one thousand'        
              8000000 | 'eight million'
              8888888 | 'eight million eight hundred and eighty eight thousand eight hundred and eighty eight'
             90000000 | 'ninety million'
            999999999 | 'nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine'
    }

}
