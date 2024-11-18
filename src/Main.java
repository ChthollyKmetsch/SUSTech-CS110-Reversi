import GUI.Frame;

public class Main {
    public static void main(String[] args) {
        Algo app = new Algo();
        app.initiate();
        app.findValidPlace(1);
        app.printValidPlace();
    }
}