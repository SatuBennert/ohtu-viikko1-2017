package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        varasto = new Varasto(0);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        varasto = new Varasto(10, 0);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaTilavuusNolla() {
        varasto = new Varasto(-5, 0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaAlkusaldoNolla() {
        varasto = new Varasto(0, -4);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);
        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysNegatiivinenEiTeeMitaanSaldolle() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-4);
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysPositiivinenLisaaSaldoa() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(4);
        assertEquals(saldo + 4, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysJokaEiMahduMeneeHukkaan() {
        double saldo = varasto.getSaldo();
//        System.out.println("saldo: " + varasto.getSaldo());
//        System.out.println("tilavuus: " + varasto.getTilavuus());
        varasto.lisaaVarastoon(varasto.getTilavuus() + 1);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
//        System.out.println(" uusi saldo: " + varasto.getSaldo());
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaPalauttaaNollanNegMaaralle() {
        double saatuMaara = varasto.otaVarastosta(-4);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaPalauttaaNollanNollaMaaralle() {
        double saatuMaara = varasto.otaVarastosta(0);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaPalauttaaKaikenKunPyydettyYli() {
        varasto.lisaaVarastoon(8);
        double saldo = varasto.getSaldo(); // antaa kaiken saldon ulos
        double saatuMaara = varasto.otaVarastosta(100);
        assertEquals(saldo, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void tulostaString() {
        Varasto nollaVarasto = new Varasto(0, 0);
        String string = "saldo = 0.0, vielä tilaa 10.0";
        int ok = 1;
        if (string.equals(varasto.toString())) {
            ok = 0;
        }
        assertEquals(10, ok, vertailuTarkkuus);
        System.out.println("jepulis. Travista varten muutos.");

    }
}
