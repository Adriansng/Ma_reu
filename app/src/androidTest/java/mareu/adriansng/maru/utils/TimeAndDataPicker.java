package mareu.adriansng.maru.utils;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;

import org.hamcrest.Matchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Adrian SENEGAS 23/04/2020.
 */
public class TimeAndDataPicker {

    public static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
        onView(withId(datePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
    }

    public static void setTime(int timePickerLaunchViewId, int hour, int minute) {
        onView(withId(timePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour,minute));
        onView(withId(android.R.id.button1)).perform(click());
    }

}
