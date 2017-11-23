import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World!");

        Grid grid = new Grid();
        grid.wyswietlSiatke();
        grid.wyswietlJakobiany();
    }
}
