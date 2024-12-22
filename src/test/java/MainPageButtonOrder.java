import model.MainPage;
import model.PageForWhomScooter;
import model.SelectBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MainPageButtonOrder {
    private static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/"; //главная станица
    private static final String BROWSER_NAME = "Chrome"; //задаем браузер Chrome или Firefox

    private static WebDriver driver;

    //создаем браузер
    @Before
    public void before() {
        driver = SelectBrowser.selectDriverBrowser(BROWSER_NAME);
    }

    //поля класса
    private final String selectButton;

    //конструктор класса
    public MainPageButtonOrder(String selectButton){
        this.selectButton = selectButton;
    }

    @Parameterized.Parameters

    public static Object[][] dataTestButtonOrder() {
        return new Object[][]{
                {"Проверить верхнюю кнопку"},
                {"Проверить нижнюю кнопку"},
        };
    }

    @Test
    public void testButtonOrder() {
        driver.get(MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        PageForWhomScooter pageForWhomScooter = new PageForWhomScooter(driver);
        mainPage.closeCookie();
        if (selectButton == "Проверить верхнюю кнопку") {
            mainPage.clickButtonOrderInTheHeader();
        } else if (selectButton == "Проверить нижнюю кнопку") {
            mainPage.clickButtonOrderAtTheBottom();
        } else {
            throw new RuntimeException("Введите: Проверить верхнюю(или нижнюю) кнопку");
        };
        assertTrue(pageForWhomScooter.pageIsDisplayed()); //проверили, что перешли в окно оформления заказа
        driver.quit();
    }

    @After
    public void after(){
        driver.quit();
    }
}