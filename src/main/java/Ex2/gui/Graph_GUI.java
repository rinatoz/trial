package gui;

import algorithms.Graph_Algo;
import dataStructure.*;
import utils.Point3D;
import utils.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class Graph_GUI extends Graph_Algo
{
	
    graph gA;


    public Graph_GUI()
    {
      gA=new DGraph();
    }
    public Graph_GUI(graph g)
    {
      gA=g;
    }
    public void draw(int width,int height)
    {
        StdDraw.setCanvasSize(width,height);
        StdDraw.setXscale(0,100);
        StdDraw.setYscale(0,100);
    	if (isConnected()) 
    	{
    		StdDraw.text(46,92,"This Graph is Connected");
    	}
    	else
    	{
    		StdDraw.text(46,92,"This Graph isn't Connected");
    	}
        StdDraw.setPenRadius(0.01);
        Collection <node_data> nodes=this.gA.getV();
        Iterator<node_data> it=nodes.iterator();
        while (it.hasNext()){
        	node_data n=it.next();
        	try
        	{
            Collection<edge_data> myE =this.gA.getE(n.getKey());
            Iterator<edge_data> it2=myE.iterator();
            StdDraw.setPenColor(Color.blue);
            StdDraw.setPenRadius(0.02);
            StdDraw.point(n.getLocation().x(),n.getLocation().y());
            String s="";
            s=s+n.getKey();
            StdDraw.text(n.getLocation().x(),n.getLocation().y()-2,s);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.004);
            while (it2.hasNext()) 
            {            	
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(0.004);
            	edge_data e=it2.next();
            	if (e.getInfo().equals("path"))
            	{
            		 StdDraw.setPenColor(StdDraw.GREEN);
                     StdDraw.setPenRadius(0.004);
            	}
            	if (e.getInfo().equals("TSP"))
            	{
            		 StdDraw.setPenColor(StdDraw.BLACK);
                     StdDraw.setPenRadius(0.004);
            	}
                double x0 = n.getLocation().x();
                double y0 = n.getLocation().y();
                double y1 = this.gA.getNode(e.getDest()).getLocation().y();
                double x1 = this.gA.getNode(e.getDest()).getLocation().x();
                double centerX= (x0+x1)/2;
                double centerY=(y0+y1)/2;
                String weight="";
                weight=weight+e.getWeight();
                StdDraw.text(centerX+0.5,centerY+0.5,weight);
                StdDraw.line(x0,y0,x1,y1);
                double dX = x1-x0;
                double dY = y1-y0;
                double m = dY/dX;
                double stepX=0.07*(x1-x0);
                StdDraw.setPenColor(StdDraw.YELLOW);
                StdDraw.setPenRadius(0.015);
                StdDraw.point(x1-stepX,-m*stepX+y1);

            }
            
        	}
        	catch (Exception e) 
        	{
                StdDraw.setPenColor(Color.blue);
                StdDraw.setPenRadius(0.02);
                StdDraw.point(n.getLocation().x(),n.getLocation().y());
                String s="";
                s=s+n.getKey();
                StdDraw.text(n.getLocation().x(),n.getLocation().y()-2,s);
        	}

        }
    }
    public void saveaspicture (int width,int height)
    {
    	this.draw(width, height);
    	StdDraw.save("Draw_functions.jpg"); //save the draw as a jpg file
    }


}