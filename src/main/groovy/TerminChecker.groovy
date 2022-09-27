import geb.Browser
import geb.driver.CachingDriverFactory
import org.openqa.selenium.By

import java.awt.Toolkit

class TerminChecker {
    void checkTermin() {
        try {
            Browser.drive {
                browser.go("https://otv.verwalt-berlin.de/")
                driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/form/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div[2]/a")).click()
                Thread.sleep(5000);
                driver.findElement(By.id("xi-cb-1")).click()
                driver.findElement(By.id("applicationForm:managedForm:proceed")).click()
                Thread.sleep(6000);
                // stastsangehörigkeit dropdown
                driver.findElement(By.id("xi-sel-400")).click()
                Thread.sleep(2000);
                // Syrien
                driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/div[2]/form/div[2]/div/div[2]/div[8]/div[2]/div[2]/div[1]/fieldset/div[2]/select/option[160]")).click()
                //Anzahl der Personen drop down
                driver.findElement(By.id("xi-sel-422")).click()
                // eine Person
                driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/div[2]/form/div[2]/div/div[2]/div[8]/div[2]/div[2]/div[1]/fieldset/div[3]/div[1]/div[1]/select/option[2]")).click()
                Thread.sleep(4000)
                //Leben sie in Berlin mit einem Familienangehörigen
                driver.findElement(By.id("xi-sel-427")).click()
                //nein
                driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/div[2]/form/div[2]/div/div[2]/div[8]/div[2]/div[2]/div[1]/fieldset/div[4]/select/option[3]")).click()
                Thread.sleep(2000)
                //Aufentahltstitel beantragen
                driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/div[2]/form/div[2]/div/div[2]/div[8]/div[2]/div[2]/div[1]/fieldset/div[9]/div[1]/div[1]/div[1]/div[1]/label")).click()
                Thread.sleep(1000)
                //Erwerbstätigkeit
                driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/div[2]/form/div[2]/div/div[2]/div[8]/div[2]/div[2]/div[1]/fieldset/div[9]/div[1]/div[1]/div[1]/div[5]/div/div[3]")).click()
                Thread.sleep(1000)
                //Aufenthaltserlaubnis für Fachkräfte zur Arbeitsplatzsuche - Erteilung (§ 20)
                driver.findElement(By.id("SERVICEWAHL_DE473-0-1-1-324661")).click()
                Thread.sleep(4000)
                driver.findElement(By.id("applicationForm:managedForm:proceed")).click();
                Thread.sleep(5000);

                boolean available = false;
                while (!available) {
                    if (driver.findElement(By.id("messagesBox")).getText().
                            contains("Für die gewählte Dienstleistung sind aktuell keine Termine frei! Bitte")) {
                        Thread.sleep(6000);
                        driver.findElement(By.id("applicationForm:managedForm:proceed")).click();
                    } else {
                        available = true;
                        for (int i = 0; i < 5; i++) {
                            final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().
                                    getDesktopProperty("win.sound.exclamation");
                            if (runnable != null) runnable.run();
                            Thread.sleep(500);
                        }
                    }
                }
            }
        } catch (Exception exception) {
            Browser.drive {
                CachingDriverFactory.clearCacheAndQuitDriver()
            }
            println("Exception was Thrown, starting over")
            checkTermin()
        }
    }
}
