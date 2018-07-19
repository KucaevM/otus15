import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasToString;


public class TestGetLangs {

    private static final String API_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/getLangs";

    @Test
    public void getLangsTest() {

        RestAssured.useRelaxedHTTPSValidation();//SSL

        String jsonString = "[be-be, be-ru, bg-ru, cs-en, cs-ru, da-en, da-ru, de-de, de-en, de-ru, de-tr, el-en, el-ru, en-cs, en-da, en-de, en-el, en-en, en-es, en-et, en-fi, en-fr, en-it, en-lt, en-lv, en-nl, en-no, en-pt, en-ru, en-sk, en-sv, en-tr, en-uk, es-en, es-es, es-ru, et-en, et-ru, fi-en, fi-ru, fi-fi, fr-fr, fr-en, fr-ru, hu-hu, hu-ru, it-en, it-it, it-ru, lt-en, lt-lt, lt-ru, lv-en, lv-ru, mhr-ru, mrj-ru, nl-en, nl-ru, no-en, no-ru, pl-ru, pt-en, pt-ru, ru-be, ru-bg, ru-cs, ru-da, ru-de, ru-el, ru-en, ru-es, ru-et, ru-fi, ru-fr, ru-hu, ru-it, ru-lt, ru-lv, ru-mhr, ru-mrj, ru-nl, ru-no, ru-pl, ru-pt, ru-ru, ru-sk, ru-sv, ru-tr, ru-tt, ru-uk, sk-en, sk-ru, sv-en, sv-ru, tr-de, tr-en, tr-ru, tt-ru, uk-en, uk-ru, uk-uk]";

        given()
                .header("User-Agent", "Mozilla...")
                .header("JWT", "jwt_token")
                .when()
                .get(getPathFormated())
                .then()
                //.statusCode(200)
                .body("$", hasToString(jsonString));
    }


    protected String getPathFormated() {

        return String.format(API_URL + "?key=%s", EndpointUrl.API_KEY_YANDEX);

    }


}
