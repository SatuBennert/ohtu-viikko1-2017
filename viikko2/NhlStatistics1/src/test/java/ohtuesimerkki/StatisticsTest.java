/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

//import .*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bensatu
 */
public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void tulostaSopivaMaara() {
        List<Player> playersOfTeam = new ArrayList<>();
        playersOfTeam = stats.topScorers(2);
//        System.out.println(stats.topScorers(2));
        assertEquals(2, playersOfTeam.size());
    }

    @Test
    public void tulostaNolla() {
        List<Player> playersOfTeam = new ArrayList<>();
        playersOfTeam = stats.topScorers(0);
//        System.out.println(stats.topScorers(0));
        assertEquals(0, playersOfTeam.size());
    }

    @Test
    public void tulostaOlemassaOlevanTiiminPelaajat() {
        List<Player> playersOfTeam = new ArrayList<>();
        playersOfTeam = stats.team("EDM");
//        System.out.println("pelaajia on: " + playersOfTeam.size());
        assertEquals(3, playersOfTeam.size());

    }

    @Test
    public void tulostaEiOlemassaOlevanTiiminPelaajat() {
        List<Player> playersOfTeam = new ArrayList<>();
        playersOfTeam = stats.team("XYZ");
//        System.out.println("pelaajia on: " + playersOfTeam.size());
        assertEquals(0, playersOfTeam.size());

    }

    @Test
    public void searchPlayerNimellaLoytyy() {
        int ok = 1;
        Player pelaaja = stats.search("Gretzky");
        if (pelaaja.getName().equalsIgnoreCase("Gretzky")) {
            ok = 2;
        }
        System.out.println(pelaaja);
        assertEquals(2, ok);
    }
    @Test
    public void searchPlayerNimellaEiLoydy() {
        int ok = 1;
        Player pelaaja = stats.search("Ville");
        if (pelaaja == null){
            ok = 2;
        }
        assertEquals(2, ok);
    }
}
