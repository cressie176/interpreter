package uk.co.acuminous.emergn.interpreter.core

class TensStrategy extends BaseRenderingStrategy {

    Quantifier relatedQuantifier

    void apply(Integer number) {
        value = quantifier.extract(number)
        Integer relatedValue = relatedQuantifier.extract(number)
        
        if (relatedValue != 0 && value == 0) {
            appendQuantifier()
        } else if (value == 0) {
            // Do nothing
        } else if (!renderer.isDirty() || relatedValue == 0) {
            appendValueAndQuantifier()
        } else {
            appendValueQuantifierAndConjunction()            
        }
    }
}
