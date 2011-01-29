import uk.co.acuminous.emergn.*
import uk.co.acuminous.emergn.interpreter.core.Quantifier as Q
import org.springframework.beans.factory.config.ListFactoryBean
import uk.co.acuminous.emergn.interpreter.core.LocalisedRenderer
import uk.co.acuminous.emergn.interpreter.core.TensStrategy
import uk.co.acuminous.emergn.interpreter.core.NumericInterpreter
import uk.co.acuminous.emergn.interpreter.core.HundredsStrategy
import uk.co.acuminous.emergn.interpreter.core.LeastSignificantStrategy
import uk.co.acuminous.emergn.interpreter.core.NumericInterpreter

beans = {

    localisedRenderer(LocalisedRenderer) {
        locale = Locale.UK        
        messageSource = ref('messageSource')
    }

    tens(LeastSignificantStrategy) { bean ->
        quantifier = Q.tens
    }

    hundreds(HundredsStrategy) { bean ->
        quantifier = Q.hundreds
    }

    tensOfThousands(TensStrategy) { bean ->
        quantifier = Q.tensOfThousands
        relatedQuantifier = Q.hundredsOfThousands
    }

    hundredsOfThousands(HundredsStrategy) { bean ->
        quantifier = Q.hundredsOfThousands
    }

    tensOfMillions(TensStrategy) { bean ->
        quantifier = Q.tensOfMillions
        relatedQuantifier = Q.hundredsOfMillions        
    }

    hundredsOfMillions(HundredsStrategy) { bean ->
        quantifier = Q.hundredsOfMillions
    }

    numericInterpreter(NumericInterpreter) { bean ->
        renderer = ref('localisedRenderer')
        strategies = { ListFactoryBean factory ->
            sourceList = [
                ref('hundredsOfMillions'),
                ref('tensOfMillions'),
                ref('hundredsOfThousands'),
                ref('tensOfThousands'),                    
                ref('hundreds'),
                ref('tens')
            ]
        }
    }
}
