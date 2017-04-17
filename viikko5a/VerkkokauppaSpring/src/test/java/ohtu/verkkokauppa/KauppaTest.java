/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author bensatu
 */
public class KauppaTest {

    public KauppaTest() {
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // oikeilla parametreilla: nimi "pekka", viitenumero 42, tililtä "12345", tilille ihansama, summa 5
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));

    }

    @Test
    public void kahdenEriOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
// määritellään että tuote numero 2 on leipa jonka hinta on 3 ja saldo 15
        when(varasto.saldo(2)).thenReturn(15);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "leipa", 3));
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli leipaa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // oikeilla parametreilla: nimi "pekka", viitenumero 42, tililtä "12345", tilille ihansama, summa 5
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(8));

    }

    // aloitetaan asiointi, koriin lisätään kaksi samaa tuotetta jota 
    // on varastossa tarpeeksi ja suoritetaan ostos. 
    // varmistettava että kutsutaan pankin metodia tilisiirto oikealla
    // asiakkaalla, tilinumerolla ja summalla
    @Test
    public void kahdenSamanOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // oikeilla parametreilla: nimi "pekka", viitenumero 42, tililtä "12345", tilille ihansama, summa 5
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(10));

    }
    // aloitetaan asiointi, koriin lisätään tuote jota on varastossa
    // tarpeeksi ja tuote joka on loppu ja suoritetaan ostos. 
    // varmistettava että kutsutaan pankin metodia tilisiirto oikealla
    // asiakkaalla, tilinumerolla ja summalla

    @Test
    public void kahdenEriOstoksenJoistaToinenLoppuPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
// määritellään että tuote numero 2 on leipa jonka hinta on 3 ja saldo 0
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(0, "leipa", 3));
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli leipaa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // oikeilla parametreilla: nimi "pekka", viitenumero 42, tililtä "12345", tilille ihansama, summa 5
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));

        // varmistettava, että metodin aloitaAsiointi kutsuminen nollaa 
        // edellisen ostoksen tiedot (eli edellisen ostoksen hinta ei näy
        //uuden ostoksen hinnassa), 
        //katso tarvittaessa apua projektin MockitoDemo testeistä!
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että metodi palauttaa ensimmäisellä kutsukerralla 1, toisella 2 
        when(viite.uusi()).thenReturn(42).thenReturn(67);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        // korin hinta po. nolla
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 2 eli leipaa
        k.tilimaksu("pekka", "12345");
        // korin hinta po. 10

        // varmistetaan, että nyt käytössä ensimmäisenä pyydetty viite
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(10));

        // uusi asiakas,joka ei osta mitään
        k.aloitaAsiointi();
        k.tilimaksu("kalle", "1222");

        // ... toisena pyydetty viite
        verify(pankki).tilisiirto(eq("kalle"), eq(67), eq("1222"), anyString(), eq(0));

    }
    // varmistettava, että kauppa pyytää uuden viitenumeron jokaiselle 
    // maksutapahtumalle, 

    @Test
    public void jokaiselleAsioinnileOmaViite() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että metodi palauttaa ensimmäisellä kutsukerralla 42, toisella 67 
        when(viite.uusi()).thenReturn(42).thenReturn(67);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        // korin hinta po. nolla
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 2 eli leipaa
        k.tilimaksu("pekka", "12345");
        // korin hinta po. 10

        // varmistetaan, että nyt käytössä ensimmäisenä pyydetty viite
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(10));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("kalle", "1222");

        // ... toisena pyydetty viite
        verify(pankki).tilisiirto(eq("kalle"), eq(67), eq("1222"), anyString(), eq(5));

    }

    @Test
    public void koristaPoistoOnnistuu() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);
        k.poistaKorista(1);    // ostetaan tuotetta numero 2 eli leipaa
        k.tilimaksu("pekka", "12345");
        // korin hinta po. 5

        // varmistetaan, että nyt käytössä ensimmäisenä pyydetty viite
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));

    }
}
