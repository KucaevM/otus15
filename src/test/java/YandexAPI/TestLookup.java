package YandexAPI;

import YandexAPI.EndpointUrl;
import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasToString;

public class TestLookup {

    private static final String API_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup";

    @Test(dataProvider = "DataLangsTest")
    public void lookupTest(String lang, String text , String jsonPath, String result) {

        RestAssured.useRelaxedHTTPSValidation();//SSL

        given()
                .header("User-Agent", "Mozilla...")
                .header("JWT", "jwt_token")
                .when()
                .get(getPathFormated(lang,text))
                .then()
                .body(jsonPath, hasToString(result));
    }


    protected String getPathFormated(String languageFormat, String text) {

        return String.format(API_URL + "?key=%s&lang=%s&text=%s", EndpointUrl.API_KEY_YANDEX, languageFormat, text);

    }

    @DataProvider(name = "DataLangsTest")
    private Object[][] xssDataProvider(){

      return new Object[][] {
              {"ru-en","время","def.tr.mean.text[0]","[[раз, период, срок, продолжительность], [день, час], [момент], [эпоха, возраст], [сезон], [напряжение], [сессия], [чтение]]"},
              {"ru-en","время","def.tr.syn.text[0]","[[period, timing, duration], [hour], [age]]"},
              {"ru-en","время","def.tr.text[0]","[time, while, day, moment, era, season, tense, session, read]"}
      };

    }
}

