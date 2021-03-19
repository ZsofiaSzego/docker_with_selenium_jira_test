import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.BeforeEach;
import pages.DashboardPage;
import org.junit.jupiter.params.ParameterizedTest;
import pages.LogoutPage;
import pages.MainPage;
import util.UtilDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest {
    UtilDriver driver;
    DashboardPage dashboardPage;
    MainPage mainPage;
    LogoutPage logoutPage;


    @BeforeEach
    public void setup() {
        driver = new UtilDriver();
        dashboardPage = new DashboardPage(driver.getDriver());
        mainPage = new MainPage(driver.getDriver());
        logoutPage = new LogoutPage(driver.getDriver());
    }

    @AfterEach
    public void teardown() {
        driver.close();
    }

    @Test
    public void logoutTest_successfulLogOut_isWorking () {
        Dotenv dotenv = Dotenv.configure()
                .directory("/")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
        dashboardPage.login(dotenv.get("JIRAUSERNAME"), dotenv.get("JIRAPASSWORD"));
        mainPage.clickOnLogout();

        assertTrue(logoutPage.verifySuccessLogOut());
    }
}
