package org.jbehave.tutorials.etsy;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.NullStoryReporter;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.StepMonitor;
import org.jbehave.core.steps.StepType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class XRefCapturingFormatAndStepMonitor extends org.jbehave.core.reporters.Format implements StepMonitor {

    private Root root = new Root();
    public String currStoryPath;
    public String currScenarioTitle;

    private static class Root {
        private List<Story> stories = new ArrayList<Story>();
        public List<StepMatch> stepMatches = new ArrayList<StepMatch>();
    }

    private static class StepMatch {
        private final String storyPath;
        private final String scenarioTitle;
        private final String step;
        private final String pattern;

        public StepMatch(String storyPath, String scenarioTitle, String step, String pattern) {
            this.storyPath = storyPath;
            this.scenarioTitle = scenarioTitle;
            this.step = step;
            this.pattern = pattern;
        }
    }

    private SilentStepMonitor delegateStepMonitor = new SilentStepMonitor();

    public XRefCapturingFormatAndStepMonitor() {
        super("xref collecting");
    }

    public void outputToFiles() throws FileNotFoundException {
        new PrintStream(new FileOutputStream("target/jbehave/xref.xml"))
                .println(withAliasesEtc(new XStream()).toXML(root));
        new PrintStream(new FileOutputStream("target/jbehave/xref.json"))
                .println(withAliasesEtc(new XStream(new JsonHierarchicalStreamDriver())).toXML(root));
    }

    private XStream withAliasesEtc(XStream xStream) {
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("xref", Root.class);
        xStream.alias("StepMatch", StepMatch.class);
        xStream.alias("Story", Story.class);
        //xStream.addImplicitCollection(Scenario.class, "steps", "step", List.class);
        xStream.useAttributeFor(Story.class, "path");
        xStream.useAttributeFor(StepMatch.class, "storyPath");
        xStream.useAttributeFor(Scenario.class, "title");
        xStream.alias("Scenario", Scenario.class);
        xStream.omitField(ExamplesTable.class, "parameterConverters");
        xStream.omitField(ExamplesTable.class, "defaults");
        return xStream;
    }

    @Override
    public StoryReporter createStoryReporter(FilePrintStreamFactory factory, StoryReporterBuilder storyReporterBuilder) {
        return new NullStoryReporter() {

            @Override
            public void beforeStory(Story story, boolean givenStory) {
                root.stories.add(story);
                currStoryPath = story.getPath();
            }

            @Override
            public void beforeScenario(String title) {
                currScenarioTitle = title;
            }
        };
    }

    public void stepMatchesPattern(String step, boolean matches, String pattern, Method method, Object stepsInstance) {
        if (matches) {
            root.stepMatches.add(new StepMatch(currStoryPath, currScenarioTitle, step, pattern));
        }
        delegateStepMonitor.stepMatchesPattern(step, matches, pattern, method, stepsInstance);
    }

    public void stepMatchesType(String stepAsString, String previousAsString, boolean matchesType, StepType stepType, Method method, Object stepsInstance) {
        delegateStepMonitor.stepMatchesType(stepAsString, previousAsString, matchesType, stepType, method, stepsInstance);
    }

    public void convertedValueOfType(String value, Type type, Object converted, Class<?> converterClass) {
        delegateStepMonitor.convertedValueOfType(value, type, converted, converterClass);
    }

    public void performing(String step, boolean dryRun) {
        delegateStepMonitor.performing(step, dryRun);
    }

    public void usingAnnotatedNameForParameter(String name, int position) {
        delegateStepMonitor.usingAnnotatedNameForParameter(name, position);
    }

    public void usingParameterNameForParameter(String name, int position) {
        delegateStepMonitor.usingParameterNameForParameter(name, position);
    }

    public void usingTableAnnotatedNameForParameter(String name, int position) {
        delegateStepMonitor.usingTableAnnotatedNameForParameter(name, position);
    }

    public void usingTableParameterNameForParameter(String name, int position) {
        delegateStepMonitor.usingTableParameterNameForParameter(name, position);
    }

    public void usingNaturalOrderForParameter(int position) {
        delegateStepMonitor.usingNaturalOrderForParameter(position);
    }

    public void foundParameter(String parameter, int position) {
        delegateStepMonitor.foundParameter(parameter, position);
    }
}
