package org.jbehave.tutorials.etsy;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import org.jbehave.core.model.*;
import org.jbehave.core.reporters.*;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.StepMonitor;
import org.jbehave.core.steps.StepType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

class XRefCapturingFormatAndStepMonitor extends Format implements StepMonitor {

    private Root root = new Root();
    public String currStoryPath;
    public String currScenarioTitle;

    private static class Root {
        private Map<String, Set<String>> metaMap = new HashMap<String, Set<String>>();
        private List<Stori> stories = new ArrayList<Stori>();
        public List<StepMatch> stepMatches = new ArrayList<StepMatch>();
    }

    @SuppressWarnings("unused")
    private static class Stori {
        private String description;
        private String narrative = "";
        private String name;
        private String path;
        private String meta = "";
        private List<Scenari0> scenarios = new ArrayList<Scenari0>();

        public Stori(Story story, Root root) {
            Narrative narrative = story.getNarrative();
            if (!narrative.isEmpty()) {
                this.narrative = "In order to " + narrative.inOrderTo() + "\n" +
                        "As a " + narrative.asA() + "\n" +
                        "I want to " + narrative.iWantTo() + "\n";
            }
            this.description = story.getDescription().asString();
            this.name = story.getName();
            this.path = story.getPath();
            for (String next : story.getMeta().getPropertyNames()) {
                Set<String> vals = root.metaMap.get(next);
                if (vals == null) {
                    vals = new HashSet<String>();
                    root.metaMap.put(next, vals);
                }
                String val = story.getMeta().getProperty(next);
                vals.add(val);
                meta = meta + next + "=" + val + "\n";

            }
            List<Scenario> scenarios1 = story.getScenarios();
            for (Scenario scenario : scenarios1) {
                scenarios.add(new Scenari0(scenario));
            }
        }
    }

    @SuppressWarnings("unused")
    private static class Scenari0 {
        private String title;
        private String body;

        public Scenari0(Scenario scenario) {
            title = scenario.getTitle();
            body = "Scenario:" + scenario.getTitle() + "\n";
            List<String> steps = scenario.getSteps();
            for (String s : steps) {
                body = body + s + "\n";
            }
        }
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

    public void outputToFiles(StoryReporterBuilder storyReporterBuilder) throws IOException {
        try {
            outputFile("xref.xml", new XStream(), storyReporterBuilder);
            outputFile("xref.json", new XStream(new JsonHierarchicalStreamDriver()), storyReporterBuilder);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("eeee " + e.getMessage());
        }
    }

    private void outputFile(String name, XStream xstream, StoryReporterBuilder storyReporterBuilder) throws IOException {
        File outputDir = storyReporterBuilder.outputDirectory();
        System.out.println("---->OD " + outputDir.getCanonicalPath());
        outputDir.mkdirs();
        FileWriter fileWriter = new FileWriter(new File(outputDir, name));
        fileWriter.write(configure(xstream).toXML(root));
        fileWriter.flush();
        fileWriter.close();

    }

    private XStream configure(XStream xstream) {
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.alias("xref", Root.class);
        xstream.alias("StepMatch", StepMatch.class);
        xstream.alias("Story", Story.class);
        // xStream.addImplicitCollection(Scenario.class, "steps", "step",
        // List.class);
//        xstream.useAttributeFor(Story.class, "path");
//        xstream.useAttributeFor(StepMatch.class, "storyPath");
//        xstream.useAttributeFor(Scenario.class, "title");
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
                root.stories.add(new Stori(story, root));
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
