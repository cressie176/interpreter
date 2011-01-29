package uk.co.acuminous.emergn.interpreter.core

class NumericInterpreter {

    Renderer renderer
    List strategies
    
    String interpret(Integer number) {
        strategies.each { BaseRenderingStrategy strategy ->
            strategy.setRenderer(renderer)
            strategy.apply(number)
        }
        return renderer.render()
    }
}
