package YandexAPI;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.RestAssured;
import org.testng.annotations.Test;


public class TestTranslate {

    private static final String API_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";

    @Test
    public void getLangsTest() throws UnirestException {

        RestAssured.useRelaxedHTTPSValidation();//SSL

        String text = "Автоматизация";
        String languageFormat = "ru-en";

        String textTranslate = getServerResponse(languageFormat,text);

        System.out.println(textTranslate);

    }


    public String getServerResponse(String languageFormat, String text) throws UnirestException {

       // String queryParameter = String.format(API_URL + "?key=%s&text=%s&lang=%s", EndpointUrl.API_KEY_YANDEX_TRANSLATE, text, languageFormat);
        //String queryParameter = String.format("?key=%s&text=%s&lang=%s",EndpointUrl.API_KEY_YANDEX_TRANSLATE, text, languageFormat);
       // return Unirest.get(queryParameter).asString().getBody();

        return Unirest.get(API_URL).queryString("key",EndpointUrl.API_KEY_YANDEX_TRANSLATE)
                .queryString("text",text).queryString("lang",languageFormat).asString().getBody();
    }

}
