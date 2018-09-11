package Test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import Model.Hunter;
import Model.Player;
import Model.Sword;

public class testSwordAttack {

	Player p = new Player();
	Sword s = new Sword();
	Hunter h = new Hunter();
	Hunter h1 = new Hunter();
	Hunter h2 = new Hunter();
	Hunter h3 = new Hunter();
	Hunter h4 = new Hunter();
	Hunter h5 = new Hunter();

	@Test
	public void testHunterAttack() {
		p.pickupWeapon(s);
		assertEquals(h.getHealth(), 100);
		p.attack(h);
		assertEquals(h.getHealth(), 0);
	}
	@Test
	public void testFiveAttack() {
		p.pickupWeapon(s);
		assertEquals(s.getnumUses(), 5);
		assertEquals(p.getWeapon(), s);
		p.attack(h);
		p.attack(h1);
		p.attack(h2);
		p.attack(h3);
		p.attack(h4);
		assertEquals(s.getnumUses(), 0);
		assertEquals(p.getWeapon(), null);
		assertEquals(p.getHealth(), 100);
		p.attack(h5);
		assertEquals(p.getHealth(), 0);
	}
	
}
