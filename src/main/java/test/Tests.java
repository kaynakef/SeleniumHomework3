package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Assert.*;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


public class Tests {
    @Test
    public void TestCase1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("http://uitestpractice.com/Students/Index");
        WebElement createNew=driver.findElement(By.linkText("Create New"));
        createNew.click();
        Thread.sleep(2000);
        driver.navigate().refresh();
        createNew=driver.findElement(By.linkText("Create New"));
        createNew.click();

        WebElement firstName=driver.findElement(By.id("FirstName"));
        firstName.sendKeys("Trader");

        WebElement lastName=driver.findElement(By.id("LastName"));
        lastName.sendKeys("Joes");

        WebElement enrollmentDate=driver.findElement(By.id("EnrollmentDate"));
        enrollmentDate.sendKeys("05.25.2022");

        WebElement submit= driver.findElement(By.xpath("//input[@type='submit']"));
        submit.click();

        driver.navigate().to("http://uitestpractice.com/Students/Index");
        WebElement search=driver.findElement(By.id("Search_Data"));
        search.sendKeys("Trader");
        WebElement find=driver.findElement(By.xpath("//input[@value='Find']"));
        find.click();

        WebElement firstNameTable=driver.findElement(By.xpath("//*[text() [contains(. , 'Trader')]]"));
        Assert.assertEquals(BrowserUtils.getText(firstNameTable), "Trader");

    }
    @Test
    public void TestCase2(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("http://uitestpractice.com/Students/Index");
        WebElement search=driver.findElement(By.id("Search_Data"));
        search.sendKeys("Joes");
        WebElement find=driver.findElement(By.xpath("//input[@value='Find']"));
        find.click();
        WebElement edit=driver.findElement(By.xpath("//*[.='EDIT']"));
        edit.click();
        WebElement firstName=driver.findElement(By.id("FirstName"));
        firstName.clear();
        firstName.sendKeys("Bob");
        WebElement submit= driver.findElement(By.xpath("//input[@type='submit']"));
        submit.click();
        driver.navigate().to("http://uitestpractice.com/Students/Index");
        search=driver.findElement(By.id("Search_Data"));
        search.sendKeys("Bob");
        find=driver.findElement(By.xpath("//input[@value='Find']"));
        find.click();
        WebElement firstNameTable=driver.findElement(By.xpath("//*[text() [contains(. , 'Bob')]]"));
        Assert.assertEquals(BrowserUtils.getText(firstNameTable), "Bob");
    }

    @Test
    public void TestCase3(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("http://uitestpractice.com/Students/Index");
        WebElement search=driver.findElement(By.id("Search_Data"));
        search.sendKeys("Joes");
        WebElement delete=driver.findElement(By.xpath("//*[.='DELETE']"));
        delete.click();
        WebElement confirmDelete=driver.findElement(By.xpath("//input[@value='Delete']"));
        confirmDelete.click();
        search=driver.findElement(By.id("Search_Data"));
        search.sendKeys("Joes");
        WebElement find=driver.findElement(By.xpath("//input[@value='Find']"));
        find.click();
        WebElement warning=driver.findElement(By.xpath("//*[text() [contains(. , 'There')]]"));
        Assert.assertTrue(BrowserUtils.getText(warning).contains("There are zero students with this search text Page 0 of 0"));

    }

    @Test
    public void TestCase4(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("http://uitestpractice.com/");
        WebElement draggable=driver.findElement(By.id("draggable"));
        Actions actions=new Actions(driver);
        actions.clickAndHold(draggable).perform();
        WebElement droppable=driver.findElement(By.id("droppable"));
        actions.moveToElement(droppable).release().perform();
        Assert.assertTrue(BrowserUtils.getText(droppable).contains("Dropped!"));
    }
    @Test
    public void TestCase5(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("http://uitestpractice.com/");
        WebElement dblClick= driver.findElement(By.name("dblClick"));
        Actions actions =new Actions(driver);
        actions.doubleClick(dblClick).perform();
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Double Clicked !!"));
        Alert alertOK = driver.switchTo().alert();
        alertOK.accept();

    }

}









