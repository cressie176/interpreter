package uk.co.acuminous.emergn.interpreter.core

public interface Renderer {

    void append(Partial partial)
    String render()
    Boolean isDirty()

}