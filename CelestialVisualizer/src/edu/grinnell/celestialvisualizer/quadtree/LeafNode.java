package edu.grinnell.celestialvisualizer.quadtree;

import edu.grinnell.celestialvisualizer.physics.Centroid;
import edu.grinnell.celestialvisualizer.physics.Physics;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class LeafNode implements Node {
	
	public LeafNode(double m, Point p) {
		this.position = p;
		this.mass = m;
	}

	public Point position;
	public double mass;
	
	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		return position.equals(pos);
	}

	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		return Physics.calculateAccelerationOn(position, mass, p);
	}

	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		Node ret = new CentroidNode(
				new Centroid(0, new Point(0, 0)),
				new EmptyNode(),
				new EmptyNode(),
				new EmptyNode(),
				new EmptyNode());
		ret = ret.insert(mass, p, bb);
		ret = ret.insert(this.mass, this.position, bb);
		return ret;
	}
	
	public boolean equals(Object o) {
		if(o instanceof LeafNode) {
			return ((LeafNode) o).position.equals(this.position) && 
				   ((LeafNode) o).mass == this.mass;
		}
		return false;
	}
	
}
