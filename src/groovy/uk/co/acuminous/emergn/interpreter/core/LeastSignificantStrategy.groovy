package uk.co.acuminous.emergn.interpreter.core

class LeastSignificantStrategy extends BaseRenderingStrategy {

    void apply(Integer number) {
        value = quantifier.extract(number)

        if (value == 0 && renderer.isDirty()) {
            // Do nothing
        } else if (value != 0 && renderer.isDirty()) {
            renderValueAndConjunction()            
        } else {
            appendValue()
        }        
    }
}
