package YandexTranslateUI;

import TranslateYandex.PageText;
import YandexAPI.TestGetLangs;
import YandexAPI.TestTranslate;
import com.codeborne.selenide.Configuration;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.jayway.jsonpath.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import org.testng.Assert;
import static TranslateYandex.PageText.enterToTextPage;


public class TestTranslateUI {

    @BeforeClass
    public void BeforeTest(){

        //System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/FirstTest/chromedriver.exe");
        Configuration.browser = "Chrome";

    }
   @Test(dataProvider = "DataTestTranslateUI")
   public void TestTranslateUI(String TextForTranslation, String LanguageFrom, String LanguageWhich, String TranslatedText) {

      PageText pageText = enterToTextPage().enterSrcLang(LanguageFrom).enterDstLang(LanguageWhich).inputTextSpeller(TextForTranslation);//.checkTranslation(TranslatedText);

      Assert.assertTrue(pageText.checkTranslation(TranslatedText));

    }

    @DataProvider(name = "DataTestTranslateUI")
    private Object[][] DataProvider() throws UnirestException {

        String[][] ListWords = {
                {"Привет", "ru"},
                {"Result", "en"}
        };

        String StringLanguages = new TestGetLangs().getServerResponse();
        ArrayList<String> ListLanguages = new ArrayList<String>(Arrays.asList(StringLanguages.split("\",\"")));
        int numberOfTransfers = 0;
        for (int i = 0; i < ListWords.length; i++) {
            numberOfTransfers = numberOfTransfers + StringLanguages.split(ListWords[i][1] +"-").length - 1;
        }

        TestTranslate testTranslate = new TestTranslate();
        Object[][] dataObject = new Object[numberOfTransfers][4];
        int number = 0;

        for (int i = 0; i < ListWords.length; i++)
        { String TextForTranslation = ListWords[i][0];
          String LanguageFrom = ListWords[i][1];
             for (int j = 0; j < ListLanguages.size(); j++)
             { String currentLanguage = ListLanguages.get(j);
                  if (currentLanguage.contains(LanguageFrom + "-")){

                     String[] listCurrentLanguage = currentLanguage.split("-");

                     dataObject[number][0] = TextForTranslation;
                     dataObject[number][1] = listCurrentLanguage[0];
                     dataObject[number][2] = listCurrentLanguage[1];
                     String tr = testTranslate.getServerResponse(currentLanguage, TextForTranslation);
                     dataObject[number][3] = JsonPath.read(tr,"$.text[0]");
                     number = number + 1;

            }
        }
    }

        return dataObject;

    }

}
