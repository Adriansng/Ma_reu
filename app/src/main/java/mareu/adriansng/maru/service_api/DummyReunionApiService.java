package mareu.adriansng.maru.service_api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Person;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.ui_reunion_list.utils.DateUtils;

public class DummyReunionApiService implements ReunionApiService {

    private List<Reunion> reunions= DummyReunionList.generateReunion();
    private List<Reunion> filterReunions = new ArrayList<>();
    private List<MeetingRoom> meetingRoom= DummyReunionList.DUMMY_MEETING_ROOM;
    private List<MeetingRoom> availabilityMeetingRoom= new ArrayList<>();
    private List<Person> personList=DummyReunionList.DUMMY_PERSON;
    private List<Person> personParticipant;
    private int idMeetingRoom;
    private int idPerson;
    private int idMeetingRoomFilter;
    private List<String> addressMailList;
    private String addressMail;
    private String nameOrganizer;
    private String dateReunion;
    private int index;

    // REUNION LIST
    @Override
    public List<Reunion> getReunions() {
        return reunions; }


    // ID REUNION
    @Override
    public int getReunionSize(){
        index=reunions.size()-1;
        return index;
    }

    @Override
    public Reunion getReunion(int id) { return reunions.get(id);}

    // MEETING ROOM
    @Override
    public String getNameMeetingRome(int idMeetingRoom) {
        return meetingRoom.get(idMeetingRoom).getNameRoom();
    }

    @Override
    public int getColorAvatarMeetingRoom(int idMeetingRoom){
        return meetingRoom.get(idMeetingRoom).getColorAvatar();
    }

    @Override
    public List<MeetingRoom> getMeetingRoom(){return meetingRoom;}

    // LIST PERSON
    @Override
    public List<Person> getPersonParticipant(){
        return personList;
    }


    // FILTER
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

    @Override
    public List<Reunion> getFilterReunions(){
        return filterReunions;
    }


    // AVAILABILITY
    @Override
    public List<MeetingRoom> getInitListSpinnerRoomAvailability(){
        availabilityMeetingRoom.clear();
        for( MeetingRoom meetingRoom: meetingRoom){
            if(meetingRoom.getAvailability()){
                availabilityMeetingRoom.add(meetingRoom);
            }
        }
        return availabilityMeetingRoom;
    }

    @Override
    public List<MeetingRoom> getAvailabilityMeetingRoom(String date, String hour, int nbMinute) {
        for (Reunion reunion : reunions) {
            for (int i = 0; i <= nbMinute; i++) {
                String time = DateUtils.formatDateLong(date, hour);
                String timeReunion = DateUtils.addMinute(DateUtils.formatDateLong(reunion.getDate(), reunion.getHour()), i);
                if (timeReunion.equals(time)) {
                    DateUtils.addMinute(time, i);
                    idMeetingRoom = reunion.getIdMeetingRoom();
                    meetingRoom.get(idMeetingRoom).setAvailability(false);
                }
            }
        }
      return meetingRoom;
    }

    @Override
    public List<MeetingRoom> getResetAvailabilityMeetingRoom(){
        for(Reunion reunion:reunions){
            idMeetingRoom=reunion.getIdMeetingRoom();
            meetingRoom.get(idMeetingRoom).setAvailability(true);
        }
        return meetingRoom;
    }


    // ACTIONS
    @Override
    public void addReunion(Reunion newReunion) {
        reunions.add(newReunion);
    }
    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }

}
