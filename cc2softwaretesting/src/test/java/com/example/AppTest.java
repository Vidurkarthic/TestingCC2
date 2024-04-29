package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest 
{
    WebDriver driver;
    ExtentReports reports;
    ExtentTest test;
    Logger logger=Logger.getLogger(AppTest.class);
    @BeforeTest
    public void setup() {
        reports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(
                "C:\\Users\\VIDUR KARTHIC\\Desktop\\CC2 SOFTWARE TESTING\\cc2softwaretesting\\report.html");
        reports.attachReporter(spark);
        test = reports.createTest("Demo Result");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        PropertyConfigurator.configure("C:\\Users\\VIDUR KARTHIC\\Desktop\\CC2 SOFTWARE TESTING\\cc2softwaretesting\\src\\test\\java\\com\\resources\\log4j.properties");
    }
    @BeforeMethod
    public void navigateUrl() {
        driver.navigate().to("https://www.barnesandnoble.com/");
    }
    @Test(priority = 0) 
    public void testCase1() throws IOException, InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[1]/a")).click();
        driver.findElement(By.linkText("Books")).click();
        FileInputStream fs = new FileInputStream("C:\\Users\\VIDUR KARTHIC\\Desktop\\CC2 SOFTWARE TESTING\\cc2softwaretesting\\input for websites.xlsx");
        XSSFWorkbook work = new XSSFWorkbook(fs);
        XSSFSheet sheet = work.getSheet("Bank login");
        XSSFRow row = sheet.getRow(6);
        String input = row.getCell(0).getStringCellValue();
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys(input);
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/span/button")).click();
        Thread.sleep(5000);
        String name = driver.findElement(By.xpath("//*[@id=\"searchGrid\"]/div/section[1]/section[1]/div/div[1]/div[1]/h1")).getText();
        if(name.contains("Chetan Bhagat")) {
            logger.info("The text contains Chetan Bhagat");
        }
        else {
            logger.error("The text doesnot contains Chetan Bhagat");
        }
    }
    @Test(priority = 1)
    public void testing2() throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.barnesandnoble.com/");
        driver.manage().window().maximize();
        WebElement clickable = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[4]/div/ul/li[5]/a"));
        Thread.sleep(2000);

        new Actions(driver).clickAndHold(clickable).perform();
        driver.findElement(By.linkText("Audiobooks Top 100")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div/div[2]/div/div[2]/section[2]/ol/li[1]/div/div[2]/div[1]/h3/a")).click();
        Thread.sleep(2000);
        // driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/section/div[2]/div/div[3]/div[2]/ul/li[2]/div/div/label/span")).click();
        // Thread.sleep(2000);
        JavascriptExecutor js10 = (JavascriptExecutor) driver;
        js10.executeScript("window.scrollBy(0,400)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/section/div[2]/div/div[3]/div[2]/ul/li[2]/div/div/label/span")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/section/div[2]/div/div[3]/div[2]/div[3]/div[1]/div[1]/form/input[5]")).click();
        Thread.sleep(2000);

        driver.quit();
    }
    @Test(priority = 2)
    public void testCase3() throws InterruptedException, IOException {
        driver.manage().window().maximize();

        driver.get("https://www.barnesandnoble.com/");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript("window.scrollBy(0,2000)");
        // Thread.sleep(2000);
        driver.navigate().to("https://www.barnesandnoble.com/membership/");
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,2000)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/main/section/div[1]/div[2]/div/div/div[2]/div/div[73]/div/div[1]/a")).click();
        Thread.sleep(2000);
        driver.quit();

    }
}
