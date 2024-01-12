import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> urlList = new ArrayList<>();
        urlList.add("https://www.coupang.com/np/categories/502994"); //티셔츠
        urlList.add("https://www.coupang.com/np/categories/502995");
        urlList.add("https://www.coupang.com/np/categories/502998");
        urlList.add("https://www.coupang.com/np/categories/502999");
        urlList.add("https://www.coupang.com/np/categories/503000");
        urlList.add("https://www.coupang.com/np/categories/503001");
        urlList.add("https://www.coupang.com/np/categories/503002");
        urlList.add("https://www.coupang.com/np/categories/178456");
        urlList.add("https://www.coupang.com/np/categories/403245");
        urlList.add("https://www.coupang.com/np/categories/486733");
        urlList.add("https://www.coupang.com/np/categories/178627");
        urlList.add("https://www.coupang.com/np/categories/497135");
        urlList.add("https://www.coupang.com/np/categories/497136");
        urlList.add("https://www.coupang.com/np/categories/497245"); // 태블릿
        urlList.add("https://www.coupang.com/np/categories/409304");
        urlList.add("https://www.coupang.com/np/categories/401027");
        urlList.add("https://www.coupang.com/np/categories/401394");
        urlList.add("https://www.coupang.com/np/categories/401207");
        urlList.add("https://www.coupang.com/np/categories/486447");
        urlList.add("https://www.coupang.com/np/categories/497290");
        urlList.add("https://www.coupang.com/np/categories/401098"); // 익스테리어
        urlList.add("https://www.coupang.com/np/categories/445725");
        urlList.add("https://www.coupang.com/np/categories/118900");
        urlList.add("https://www.coupang.com/np/categories/118876");
        urlList.add("https://www.coupang.com/np/categories/445859");
        urlList.add("https://www.coupang.com/np/categories/119402");
        urlList.add("https://www.coupang.com/np/categories/118878"); // 고양이 용품

        int no = 1;
        int category2 = 5;

        for(String url : urlList) {
//        System.out.println("Hello world!");
            RemoteWebDriver driver = new ChromeDriver();
//        RemoteWebDriver driver2 = new ChromeDriver();

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver2.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        WebDriverWait wait = new WebDriverWait(driver, 10);

            // 크롤링 주소

            StringBuilder product = new StringBuilder();
            product.append("no, title, price, delivery_price, main_img, category \n");
            StringBuilder productImg = new StringBuilder();
            productImg.append("product_no, img \n");
            StringBuilder productOption = new StringBuilder();
            productOption.append("product_no, name \n");

            driver.get(url);
            // 필요한 영역
            WebElement webElement = driver.findElement(By.cssSelector("#productList"));
            List<WebElement> itemList = webElement.findElements(By.cssSelector(".renew-badge"));

            // List 정보
            for (WebElement li : itemList) {
                RemoteWebDriver driver2 = new ChromeDriver();
                driver2.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                product.append(no)
                        .append(",")
                        .append("\"").append(li.findElement(By.cssSelector(".name")).getText()).append("\"")
                        .append(",")
                        .append(li.findElement(By.cssSelector(".price-value")).getText().replace(",", ""))
                        .append(",")
                        .append("3000")
                        .append(",")
                        .append(li.findElement(By.cssSelector("img")).getAttribute("src"))
                        .append(",")
                        .append(category2)
                        .append("\n");

                String aHref = li.findElement(By.cssSelector(".baby-product-link")).getAttribute("href");
                System.out.println(aHref);

//            Thread.sleep(1000);

                // 상세 페이지 내용
                driver2.get(aHref);


                // 옵션
                String a = (String) driver2.executeScript("return (function (){\n" +
                        "    const items = document.querySelectorAll('.Dropdown-Select__Dropdown__Item');\n" +
                        "    let result = \"\";\n" +
                        "    for (let i = 0; i < items.length; i++) {\n" +
                        "        result += items[i].textContent.trim();\n" +
                        "        if(i !== items.length - 1){\n" +
                        "            result += ',';\n" +
                        "        }\n" +
                        "    }\n" +
                        "    console.log(result);\n" +
                        "    return result;\n" +
                        "}());");

                String[] aa = a.split(",");
                if (!Objects.equals(aa[0], "")) {
                    System.out.println(aa.length);
                    for (String s : aa) {
                        System.out.println(s);
                        productOption.append(no)
                                .append(",")
                                .append(s)
                                .append("\n");
                    }
                }




                // 이미지
                List<WebElement> webElement2 = driver2.findElements(By.cssSelector(".vendor-item img"));
                System.out.println(webElement2.size());
//        List<WebElement> webElement1 = driver.findElements(By.cssSelector(".subType-TEXT"));
                for (WebElement web : webElement2) {
                    if(!Objects.isNull(web.getAttribute("src"))) {
                        productImg.append(no)
                                .append(",")
                                .append(web.getAttribute("src"))
                                .append("\n");

                    }
                }

                driver2.quit();



//            driver2.quit();


//            new Actions(driver)
//                    .click(li.findElement(By.cssSelector(".name")))
//                    .perform();
//
//            Thread.sleep(5000);
//
//            PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "default mouse");
//            Sequence actions = new Sequence(mouse, 0)
//                    .addAction(mouse.createPointerDown(PointerInput.MouseButton.BACK.asArg()))
//                    .addAction(mouse.createPointerUp(PointerInput.MouseButton.BACK.asArg()));
//            driver.perform(Collections.singletonList(actions));


//            System.out.println(li.findElement(By.cssSelector(".name")).getText());
//            System.out.println(li.findElement(By.cssSelector(".price-value")).getText());
//            System.out.println(li.findElement(By.cssSelector("img")).getAttribute("src"));
                no++;
//            driver2.quit();
            }

            System.out.println(product);
            String productPath = "src/csv/"+ category2 + ".csv";
            String productImgPath = "src/csv/"+ category2 + "img.csv";
            String productOptionPath = "src/csv/"+ category2 + "option.csv";

            Files.writeString(Path.of(productPath), "\uFEFF" + product, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            Files.writeString(Path.of(productImgPath), "\uFEFF" + productImg, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            Files.writeString(Path.of(productOptionPath), "\uFEFF" + productOption, StandardCharsets.UTF_8, StandardOpenOption.CREATE);

            category2 += 1;
            driver.quit();

        }

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