/**
 * Created by Daniel on 2017-11-23.
 */
public class Jakobian {
    public double tab1[][] = new double[4][4];
    public double tab2[][] = new double[4][4];

    public double[][] jacobian = new double[2][2];

    Element element;

    Jakobian(Element element)
    {
        this.element = element;
        for(int i=0;i<2;i++)
            for(int j=0;i<2;i++)
                jacobian[i][j] = 0;

        // dx/dksi
        jacobian[0][0]+= dn1dksi(-1.)*element.I[0].x;
        jacobian[0][0]+= dn2dksi(1.)*element.I[1].x;
        jacobian[0][0]+= dn3dksi(1.)*element.I[2].x;
        jacobian[0][0]+= dn4dksi(-1.)*element.I[3].x;

        //dy/dksi
        jacobian[0][1]+= dn1dksi(-1.)*element.I[0].y;
        jacobian[0][1]+= dn2dksi(-1.)*element.I[1].y;
        jacobian[0][1]+= dn3dksi(1.)*element.I[2].y;
        jacobian[0][1]+= dn4dksi(1.)*element.I[3].y;

        //dx/deta
        jacobian[1][0]+= dn1deta(-1.)*element.I[0].x;
        jacobian[1][0]+= dn2deta(1.)*element.I[1].x;
        jacobian[1][0]+= dn3deta(1.)*element.I[2].x;
        jacobian[1][0]+= dn4deta(-1.)*element.I[3].x;

        //dy/deta
        jacobian[1][1]+= dn1deta(-1.)*element.I[0].y;
        jacobian[1][1]+= dn2deta(-1.)*element.I[1].y;
        jacobian[1][1]+= dn3deta(1.)*element.I[2].y;
        jacobian[1][1]+= dn4deta(1.)*element.I[3].y;

    }


    double dn1dksi(double eta){ return -0.25*(1-eta);}
    double dn2dksi(double eta){ return 0.25*(1-eta);}
    double dn3dksi(double eta){ return 0.25*(1+eta);}
    double dn4dksi(double eta){ return -0.25*(1+eta);}

    double dn1deta(double ksi){ return -0.25*(1-ksi);}
    double dn2deta(double ksi){ return -0.25*(1+ksi);}
    double dn3deta(double ksi){ return 0.25*(1+ksi);}
    double dn4deta(double ksi){ return 0.25*(1-ksi);}

    void wyswietlJakobian()
    {
        for(int i =0;i<2;i++) {
            for (int j = 0; j < 2; j++)
                System.out.print(jacobian[i][j]+"  ");
            System.out.println();
        }
    }

}
