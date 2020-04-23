package mareu.adriansng.maru;

import android.content.Context;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.AddReunionActivity;
import mareu.adriansng.maru.utils.ChangeText;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;



/**
 * Created by Adrian SENEGAS 21/04/2020.
 */
public class ReunionAddTest {

    private ReunionApiService service;

    @Rule
    public ActivityTestRule<AddReunionActivity> mActivityRule =
            new ActivityTestRule<>(AddReunionActivity.class);

    @Before
    public void setUp(){
        AddReunionActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getInstrumentation().getTargetContext();

        assertEquals("mareu.adriansng.maru", appContext.getPackageName());
    }

    @Test
    public void AddingReunion() {

        // Given: We launch AddReunionActivity
        //Calendar ITEM_HOUR_DATE= 1579512600000;
        String ITEM_ORGANIZER = "Name";
        String ITEM_SUBJECT= "Subject";
        String ITEM_DATE="01/01/01";
        String ITEM_HOUR="10H00";
        // Date reunion
        onView(allOf(withId(R.id.add_date_txt), isDisplayed())).perform(ChangeText.setTextInTextView(ITEM_DATE));
        // Hour reunion
        onView(allOf(withId(R.id.add_hour_txt), isDisplayed())).perform(ChangeText.setTextInTextView(ITEM_HOUR));
        // Name organizer
        onView(allOf(withId(R.id.add_name_organizer_edit_txt), isDisplayed())).perform(replaceText(ITEM_ORGANIZER));
        // Subject
        onView(allOf(withId(R.id.add_subject_reunion_edit), isDisplayed())).perform(replaceText(ITEM_SUBJECT)); // EditText info reunion
        // Room
        onView(allOf(withId(R.id.add_roomReunion_spinner), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.item_spinner_room_txt),isDisplayed(),withText("Meeting Room A"))).perform(click());
        // Validate reunion
        onView(allOf(withId(R.id.add_validate_btn), isDisplayed())).perform(click());
    }
}
