package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class Treasury extends BasePage{
  def Treasury(WebDriverProvider webDriverProvider){
    super(webDriverProvider)
  }

  def chooseFirstGallery() {
    findElement(By.xpath("//td[@class='name']/h4/a")).click()
  }
}
