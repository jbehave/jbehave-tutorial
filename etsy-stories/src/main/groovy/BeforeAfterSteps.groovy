import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories

class BeforeAfterSteps {

    @BeforeStories
    def one() {
        println "one";
    }

    @AfterStories
    def two() {
        println "two";
    }

}
