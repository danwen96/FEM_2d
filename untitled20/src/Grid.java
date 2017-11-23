import java.io.FileNotFoundException;

/**
 * Created by Daniel on 2017-11-09.
 */
public class Grid
{
    Node[] MD;
    Element[] EL;
    double dx;
    double dy;
    Jakobian[] jakobiany;
    GlobalDate globalDate = new GlobalDate();
    Grid() throws FileNotFoundException {
        MD = new Node[globalDate.nh];
        dx = (globalDate.B)/(double)(globalDate.nB-1);
        dy = (globalDate.H)/(double)(globalDate.nH-1);
        for(int i=0;i<globalDate.nH;i++)
        {
            for(int j=0;j<globalDate.nB;j++)
            {
                MD[j+i*globalDate.nB] = new Node(dx*(double)i,dy*(double)j,0);
                if((i==0 || j==0) || (i == (globalDate.nH-1) || j == (globalDate.nB-1)))
                {
                    MD[j+i*globalDate.nB].krawedz = 1;
                }
            }
        }

        EL = new Element[globalDate.ne];

        for(int i=0;i<(globalDate.nH-1);i++) {
            for (int j = 0; j < (globalDate.nB-1); j++) {
                EL[j+i*(globalDate.nB-1)] = new Element(MD[j+i*globalDate.nH],MD[j+(i+1)*globalDate.nH],MD[j+(i+1)*globalDate.nH+1],MD[j+i*globalDate.nH+1]);
            }
        }

        jakobiany = new Jakobian[globalDate.ne];

        for(int i =0;i<globalDate.ne;i++)
            jakobiany[i] = new Jakobian(EL[i]);



    }

    void wyswietlSiatke()
    {
        for(int i=0;i<globalDate.nh;i++)
        {
            System.out.println();
            System.out.print("Node nr "+i+ " ");
            MD[i].wyswietl();
        }

        System.out.println("\nElementy");

        for(int j=0;j<globalDate.ne;j++)
        {
            System.out.println();
            System.out.print("Element nr "+j+" ");
            EL[j].wyswietl();
        }
    }

    void wyswietlJakobiany()
    {
        System.out.println("\nJakobiany:");

        for(int i =0;i<globalDate.ne;i++) {
            System.out.println();
            System.out.println("Dla elementu "+ i );
            jakobiany[i].wyswietlJakobian();
        }
    }

}
