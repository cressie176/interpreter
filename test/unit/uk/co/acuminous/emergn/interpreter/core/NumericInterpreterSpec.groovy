package uk.co.acuminous.emergn.interpreter.core

import spock.lang.Specification

class NumericInterpreterSpec extends Specification {

    def "Numeric interpreter applies strategies then renders result"() {

        given:
            Renderer renderer = Mock()
            BaseRenderingStrategy strategy1 = Mock()
            BaseRenderingStrategy strategy2 = Mock()

            NumericInterpreter interpreter = new NumericInterpreter(
                renderer: renderer,
                strategies: [strategy1, strategy2]
            )
        
            Integer number = 978342

        when:
            interpreter.interpret(number)

        then:
            1 * strategy1.setRenderer(renderer)
            1 * strategy1.apply(number)

        then:
            1 * strategy2.setRenderer(renderer)
            1 * strategy2.apply(number)

        then:
            1 * renderer.render()
    }
}
