package mareu.adriansng.maru.service_api;

import java.util.List;

import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Reunion;

public class DummyReunionApiService implements ReunionApiService {

    private List<Reunion> reunions= DummyReunionList.generateReunion();
    private List<MeetingRoom> meetingRoom= DummyReunionList.DUMMY_MEETING_ROOM;
    private Reunion reunion;

    @Override
    public String getNameMeetingRome() {
        return meetingRoom.get(reunion.getIdMeetingRoom()).getNameRoom();
    }

    @Override
    public List<MeetingRoom> getMeetingRoom(){return meetingRoom;}

    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }

    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }

    @Override
    public Reunion getReunion(int id) {
        return null;
    }

    @Override
    public void addReunion(Reunion addNewReunion) {
    }
}
