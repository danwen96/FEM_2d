/**
 * Created by Daniel on 2017-11-09.
 */
public class Node {
    double x,y;
    double t = 800;
    int krawedz = 0;
    int globalnyAdres;

    Node(double x,double y,int krawedz,int globalnyAdres)
    {
        this.x = x;
        this.y = y;
        this.krawedz = krawedz;
        this.globalnyAdres = globalnyAdres;
        //this.t =100;
    }

    public void wyswietl()
    {
        System.out.print("x = "+x+" y = "+y+" krawedz = "+krawedz+" temperatura = "+t);
    }

}
