package mareu.adriansng.maru.service_api;

import java.util.List;

import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;

public class DummyReunionApiService implements ReunionApiService {

    private List<Reunion> reunions= DummyReunionList.generateReunion();
    private List<MeetingRoom> meetingRoom= DummyReunionList.DUMMY_MEETING_ROOM;
    private List<Person> personList=DummyReunionList.DUMMY_PERSON;
    private Reunion reunion;
    private int idMeetingRoom;
    private String nameMeetingRoom;
    private String addressMailList;
    private String nameOrganizer;
    private String hourDate;

    @Override
    public String getNameMeetingRome() {
        nameMeetingRoom= meetingRoom.get(reunion.getIdMeetingRoom()).getNameRoom();
        return nameMeetingRoom;
    }

    @Override
    public String getAddressMailList() {
        String person1 = personList.get(0).getAddressMail();
        String person2 = personList.get(1).getAddressMail();
        String person3 = personList.get(2).getAddressMail();
        addressMailList=person1+"-"+person2+"-"+person3;
        return addressMailList;
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
        return reunions.get(id);
    }

    @Override
    public void addReunion(Reunion addNewReunion) {
        reunions.add(Reunion.addNewReunion(reunions.size(),idMeetingRoom,nameOrganizer,hourDate,addressMailList));
    }
    @Override
    public List<Person> getPersonList(){return personList;}
}
