package gridwhack;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gridwhack.entity.unit.Player;
import gridwhack.grid.GridUnit.Directions;

/**
 * GridWhack keyboard handler class file.
 * @author Christoffer Niska <ChristofferNiska@gmail.com>
 */
public class KeyboardHandler extends KeyAdapter
{
	private GridWhack game;
	
	/**
	 * Creates the keyboard handler.
	 * @param game the game.
	 */
	public KeyboardHandler(GridWhack game)
	{
		this.game = game;
	}
	
	/**
	 * Actions to be taken when a key is pressed.
	 * @param e the key event.
	 */
	public void keyPressed(KeyEvent e)
	{
		Player player = game.getPlayer();
		
		switch( e.getKeyCode() )
		{
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_NUMPAD4:
				player.move(Directions.LEFT);
				break;
				
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_NUMPAD6:
				player.move(Directions.RIGHT);
				break;
			
			case KeyEvent.VK_UP:
			case KeyEvent.VK_NUMPAD8:
				player.move(Directions.UP);
				break;
				
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_NUMPAD2:
				player.move(Directions.DOWN);
				break;
				
			default:
		}
		
		e.consume();
	}
	
	/**
	 * Actions to be taken when a key is released.
	 * @param e the key event.
	 */
	public void keyReleased(KeyEvent e)
	{
		e.consume();
	}
	
	/**
	 * Actions to be taken when a key is typed.
	 * @param e the key event.
	 */
	public void keyTyped(KeyEvent e)
	{
		e.consume();
	}
}
