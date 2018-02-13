/**
 * Created by Daniel on 2017-11-23.
 */
public class Macierze {

    public double[][] macierzJac = new double[2][2];
    public double[][] odwroconaMacierz = new double[2][2];
    public double[] punktyCalkowania = new double[2];
    public double jakobian;
    public double[][] pochodneNx  =new double[4][4];//wektory z pochodnymi N po x  i N po y
    public double[][] pochodneNy  =new double[4][4];
    public double[][] macierzH = new double[4][4];
    public double[][] macierzC = new double[4][4];
    double wektorP[] = new double[4];
    //public double alfa = 300;
    public double[][] wektoryN = new double[4][4];
    public double[] punktyCalkowaniax = new double[4];
    public double[] punktyCalkowaniay = new double[4];
    public double[][] punktyCalkowaniaPowierzchnix = new double [4][2];
    public double[][] punktyCalkowaniaPowierzchniy = new double [4][2];
    public double[][][] wektoryNpowierzchni = new double[4][2][4];
    public double[] temperaturyPoczatkowe = new double[4];
    double tempPoczatkowaWpktCalk, wartosciWmacierzyC;


    Element element;

    Macierze(Element element) {
//        for (int i = 0; i < 4; i++)
//            t0[i] = element.I[i].t;

        punktyCalkowania[0] = -0.577;
        punktyCalkowania[1] = 0.577;

        punktyCalkowaniax[0] = -0.577;
        punktyCalkowaniax[1] = 0.577;
        punktyCalkowaniax[2] = 0.577;
        punktyCalkowaniax[3] = -0.577;

        punktyCalkowaniay[0] = -0.577;
        punktyCalkowaniay[1] = -0.577;
        punktyCalkowaniay[2] = 0.577;
        punktyCalkowaniay[3] = 0.577;

//        punktyCalkowaniaPowierzchnix[0][0] = -0.577;
//        punktyCalkowaniaPowierzchniy[0][0] = -1;
//        punktyCalkowaniaPowierzchnix[0][1] = 0.577;
//        punktyCalkowaniaPowierzchniy[0][1] = -1;
//        punktyCalkowaniaPowierzchnix[1][0] = 1;
//        punktyCalkowaniaPowierzchniy[1][0] = -0.577;
//        punktyCalkowaniaPowierzchnix[1][1] = 1;
//        punktyCalkowaniaPowierzchniy[1][1] = 0.577;
//        punktyCalkowaniaPowierzchnix[2][0] = 0.577;
//        punktyCalkowaniaPowierzchniy[2][0] = 1;
//        punktyCalkowaniaPowierzchnix[2][1] = -0.577;
//        punktyCalkowaniaPowierzchniy[2][1] = 1;
//        punktyCalkowaniaPowierzchnix[3][0] = -1;
//        punktyCalkowaniaPowierzchniy[3][0] = 0.577;
//        punktyCalkowaniaPowierzchnix[3][1] = -1;
//        punktyCalkowaniaPowierzchniy[3][1] = -0.577;


        punktyCalkowaniaPowierzchnix[0][0] = -1;
        punktyCalkowaniaPowierzchniy[0][0] = 0.577;
        punktyCalkowaniaPowierzchnix[0][1] = -1;
        punktyCalkowaniaPowierzchniy[0][1] = -0.577;
        punktyCalkowaniaPowierzchnix[1][0] = -0.577;
        punktyCalkowaniaPowierzchniy[1][0] = -1;
        punktyCalkowaniaPowierzchnix[1][1] = 0.577;
        punktyCalkowaniaPowierzchniy[1][1] = -1;
        punktyCalkowaniaPowierzchnix[2][0] = 1;
        punktyCalkowaniaPowierzchniy[2][0] = -0.577;
        punktyCalkowaniaPowierzchnix[2][1] = 1;
        punktyCalkowaniaPowierzchniy[2][1] = 0.577;
        punktyCalkowaniaPowierzchnix[3][0] = 0.577;
        punktyCalkowaniaPowierzchniy[3][0] = 1;
        punktyCalkowaniaPowierzchnix[3][1] = -0.577;
        punktyCalkowaniaPowierzchniy[3][1] = 1;


        this.element = element;
        for (int i = 0; i < 2; i++)
            for (int j = 0; i < 2; i++)
                macierzJac[i][j] = 0;

        // dx/dksi
        macierzJac[0][0] += dn1dksi(-1.) * element.I[0].x;
        macierzJac[0][0] += dn2dksi(1.) * element.I[1].x;
        macierzJac[0][0] += dn3dksi(1.) * element.I[2].x;
        macierzJac[0][0] += dn4dksi(-1.) * element.I[3].x;

        //dy/dksi
        macierzJac[0][1] += dn1dksi(-1.) * element.I[0].y;
        macierzJac[0][1] += dn2dksi(-1.) * element.I[1].y;
        macierzJac[0][1] += dn3dksi(1.) * element.I[2].y;
        macierzJac[0][1] += dn4dksi(1.) * element.I[3].y;

        //dx/deta
        macierzJac[1][0] += dn1deta(-1.) * element.I[0].x;
        macierzJac[1][0] += dn2deta(1.) * element.I[1].x;
        macierzJac[1][0] += dn3deta(1.) * element.I[2].x;
        macierzJac[1][0] += dn4deta(-1.) * element.I[3].x;

        //dy/deta
        macierzJac[1][1] += dn1deta(-1.) * element.I[0].y;
        macierzJac[1][1] += dn2deta(-1.) * element.I[1].y;
        macierzJac[1][1] += dn3deta(1.) * element.I[2].y;
        macierzJac[1][1] += dn4deta(1.) * element.I[3].y;


        jakobian = 0;
        jakobian = macierzJac[0][0] * macierzJac[1][1] - macierzJac[0][1] * macierzJac[1][0];

        odwroconaMacierz[0][0] = macierzJac[1][1];
        odwroconaMacierz[1][1] = macierzJac[0][0];
        odwroconaMacierz[0][1] = -macierzJac[0][1];
        odwroconaMacierz[1][0] = -macierzJac[1][0];

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                odwroconaMacierz[i][j] = odwroconaMacierz[i][j] / jakobian;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
            pochodneNx[i][j] = 0;
            pochodneNy[i][j] = 0;
        }

