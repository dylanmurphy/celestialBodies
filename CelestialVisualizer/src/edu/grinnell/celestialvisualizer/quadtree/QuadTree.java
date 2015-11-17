package edu.grinnell.celestialvisualizer.quadtree;

import edu.grinnell.celestialvisualizer.physics.Centroid;
//import edu.grinnell.celestialvisualizer.physics.Centroid;
import edu.grinnell.celestialvisualizer.util.BoundingBox;
import edu.grinnell.celestialvisualizer.util.Point;
import edu.grinnell.celestialvisualizer.util.Vector2d;

public class QuadTree {
	
	Node first;
	
	public QuadTree() {
		first = new EmptyNode();
	}
	
	public QuadTree(Node n) {
		first = n;
	}
    
    public boolean lookup(Point pos, BoundingBox bb) {
    	if(first == null)
    		return false;
        return first.lookup(pos, bb);
    }
    
    public Vector2d calculateAcceleration(Point p, BoundingBox bb, double thresh) {
        if(first == null)
        	return new Vector2d(0,0);
        return first.calculateAcceleration(p, bb, thresh);
    }
    
    public void insert(double mass, Point pos, BoundingBox bb) {
        first = first.insert(mass, pos, bb);
    }
    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof QuadTree))
        	return false;
        
        return first.equals(((QuadTree) other).first);
        
    }
    
    public static QuadTree q0() {
        return new QuadTree();
    }
    
    public static QuadTree q1() {
    	return new QuadTree(new LeafNode(1.0, new Point(1.5, 2.5)));
    }

    public static QuadTree q2() {
    	Centroid b1 = new Centroid(1.0, new Point(1.5, 2.5));
    	Centroid b2 = new Centroid(1.0, new Point(2.1, 2.1));

    	Node n = new CentroidNode(
    			b1.add(b2),
    			new EmptyNode(),
    			new EmptyNode(),
    			new LeafNode(1.0, new Point(1.5, 2.5)),
    			new LeafNode(1.0, new Point(2.1, 2.1)));
    	
    	return new QuadTree(n);

    }
    
    public static QuadTree q5() {
    	Centroid b1 = new Centroid(1.0, new Point(1.5, 2.5));
    	Centroid b2 = new Centroid(1.0, new Point(2.1, 2.1));

    	Node n = new CentroidNode(
    			b1.add(b2),
    			new EmptyNode(),
    			new EmptyNode(),
    			new LeafNode(1.0, new Point(1.5, 2.5)),
    			new LeafNode(1.0, new Point(2.1, 2.1)));
    	
    	return new QuadTree(n);

    }
    
    public static QuadTree q3() {
    	Centroid b1 = new Centroid(1.0, new Point(1.5, 2.5));
    	Centroid b2 = new Centroid(1.0, new Point(2.1, 2.1));
    	Centroid b3 = new Centroid(2.0, new Point(1.0, 1.0));

    	Node n = new CentroidNode(
    			b1.add(b2).add(b3),
    			new LeafNode(2.0, new Point(1.0, 1.0)),
    			new EmptyNode(),
    			new LeafNode(1.0, new Point(1.5, 2.5)),
    			new LeafNode(1.0, new Point(2.1, 2.1)));
    	
    	return new QuadTree(n);
    }
    
    public static final QuadTree q4() {
    	Centroid b1 = new Centroid(1.0, new Point(1.5, 2.5));
    	Centroid b2 = new Centroid(1.0, new Point(2.1, 2.1));
    	Centroid b3 = new Centroid(2.0, new Point(1.0, 1.0));
    	Centroid b4 = new Centroid(1.0, new Point(2.6, 2.8));

    	Node n = new CentroidNode(
    			b1.add(b2).add(b3).add(b4),
    			new LeafNode(2.0, new Point(1.0, 1.0)),
    			new EmptyNode(),
    			new LeafNode(1.0, new Point(1.5, 2.5)),
    			new CentroidNode(b2.add(b4),
    					new CentroidNode(b2.add(b4),
    							new LeafNode(1.0, new Point(2.1, 2.1)),
    							new EmptyNode(),
    							new EmptyNode(),
    							new LeafNode(1.0, new Point(2.6, 2.8))
    							),
    					new EmptyNode(),
    					new EmptyNode(),
    					new EmptyNode()
    					));

    	return new QuadTree(n);
    }
}
