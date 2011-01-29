package uk.co.acuminous.emergn.interpreter.core

import org.springframework.context.MessageSource

class LocalisedRenderer implements Renderer {

    Locale locale
    MessageSource messageSource
    List words = []

    void append(Partial partial) {

        if (partial.conjunction) {
            words << resolve("conjunction")
        }

        if (partial.value != null) {
            words << resolve("${partial.value}")
        }

        if (partial.quantifier != null) {
            words << resolve(partial.quantifier.code)
        }
    }

    String render() {
        String text = words.join(' ')
        words.clear()
        return text
    }

    Boolean isDirty() {
        return !words.isEmpty()
    }

    private String resolve(String key) {
        return messageSource.getMessage(key, [] as Object[], locale)
    }
}
