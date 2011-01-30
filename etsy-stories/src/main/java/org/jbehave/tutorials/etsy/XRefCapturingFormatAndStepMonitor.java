package org.jbehave.tutorials.etsy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.NullStoryReporter;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.StepMonitor;
import org.jbehave.core.steps.StepType;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

class XRefCapturingFormatAndStepMonitor extends Format implements StepMonitor {

    private Root root = new Root();
    public String currStoryPath;
    public String currScenarioTitle;

    private static class Root {
        private List<Story> stories = new ArrayList<Story>();
        public List<StepMatch> stepMatches = new ArrayList<StepMatch>();
    }

    @SuppressWarnings("unused")
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

    public void outputToFiles() throws IOException {
        outputFile("xref.xml", new XStream());
        outputFile("xref.json", new XStream(new JsonHierarchicalStreamDriver()));
    }

    private void outputFile(String name, XStream xstream) throws IOException {        
        File outputDir = new StoryReporterBuilder().withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass())).outputDirectory();
        outputDir.mkdirs();
        new FileWriter(new File(outputDir, name)).write(configure(xstream).toXML(root));        
    }

    private XStream configure(XStream xstream) {
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.alias("xref", Root.class);
        xstream.alias("StepMatch", StepMatch.class);
        xstream.alias("Story", Story.class);
        // xStream.addImplicitCollection(Scenario.class, "steps", "step",
        // List.class);
        xstream.useAttributeFor(Story.class, "path");
        xstream.useAttributeFor(StepMatch.class, "storyPath");
        xstream.useAttributeFor(Scenario.class, "title");
        xstream.alias("Scenario", Scenario.class);
        xstream.omitField(ExamplesTable.class, "parameterConverters");
        xstream.omitField(ExamplesTable.class, "defaults");
        return xstream;
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

    public void stepMatchesType(String stepAsString, String previousAsString, boolean matchesType, StepType stepType,
            Method method, Object stepsInstance) {
        delegateStepMonitor.stepMatchesType(stepAsString, previousAsString, matchesType, stepType, method,
                stepsInstance);
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
