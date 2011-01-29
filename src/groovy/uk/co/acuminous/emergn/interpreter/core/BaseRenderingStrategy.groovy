package uk.co.acuminous.emergn.interpreter.core

abstract class BaseRenderingStrategy implements RenderingStrategy {

    Renderer renderer
    Quantifier quantifier
    Integer value

    void appendValue() {
        renderer.append(new Partial(value: value))
    }

    void appendQuantifier() {
        renderer.append(new Partial(quantifier: quantifier))
    }

    void appendValueAndQuantifier() {
        renderer.append(new Partial(value: value, quantifier: quantifier))
    }

    void renderValueAndConjunction() {
        renderer.append(new Partial(value: value, conjunction: true))
    }

    void appendValueQuantifierAndConjunction() {
        renderer.append(new Partial(value: value, quantifier: quantifier, conjunction: true))        
    }

}
