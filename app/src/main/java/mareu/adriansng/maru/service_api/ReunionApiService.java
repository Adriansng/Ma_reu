package mareu.adriansng.maru.service_api;

import java.util.List;

import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Reunion;

public interface ReunionApiService {


    //Reunion List

    /**
     * Get all reunion
     * @return {@link List}
     */
    List<Reunion> getReunions();

    //Id Reunion

    int getReunionSize();

    /**
     * get a single reunion by id
     *@param id
     */
    Reunion getReunion(int id);

    //Meeting Room

    /**
     * get a name meeting room by id
     *@param idMeetingRoom
     */
    String getNameMeetingRome(int idMeetingRoom);

    int getSelectionFilterMeetingRoom(int idMeetingRoom);

    List<Reunion> getFilterMeetingRoom();

    List<MeetingRoom> getMeetingRoom();

    //Address Mail

    //Date
    String getSelectionFilterDate(String dateReunion);

    //Date
    List<Reunion> getFilterDate();

    //Action

    /**
     * Deletes a reunion
     * @param reunion
     */
    void deleteReunion(Reunion reunion);

    //Address mail List
    String getAddressMail(int idPerson);

    List<String> getAddressMailList(int idPerson1, int idPerson2, int idPerson3);

    List<String> getAddressMailListReunion();

    /**
     * Add a reunion
     * @param newReunion
     */
    void addReunion(Reunion newReunion);

}
