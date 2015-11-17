package edu.grinnell.celestialvisualizer.quadtree;

import edu.grinnell.celestialvisualizer.physics.Centroid;
import edu.grinnell.celestialvisualizer.physics.Physics;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Quadrant;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class CentroidNode implements Node {
	
	Node node1;
	Node node2;
	Node node3;
	Node node4;
	
	Centroid c;
	
	public CentroidNode(Centroid c, Node node1, Node node2, Node node3, Node node4) {
		this.node1 = node1;
		this.node2 = node2;
		this.node3 = node3;
		this.node4 = node4;
		
		this.c = c;
	}

	@Override
	public boolean lookup(Point pos, BoundingBox bb) {
		return node1.lookup(pos, bb)
				|| node2.lookup(pos, bb)
				|| node3.lookup(pos, bb)
				|| node4.lookup(pos, bb);
	}

	@Override
	public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
		if(bb.contains(p) || p.distance(c.getPosition()).magnitude() < thresh) {
			Vector2d v =  node1.calculateAcceleration(p, bb.getQuadrant(Quadrant.UPPER_LEFT), thresh)
					.add(node2.calculateAcceleration(p, bb.getQuadrant(Quadrant.UPPER_RIGHT), thresh))
					.add(node3.calculateAcceleration(p, bb.getQuadrant(Quadrant.LOWER_LEFT), thresh))
					.add(node4.calculateAcceleration(p, bb.getQuadrant(Quadrant.LOWER_RIGHT), thresh));
			return v;
		}
		return Physics.calculateAccelerationOn(c.getPosition(), c.getMass(), p);
	}

	@Override
	public Node insert(double mass, Point p, BoundingBox bb) {
		Centroid c = this.c.add(new Centroid(mass, p));
		CentroidNode ret = new CentroidNode(c, node1, node2, node3, node4);
		if (bb.quadrantOf(p) == Quadrant.UPPER_LEFT) {
			ret.node1 = ret.node1.insert(mass, p, bb.getQuadrant(Quadrant.UPPER_LEFT));
			return ret;
		} else if (bb.quadrantOf(p) == Quadrant.UPPER_RIGHT) {
			ret.node2 = ret.node2.insert(mass, p, bb.getQuadrant(Quadrant.UPPER_RIGHT));
			return ret;
		} else if (bb.quadrantOf(p) == Quadrant.LOWER_LEFT) {
			ret.node3 = ret.node3.insert(mass, p, bb.getQuadrant(Quadrant.LOWER_LEFT));
			return ret;
		} else {
			ret.node4 = ret.node4.insert(mass, p, bb.getQuadrant(Quadrant.LOWER_RIGHT));
			return ret;
		}
	}

	public boolean equals(Object o) {
		if(o instanceof CentroidNode) {
			return (((CentroidNode) o).node1.equals(node1) &&
					((CentroidNode) o).node2.equals(node2) &&
					((CentroidNode) o).node3.equals(node3) &&
					((CentroidNode) o).node4.equals(node4));
		}
		return false;
	}

}
