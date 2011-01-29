package uk.co.acuminous.emergn.interpreter.geb

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import geb.spock.GebReportingSpec

@Mixin(WaitFor)
abstract class FunctionalSpec extends GebReportingSpec {

    String getBaseUrl() {
        ConfigurationHolder.config.grails.serverURL + '/'
    }

    File getReportDir() {
        new File("target/test-reports/geb")
    }
}
