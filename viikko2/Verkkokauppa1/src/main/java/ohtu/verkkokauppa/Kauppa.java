package ohtu.verkkokauppa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Kauppa {
    
    private VarastoRajapinta varasto;
//    private Varasto varasto;
    private PankkiRajapinta pankki;
//    private Pankki pankki;
    private Ostoskori ostoskori;
    private ViitegeneraattoriRajapinta viitegeneraattori;
//    private Viitegeneraattori viitegeneraattori;
    private String kaupanTili;

//    public Kauppa() {
//        varasto = Varasto.getInstance();
//        pankki = Pankki.getInstance();
//        viitegeneraattori = Viitegeneraattori.getInstance();
//        kaupanTili = "33333-44455";
//    }
    public Kauppa(VarastoRajapinta vr, PankkiRajapinta pr, ViitegeneraattoriRajapinta vgr) {
//        varasto = Varasto.getInstance();
        varasto = vr;
//        pankki = Pankki.getInstance();
        pankki = pr;
//        viitegeneraattori = Viitegeneraattori.getInstance();
        viitegeneraattori = vgr;
        kaupanTili = "33333-44455";
    }
    

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
