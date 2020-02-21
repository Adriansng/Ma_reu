package mareu.adriansng.maru.event;

import mareu.adriansng.maru.model.Reunion;

public class AddReunionEvent {

    public Reunion addReunion;

    public AddReunionEvent(Reunion reunion) {
        this.addReunion = reunion;
    }
}

