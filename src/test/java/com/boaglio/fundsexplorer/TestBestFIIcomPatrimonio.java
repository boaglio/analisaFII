package com.boaglio.fundsexplorer;

import com.boaglio.analisaFII.analise.BestFII;
import com.boaglio.analisaFII.vo.FundoImobiliario;
import com.boaglio.analisaFII.vo.Rank;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBestFIIcomPatrimonio {

    static String                 SETOR = "Logística";
    static List<FundoImobiliario> fiiList;

    @BeforeAll
    static void init() {

        fiiList = new ArrayList<FundoImobiliario>( List.of(
                new FundoImobiliario ("XPLG11",SETOR, 0.64, 6.55,0.52,5111.1,0.0,0.0,10.0,0.0,0.0,10.0,0.0,0.0,0.0,0.0,1000.0,0.0,20.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
                new FundoImobiliario ("LVBI11",SETOR, 0.14, 1.55,0.72,2111.1,0.0,0.0,10.0,0.0,0.0,10.0,0.0,0.0,0.0,0.0,2000.0,0.0,20.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
                new FundoImobiliario ("GGRC11",SETOR, 0.54, 8.55,0.42,7111.1,0.0,0.0,10.0,0.0,0.0,10.0,0.0,0.0,0.0,0.0,3000.0,0.0,20.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
        ));

    }

    @Test
    public void testTopFIImaiorPatrimonio() {

        List<Rank> top = BestFII.getList(fiiList);

        System.out.println("Top FII por maior patrimonio:");
        top.forEach(System.out::println);

        assertEquals("GGRC11", top.get(0).name());
        assertEquals("LVBI11", top.get(1).name());
        assertEquals("XPLG11", top.get(2).name());
    }

}
