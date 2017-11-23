/**
 * Created by Daniel on 2017-11-09.
 */
public class Node {
    double x,y;
    double t;
    int krawedz = 0;

    Node(double x,double y,int krawedz)
    {
        this.x = x;
        this.y = y;
        this.krawedz = krawedz;
    }

    public void wyswietl()
    {
        System.out.print("x = "+x+" y = "+y+" krawedz = "+krawedz);
    }

}
