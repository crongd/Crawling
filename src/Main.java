import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
//        System.out.println("Hello world!");
        WebDriver driver = new ChromeDriver();

        Thread.sleep(3000);

        // 크롤링 주소
        driver.get("https://www.coupang.com/np/categories/184060?page=2");
        // 필요한 영역
        WebElement webElement = driver.findElement(By.cssSelector("#productList"));
        List<WebElement> itemList = webElement.findElements(By.cssSelector(".renew-badge"));

        StringBuilder csv = new StringBuilder();
        csv.append("title, price, delivery_price, img, category \n");

        for(WebElement li : itemList) {
            csv.append("\"").append(li.findElement(By.cssSelector(".name")).getText()).append("\"")
                            .append(",")
                            .append(li.findElement(By.cssSelector(".price-value")).getText())
                            .append(",")
                            .append("2000")
                            .append(",")
                            .append(li.findElement(By.cssSelector("img")).getAttribute("src"))
                            .append(",")
                            .append("1")
                            .append("\n");



            System.out.println(li.findElement(By.cssSelector(".name")).getText());
            System.out.println(li.findElement(By.cssSelector(".price-value")).getText());
            System.out.println(li.findElement(By.cssSelector("img")).getAttribute("src"));

        }

        String filePath = "src/자동차용품2.csv";
        Files.writeString(Path.of(filePath), "\uFEFF" + csv, StandardCharsets.UTF_8, StandardOpenOption.CREATE);

//        Files.writeString(Path.of(filePath), "\uFEFF" + csv.toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE);

//        System.out.println(webElement);





//        System.out.println(list.size());
        //이미지 주소
//        System.out.println(list.findElement(By.cssSelector("img")).getAttribute("src"));


        // title





//        System.out.println(webElement);


//        driver.get("https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=100");
//        WebElement webElement = driver.findElement(By.cssSelector("ul.sh_list"));
//        List<WebElement> elements = webElement.findElements(By.cssSelector("div.sh_text > a"));
//        for(WebElement aTag : elements) {
//            System.out.println("제목: " + aTag.getText());
//            System.out.println("링크: " + aTag.getAttribute("href"));
//        }
////        driver.quit();


//        driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https://www.naver.com/");
//        WebElement idInput = driver.findElement(By.id("id")); // id input
//        WebElement pwInput = driver.findElement(By.id("pw")); // pw input
//        idInput.sendKeys("아이디 입니다.");
//        pwInput.sendKeys("비밀번호 입니다.");
//        idInput.sendKeys(Keys.ENTER);
    }
}