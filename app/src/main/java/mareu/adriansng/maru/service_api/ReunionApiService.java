package mareu.adriansng.maru.service_api;

import java.util.List;

import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;

public interface ReunionApiService {


    String getNameMeetingRome();

    List<MeetingRoom> getMeetingRoom();

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

    List<Person> getPersonList();
}
