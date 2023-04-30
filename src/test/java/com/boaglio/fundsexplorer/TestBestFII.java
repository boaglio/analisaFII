package com.boaglio.fundsexplorer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.boaglio.analisaFII.analise.BestFII;
import com.boaglio.analisaFII.vo.FundoImobiliario;
import com.boaglio.analisaFII.vo.Rank;

class TestBestFII {

    static String                 SETOR = "Logística";
    static List<FundoImobiliario> fiiList;

    @BeforeAll
    static void init() {
     // @formatter:off
     fiiList = new ArrayList<FundoImobiliario>( List.of( 
        new FundoImobiliario ("XPLG11",SETOR, 0.64, 6.55,0.52,5111.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
        new FundoImobiliario ("LVBI11",SETOR, 0.14, 1.55,0.72,2111.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
        new FundoImobiliario ("GGRC11",SETOR, 0.54, 8.55,0.42,7111.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
        new FundoImobiliario ("PQAG11",SETOR, 0.74, 9.55,0.52,4111.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
        new FundoImobiliario ("SDIL11",SETOR, 0.34, 2.55,0.12,3111.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
        new FundoImobiliario ("LGCP11",SETOR, 0.64, 6.55,0.52,1111.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
    ));
     // @formatter:on
    }

    @Test
    public void testTopFII() {

        // fiiList.forEach(System.out::println);

        List<Rank> top = BestFII.getList(fiiList);

        System.out.println("Top 5 FII:");
        top.forEach(System.out::println);

        System.out.println("Top FII: " + top.get(0));

        assertEquals("LGCP11", top.get(0).name());
        assertEquals("SDIL11", top.get(1).name());
    }
}
