/**
 * Created by Daniel on 2017-11-09.
 */
public class Element {
    Node[] I = new Node[4];

    Element(Node I1,Node I2,Node I3,Node I4)
    {
        this.I[0] = I1;
        this.I[1] = I2;
        this.I[2] = I3;
        this.I[3] = I4;
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
