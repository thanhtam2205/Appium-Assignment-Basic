import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestContactAppAndroid {
    AppiumDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")
                .setNewCommandTimeout(Duration.ofSeconds(30))
                .setAppPackage("com.google.android.contacts")
                .setAppActivity("com.android.contacts.activities.PeopleActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test() {
        // Add your test code here
        driver.findElement(AppiumBy.accessibilityId("Create contact")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='First name']")).sendKeys("Thanh Tam");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Last name']")).sendKeys("Le");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Phone']")).sendKeys("123456");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='com.google.android.contacts:id/toolbar_button']")).click();

        driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Close Popup Window']")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Thanh Tam Le']")).click();
        String name = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.google.android.contacts:id/large_title']")).getText();
        Assert.assertEquals(name, "Thanh Tam Le"  );
        String phone = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.google.android.contacts:id/header']")).getText();
        Assert.assertEquals(phone.replaceAll("[ ()-]", ""), "123456"  );
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
