package edu.grinnell.celestialvisualizer.quadtree;

import edu.grinnell.celestialvisualizer.physics.Body;
import edu.grinnell.celestialvisualizer.physics.Physics;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class LeafNode implements Node {

	private Body b;
	
	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		return b.getPosition().equals(pos);
	}

	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		return Physics.calculateAccelerationOn(b.getPosition(), b.getMass(), p);
	}

	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		return null;
	}

}
