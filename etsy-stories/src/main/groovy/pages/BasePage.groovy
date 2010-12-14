package pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import java.net.ConnectException;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import com.github.tanob.groobe.GrooBe
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.WebElement

public class BasePage extends WebDriverPage {

  public BasePage(WebDriverProvider driverProvider) {
    super(driverProvider)
    GrooBe.activate()
  }

}
