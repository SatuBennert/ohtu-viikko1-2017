package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
// onnistunut kirjautuminen: käyttäjä ok ja salasana ok
//        kayttajaOkSalasanaOk();
// epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
//        kayttajaOkSalasanaEi();
// epäonnistunut kirjautuminen: ei-olemassaoleva käyttäjätunnus
//        kayttajaEiOle();
// uuden käyttäjätunnuksen luominen onnistuneesti
//        uusiKayttajaOk();
// uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
        uusiKayttajaOkJaUlos();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }

    private static void kayttajaOkSalasanaEi() {
        System.setProperty("webdriver.chrome.driver", "/home/bensatu/Downloads/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:8080");
      
        //--
               //--

        // Login testi, kun käyttäjätunnus on oikein ja salasana on väärin
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("ak");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);
        driver.quit();
    }

    public static void kayttajaOkSalasanaOk() {
        // ao. rivi lisätty, jotta toimii chrome -selaimella
        System.setProperty("webdriver.chrome.driver", "/home/bensatu/Downloads/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);
        // Login testi, kun käyttäjätunnus on oikein ja salasana on oikein
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        driver.quit();
    }

    private static void kayttajaEiOle() {
        System.setProperty("webdriver.chrome.driver", "/home/bensatu/Downloads/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        // Login testi, kun käyttäjätunnus on oikein ja salasana on väärin
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("satu");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);
        driver.quit();
    }
    public static void uusiKayttajaOk(){
         System.setProperty("webdriver.chrome.driver", "/home/bensatu/Downloads/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("satu");
        element = driver.findElement(By.name("password"));
        element.sendKeys("Wild66Fe");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("Wild66Fe");
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        sleep(3);
        driver.quit();
    }
     public static void uusiKayttajaOkJaUlos(){
        System.setProperty("webdriver.chrome.driver", "/home/bensatu/Downloads/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("Tatu");
        element = driver.findElement(By.name("password"));
        element.sendKeys("Wild26Fe");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("Wild26Fe");
        element = driver.findElement(By.name("signup"));

        
        sleep(2);
        element.submit();

        
        sleep(2);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        
        sleep(3);
        driver.quit();
    }
}
