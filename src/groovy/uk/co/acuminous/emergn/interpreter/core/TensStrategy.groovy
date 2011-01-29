package uk.co.acuminous.emergn.interpreter.core

class TensStrategy extends BaseRenderingStrategy {

    Quantifier relatedQuantifier

    void apply(Integer number) {
        value = quantifier.extract(number)
        
        if (relatedQuantifier.extract(number) != 0 && value == 0) {
            appendQuantifier()
        } else if (value == 0) {
            // Do nothing
        } else if (renderer.isDirty()) {
            appendValueQuantifierAndConjunction()
        } else {
            appendValueAndQuantifier()
        }
    }
}
