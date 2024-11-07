import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import object.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class TestWebShoppingSite {
    AppiumDriver driver;
    List<Product> listProducts;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")
                .setNewCommandTimeout(Duration.ofSeconds(30))
                .withBrowserName("Chrome");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        listProducts = new ArrayList<>();
    }

    @Test
    public void test() {
        // Add your test code here
        driver.get("https://magento.softwaretestingboard.com/");
//        driver.findElement(AppiumBy.xpath("//*[@class='action nav-toggle']")).click();
//        driver.findElement(AppiumBy.xpath("//*[@class='level0 nav-3 category-item level-top parent ui-menu-item']")).click();
//        driver.findElement(AppiumBy.xpath("//*[@class='level2 nav-3-1-1 category-item first ui-menu-item']")).click();
//        driver.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(AppiumBy.xpath("//*[@class='page-title']/span")));
//        driver.findElement(AppiumBy.xpath("//div[@class='products wrapper grid products-grid']//*[@class='item product product-item'][1]")).click();
//
//        String name_product = driver.findElement(AppiumBy.xpath("//*[@class='page-title']/span")).getText();
//        String size_product = "M";
//        String color_product = "Black";
//        String number_product = "1";
//
//        //Add item to cart
//        WebElement imageItem =   driver.findElement(AppiumBy.xpath("//*[@class='fotorama__stage__frame fotorama__active fotorama_vertical_ratio fotorama__loaded fotorama__loaded--img']"));
//        driver.executeScript("arguments[0].scrollIntoView(true);", imageItem);
//
//        driver.findElement(AppiumBy.xpath("//*[@class='swatch-option text'][contains(., '"+size_product+"')]")).click();
//        driver.findElement(AppiumBy.xpath("//*[@option-label='"+color_product+"']")).click();
//        driver.findElement(AppiumBy.cssSelector("input[type='number']")).clear();
//        driver.findElement(AppiumBy.cssSelector("input[type='number']")).sendKeys(number_product);
//        //add item into list product array
//        listProducts.add(new Product(name_product, size_product, color_product, number_product));
//        //scroll to view and click addToCartButton
//        WebElement addToCartButton =   driver.findElement(AppiumBy.xpath("//*[@class='action primary tocart']"));
//        driver.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(AppiumBy.xpath("//*[@class='page-title']/span")));
//        addToCartButton.click();
        addItemToCard("Men","Jackets","M","Black","1");
        //scroll to view and click myCartButton
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement messageGlobal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='message global demo']")));
        driver.executeScript("arguments[0].scrollIntoView(true);", messageGlobal);

        WebElement myCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'action showcart')]")));
        myCartButton.click();

        WebElement viewcartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='action viewcart']")));
        viewcartButton.click();

        verifyItemInCard();
        checkout();

    }

    public void addItemToCard(String typeCategory,String typeItem, String sizeProduct, String colorProduct, String numberProduct) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement messageGlobal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='message global demo']")));
        driver.executeScript("arguments[0].scrollIntoView(true);", messageGlobal);

        driver.findElement(AppiumBy.xpath("//*[@class='action nav-toggle']")).click();
        driver.findElement(AppiumBy.xpath("//*[@class='navigation']//span[contains(.,'"+typeCategory+"')]")).click();
        driver.findElement(AppiumBy.xpath("//*[@class='navigation']//li[contains(.,'" + typeCategory + "')]//span[contains(.,'" + typeItem + "')]")).click();

        driver.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(AppiumBy.xpath("//*[@class='page-title']/span")));
        driver.findElement(AppiumBy.xpath("//div[@class='products wrapper grid products-grid']//*[@class='item product product-item'][1]")).click();
        //Add item to cart
        WebElement imageItem =   driver.findElement(AppiumBy.xpath("//*[@class='fotorama__stage__frame fotorama__active fotorama_vertical_ratio fotorama__loaded fotorama__loaded--img']"));
        driver.executeScript("arguments[0].scrollIntoView(true);", imageItem);

        if (!typeCategory.equals("Gear")) {
            driver.findElement(AppiumBy.xpath("//*[text()='" + sizeProduct + "']")).click();
            driver.findElement(AppiumBy.xpath("//*[@option-label='" + colorProduct + "']")).click();
            driver.findElement(AppiumBy.cssSelector("input[type='number']")).clear();
            driver.findElement(AppiumBy.cssSelector("input[type='number']")).sendKeys(numberProduct);
        }
        else {
            driver.findElement(AppiumBy.cssSelector("input[type='number']")).clear();
            driver.findElement(AppiumBy.cssSelector("input[type='number']")).sendKeys(numberProduct);
        }
        //add item into list product array
        String nameProduct = driver.findElement(AppiumBy.xpath("//*[@class='page-title']/span")).getText();
        listProducts.add(new Product(nameProduct, sizeProduct, colorProduct, numberProduct));
        //scroll to view and click addToCartButton
        WebElement addToCartButton =   driver.findElement(AppiumBy.xpath("//*[@class='action primary tocart']"));
        driver.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(AppiumBy.xpath("//*[@class='page-title']/span")));
        addToCartButton.click();

    }
    public void verifyItemInCard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement shoppingCartTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='base']")));
        driver.executeScript("arguments[0].scrollIntoView(true);", shoppingCartTitle);

        List<WebElement> productItems = driver.findElements(By.xpath("//*[@class='cart table-wrapper']"));

        for (Product product : listProducts)
        {
            boolean isFound = false;
           for (WebElement productItem : productItems) {
               String name_product = productItem.findElement(By.xpath("//div[@class='product-item-details']//strong[@class='product-item-name']/a")).getText();
               String size_product = productItem.findElement(By.xpath("//div[@class='product-item-details']//dl[@class='item-options']/dt[1]")).getText();
               String color_product = productItem.findElement(By.xpath("//div[@class='product-item-details']//dl[@class='item-options']/dt[2]")).getText();
               String quantity_product = productItem.findElement(By.xpath("//div[@class='control qty']//input")).getText();
               if (name_product.equals(product.getNameProduct())){
                   Assert.assertEquals(size_product, product.getSize() );
                   Assert.assertEquals(color_product, product.getColor() );
                   Assert.assertEquals(quantity_product, product.getQuantity());

                   System.out.println("Item " + product.getNameProduct() + " is in the cart.");
                   isFound = true;
               }
           }
            if (!isFound) {
                System.out.println("Product: "+product.getNameProduct()+" not found in cart");
            }
        }
    }

    public void checkout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']")).click();
        WebElement estimatedTotal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Estimated Total']")));
        driver.executeScript("arguments[0].scrollIntoView(true);", estimatedTotal);

        String firstName = "Thanh Tam";
        String lastName = "Le";
        String company = "KMS";
        String phone = "123456";
        String email = "thanhtam@gmail.com";

        // Input email
        WebElement emailInput = driver.findElement(By.xpath("//div[@class='control _with-tooltip']/input[@class='input-text' and @type='email']"));
        emailInput.clear();
        emailInput.sendKeys(email);

        // Input first name
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@name='firstname']"));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        // Input last name
        WebElement lastNameInput = driver.findElement(By.xpath("//input[@name='lastname']"));
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        // Input company
        WebElement companyInput = driver.findElement(By.xpath("//input[@name='company']"));
        companyInput.clear();
        companyInput.sendKeys(company);

        // Input street address
        WebElement streetAddressInput = driver.findElement(By.xpath("//input[@name='street[0]']"));
        streetAddressInput.clear();
        streetAddressInput.sendKeys("97 QL13");

        // Input city
        WebElement cityInput = driver.findElement(By.xpath("//input[@name='city']"));
        cityInput.clear();
        cityInput.sendKeys("Ho Chi Minh");

        // Input zip code
        WebElement zipCodeInput = driver.findElement(By.xpath("//input[@name='postcode']"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("10000");

        // Select state/province
        WebElement stateSelect = driver.findElement(By.xpath("//select[@name='region_id']"));
        stateSelect.sendKeys("California");

        // Input phone number
        WebElement phoneInput = driver.findElement(By.xpath("//input[@name='telephone']"));
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        // scroll to view shippingMethodOptions
        WebElement shippingMethodOptions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout-shipping-method-load")));
        driver.executeScript("arguments[0].scrollIntoView(true);", shippingMethodOptions);

        // Find and click the radio button
        WebElement shippingMethodRadioButton = driver.findElement(By.xpath("//input[@type='radio' and @value='flatrate_flatrate']"));
        shippingMethodRadioButton.click();

        // Click "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[span[text()='Next']]"));
        driver.executeScript("arguments[0].scrollIntoView(true);", nextButton);
        nextButton.click();

        // Wait for the "Place Order" button to be clickable
        WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Place Order']")));
        driver.executeScript("arguments[0].scrollIntoView(true);", estimatedTotal);
        placeOrderButton.click();

        // Wait for the success message to appear
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Thank you for your purchase!']")));
        driver.executeScript("arguments[0].scrollIntoView(true);", successMessage);

        WebElement emailSpan = driver.findElement(By.xpath("//div[@id='registration']//span[2]"));
        Assert.assertEquals(emailSpan.getText(), email);

    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
