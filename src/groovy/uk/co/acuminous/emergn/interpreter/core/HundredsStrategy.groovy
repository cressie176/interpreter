package uk.co.acuminous.emergn.interpreter.core

class HundredsStrategy extends BaseRenderingStrategy {

    void apply(Integer number) {
        Integer value = quantifier.extract(number);
        
        if (value != 0) {
            renderer.append(new Partial(value: value, quantifier: quantifier))
        }
    }

}
