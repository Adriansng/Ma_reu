package mareu.adriansng.maru.service_api;

import java.util.ArrayList;
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
    private int idMeetingRoomFilter;
    private String nameMeetingRoom;
    private String addressMailList;
    private String addressMail;
    private String nameOrganizer;
    private String dateReunion;
    private int index;

    //Reunion List
    @Override
    public List<Reunion> getReunions() { return reunions; }

    //ID Reunion
    @Override
    public int getReunionSize(){
        index=reunions.size()-1;
        return index;
    }

    @Override
    public Reunion getReunion(int id) { return reunions.get(id);}

    //Meeting Room
    @Override
    public String getNameMeetingRome(int idMeetingRoom) {
        nameMeetingRoom= meetingRoom.get(idMeetingRoom).getNameRoom();
        return nameMeetingRoom;
    }

    @Override
    public int getSelectionFilterMeetingRoom(int idMeetingRoomFilter){
        return idMeetingRoomFilter;
    }

    @Override
    public List<Reunion> getFilterMeetingRoom(){
        reunions.clear();
        for(Reunion n: reunions ){
            if(n.getIdMeetingRoom() == idMeetingRoomFilter){
                reunions.add(n);
            }
        }
        return reunions;
    }

    @Override
    public List<MeetingRoom> getMeetingRoom(){return meetingRoom;}

    //Date
    @Override
    public String getSelectionFilterDate(String dateReunion){
        return dateReunion;
    }

    @Override
    public List<Reunion> getFilterDate(){
        reunions.clear();
        for(Reunion n: reunions){
            if(n.getDate()== dateReunion){
                reunions.add(n);
            }
        }
        return reunions;
    }

    //Address mail List
    @Override
    public List<Person> getPersonList(){return personList;}

    @Override
    public List<Person> getAddressMail(){return personList;
    }

    @Override
    public String getAddressMailList() {
        personList= new ArrayList<>();
        addressMailList= String.valueOf(personList.addAll(getAddressMail()));
        return addressMailList;
    }

    //Action
    @Override
    public void addReunion(Reunion newReunion) {
        reunions.add(newReunion);
    }
    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }

}
