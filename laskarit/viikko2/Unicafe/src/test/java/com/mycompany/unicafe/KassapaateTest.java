package com.mycompany.unicafe;



import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    @Test
    public void luotuKassapaateOnOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisOstoEdullinen() {
        int rahaaTakaisin = kassapaate.syoEdullisesti(250);
        assertEquals(10, rahaaTakaisin);
        assertEquals(100240, kassapaate.kassassaRahaa());
        
    }
    
    @Test
    public void myytyjenLounaidenMaaraKasvaa() {
        kassapaate.syoEdullisesti(240);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void ostoaEiTapahduJosRahaEiRiita() {
        int vaihtoraha = kassapaate.syoEdullisesti(100);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(100, vaihtoraha);
    }
    
     @Test
    public void kateisOstoMaukas() {
        int rahaaTakaisin = kassapaate.syoMaukkaasti(500);
        assertEquals(100, rahaaTakaisin);
        assertEquals(100400, kassapaate.kassassaRahaa());
        
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void ostoaEiTapahduJosRahaEiRiitaMaukas() {
        int vaihtoraha = kassapaate.syoMaukkaasti(100);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(100, vaihtoraha);
    }
    
    @Test
    public void korttiOstoToimiiEdullinenLounas() {
        boolean onnistuu = kassapaate.syoEdullisesti(kortti);
        assertEquals(260, kortti.saldo());
        assertEquals(true, onnistuu);
    }
    
    @Test
    public void korttiOstoToimiiMaukasLounas() {
        boolean onnistuu = kassapaate.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
        assertEquals(true, onnistuu);
    }
    
    @Test
    public void korttiostoKerryttaaMyytyjaEdullisiaLounaita() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoKerryttaaMyytyjaMaukkaitaLounaita() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void ostoaEiTapahduJosKortillaEiRahaaMaukas() {
        kassapaate.syoMaukkaasti(kortti);
        boolean ostoOnnistui = kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(false, ostoOnnistui);
        assertEquals(100, kortti.saldo());
        
    }
    
    @Test
    public void ostoaEiTapahduJosKortillaEiRahaaEdullinen() {
        kassapaate.syoMaukkaasti(kortti);
        boolean ostoOnnistui = kassapaate.syoEdullisesti(kortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(false, ostoOnnistui);
        assertEquals(100, kortti.saldo());
        
    }
    
    @Test
    public void kassassaOlevaRahamaaraEiMuutuKortillaOstettaessa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void rahanLataaminenKortille() {
        kassapaate.lataaRahaaKortille(kortti, 100);
        assertEquals(600, kortti.saldo());
        assertEquals(100100, kassapaate.kassassaRahaa());
        
    }
    
    @Test
    public void negatiivisenSummanLataaminenKortilleEiOnnistu() {
        kassapaate.lataaRahaaKortille(kortti, -10);
        assertEquals(500, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    
    
}
