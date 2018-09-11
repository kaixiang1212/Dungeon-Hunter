package Test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import Model.Hunter;
import Model.Player;
import Model.Sword;

public class testSwordAttack {

	Player p = new Player();
	Hunter h = new Hunter();

	@Test
	public void testHunterAttack() {
		p.pickupWeapon(new Sword());
		assertEquals(h.getHealth(), 100);
		p.attack(h);
		assertEquals(h.getHealth(), 0);
	}
	
}
