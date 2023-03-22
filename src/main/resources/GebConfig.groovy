import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.remote.RemoteWebDriver

quitCachedDriverOnShutdown = true

driver = {
    Map<String, Object> prefs = new HashMap<String, Object>()
    prefs.put("credentials_enable_service", false)
    prefs.put("password_manager_enabled", false)
    prefs.put("profile.password_manager_enabled", false)
    prefs.put("profile.default_content_settings.popups", false)

    ChromeOptions options = new ChromeOptions()
    options.setExperimentalOption("prefs", prefs)
    options.addArguments("disable-infobars")
    options.addArguments("--remote-allow-origins=*")
    options.addArguments("--disable-blink-features=AutomationControlled")
    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
    options.setExperimentalOption("useAutomationExtension", false);
    ChromeDriverManager.instance.version("2.41").arch64().setup()
    ((new ChromeDriver(options)) as RemoteWebDriver)
}

baseNavigatorWaiting = true

// run as “mvn clean test -Dgeb.env=chrome”
environments {
    chrome {
        // the chrome browser is used as default, hence we don't need to do anything here
        driver = {
        }
    }
}