import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;

/**
 * Stellt einen Kreis dar, der sich über den Bildschirm bewegt und einen registrierten
 * Observer informiert, wenn der Bildschirmrand erreicht wurde.
 */
public class ChattyBall {

    private static final int RADIUS = 25;
    private static final Color COLOR = Colors.RED;
    private static final int DEFAULT_SPEED = 5;
    // Circle-Instanz zur Darstellung der Kreisfläche (Composition, nicht Inheritance)
    private Circle shape;
    // Aktuelle Geschwindigkeit des Balls auf der x- und y-Achse
    private int xSpeed;
    private int ySpeed;
    /**
     * Variable zum Speichern eines registrierten Observers
     * Normalerweise würde hier eine Liste, z.B. ein Array, an Observern verwaltet werden
     */
    private ChattyBallObserver currentObserver;

    public ChattyBall(int x, int y) {
        shape = new Circle(x, y, RADIUS, COLOR);
        xSpeed = DEFAULT_SPEED;
        ySpeed = DEFAULT_SPEED;
    }

    /**
     * Mit dieser Methode können sich andere Objekte als Observer beim ChattyBall registrieren. Als Parameter
     * wird das Observer-Objekt selbst übergeben, das das entsprechende Interface (ChattyBallObserver) implementiert
     */
    public void registerObserver(ChattyBallObserver observer) {
        // Beim Registrieren des Observers "merken" wir uns diesen, in dem wir das Objekt in der entsprechenden Variable speichern
        currentObserver = observer;
    }

    /**
     * Mit dieser Methode wird die Bewegungsrichtung des Balls umgedreht
     */
    public void reverse() {
        xSpeed = -xSpeed;
        ySpeed = -ySpeed;
    }

    /**
     * Mit dieser Methode wird der Ball gemäß der aktuellen Geschwindigkeit bewegt. Nach der Bewegung wird geprüft, ob der
     * Bildschirmrand erreicht wurde. Falls ja, wird der ggf. vorhande Observer darüber informiert.
     */
    public void update() {
        shape.move(xSpeed, ySpeed);
        // Wenn der Bildschirmrand erreicht wurde ...
        if (doesCollide()) {
            // ... und im Vorfeld ein Observer registriert wurde ...
            if (currentObserver != null) {
                // ... wird dieser durch Aufruf der Interfcae-Methode informiert!
                currentObserver.onBorderReached();
            }
        }
    }

    /**
     * Gibt true zurück, wenn der Kreis in shape mit einem der vier Bildschirmränder kollidiert oder diesen bereits verlassen hat
     */
    private boolean doesCollide() {
        int canvasWidth = GraphicsApp.getApp().getWidth();
        int canvasHeight = GraphicsApp.getApp().getHeight();
        if (shape.getRightBorder() >= canvasWidth) {
            return true;
        }
        if (shape.getLeftBorder() <= 0) {
            return true;
        }
        if (shape.getTopBorder() <= 0) {
            return true;
        }
        if (shape.getBottomBorder() >= canvasHeight) {
            return true;
        }
        return false;
    }

    /**
     * Zeichnen den Kreis in shape an dessen aktueller Position ein
     */
    public void draw() {
        shape.draw();
    }

}
