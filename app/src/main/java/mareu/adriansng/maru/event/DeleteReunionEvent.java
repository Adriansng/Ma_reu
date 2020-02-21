package mareu.adriansng.maru.event;

import mareu.adriansng.maru.model.Reunion;

public class DeleteReunionEvent {
    /**
     * Reunion to delete
     */
    public Reunion reunion;

    /**
     * Constructor.
     *
     * @param reunion
     */
    public DeleteReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }

}
