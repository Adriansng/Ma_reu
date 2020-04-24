package mareu.adriansng.maru.utils;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

public class SpinnerSelect {
    public static void setSpinner(int spinnerId, int atPosition){
        onView(withId(spinnerId)).perform(click());
        onData(anything()).atPosition(atPosition).perform(click());
    }


    public static void setSpinnerDialog(int spinnerId,int selection){
        onView(withId(spinnerId)).perform(click());
        onData(anything()).atPosition(selection).inRoot(isPlatformPopup()).perform(click());
    }
}
