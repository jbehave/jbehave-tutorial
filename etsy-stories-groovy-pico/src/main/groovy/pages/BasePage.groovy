package pages;


import com.github.tanob.groobe.GrooBe
import geb.Browser
import org.jbehave.web.selenium.WebDriverPage
import org.jbehave.web.selenium.WebDriverProvider

public class BasePage extends WebDriverPage {

    public BasePage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
        GrooBe.activate()
    }

    def methodMissing(String name, args) {
        try {
            getBrowser()."$name"(* args)
        } catch (Exception re) {
            throw new RuntimeException(where(re), re)
        }
    }

    String where(Exception e) {
        StackTraceElement[] stes = e.getStackTrace()
        for (int i = 0; i < stes.length; i++) {
            StackTraceElement ste = stes[i];
            if (ste.getClassName().equals(BasePage.class.getName())
                    && ste.getMethodName().equals("methodMissing")) {
                i++
                def className = ste.getClassName()
                while (className.startsWith("org.org.codehaus.groovy") ||
                       className.startsWith("groovy.lang") ||
                       className.startsWith("java.lang.reflect") ||
                       className.startsWith("sun.reflect.")) {
                    i++
                }
                ste = stes[i-1]
                return "Failure in:" + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")"
            }
        }
        return "unknown line causing methodMissing error"
    }

    private browser

    private Browser getBrowser() {
        if (browser == null) {
            browser = new Browser()
            browser.setDriver(webDriver())
        }
        browser
    }

}
