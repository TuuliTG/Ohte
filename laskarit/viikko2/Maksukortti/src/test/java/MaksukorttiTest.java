
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class MaksukorttiTest {
    
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }
    
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        String vastaus = kortti.toString();
        assertEquals("Kortilla on rahaa 10.0 euroa", vastaus);
    }
    
    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        // nyt kortin saldo on 2
        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    @Test
    public void negatiivisenSummanLataaminenEiMuutaKortinSaldoa() {
        kortti.lataaRahaa(-2);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    
    @Test
    public void edullisenLounaanOstaminenOnnistuuTasarahalla() {
        Maksukortti kortti2 = new Maksukortti(2.5);
        kortti2.syoEdullisesti();
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti2.toString());
    }
    
    @Test
    public void maukkaanLounaanOstaminenOnnistuuTasarahalla() {
        Maksukortti kortti2 = new Maksukortti(4.0);
        kortti2.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti2.toString());
    }
}
