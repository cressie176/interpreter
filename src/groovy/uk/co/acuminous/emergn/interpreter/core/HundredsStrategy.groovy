package uk.co.acuminous.emergn.interpreter.core

class HundredsStrategy extends BaseRenderingStrategy {

    void apply(Integer number) {
        value = quantifier.extract(number);
        
        if (value != 0) {
            appendValueAndQuantifier()
        }
    }

}
