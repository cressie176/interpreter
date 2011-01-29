package uk.co.acuminous.emergn.interpreter.core

import spock.lang.Specification
import static uk.co.acuminous.emergn.interpreter.core.Quantifier.*
import uk.co.acuminous.emergn.interpreter.core.Renderer
import uk.co.acuminous.emergn.interpreter.core.RenderingStrategy
import uk.co.acuminous.emergn.interpreter.core.HundredsStrategy
import uk.co.acuminous.emergn.interpreter.core.Partial

class HundredsStrategySpec extends Specification {

    def "Nothing is rendered when value is zero"() {

        given:
            Renderer renderer = Mock()
            RenderingStrategy strategy = new HundredsStrategy(quantifier: hundredsOfMillions, renderer: renderer)

        when:
            strategy.apply(87654321)

        then:
            0 * renderer.append(_)

    }

    def "Number and quantifier are rendered when quantifier value is non zero"() {
        given:
            Renderer renderer = Mock()
            HundredsStrategy strategy = new HundredsStrategy(quantifier: hundredsOfMillions, renderer: renderer)

        when:
            strategy.apply(987654321)

        then:
            1 * renderer.append({ Partial p ->
                p.value == 9
                p.quantifier == hundredsOfMillions
                p.conjunction == false
            })
    }

}
