package gridwhack.entity.character.hostile;

import gridwhack.grid.Grid;

public class Orc extends HostileCharacter
{
	//public Item.Type[] items = {
	//	Item.Type.HEALTH_ORB,
	//};

	public Orc(Grid grid) 
	{
		super("orc.png", grid);

		setName("Orc");
		setDamage(50, 100);
		setHealth(100);
		setAttackCooldown(2000);
		setMovementCooldown(2000);
		setViewRange(5);
		setExperienceValue(200);
	}
}