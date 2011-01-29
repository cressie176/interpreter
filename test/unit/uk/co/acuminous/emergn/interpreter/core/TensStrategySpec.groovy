package uk.co.acuminous.emergn.interpreter.core

import spock.lang.Specification
import static uk.co.acuminous.emergn.interpreter.core.Quantifier.*

class TensStrategySpec extends Specification {

    def "Quantifier is rendered when related hundreds is non zero and value is zero"() {

        given:
            Renderer renderer = Mock()
            RenderingStrategy strategy = new TensStrategy(
                    quantifier: tensOfMillions, relatedQuantifier: hundredsOfMillions, renderer: renderer)

        when:
            strategy.apply(900654321)

        then:
            1 * renderer.append({ Partial p ->
                p.value == null &&
                p.quantifier == tensOfMillions &&
                p.conjunction == false
            })
    }

    def "Otherwise nothing is rendered when value is zero"() {

        given:
            Renderer renderer = Mock()
            RenderingStrategy strategy = new TensStrategy(
                    quantifier: tensOfThousands, relatedQuantifier: hundredsOfThousands, renderer: renderer)

        when:
            strategy.apply(987000321)

        then:
            0 * renderer.append(_)
    }

    def "Otherwise number and quantifier are rendered when related hundreds is zero"() {

        given:
            Renderer renderer = Mock()
            RenderingStrategy strategy = new TensStrategy(
                    quantifier: tensOfThousands, relatedQuantifier: hundredsOfThousands, renderer: renderer)

        when:
            strategy.apply(987054321)

        then:
            1 * renderer.append({ Partial p ->
                p.value == 54 &&
                p.quantifier == tensOfThousands &&
                p.conjunction == false
            })
    }
    
    def "Otherwise number and quantifier are rendered when the renderer is unused"() {

        given:
            Renderer renderer = Mock()
            RenderingStrategy strategy = new TensStrategy(
                    quantifier: tensOfThousands, relatedQuantifier: hundredsOfThousands, renderer: renderer)

        when:
            strategy.apply(54321)

        then:
            1 * renderer.isDirty() >> false
            1 * renderer.append({ Partial p ->
                p.value == 54 &&
                p.quantifier == tensOfThousands &&
                p.conjunction == false
            })
    }

    def "Otherwise number and quantifier and conjunction are rendererd"() {

        given:
            Renderer renderer = Mock()
            RenderingStrategy strategy = new TensStrategy(
                    quantifier: tensOfThousands, relatedQuantifier: hundredsOfThousands, renderer: renderer)

        when:
            strategy.apply(987654321)

        then:
            1 * renderer.isDirty() >> true
            1 * renderer.append({ Partial p ->
                p.value == 54 &&
                p.quantifier == tensOfThousands &&
                p.conjunction == true
            })
    }

}
