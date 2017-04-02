package ohtu;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kirjanpito;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class Main {
    
    public static void main(String[] args){
        System.out.println(" VERKKOKAUPPA - COPY");
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

        System.out.println(" spring määritelty");
        Kauppa kauppa = ctx.getBean(Kauppa.class);
        System.out.println(" kauppa luotu");
        Kirjanpito kirjanpito = ctx.getBean(Kirjanpito.class);
        System.out.println("kauppa ja kirjanpito luotu");

//        Kauppa kauppa = new Kauppa();
//        Kauppa kauppa = new Kauppa(Varasto.getInstance(), Pankki.getInstance(), Viitegeneraattori.getInstance());
//        Kirjanpito kirjanpito = new Kirjanpito();
//        Varasto varasto = new Varasto(kirjanpito);
//        Pankki pankki = new Pankki(kirjanpito);
//        Viitegeneraattori viitegeneraattori = new Viitegeneraattori();
//        Kauppa kauppa = new Kauppa(varasto, pankki, viitegeneraattori);
// kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
       
        kauppa.aloitaAsiointi();
        
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");

        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");
        
        // kirjanpito
//        for (String tapahtuma : Kirjanpito.getInstance().getTapahtumat()) {
        for (String tapahtuma : kirjanpito.getTapahtumat()) {
            System.out.println(tapahtuma);
        }
    }
}
