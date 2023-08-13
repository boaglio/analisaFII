package com.boaglio.analisaFII.util;

import com.boaglio.analisaFII.AnalisaFundosImobiliarios;
import com.boaglio.analisaFII.config.Config;
import com.boaglio.analisaFII.vo.FundoImobiliario;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.HashSet;

public class WebCrawlerUtil {
    public static HashSet<FundoImobiliario> parseListaDeFundos() throws IOException {

       /*
           Baixe daqui o Chrome Driver do seu sistema operacional:
           https://chromedriver.chromium.org/downloads
           Testado com sucesso usando :
           https://chromedriver.storage.googleapis.com/114.0.5735.90/chromedriver_linux64.zip
        */
        System.setProperty("webdriver.chrome.driver", "/home/fb/Downloads/chrome.driver/chromedriver");

        WebDriver driver = new ChromeDriver();
        String url = Config.URL_FUNDSEXPLORER;
        driver.get(url);
        String pageContent = driver.getPageSource();
        driver.quit();

        System.out.println("Página lida: "+pageContent.length()+" bytes") ;

        Document doc = Jsoup.parse(pageContent);
        HashSet<FundoImobiliario> hashSetListaDeFundos = new HashSet<>();

        Elements rows = doc.select("tr");

        for (Element row : rows) {
            Elements columns = row.select("td");
            if (columns.size() > Config.FUNDSEXPLORER_COLUMNS) {
                hashSetListaDeFundos.add(LoadUtil.populateFI(columns));
            }
            AnalisaFundosImobiliarios.contador++;
        }

        if (AnalisaFundosImobiliarios.contador == 0) {
            System.out.println(
                    "Site [" + Config.URL_FUNDSEXPLORER + "] inacessível. Tente mais tarde... =( ");
            System.exit(0);
        }

        return hashSetListaDeFundos;
    }

}