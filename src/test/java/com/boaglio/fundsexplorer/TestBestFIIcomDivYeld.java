package com.boaglio.fundsexplorer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.boaglio.analisaFII.analise.BestFII;
import com.boaglio.analisaFII.vo.FundoImobiliario;
import com.boaglio.analisaFII.vo.Rank;

class TestBestFIIcomDivYield {

    static String                 SETOR = "Logística";
    static List<FundoImobiliario> fiiList;

    @BeforeAll
    static void init() {
     // @formatter:off
     fiiList = new ArrayList<FundoImobiliario>( List.of( 
        new FundoImobiliario ("XPLG11",SETOR, 0.64, 6.55,0.52,1111.1,0.0,0.0,10.0,0.0,0.0,10.0,0.0,0.0,0.0,0.0,1000.0,0.0,20.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
        new FundoImobiliario ("LVBI11",SETOR, 0.14, 1.55,0.72,1111.1,0.0,0.0,20.0,0.0,0.0,10.0,0.0,0.0,0.0,0.0,1000.0,0.0,20.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
        new FundoImobiliario ("GGRC11",SETOR, 0.54, 8.55,0.42,1111.1,0.0,0.0,30.0,0.0,0.0,10.0,0.0,0.0,0.0,0.0,1000.0,0.0,20.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
        new FundoImobiliario ("PQAG11",SETOR, 0.74, 9.55,0.52,1111.1,0.0,0.0,40.0,0.0,0.0,10.0,0.0,0.0,0.0,0.0,1000.0,0.0,20.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
        new FundoImobiliario ("SDIL11",SETOR, 0.34, 2.55,0.12,1111.1,0.0,0.0,50.0,0.0,0.0,10.0,0.0,0.0,0.0,0.0,1000.0,0.0,20.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
        new FundoImobiliario ("LGCP11",SETOR, 0.64, 6.55,0.52,1111.1,0.0,0.0,60.0,0.0,0.0,10.0,0.0,0.0,0.0,0.0,1000.0,0.0,20.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
    ));
     // @formatter:on
    }

    @Test
    public void testTopFIIporDivYield() {

        List<Rank> top = BestFII.getList(fiiList);

        System.out.println("Top FII por dividend yield :");
        top.forEach(System.out::println);

        assertEquals("XPLG11", top.get(0).name());
        assertEquals("LVBI11", top.get(1).name());
        assertEquals("GGRC11", top.get(2).name());
        assertEquals("PQAG11", top.get(3).name());
        assertEquals("SDIL11", top.get(4).name());
        assertEquals("LGCP11", top.get(5).name());
    }
}
