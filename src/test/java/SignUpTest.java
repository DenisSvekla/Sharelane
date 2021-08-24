import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {

    @Test
    public void zipCodeShouldAccept5Digits() {
        //убедиться что мы на странице SignUp
        //закрыть браузер
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        // открыть страницу https://sharelane.com/cgi-bin/register.py
        driver.get("https://sharelane.com/cgi-bin/register.py");
        // ввести любые 5 цифр, например (12345)
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        // нажать кнопку CONTINUE
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        //убедиться что мы на странице SignUp
        boolean isOpened = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isOpened, "не открылась");
        driver.quit();
    }

    @Test
    public void zipCodeShouldNotAccept6Digits() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        // открыть страницу https://sharelane.com/cgi-bin/register.py
        driver.get("https://sharelane.com/cgi-bin/register.py");
        // ввести любые 5 цифр, например (12345)
        driver.findElement(By.name("zip_code")).sendKeys("12776512341d");
        // нажать кнопку CONTINUE
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        Assert.assertEquals(error,
                "Oops, error on page. ZIP code should have 5 digits",
                "сообщение об ошибке некорректно");
        driver.quit();
    }
    @Test
    public void zipCodeShouldNotAccept4Digits() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        // открыть страницу https://sharelane.com/cgi-bin/register.py
        driver.get("https://sharelane.com/cgi-bin/register.py");
        // ввести любые 5 цифр, например (12345)
        driver.findElement(By.name("zip_code")).sendKeys("1277");
        // нажать кнопку CONTINUE
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        Assert.assertEquals(error,
                "Oops, error on page. ZIP code should have 5 digits",
                "сообщение об ошибке некорректно");
        driver.quit();
    }
    @Test
    public void succesfullSignUp() {
        //установка переменной среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        // открыть страницу https://sharelane.com/cgi-bin/register.py
        driver.get("https://sharelane.com/cgi-bin/register.py");
        // ввести любые 5 цифр, например (12345)
        driver.findElement(By.name("zip_code")).sendKeys("12777");
        // нажать кнопку CONTINUE
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Denis");
        driver.findElement(By.name("last_name")).sendKeys("Svekla");
        driver.findElement(By.name("email")).sendKeys("denk@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("katka");
        driver.findElement(By.name("password2")).sendKeys("katka");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String message = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        Assert.assertEquals(message, "Account is created!");
        driver.quit();
    }
}
