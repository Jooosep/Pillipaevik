package com.vaskjala.vesiroosi20.pillipaevik.HarjutusLisaTehtudTestid;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import com.vaskjala.vesiroosi20.pillipaevik.PeaActivity;
import com.vaskjala.vesiroosi20.pillipaevik.R;
import com.vaskjala.vesiroosi20.pillipaevik.TestTooriistad;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static com.vaskjala.vesiroosi20.pillipaevik.TestTooriistad.*;

/**
 * Created by mihkel on 1.10.2016.
 */
public class TestLisaTehtudKlikiTeistHarjutust {
    @Rule
    public ActivityTestRule<PeaActivity> mActivityRule = new ActivityTestRule(
            PeaActivity.class);

    @Before
    public void Seadista_Test() {
        MultiFragmentTuvastus(mActivityRule);
    }


    @Test
    public void TestLisaTehtudKlikiTeistHarjutust() {
        if(OnMultiFragment()) {
            Context context = InstrumentationRegistry.getTargetContext();
            Resources resources = context.getResources();

            KeeraParemale();
            ValiTeos(resources.getString(R.string.test_teos3_nimi));
            VajutaLisaTehtudHarjutus();
            onView(withId(R.id.harjutusekirjeldus))
                    .perform(ViewActions.replaceText(resources.getString(R.string.test_teos3_h7_nimi)), ViewActions.closeSoftKeyboard());
            OnHarjutusLisaTehtudFragment();

            Calendar c0 = Calendar.getInstance();
            Calendar c1 = Calendar.getInstance(); c1.setTime(c0.getTime()); c1.add(Calendar.MINUTE, -120);
            SeaKellaaeg(c1, R.id.alguskellaaeg);
            VajutaDialoogOK();
            onView(withId(R.id.pikkusminutites)).check(ViewAssertions.matches(withText("120")));

            KeeraVasakule();
            onView(withId(R.id.harjutusekirjeldus)).check(ViewAssertions.matches(withText(resources.getString(R.string.test_teos3_h7_nimi))));
            LeiaHarjutus(resources.getString(R.string.test_teos3_h7_nimi)).perform(click());
            OnHarjutusLisaTehtudFragment();

            LeiaHarjutus(resources.getString(R.string.test_teos3_h2_nimi)).perform(click());
            LeiaHarjutus(resources.getString(R.string.test_teos3_h7_nimi)).perform(click());

            OnHarjutusMuudaFragment();

            LeiaHarjutus(resources.getString(R.string.test_teos3_h7_nimi)).perform(click());
            VajutaKustutaHarjutus();
            VajutaDialoogOK();
            VabastaKeeramine();
        }
    }
}
