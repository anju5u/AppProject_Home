package admin.ats.com.focaloidproject.Fragments;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import admin.ats.com.focaloidproject.BroadcastReceiver.AlarmBroadcast;
import admin.ats.com.focaloidproject.R;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by user on 3/29/2017.
 */

public class Menu_Event extends Fragment{
    TextView txt_time,txt_date;
    Button btn_add_evnt;
    EditText txt_evnt_name;
    String evnt_name;
    private Calendar target_cal =Calendar.getInstance();
    private int mYear, mMonth, mDay, mHour, mMinute;
    SQLiteDatabase db;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_event,container,false);
        txt_evnt_name= (EditText)rootView.findViewById(R.id.edtxtEventName);
        txt_date=(TextView)rootView.findViewById(R.id.edtxtDate);
        txt_time=(TextView)rootView.findViewById(R.id.edtxtTime);
        btn_add_evnt=(Button)rootView.findViewById(R.id.btnAddEvent);
        evnt_name=txt_evnt_name.getText().toString();

        txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int display_month;
                        display_month = monthOfYear + 1;
                        txt_date.setText(dayOfMonth + "-" + (display_month) + "-" + year);
                        target_cal.set(Calendar.YEAR, year);

                        target_cal.set(Calendar.MONTH, monthOfYear);
                        target_cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                    }
                }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });

        txt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog= new TimePickerDialog(getActivity(),new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        txt_time.setText(hourOfDay+" : "+minute);
                        target_cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        target_cal.set(Calendar.MINUTE,minute);
                    }
                },mHour,mMinute,false);
                timePickerDialog.show();


            }
        });
        btn_add_evnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Target CAL", String.valueOf(target_cal.get(Calendar.MONTH)));

                AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(ALARM_SERVICE);

                Intent i =new Intent(getContext().getApplicationContext(),AlarmBroadcast.class);
                Log.d("Title from menu_evnt",evnt_name);
                i.putExtra("Title",evnt_name);

                PendingIntent pendingIntent =PendingIntent.getBroadcast(getContext().getApplicationContext(),0,i,0);
                Log.d("Target time", String.valueOf(target_cal.getTime()));
                alarmManager.set(AlarmManager.RTC,target_cal.getTimeInMillis(),pendingIntent);

            }
        });
        return rootView;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Event");
    }



}