        for (int ip = 0; ip < 4; ip++)
        {
            pochodneNx[ip][0] = odwroconaMacierz[0][0] * dn1dksi(punktyCalkowaniax[ip]) + odwroconaMacierz[0][1] * dn1deta(punktyCalkowaniay[ip]);
            pochodneNy[ip][0] = odwroconaMacierz[1][0] * dn1dksi(punktyCalkowaniax[ip]) + odwroconaMacierz[1][1] * dn1deta(punktyCalkowaniay[ip]);
            pochodneNx[ip][1] = odwroconaMacierz[0][0] * dn2dksi(punktyCalkowaniax[ip]) + odwroconaMacierz[0][1] * dn2deta(punktyCalkowaniay[ip]);
            pochodneNy[ip][1] = odwroconaMacierz[1][0] * dn2dksi(punktyCalkowaniax[ip]) + odwroconaMacierz[1][1] * dn2deta(punktyCalkowaniay[ip]);
            pochodneNx[ip][2] = odwroconaMacierz[0][0] * dn3dksi(punktyCalkowaniax[ip]) + odwroconaMacierz[0][1] * dn3deta(punktyCalkowaniay[ip]);
            pochodneNy[ip][2] = odwroconaMacierz[1][0] * dn3dksi(punktyCalkowaniax[ip]) + odwroconaMacierz[1][1] * dn3deta(punktyCalkowaniay[ip]);
            pochodneNx[ip][3] = odwroconaMacierz[0][0] * dn4dksi(punktyCalkowaniax[ip]) + odwroconaMacierz[0][1] * dn4deta(punktyCalkowaniay[ip]);
            pochodneNy[ip][3] = odwroconaMacierz[1][0] * dn4dksi(punktyCalkowaniax[ip]) + odwroconaMacierz[1][1] * dn4deta(punktyCalkowaniay[ip]);
        }

