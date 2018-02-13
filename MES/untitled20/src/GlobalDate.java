import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Daniel on 2017-11-09.
 */
public class GlobalDate {
    public double H,B;
    public int nH,nB,nh,ne;

    GlobalDate() throws FileNotFoundException {
        File file = new File("dane.txt");
        Scanner in = new Scanner(file);

        String zdanie = in.nextLine();
        //System.out.println(zdanie);
        this.H = Double.parseDouble(zdanie);

        zdanie = in.nextLine();
        this.B = Double.parseDouble(zdanie);
        //System.out.println(zdanie);

        zdanie = in.nextLine();
        this.nH = Integer.parseInt(zdanie);
        //System.out.println(zdanie);

        zdanie = in.nextLine();
        this.nB = Integer.parseInt(zdanie);
        //System.out.println(zdanie);

        this.nh = this.nH*this.nB;
        this.ne = (this.nH-1)*(this.nB-1);

    }
}
