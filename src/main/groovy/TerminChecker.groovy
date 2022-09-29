import geb.Browser
import geb.driver.CachingDriverFactory
import javazoom.jl.player.Player
import org.openqa.selenium.By

class TerminChecker {
    void checkTermin() {
        try {
            Browser.drive {
                browser.go("https://otv.verwalt-berlin.de/")
                driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/form/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div[2]/a")).click()
                Thread.sleep(8000)
                driver.findElement(By.id("xi-cb-1")).click()
                driver.findElement(By.id("applicationForm:managedForm:proceed")).click()
                Thread.sleep(10000)
                // stastsangehörigkeit dropdown
                driver.findElement(By.id("xi-sel-400")).click()
                Thread.sleep(6000)
                // Syrien
                driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/div[2]/form/div[2]/div/div[2]/div[8]/div[2]/div[2]/div[1]/fieldset/div[1]/select/option[160]")).click()
                //Anzahl der Personen drop down
                driver.findElement(By.id("xi-sel-422")).click()
                // eine Person
                driver.findElement(By.id("xi-sel-422_1")).click()
                Thread.sleep(8000)
                //Leben sie in Berlin mit einem Familienangehörigen
                driver.findElement(By.id("xi-sel-427")).click()
                //nein
                driver.findElement(By.id("xi-sel-427_2")).click()
                Thread.sleep(8000)
                //Aufentahltstitel beantragen
                driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/div[2]/form/div[2]/div/div[2]/div[8]/div[2]/div[2]/div[1]/fieldset/div[8]/div[1]/div[1]/div[1]/div[1]/label")).click()
                Thread.sleep(3000)
                //Erwerbstätigkeit
                driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/div[2]/form/div[2]/div/div[2]/div[8]/div[2]/div[2]/div[1]/fieldset/div[8]/div[1]/div[1]/div[1]/div[5]/div/div[3]/label")).click()
                Thread.sleep(3000)
                //Aufenthaltserlaubnis für Fachkräfte zur Arbeitsplatzsuche - Erteilung (§ 20)
                driver.findElement(By.id("SERVICEWAHL_DE473-0-1-1-324661")).click()
                Thread.sleep(7000)
                driver.findElement(By.id("applicationForm:managedForm:proceed")).click()
                Thread.sleep(8000)

                boolean available = false
                while (!available) {
                    if (driver.findElement(By.id("messagesBox")).getText().
                            contains("Für die gewählte Dienstleistung sind aktuell keine Termine frei! Bitte")) {
                        Thread.sleep(8000)
                        driver.findElement(By.id("applicationForm:managedForm:proceed")).click()
                    } else {
                        available = true
                        for (int i = 0; i < 5; i++) {
                            FileInputStream fis = new FileInputStream(getClass().getResource("windows_11.mp3").getFile())
                            Player playMP3 = new Player(fis)
                            playMP3.play()
                            Thread.sleep(500)
                        }
                    }
                }
            }
        } catch (Exception exception) {
            Browser.drive {
                CachingDriverFactory.clearCacheAndQuitDriver()
            }
            println("Exception was Thrown, starting over")
            //todo: remove Recursion
            checkTermin()
        }
    }
}
