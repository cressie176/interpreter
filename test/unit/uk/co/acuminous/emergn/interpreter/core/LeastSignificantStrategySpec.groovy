package uk.co.acuminous.emergn.interpreter.core

import spock.lang.Specification
import static uk.co.acuminous.emergn.interpreter.core.Quantifier.*
import uk.co.acuminous.emergn.interpreter.core.Renderer
import uk.co.acuminous.emergn.interpreter.core.RenderingStrategy
import uk.co.acuminous.emergn.interpreter.core.LeastSignificantStrategy
import uk.co.acuminous.emergn.interpreter.core.Partial

class LeastSignificantStrategySpec extends Specification {

    def "Zero is rendered when value is zero and renderer is clean"() {

        given:
            Renderer renderer = Mock()
            RenderingStrategy strategy = new LeastSignificantStrategy(quantifier: tens, renderer: renderer)

        when:
            strategy.apply(0)

        then:
            1 * renderer.isDirty() >> false
            1 * renderer.append({ Partial p ->
                p.value == 0 &&
                p.quantifier == null &&
                p.conjunction == false
            })
    }

    def "Nothing is rendered when value is zero and renderer is dirty"() {

        given:
            Renderer renderer = Mock()
            RenderingStrategy strategy = new LeastSignificantStrategy(quantifier: tens, renderer: renderer)

        when:
            strategy.apply(0)

        then:
            1 * renderer.isDirty() >> true
            0 * renderer.append(_)
    }

    def "Value is rendered when value is non zero and renderer is clean"() {

        given:
            Renderer renderer = Mock()
            RenderingStrategy strategy = new LeastSignificantStrategy(quantifier: tens, renderer: renderer)

        when:
            strategy.apply(7)

        then:
            1 * renderer.isDirty() >> false
            1 * renderer.append({ Partial p ->
                p.value == 7 &&
                p.quantifier == null &&
                p.conjunction == false
            })
    }

    def "Value and conjunction are rendered when value is non zero and renderer is dirty"() {

        given:
            Renderer renderer = Mock()
            RenderingStrategy strategy = new LeastSignificantStrategy(quantifier: tens, renderer: renderer)

        when:
            strategy.apply(7)

        then:
            1 * renderer.isDirty() >> true
            1 * renderer.append({ Partial p ->
                p.value == 7 &&
                p.quantifier == null &&
                p.conjunction == true
            })
    }
}
