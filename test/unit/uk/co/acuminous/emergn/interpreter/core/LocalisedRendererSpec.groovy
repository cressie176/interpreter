package uk.co.acuminous.emergn.interpreter.core

import static uk.co.acuminous.emergn.interpreter.core.Quantifier.*
import org.springframework.context.support.StaticMessageSource
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import uk.co.acuminous.emergn.interpreter.core.Partial
import uk.co.acuminous.emergn.interpreter.core.Renderer
import uk.co.acuminous.emergn.interpreter.core.LocalisedRenderer

class LocalisedRendererSpec extends Specification {

    @Shared
    Locale english = Locale.UK

    @Shared
    StaticMessageSource messageSource = new StaticMessageSource()

    void setupSpec() {
        ['0': 'zero', '1': 'one', '2': 'two', '3': 'three', '4': 'four',
         conjunction: 'and', hundred: 'hundred', thousand: 'thousand'].each { String key, String msg ->
            messageSource.addMessage(key, english, msg)
        }
    }

    @Unroll
    def "Renders partial"() {

        given:
            Partial partial = new Partial(value: value, quantifier: quantifier, conjunction: conjunction)
            Renderer renderer = new LocalisedRenderer(locale: english, messageSource: messageSource)

        when:
            renderer.append(partial)

        then:
            renderer.render() == result

        where:
            value | quantifier      | conjunction | result
            0     | null            | false       | 'zero'
            1     | null            | false       | 'one'
            2     | null            | true        | 'and two'
            3     | hundreds        | false       | 'three hundred'
            4     | tensOfThousands | true        | 'and four thousand'
    }

    def "Renders one hundred"() {
        given:
            Renderer renderer = new LocalisedRenderer(locale: english, messageSource: messageSource)

        when:
            renderer.append(new Partial(value: 1, quantifier: hundreds, conjunction: false))
            renderer.append(new Partial(conjunction: false))

        then:
            renderer.render() == 'one hundred'

    }


    def "Renders one hundred and one"() {
        given:
            Renderer renderer = new LocalisedRenderer(locale: english, messageSource: messageSource)

        when:
            renderer.append(new Partial(value: 1, quantifier: hundreds, conjunction: false))
            renderer.append(new Partial(value: 1, conjunction: true))

        then:
            renderer.render() == 'one hundred and one'

    }

    def 'Renderer is clean when renderer has not been used'() {

        given:
            Renderer renderer = new LocalisedRenderer(locale: english, messageSource: messageSource)

        expect:
            renderer.isDirty() == false

    }

    def 'Renderer is clean when words have not been appended'() {

        given:
            Renderer renderer = new LocalisedRenderer(locale: english, messageSource: messageSource)

        when:
            renderer.append(new Partial())

        then:
            renderer.isDirty() == false        
    }

    def 'Renderer is dirty when renderer has been appended to'() {

        given:
            Renderer renderer = new LocalisedRenderer(locale: english, messageSource: messageSource)

        when:
            renderer.append(new Partial(value: 1))

        then:
            renderer.isDirty() == true         
    }

}
