import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.*;
import util.UtilDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VersionTest {
    UtilDriver utilDriver;
    DashboardPage dashboardPage;
    MainPage mainPage;
    GlassDocumentationPage glassDocumentationPage;
    ReleasePage releasePage;

    @BeforeEach
    public void setup() {
        utilDriver = new UtilDriver();
        dashboardPage = new DashboardPage(utilDriver.getDriver());
        dashboardPage.login(System.getenv("jirausername"), System.getenv("jirapassword"));
        mainPage = new MainPage(utilDriver.getDriver());
        mainPage.loadpage();
        glassDocumentationPage = new GlassDocumentationPage(utilDriver.getDriver());
        releasePage = new ReleasePage(utilDriver.getDriver());
    }

//    @AfterEach
//    public void teardown() {
//        utilDriver.close();
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/version/add_version_to_glass_documentation.csv", numLinesToSkip = 1)
    public void versionTest_addNewRelease_isShownInGlassProject(String key, String releaseName, String description) {
        utilDriver.navigationToCertainProjectReleasePage(key);
        releasePage.addNewRelease(releaseName, description);
        utilDriver.navigationToCertainProjectGlassProject(key);
        glassDocumentationPage.clickOnVersionsTab();

        assertTrue(glassDocumentationPage.isAdded(releaseName));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/version/add_version_to_glass_documentation.csv", numLinesToSkip = 1)
    public void versionTest_editingRelease_isShownInGlassProject (String key, String releaseName, String description, String editName, String editDescription) {
        utilDriver.navigationToCertainProjectReleasePage(key);
        releasePage.addNewRelease(releaseName, description);
        utilDriver.navigationToCertainProjectGlassProject(key);
        glassDocumentationPage.clickOnVersionsTab();
        utilDriver.navigationToCertainProjectReleasePage(key);
        releasePage.clickOnOperations();
        releasePage.clickOnEdit();
        releasePage.editRelease(editName, editDescription);
        utilDriver.navigationToCertainProjectReleasePage(key);
        glassDocumentationPage.clickOnVersionsTab();

        assertTrue(glassDocumentationPage.isEdited(releaseName, editName));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/version/add_version_to_glass_documentation.csv", numLinesToSkip = 1)
    public void versionTest_changeStatus_isShownInGlassProject (String key, String releaseName, String description) {
        utilDriver.navigationToCertainProjectReleasePage(key);
        releasePage.addNewRelease(releaseName, description);
        utilDriver.navigationToCertainProjectGlassProject(key);
        glassDocumentationPage.clickOnVersionsTab();
        utilDriver.navigationToCertainProjectReleasePage(key);
        releasePage.clickOnOperations();
        releasePage.release();
        utilDriver.navigationToCertainProjectGlassProject(key);
        glassDocumentationPage.clickOnVersionsTab();
    }
}
