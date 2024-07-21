import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class GoogleCarFinder {
    private static final Logger logger = LoggerFactory.getLogger(GoogleCarFinder.class);

    public static void main(String[] args) {
        // Specifying the file location of chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:/Users/ekino/Desktop/CODE/chromedriver-win64/chromedriver-win64/chromedriver.exe");

        // Using Scanner to receive data from the user
        Scanner scanner = new Scanner(System.in);

        // Getting search criteria from the user
        System.out.print("Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Fuel Type: ");
        String fuelType = scanner.nextLine();

        System.out.print("Transmission Type: ");
        String transmission = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        scanner.close();  // Closing Scanner

        WebDriver driver = new ChromeDriver();

        try {
            // Going to Google
            driver.get("https://www.google.com");

            // Finding Google Searchbox
            WebElement searchBox = driver.findElement(By.name("q"));

            // Creating the search query
            String query = brand + " " + model + " " + fuelType + " " + transmission + " gear " + " " + color + " colored " + " for sale ";

            // Typing the query in the search box and pressing enter
            searchBox.sendKeys(query);
            searchBox.submit();

            // Waiting for search results to load
            Thread.sleep(3000); // Waiting for 3 seconds

            // Finding filtered search results
            List<WebElement> searchResults = driver.findElements(By.cssSelector(".g"));

            for (WebElement result : searchResults) {
                try {
                    String title = result.findElement(By.tagName("h3")).getText();
                    String link = result.findElement(By.tagName("a")).getAttribute("href");

                    System.out.println("Title: " + title);
                    System.out.println("Link: " + link);
                    System.out.println("----------");
                } catch (Exception e) {
                    logger.error("Error processing search result", e);
                }
            }
        } catch (Exception e) {
            logger.error("An error occurred while performing the search", e);
        } finally {
            driver.quit();
        }
    }
}
