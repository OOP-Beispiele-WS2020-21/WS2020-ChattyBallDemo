import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

/**
 * Mit dieser GraphicsApp wird das Konzept des Observer Patterns für die Verarbeitung
 * interner Ereignisse (Events) demonstriert:
 * <p>
 * Instanzen der Klasse ChattyBall bewegen sich über den Bildschirm und informieren
 * registrierte Observer, wenn sie den Bildschirm berühren. ChattyBall-Instanzen sind
 * Observables.
 * <p>
 * ChattyBallApp ist eine GraphicsApp und erstellt eine Instanz der ChattyBall-Klasse. Die
 * App registriert sich als Observer bei dieser Instanz, wird informiert, wenn diese den
 * Bildschirmrand erreicht und ruft als Reaktion darauf die öffentliche Methode "reverse"
 * der ChattyBall-Instanz auf, die deren Bewegungsrichtung umdreht.
 * <p>
 * Im Kontext des Observer Pattern kommuniziert ChattyBall nur mit ChattyBallObserern. Die
 * ChattyBallApp tritt als ein solcher auf und implementiert dazu das entsprechende Interface
 * ChattyBallObserver.
 */

/**
 * Die ChattyBallApp implementiert da ChattyBallObserver-Interface um in dieser Rolle mit
 * einem Chatty-Ball zu kommunizieren.
 */
public class ChattyBallApp extends GraphicsApp implements ChattyBallObserver {

    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = 500;
    private static final Color CANVAS_BACKGROUND_COLOR = Colors.WHITE;
    // ChattyBall-Instanz
    private ChattyBall chattyBall;

    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        // Erzeugen der ChattyBall-Instanz
        chattyBall = new ChattyBall(50, 50);
        /**
         * Registrieren dieser (this) ChattyBallApp-Instanz als Observer beim ChattyBall
         * Die App tritt hier als Instanz des Interface (ChattyBallObserver),
         * nicht als ChattyBallApp-Instanz auf!
         */
        chattyBall.registerObserver(this);
    }

    @Override
    public void draw() {
        drawBackground(CANVAS_BACKGROUND_COLOR);
        // Aktualisieren ...
        chattyBall.update();
        // ... und zeichnen des Kreises
        chattyBall.draw();
    }

    /**
     * Diese Methode wird aufgerufen, wenn der ChattyBall den Bildschirmrand erreicht und daraufhin diese
     * ChattyBallApp, die sich im Vorfeld als Observer registriert hat, informiert. Dazu ruft ChattyBall
     * diese Methode direkt auf (Vgl. ChattyBall.java, Zeile 60).
     */
    @Override
    public void onBorderReached() {
        chattyBall.reverse();
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
