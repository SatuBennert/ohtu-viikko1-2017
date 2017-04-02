package ohtu.verkkokauppa;

public class Pankki implements PankkiRajapinta {

//    private static Pankki instanssi;

//    public static Pankki getInstance() {
//        if (instanssi == null) {
//            instanssi = new Pankki();
//        }
//
//        return instanssi;
//    }
    private KirjanpitoRajapinta kirjanpito;
    
    public Pankki(KirjanpitoRajapinta kirjanpitoRajapinta){
        kirjanpito = kirjanpitoRajapinta;
    }
    
//    public Pankki() {
//        kirjanpito = Kirjanpito.getInstance();
//    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tililta + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
