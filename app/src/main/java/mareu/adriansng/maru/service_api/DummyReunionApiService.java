package mareu.adriansng.maru.service_api;

import java.util.ArrayList;
import java.util.List;

import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;

public class DummyReunionApiService implements ReunionApiService {

    private List<Reunion> reunions= DummyReunionList.generateReunion();
    private List<Reunion> filterReunions = new ArrayList<>();
    private List<MeetingRoom> meetingRoom= DummyReunionList.DUMMY_MEETING_ROOM;
    private List<Person> personList=DummyReunionList.DUMMY_PERSON;
    private List<Person> personParticipant;
    private int idMeetingRoom;
    private int idPerson;
    private int idMeetingRoomFilter;
    private String nameMeetingRoom;
    private List<String> addressMailList;
    private String addressMail;
    private String nameOrganizer;
    private String dateReunion;
    private int index;

    //Reunion List
    @Override
    public List<Reunion> getReunions() {
        return reunions; }

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
    public List<MeetingRoom> getMeetingRoom(){return meetingRoom;}

    //List Person
    @Override
    public List<Person> getPersonParticipant(){
        return personList;
    }

    //Address mail List


    //Filter
    @Override
    public List<Reunion> getFilterMeetingRoom(int idMeetingRoomFilter){
        filterReunions.clear();
        for(Reunion n: reunions ){
            if(n.getIdMeetingRoom() == idMeetingRoomFilter){
                filterReunions.add(n);
            }
        }
        return filterReunions;
    }

    @Override
    public List<Reunion> getFilterDate(String dateReunion){
        filterReunions.clear();
        for(Reunion n: reunions){
            if(n.getDate().equals(dateReunion)){
                filterReunions.add(n);
            }
        }
        return filterReunions;
    }

    @Override
    public List<Reunion> getFilterMeetingAndDate(int idMeetingRoomFilter, String dateReunion){
        filterReunions.clear();
        for(Reunion n:reunions){
            if(n.getIdMeetingRoom()==idMeetingRoomFilter && n.getDate().equals(dateReunion)){
                filterReunions.add(n);
            }
        }
        return filterReunions;
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
