package gridwhack.gui.character;

import java.awt.Graphics2D;

import gridwhack.gameobject.character.Character;
import gridwhack.event.IEventListener;

/**
 * Status text class.
 * Allows for representing character values as text in the gui.
 * @author Christoffer Niska <ChristofferNiska@gmail.com>
 */
public abstract class StatusText extends GuiCharacterElement implements IEventListener
{
	/**
	 * Creates the status text.
	 * @param x the x-coordinate.
	 * @param y the y-coordinate.
	 * @param width the width.
	 * @param height the height.
	 * @param owner the character this text belongs to.
	 */
	public StatusText(int x, int y, int width, int height, Character owner)
	{
		super(x, y, width, height, owner);

		owner.addListener(this); // set the element to listen to its owner.
	}
	
	/**
	 * Draws this object.
	 * @param g The graphics context.
	 */
	public void draw(Graphics2D g)
	{
		g.setFont(getFont());
		g.setColor(getTextColor());
		g.drawString(getText(), getX(), getY() + (int) Math.round(getFontSize() * 0.8));
	}

	/**
	 * @return the text.
	 */
	public abstract String getText();
}
