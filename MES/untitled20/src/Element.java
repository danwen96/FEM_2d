/**
 * Created by Daniel on 2017-11-09.
 */
public class Element {
    Node[] I = new Node[4];
    double dlugoscKrawedzi;
    Macierze macierze;
    double k=58;//dane dla stali
    double c= 460;
    double ro = 7800;
    double totoczenia = 20;
    double dt = 50;
    double tau = 1000;
    double alfa= 500;
    int[] adresyNodowGlobalne = new int[4];
    int[] a_pow;
    int n_pow;

    Element(Node I1,Node I2,Node I3,Node I4)
    {
        this.I[0] = I1;
        this.I[1] = I2;
        this.I[2] = I3;
        this.I[3] = I4;
        this.adresyNodowGlobalne[0] = I1.globalnyAdres;
        this.adresyNodowGlobalne[1] = I2.globalnyAdres;
        this.adresyNodowGlobalne[2] = I3.globalnyAdres;
        this.adresyNodowGlobalne[3] = I4.globalnyAdres;


        if(this.I[0].y < 0.05)
        {
            alfa = 500;
            totoczenia = 20;
        }
        else
        {
            alfa = 30;
            totoczenia = 20;
        }

        //dlugoscKrawedzi = Math.abs(this.I[1].x - this.I[0].x);
//        powierzchnie = new Powierzchnia[4];
//        powierzchnie[0] = new Powierzchnia(-1,0.577,-1,-0.577);
//        powierzchnie[1] = new Powierzchnia(-0.577,-1,0.577,-1);
//        powierzchnie[2] = new Powierzchnia(1,-0.577,1,0.577);
//        powierzchnie[3] = new Powierzchnia(0.577,1,-0.577,1);

        this.macierze = new Macierze(this);
    }

    void wyswietl()
    {
        System.out.println("Element sklada sie z nodow: ");
        for(int i=0;i<4;i++) {
            I[i].wyswietl();
            System.out.println();
        }

    }

}
