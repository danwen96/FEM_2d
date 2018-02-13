import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World!");

        Grid grid = new Grid();
        GaussianElimination gaussianElimination = new GaussianElimination();
        //grid.obliczOstateczneMacierzeWElementach();
        //grid.wyswietlSiatke();
        //grid.wyswietlJakobiany();
//        grid.wyswietlMacierzeH();
//        grid.wyswietlMacierzeC();
//        grid.wyswietlWektoryP();
//        grid.wyliczWszystkieElementy();
//        grid.przepiszWartosciDoGlobalnychMacierzy();
//        grid.wyswietlGlobalnaMacierzH();
//        grid.wyswietlGlobalnyWektorP();
        double[] temperatury;

        grid.wyswietlTemperatury();

        for(double i=0;i<grid.EL[0].tau; i = i + grid.EL[0].dt)
        {
            grid.wyliczWszystkieElementy();
            grid.przepiszWartosciDoGlobalnychMacierzy();

           //grid.wyswietlGlobalnaMacierzH();
           //grid.wyswietlGlobalnyWektorP();
           //System.out.println();

           System.out.println();
            temperatury = gaussianElimination.lsolve(grid.globalnaMacierzH,grid.globalnyWektorP);
            //temperatury = UkladyRownanLiniowych.gaussElimination(grid.globalDate.nh,grid.globalnaMacierzH,grid.globalnyWektorP);
            //grid = new Grid();
            for(int j=0;j<grid.globalDate.nh;j++)
                grid.MD[j].t = temperatury[j];
            System.out.println("Templeratury dla czasu "+i);
            grid.wyswietlTemperatury();

        }

        //grid.wyswietlGlobalnaMacierzH();
        //grid.wyswietlGlobalnyWektorP();
        //System.out.println("\n\n");
        //grid.wyswietlTemperatury();
    }
}
