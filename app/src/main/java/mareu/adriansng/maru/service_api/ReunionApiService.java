package mareu.adriansng.maru.service_api;

import java.util.List;

public interface ReunionApiService {

    /**
     * Get all reunion
     * @return {@link List}
     */
    List<Reunion> getReunions();

    /**
     * Deletes a reunion
     * @param reunion
     */
    void deleteReunion(Reunion reunion);

    /**
     * get a single neighbour by id
     *@param id
     */
    Reunion getReunion(int id);


    //Generate Reunion

    void addReunion(Reunion addNewReunion);
}
