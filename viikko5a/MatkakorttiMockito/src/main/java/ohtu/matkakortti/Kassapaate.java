package ohtu.matkakortti;

public class Kassapaate {

    private int myytyjaLounaita;
    public static final int HINTA = 5;

    public Kassapaate() {
        this.myytyjaLounaita = 0;
    }

    public void lataa(Matkakortti kortti, int summa) {
        // jos positiivinen summa -> lataa
        if (summa > 0) {
            kortti.lataa(summa);
        }
        // jos negatiivinen summa -> ei tehdä mitään
    }

    public void ostaLounas(Matkakortti kortti) {
        // tarkista kortin saldo 
        if (kortti.getSaldo() >= HINTA) {
            // jos on rahaa - veloita
            kortti.osta(HINTA);
            myytyjaLounaita++;
        }
        // jos ei ole rahaa - ei veloitusta
    }

    public int getMyytyjaLounaita() {
        return myytyjaLounaita;
    }
}
