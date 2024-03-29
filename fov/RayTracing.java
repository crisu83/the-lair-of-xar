package gridwhack.fov;

import gridwhack.gameobject.grid.Grid;

/**
 * Ray tracing field of view class.
 * @author Christoffer Niska <ChristofferNiska@gmail.com>
 */
public class RayTracing extends GridFov
{
	/**
	 * Creates the field of view.
	 * @param viewer The owner of this field of view.
	 * @param grid The grid the viewer belongs to.
	 */
	public RayTracing(int radius, Grid grid, IViewer viewer)
	{
		super(radius, grid, viewer);
	}

	/**
	 * Updates the field of view.
	 * @param cx The current x-coordinate.
	 * @param cy The current y-coordinate.
	 */
	public void refresh(int cx, int cy)
	{
		init();

		for (int x = 0, width = getWidth(); x < width; x++)
		{
			for (int y = 0, height = getHeight(); y < height; y++)
			{
				if ((x == 0 || y == 0) || (x == width-1 || y == height-1))
				{
					castRay(cx, cy, x, y, getRadius());
				}
			}
		}
	}

	/**
	 * Raytracing method that uses an integer only version
	 * of "Bresenham line-drawing algorithm" to determine
	 * if the target can be seen from a specific position.
	 * @param sx the starting x-coordinate.
	 * @param sy the starting y-coordinate.
	 * @param tx the target x-coordinate.
	 * @param ty the target y-coordinate.
	 * @param range the view range in grid cells.
	 */
	public void castRay(int sx, int sy, int tx, int ty, int range)
	{
		// Calculate the deltas.
		int dx = Math.abs(tx - sx);
		int dy = Math.abs(ty - sy);

		int x = sx;
		int y = sy;

		// Calculate how many moves are required to reach the target.
		int n = 1 + dx + dy;

		// Calculate x- and y-increments.
		int xi = (tx > sx) ? 1 : -1;
		int yi = (ty > sy) ? 1 : -1;

		// Determine in which direction to set off.
		int error = dx - dy;

		dx *= 2;
		dy *= 2;

		Grid grid = getGrid();

		IViewer viewer = getViewer();

		// We need to remember where we started in order to check the range.
		int start = n;

		// We have not hit anything solid yet.
		boolean solid = false;

		// Loop while we have not hit anything solid.
		while (!solid && n > 0)
		{
			// Check whether the ray hit something solid.
			if (grid.isSolid(x, y, viewer))
			{
				solid = true;
			}

			// Mark grid cell within the range as visible.
			if (!visible[x][y] && n >= (start - range))
			{
				visible[x][y] = true;
			}

			// Add visible fields to the complete field of view if necessary.
			if (!complete[x][y])
			{
				complete[x][y] = true;
			}

			// Move horizontally.
			if (error > 0)
			{
				x += xi;
				error -= dy;
			}
			// Move vertically.
			else if (error < 0)
			{
				y += yi;
				error += dx;
			}
			// Line is passing through both a vertical and horizontal line.
			else
			{
				x += xi;
				error -= dy;
				y += yi;
				error += dx;
				n--;
			}

			n--;
		}
	}
}
