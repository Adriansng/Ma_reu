package mareu.adriansng.maru.service_api;

import java.util.List;

import mareu.adriansng.maru.model.Reunion;

public class DummyReunionApiService implements ReunionApiService {

    @Override
    public List<Reunion> getReunions() {
        return null;
    }

    @Override
    public void deleteReunion(Reunion reunion) {

    }

    @Override
    public Reunion getReunion(int id) {
        return null;
    }

    @Override
    public void addReunion(Reunion addNewReunion) {

    }
}
