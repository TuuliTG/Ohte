# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne on nelitasoinen: 
mastermind.gui -> mastermind.gamelogic -> mastermind.domain -> mastermind.dao

Pakkaus mastermind.gui sisältää JavaFX:llä toteutetun käyttöliittymän. Mastermind.gamelogic huolehtii käyttöliittymän ja sovelluslogiikan välisestä kommunikaatiosta. Mastermind.domain sisältää perus sovelluslogiikan ja mastermind.dao tietokantaan ja tallentamiseen liittyvät luokat.

## Käyttöliittymä
Käyttöliittymä sisältää seuraavat näkymät
* New Game
	* Pelaajan luominen / kirjautuminen 
	* Erillinen ikkuna
* Game Scene
	* Pelinäkymä
	* Scene
* Score Board
	* Pistetaulukko
	* Scene
* Options
	* Valikko, josta voi valita, kuinka monta arvausta on käytettävissä
	* Scene
* Game Over
	* Ikkuna, joka ilmestyy kun peli päättyy. 
	* Erillinen ikkuna

Jokainen näkymä on toteutettu omassa luokassaan. Osa näkymistä on omana Scene-oliona, joka sijoitetaan sovelluksen stageen, osa näkymistä luo oman ikkunan  (stagen).

Käyttöliittymä keskustelee enimmäkseen GameLogic-luokan kanssa, joka sitten kutsuu sovelluslogiikan metodeja. Lisäksi käyttöliittymä keskustelee PlayerService-luokan kanssa, joka hoitaa tietojen tallennuksen tietokantaan. Lisäksi domain-pakkauksen luokka Options on käyttöliittymän käytössä. 

## Sovelluslogiikka

Luokkakaaviossa on nähtävillä sovelluksen luokkarakenne. 

![class diagram](https://github.com/TuuliTG/Ohte/blob/main/Documents/MastermindLuokkakaavio.jpg)

## Tietojen tallentaminen tietokantaan

Tietokantaan (eli tiedostoihin) tallentamisesta vastaa mastermind.dao-pakkauksen luokat FilePlayerDao ja FileGameDao. 
Luokat on eristetty PlayerDao- ja GameDao- rajapintojen taakse. 

Testit hyödyntävät tätä rajapintaominaisuutta siten, että testit tallentavat tietoa väliaikaisesti keskusmuistiin, eikä tiedostoihin.

## Tiedostot
Sovellus tallentaa pelaajien ja pelien tiedot erillisiin tiedostoihin. Mastermind/src/main/resources polun takana on konfiguraatiotiedosto config.properties, joka määrittelee tiedostojen nimet. 
Sovellus tallettaa pelaajat seuraavassa formaatissa:
`nimi;paras aika;paras tulos;voitettujen pelien määrä;`

Pelit talletetaan seuraavassa muodossa:
`pelaajan nimi;piste tulos;aika;arvausten määrä`


## Päätoiminnallisuudet

Käyttäjän luominen / kirjautuminen:
Kun käyttäjä kirjoittaa nimikenttään nimen, ohjelma tarkistaa, onko nimi jo tietokannassa. Jos on, pelaaja kirjataan sisään, jos ei niin ohjelma luo uuden pelaajan käyttäjän antamalla nimellä. 

#### Pelin pelaaminen:
Pelaaja asettaa arvauksen painamalla nappulan kohdalta niin monta kertaa, kunnes on saatu haluttu väri. Kun neljä väriä on valittu, pelaaja voi painaa "Accept guess" -nappia. 
Alla siitä tilanteesta sekvenssikaavio:
#### Sekvenssikaavio
![Arvauksen hyväksyminen](https://github.com/TuuliTG/Ohte/blob/main/Documents/MastermindSekvenssikaavio.png)

#### Peli päättyy:
Peli voi päättyä joko siihen, että arvaukset loppuvat kesken, tai että pelaaja arvaa oikean värisarjan. 
Pelin päättyessä esiin tulee Game over -ikkuna, johon tulee teksti sen mukaan, miksi peli päättyi. Näkyviin tulee myös peliin käytetty aika. 
Pelaaja voi lopettaa pelaamisen tai aloittaa uuden pelin. 

#### Score Board:

## Parannettavaa ohjelmassa