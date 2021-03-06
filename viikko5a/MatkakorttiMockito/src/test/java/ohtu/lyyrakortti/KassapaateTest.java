package ohtu.lyyrakortti;

import ohtu.matkakortti.Matkakortti;
import ohtu.matkakortti.Kassapaate;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KassapaateTest {

    Kassapaate kassa;
    Matkakortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = mock(Matkakortti.class);
    }

    @Test
    public void kortiltaVelotetaanHintaJosRahaaOn() {
        when(kortti.getSaldo()).thenReturn(10);
        kassa.ostaLounas(kortti);

        verify(kortti, times(1)).getSaldo();
        verify(kortti).osta(eq(Kassapaate.HINTA));
    }

    @Test
    public void kortiltaEiVelotetaJosRahaEiRiita() {
        when(kortti.getSaldo()).thenReturn(4);
        kassa.ostaLounas(kortti);

        verify(kortti, times(1)).getSaldo();
        verify(kortti, times(0)).osta(anyInt());
    }

    @Test
    public void kortilleLadataanPosRaha() {
        // ladataan lisää rahaa kortille +10
        kassa.lataa(kortti, 10);
        // Kassapääte kutsuu kortin lataa -metodia summalla 10
        verify(kortti).lataa(eq(10));
    }

    @Test
    public void kortilleEiVoiLadataNegRaha() {
        // kassapäätteen lataa -metodi saa kortin ja -50
        kassa.lataa(kortti, -50);
        // kassapääte kutsuu kortin lataa -metodia nolla kertaa
        verify(kortti, times(0)).lataa(anyInt());
    }
}
