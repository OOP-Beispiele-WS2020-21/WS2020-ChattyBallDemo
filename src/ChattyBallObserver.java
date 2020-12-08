/**
 * Dieses Interface wird von allen Klassen implementiert, deren Instanzen als Observer
 * mit ChattyBall kommunizieren wollen. Es definiert die Schnittstelle, über die diese
 * Observer über das Erreichen des Randes der Zeichenfläche informiert werden.
 */
public interface ChattyBallObserver {

    /**
     * Wird von allen Observern implementiert und von ChattyBall aufgerufen, wenn der
     * Bildschirmrand erreicht wird.
     */
    void onBorderReached();

}
