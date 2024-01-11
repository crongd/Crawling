import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        RemoteWebDriver driver = new ChromeDriver();
        driver.get("https://www.coupang.com/vp/products/5364971400?itemId=7924133405&vendorItemId=75213364478&sourceType=CATEGORY&categoryId=502894&isAddedCart=");
        String a = (String) driver.executeScript("return (function (){\n" +
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
        System.out.println(a);
//        // 옵션
//        List<WebElement> selectTag  = driver.findElements(By.className("Dropdown-Select__Dropdown__Item"));
//        selectTag.forEach(x -> {
//            System.out.println(x.getTagName());
//            System.out.println(x.getAttribute("data-attribute-id"));
//            System.out.println(x.isDisplayed());
//        });
    }
}
