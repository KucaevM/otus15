package TranslateYandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.xpath;

public class PageText {

    @FindBy(id = "fakeArea")              SelenideElement inputFakeArea;
    @FindBy(id = "translation")           SelenideElement inputTranslation;
    @FindBy(xpath = "//*[@id='dictionaryContent']")     SelenideElement textDictionaryContent;
    @FindBy(xpath = "//*[@id='srcLangButton']")     SelenideElement srcLangButton;
    @FindBy(xpath = "//*[@id='dstLangButton']")     SelenideElement dstLangButton;
    @FindBy(xpath = "//*[@id='srcLangListboxContent']")     SelenideElement srcLangListbox;
    @FindBy(xpath = "//*[@id='dstLangListboxContent']")     SelenideElement dstLangListbox;


    public static PageText enterToTextPage(){
        open("https://translate.yandex.ru/");
        return page(PageText.class);
    }

    public PageText inputTextSpeller (String text){
        inputFakeArea.sendKeys(text);//should(Condition.exist)
        return page(PageText.class);
    }
    public Boolean checkTranslation(String textTranslation){
       Boolean result = false;

       if (inputTranslation.should(visible).getText().equals(textTranslation)) {
              result = true;
       }

       if (result == false){
           textDictionaryContent.$$(By.xpath("ul[1]/li/ol")).findBy(text(textTranslation)).should(exist);
           result = true;
       }

        return result;
    }

    public PageText enterSrcLang(String lang) {
        srcLangButton.click();
       // srcLangListbox.$(xpath("//*[@data-value = '" + lang + "']")).should(exist).click();
        $(xpath("//*[@id='srcLangListboxContent']//*[@data-value = '" + lang + "']")).should(exist).click();

        return page(PageText.class);
    }

    public PageText enterDstLang(String lang) {
        dstLangButton.click();
        // dstLangListbox.$(xpath("//*[@data-value = '" + lang + "']")).should(exist).click();
        $(xpath("//*[@id='dstLangListboxContent']//*[@data-value = '" + lang + "']")).should(exist).click();
        return page(PageText.class);
    }
}