        for (int i = 0; i < 4; i++) {
            wektoryN[i][0] = n1(punktyCalkowaniax[i], punktyCalkowaniay[i]);
            wektoryN[i][1] = n2(punktyCalkowaniax[i], punktyCalkowaniay[i]);
            wektoryN[i][2] = n3(punktyCalkowaniax[i], punktyCalkowaniay[i]);
            wektoryN[i][3] = n4(punktyCalkowaniax[i], punktyCalkowaniay[i]);
        }
        for (int i=0;i<4;i++)
            for (int j = 0; j < 4; j++)
                for (int m = 0; j < 2; j++) {
                    wektoryNpowierzchni[i][m][0] = n1(punktyCalkowaniaPowierzchnix[i][m], punktyCalkowaniaPowierzchniy[i][m]);
                    wektoryNpowierzchni[i][m][1] = n2(punktyCalkowaniaPowierzchnix[i][m], punktyCalkowaniaPowierzchniy[i][m]);
                    wektoryNpowierzchni[i][m][2] = n3(punktyCalkowaniaPowierzchnix[i][m], punktyCalkowaniaPowierzchniy[i][m]);
                    wektoryNpowierzchni[i][m][3] = n4(punktyCalkowaniaPowierzchnix[i][m], punktyCalkowaniaPowierzchniy[i][m]);
            }
    }


    void wylicz()
    {
        temperaturyPoczatkowe[0] = element.I[0].t;
        temperaturyPoczatkowe[1] = element.I[1].t;
        temperaturyPoczatkowe[2] = element.I[2].t;
        temperaturyPoczatkowe[3] = element.I[3].t;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                macierzH[i][j] = 0;
                macierzC[i][j] = 0;
            }
            wektorP[i] = 0;
        }

        for(int ipkt =0;ipkt<4;ipkt++){
            tempPoczatkowaWpktCalk = 0;

            for (int j = 0; j < 4; j++)
                tempPoczatkowaWpktCalk += temperaturyPoczatkowe[j] * wektoryN[ipkt][j];

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    wartosciWmacierzyC = element.c * element.ro * wektoryN[ipkt][i] * wektoryN[ipkt][j] * jakobian;
                    macierzH[i][j] += element.k * (pochodneNx[ipkt][i] * pochodneNx[ipkt][j] + pochodneNy[ipkt][i] * pochodneNy[ipkt][j]) * jakobian + wartosciWmacierzyC / element.dt;
                    wektorP[i] += wartosciWmacierzyC / element.dt * tempPoczatkowaWpktCalk;
                }
            }
        }

        double dlugoscKrawedzi;

        if((element.I[0].krawedz == 1) && (element.I[3].krawedz==1))
        {
            dlugoscKrawedzi = Math.sqrt(Math.pow(element.I[0].x - element.I[3].x,2) +Math.pow((element.I[0].y - element.I[3].y),2));
            for(int m=0;m<2;m++)
            for(int i=0;i<4;i++) {
                    for (int j = 0; j < 4; j++)
                        macierzH[i][j] += element.alfa*wektoryNpowierzchni[0][m][i]*wektoryNpowierzchni[0][m][j]*dlugoscKrawedzi;
                    wektorP[i] += dlugoscKrawedzi * element.alfa * element.totoczenia * wektoryNpowierzchni[0][m][i];

            }
        }
        for(int k=0;k<3;k++)
        {
            dlugoscKrawedzi = Math.sqrt(Math.pow(element.I[k].x - element.I[k+1].x,2) +Math.pow((element.I[k].y - element.I[k+1].y),2));
            if((element.I[k].krawedz == 1) && (element.I[k+1].krawedz==1)) {
                for (int m = 0; m < 2; m++)
                for(int i=0;i<4;i++) {
                     {
                        for (int j = 0; j < 4; j++)
                            macierzH[i][j] += element.alfa * wektoryNpowierzchni[k+1][m][i] * wektoryNpowierzchni[k+1][m][j] * dlugoscKrawedzi;
                        wektorP[i] += dlugoscKrawedzi * element.alfa * element.totoczenia * wektoryNpowierzchni[k + 1][m][i];
                    }
                }
            }
        }
    }

    double dn1dksi(double eta){ return -0.25*(1-eta);}
    double dn2dksi(double eta){ return 0.25*(1-eta);}
    double dn3dksi(double eta){ return 0.25*(1+eta);}
    double dn4dksi(double eta){ return -0.25*(1+eta);}

    double dn1deta(double ksi){ return -0.25*(1-ksi);}
    double dn2deta(double ksi){ return -0.25*(1+ksi);}
    double dn3deta(double ksi){ return 0.25*(1+ksi);}
    double dn4deta(double ksi){ return 0.25*(1-ksi);}

    double n1(double ksi,double eta){return 0.25*(1-ksi)*(1-eta);}
    double n2(double ksi,double eta){return 0.25*(1+ksi)*(1-eta);}
    double n3(double ksi,double eta){return 0.25*(1+ksi)*(1+eta);}
    double n4(double ksi,double eta){return 0.25*(1-ksi)*(1+eta);}

    void wyswietlJakobian()
    {
        for(int i =0;i<2;i++) {
            for (int j = 0; j < 2; j++)
                System.out.print(macierzJac[i][j]+"  ");
            System.out.println();
        }
        System.out.println("Wyznacznik "+jakobian);
    }

    void wyswietlMacierzH()
    {
        System.out.println();
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++)
            {
                System.out.print(macierzH[i][j]+" ");
            }
            System.out.println();
        }
    }

    void wyswietlMacierzC()
    {
        System.out.println();
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++)
            {
                System.out.print(macierzC[i][j]+" ");
            }
            System.out.println();
        }
    }

    void wysietlWektorP()
    {
        for(int i =0;i<4;i++)
            System.out.println(wektorP[i]);
    }
}
