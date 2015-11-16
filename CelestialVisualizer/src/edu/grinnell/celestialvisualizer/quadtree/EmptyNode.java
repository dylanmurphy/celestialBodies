package edu.grinnell.celestialvisualizer.quadtree;

import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class EmptyNode implements Node{

	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		return false;
	}

	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		return Vector2d.zero;
	}

	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		return new LeafNode;
	}

}
