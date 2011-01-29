package uk.co.acuminous.emergn.interpreter.geb

class WaitFor {

    def waitFor(Closure condition) {
        waitFor(null, condition)
    }

    def waitFor(Double timeoutSecs, Closure condition) {
        waitFor(timeoutSecs, null, condition)
    }

    def waitFor(Double timeoutSecs, Double intervalSecs, Closure condition) {
        timeoutSecs = timeoutSecs ?: 5
        intervalSecs = intervalSecs ?: 0.5
        def loops = Math.ceil(timeoutSecs / intervalSecs)
        def pass = condition()
        def i = 0
        while (pass == false && i++ < loops) {
            Thread.sleep((intervalSecs * 1000) as long)
            pass = condition()
        }
        if (i >= loops) {
            throw new AssertionError("condition did not pass in $timeoutSecs seconds")
        }
        true
    }
}
