package com.boaglio.analisaFII.util;

import com.boaglio.analisaFII.AnalisaFundosImobiliarios;
import com.boaglio.analisaFII.config.Config;
import com.boaglio.analisaFII.vo.FundoImobiliario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashSet;

public class WebCrawlerUtil {

    /*
        Baixe daqui o Firefox do seu sistema operacional:
        https://www.mozilla.org/pt-BR/firefox/enterprise/#download
     */
    private static final String DRIVER_DIR="/home/fb/Downloads/firefox.driver/firefox/firefox-bin";

    private static final String DRIVER_PROPERTY = "webdriver.gecko.driver";
    public static final String TABLE_ROW = "tr";
    public static final String TABLE_DATA = "td";

    public static HashSet<FundoImobiliario> parseListaDeFundos() {

        System.setProperty(DRIVER_PROPERTY,DRIVER_DIR);

        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.setLogLevel(FirefoxDriverLogLevel.TRACE);
        options.setBinary(DRIVER_DIR);

        WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver(options);

        String url = Config.URL_FUNDSEXPLORER;

        System.out.println("Loading: "+url);
        driver.get(url);
        String pageContent = driver.getPageSource();
        driver.quit();

        System.out.println("Página lida: "+pageContent.length()+" bytes") ;

        Document doc = Jsoup.parse(pageContent);
        HashSet<FundoImobiliario> hashSetListaDeFundos = new HashSet<>();

        Elements rows = doc.select(TABLE_ROW);

        for (Element row : rows) {
            Elements columns = row.select(TABLE_DATA);
            if (columns.size() > Config.FUNDSEXPLORER_COLUMNS) {
                hashSetListaDeFundos.add(LoadUtil.populateFI(columns));
            }
            AnalisaFundosImobiliarios.contador++;
        }

        if (AnalisaFundosImobiliarios.contador == 0) {
            System.out.println("Site [" + Config.URL_FUNDSEXPLORER + "] inacessível. Tente mais tarde... =( ");
            System.exit(0);
        }

        return hashSetListaDeFundos;
    }

}