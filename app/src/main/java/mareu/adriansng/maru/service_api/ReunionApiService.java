package mareu.adriansng.maru.service_api;

import java.util.List;

import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;

public interface ReunionApiService {

    // REUNION LIST
    List<Reunion> getFilterReunions();
    int getReunionSize();
    /**
     * get a single reunion by id
     *@param id;
     */
    Reunion getReunion(int id);
    /**
     * Get all reunion
     * @return {@link List}
     */
    List<Reunion> getReunions();

    // MEETING ROOM
    /**
     * get a name meeting room by id
     *@param idMeetingRoom;
     */
    String getNameMeetingRome(int idMeetingRoom);
    int getColorAvatarMeetingRoom(int idMeetingRoom);
    List<MeetingRoom> getMeetingRoom();

    // LIST PERSON
    List<Person> getPersonParticipant();

    // FILTER
    void getFilterMeetingRoom(int idMeetingRoomFilter);
    void getFilterDate(String dateReunion);
    void getFilterMeetingAndDate(int idMeetingRoomFilter, String dateReunion);

    // AVAILABILITY
    List<MeetingRoom> getListMeetingRoomAvailability();
    void getAvailabilityMeetingRoom(String date, String hour, int nbMinute);
    void getResetAvailabilityMeetingRoom();

    // ACTIONS
    /**
     * Deletes a reunion
     * @param reunion;
     */
    void deleteReunion(Reunion reunion);
    /**
     * Add a reunion
     * @param newReunion;
     */
    void addReunion(Reunion newReunion);
}
