package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukuJoukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alustaUusiJoukko(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        alustaUusiJoukko(kapasiteetti);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        alustaUusiJoukko(kapasiteetti);
        this.kasvatuskoko = kasvatuskoko;

    }

    private void alustaUusiJoukko(int koko) {
        // java alustaa int-taulun itse nolliksi, ei tarvita luuppia
        lukuJoukko = new int[koko];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public int etsiVapaaIndeksi() {
        if (alkioidenLkm == 0) {
            return 0;
        }
        if (alkioidenLkm < lukuJoukko.length) {
            return alkioidenLkm;
        }
        kasvataTaulua();
        return alkioidenLkm;
    }

    public void kasvataTaulua() {
        if (alkioidenLkm % lukuJoukko.length == 0) {
            int[] vanhaLukuJoukko = lukuJoukko;
            kopioiTaulukko(lukuJoukko, vanhaLukuJoukko);
            lukuJoukko = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(vanhaLukuJoukko, lukuJoukko);
        }
    }

    public boolean lisaa(int luku) {
        if (lukuKuuluuJoukkoon(luku)) {
            return false;
        } else {
            int indeksi = etsiVapaaIndeksi();
            lukuJoukko[indeksi] = luku;
            alkioidenLkm++;
            return true;

        }
    }

    public boolean lukuKuuluuJoukkoon(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJoukko[i]) {
                return true;
            }
        }
        return false;
    }

    public int etsiIndeksi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJoukko[i]) {
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }

    public void tiivistaLukuJoukko(int kohta) {
        int apu;
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = lukuJoukko[j];
                lukuJoukko[j] = lukuJoukko[j + 1];
                lukuJoukko[j + 1] = apu;
            }
        }
    }

    public boolean poista(int luku) {
        if (lukuKuuluuJoukkoon(luku)) {
            int indeksi = etsiIndeksi(luku);
            lukuJoukko[indeksi] = 0;
            tiivistaLukuJoukko(indeksi);
            alkioidenLkm--;
            return true;
        } else {
            return false;
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukuJoukko[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += lukuJoukko[i];
                tuotos += ", ";
            }
            tuotos += lukuJoukko[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukuJoukko[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
//            z.poista(i);
            z.poista(bTaulu[i]);
        }

        return z;
    }

}
