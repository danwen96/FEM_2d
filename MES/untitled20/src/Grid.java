import java.io.FileNotFoundException;

/**
 * Created by Daniel on 2017-11-09.
 */
public class Grid
{
    Node[] MD;
    Element[] EL;
    double[][] globalnaMacierzH;
    double[] globalnyWektorP;
    double dx;
    double dy;

    //Macierze[] macierze;
    GlobalDate globalDate = new GlobalDate();
    Grid() throws FileNotFoundException {
        MD = new Node[globalDate.nh];
        dx = (globalDate.B)/(double)(globalDate.nB-1);
        dy = (globalDate.H)/(double)(globalDate.nH-1);
        for(int i=0;i<globalDate.nH;i++)
        {
            for(int j=0;j<globalDate.nB;j++)
            {
                MD[j+i*globalDate.nB] = new Node(dx*(double)i,dy*(double)j,0,j+i*globalDate.nB);
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


        //macierze = new Macierze[globalDate.ne];

        //for(int i =0;i<globalDate.ne;i++)
            //macierze[i] = new Macierze(EL[i]);

        this.globalnaMacierzH = new double[globalDate.nh][globalDate.nh];
        this.globalnyWektorP = new double[globalDate.nh];

        for(int i=0;i<(globalDate.nH);i++)
            for (int j = 0; j < (globalDate.nH); j++)
                globalnaMacierzH[i][j] =0;
        for(int i=0;i<(globalDate.nh);i++)
            globalnyWektorP[i] = 0;

        double Asr = EL[0].k/(EL[0].c*EL[0].ro);
        double dt = Math.pow(globalDate.B/globalDate.nB,2.0)/(0.5*Asr);
        System.out.println("Wyliczony krok czasowy wynosi: "+dt);
        for(int i=0;i<globalDate.ne;i++)
            EL[i].dt = dt;
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
            EL[i].macierze.wyswietlJakobian();
        }
    }

    void wyswietlMacierzeH()
    {
        System.out.println("\nMacierze H:");

        for(int i =0;i<globalDate.ne;i++) {
            System.out.println();
            System.out.println("Dla elementu "+ i );
            EL[i].macierze.wyswietlMacierzH();
        }
    }

    void wyswietlMacierzeC()
    {
        System.out.println("\nMacierze C:");

        for(int i =0;i<globalDate.ne;i++) {
            System.out.println();
            System.out.println("Dla elementu "+ i );
            EL[i].macierze.wyswietlMacierzC();
        }
    }

    void wyswietlWektoryP()
    {
        System.out.println("\nWektoryP");

        for(int i=0;i<globalDate.ne;i++) {
            System.out.println("Element "+i);
            EL[i].macierze.wysietlWektorP();
            System.out.println();
        }
    }
    void wyliczWszystkieElementy()
    {
        for(int i=0;i<(globalDate.nH-1);i++) {
        for (int j = 0; j < (globalDate.nB-1); j++) {
            EL[j+i*(globalDate.nB-1)].macierze.wylicz();
        }
    }
    }
    void przepiszWartosciDoGlobalnychMacierzy()
    {
        for(int i=0;i<(globalDate.nh);i++)
            for (int j = 0; j < (globalDate.nh); j++)
                globalnaMacierzH[i][j] =0;
        for(int i=0;i<(globalDate.nh);i++)
            globalnyWektorP[i] = 0;


        for(int i=0;i<(globalDate.nH-1);i++) {
            for (int j = 0; j < (globalDate.nB-1); j++) {
                for(int k=0;k<4;k++)
                    for(int l=0;l<4;l++) {
                        globalnaMacierzH[EL[j + i * (globalDate.nB - 1)].adresyNodowGlobalne[k]][EL[j + i * (globalDate.nB - 1)].adresyNodowGlobalne[l]] +=
                                EL[j + i * (globalDate.nB - 1)].macierze.macierzH[k][l];

                    }
            }
        }

        for(int i=0;i<(globalDate.nH-1);i++)
            for (int j = 0; j < (globalDate.nB-1); j++)
                for(int k=0;k<4;k++)
                    globalnyWektorP[EL[j + i * (globalDate.nB - 1)].adresyNodowGlobalne[k]] +=
                            EL[j + i * (globalDate.nB - 1)].macierze.wektorP[k];
    }

    void wyswietlGlobalnaMacierzH()
    {
        System.out.println("Globalna macierzJac H");
        for(int i=0;i<globalDate.nh;i++) {
            for (int j = 0; j < globalDate.nh; j++)
                System.out.print(globalnaMacierzH[i][j] + "  ");
            System.out.println();
        }
    }

    void wyswietlGlobalnyWektorP()
    {
        System.out.println("Globalny wektor P");
        for(int i=0;i<globalDate.nh;i++) {
            System.out.print(globalnyWektorP[i] + "  ");
        }
        System.out.println();
    }

    void wyswietlKrawedzie()
    {
        System.out.println("Status krawedzi w nodach");
        for(int i=0;i<globalDate.nH;i++)
        {
            for(int j=0;j<globalDate.nB;j++)
            {
                System.out.print(MD[j+i*globalDate.nB].krawedz+" ");
            }
            System.out.println();
        }
    }

    void wyswietlTemperatury()
    {
        System.out.println("Temperatury w nodach wynoszą:");
        for(int i=0;i<globalDate.nH;i++) {
            for (int j = 0; j < globalDate.nB; j++) {
                System.out.printf("%.3f\n",MD[j + i * globalDate.nB].t);
            }
            //System.out.println();
        }
    }
}
