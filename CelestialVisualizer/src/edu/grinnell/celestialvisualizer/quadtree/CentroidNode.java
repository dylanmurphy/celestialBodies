package edu.grinnell.celestialvisualizer.quadtree;

import edu.grinnell.celestialvisualizer.physics.Centroid;
import edu.grinnell.celestialvisualizer.physics.Physics;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class CentroidNode implements Node {
	
	Node node1;
	Node node2;
	Node node3;
	Node node4;
	
	Centroid c;
	
	public CentroidNode(node1, node2, node3, node4) {
		
	}

	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		return node1.lookup(pos, bb) || node2.lookup(pos, bb) || node3.lookup(pos, bb) || node4.lookup(pos, bb);
	}

	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		return Physics.calculateAccelerationOn(c.getPosition(), c.getMass(), p);
	}

	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		return null;
	}

}
