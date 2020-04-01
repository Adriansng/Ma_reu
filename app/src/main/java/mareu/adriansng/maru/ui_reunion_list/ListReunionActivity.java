package mareu.adriansng.maru.ui_reunion_list;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.event.DeleteReunionEvent;
import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.utils.DatePickerFragment;
import mareu.adriansng.maru.ui_reunion_list.utils.SpinnerMeetingRoomAdapter;

public class ListReunionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    // FOR DESIGN
    FloatingActionButton mAddButton;
    private RecyclerView recyclerView;
    private ListReunionActivity activity;

    // FOR DATA


    private ReunionApiService reunionApiService;
    private List<Reunion>  mReunions;

    //FOR FILTER
    private SpinnerMeetingRoomAdapter mAdapterSpinner;
    private ListReunionAdapter adapter;
    private ArrayList<MeetingRoom> mMeetingRoom;
    private ArrayList<Reunion> mReunionsFilter;
    private MeetingRoom selectionRoom;
    private String dateFilter;
    private String date;
    private String currentDateString;
    private Button mButtonDate;
    private DialogFragment datePicker;

    // OVERRIDE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.activity=this;
        reunionApiService= DI.getReunionApiService();
        configureRecyclerView();
        mAddButton= findViewById(R.id.add_reunion_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=v.getContext();
                Intent intent= new Intent(context,AddReunionActivity.class);
                context.startActivity(intent);
            }
        });
        initList();

        //POPUP FILTER
        ActionMenuItemView filterPopup= findViewById(R.id.action_filter);
        filterPopup.setClickable(true);
        filterPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ListReunionActivity.this);
                dialog.setContentView(R.layout.popup_filter_room);
                dialog.setTitle("Fitler");

                //Filter Date
                Button mButtonDate = dialog.findViewById(R.id.date);
                TextView textViewDate = dialog.findViewById(R.id.view_date_filter);
                textViewDate.setText(currentDateString);
                mButtonDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragment datePicker = new DatePickerFragment();
                        datePicker.show(getSupportFragmentManager(), "date picker");
                    }
                });

                //Filter Room
                Spinner mSpinner = dialog.findViewById(R.id.spinner_room_reunion);
                initListSpinner();
                mAdapterSpinner = new SpinnerMeetingRoomAdapter(ListReunionActivity.this, mMeetingRoom);
                mSpinner.setAdapter(mAdapterSpinner);
                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectionRoom = reunionApiService.getMeetingRoom().get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                //Validate
                ImageButton buttonValidate = dialog.findViewById(R.id.validate_btn_popup);
                buttonValidate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("")) {
                            Toast.makeText(ListReunionActivity.this, "You have filter with "+selectionRoom.getNameRoom(), Toast.LENGTH_SHORT).show();
                            reunionApiService.getFilterMeetingRoom(selectionRoom.getId());
                            initNewList();
                        }
                        if (!textViewDate.toString().equalsIgnoreCase("")) {
                            Toast.makeText(ListReunionActivity.this, "and on the date "+date, Toast.LENGTH_SHORT).show();
                            reunionApiService.getSelectionFilterDate(date);
                            reunionApiService.getFilterDate();

                        }

                        dialog.dismiss();
                    }
                });
                //Cancel
                ImageButton buttonCancel = dialog.findViewById(R.id.cancel_btn_popup);
                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    // Configuration
    private void configureRecyclerView() {
        recyclerView=findViewById(R.id.list_reunions);
        mReunions= reunionApiService.getReunions();
        adapter= new  ListReunionAdapter(mReunions);
        recyclerView.setAdapter((adapter));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initList() {
        List<Reunion> mReunions= reunionApiService.getReunions();
        ListReunionAdapter mAdapter= new ListReunionAdapter(mReunions);
        recyclerView.setAdapter(mAdapter);
    }

    private void initNewList(){
        List<Reunion> mReunions= reunionApiService.getReunions();
        ListReunionAdapter mAdapter= new ListReunionAdapter(mReunions);
        mAdapter.notifyDataSetChanged();
    }

    // Actions
    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {
        reunionApiService.deleteReunion(event.reunion);
        initList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public DialogFragment getDatePicker() {return datePicker;}

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        date = currentDateString;
    }

    private void initListSpinner() {
        mMeetingRoom = new ArrayList<>();
        mMeetingRoom.addAll(reunionApiService.getMeetingRoom());
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        initList();
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        adapter.notifyDataSetChanged();
        initList();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        adapter.notifyDataSetChanged();
        initList();
    }
    }
