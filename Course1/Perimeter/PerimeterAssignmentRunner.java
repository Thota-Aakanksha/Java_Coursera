import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPoints=0;
        for(Point p:s.getPoints())
            numPoints++;
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        double currDist;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            currDist = prevPt.distance(currPt);
            largestSide=Math.max(currDist,largestSide);
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX =Double.NEGATIVE_INFINITY;
        double currX;
        for (Point currPt : s.getPoints()) {
            currX = currPt.getX();
            largestX=Math.max(currX,largestX);
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter=0;
        double currentPerimeter;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr=new FileResource(f);
            Shape s=new Shape(fr);
            currentPerimeter= getPerimeter(s);
            largestPerimeter=Math.max(currentPerimeter,largestPerimeter);

        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        double largestPerimeter=0;
        double currentPerimeter;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            currentPerimeter = getPerimeter(s);
            if (currentPerimeter > largestPerimeter){
                largestPerimeter = currentPerimeter;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        int numPoints=getNumPoints(s);
        System.out.println("number of points = "+ numPoints);
        double averageLength=getAverageLength(s);
        System.out.println("average length = "+averageLength);
        double largestSide=getLargestSide(s);
        System.out.println("largest side = "+largestSide);
        double largestX=getLargestX(s);
        System.out.println("largest x coordinate = "+largestX);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter=getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = "+largestPerimeter);// Put code here
    }

    public void testFileWithLargestPerimeter() {
        String largestPerimeterFile=getFileWithLargestPerimeter();
        System.out.println("largest perimeter file = "+largestPerimeterFile);// Put code here
    }

    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
