package com.vaskjala.vesiroosi20.pillipaevik.ArhiivTestid;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import com.vaskjala.vesiroosi20.pillipaevik.PeaActivity;
import com.vaskjala.vesiroosi20.pillipaevik.R;
import com.vaskjala.vesiroosi20.pillipaevik.TestTooriistad;
import com.vaskjala.vesiroosi20.pillipaevik.teenused.Tooriistad;
import org.hamcrest.core.AllOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static com.vaskjala.vesiroosi20.pillipaevik.TestTooriistad.*;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by mihkel on 1.10.2016.
 */
public class TestArhiivTeosArhiivKustutamine {
    @Rule
    public ActivityTestRule<PeaActivity> mActivityRule = new ActivityTestRule(
            PeaActivity.class);

    @Before
    public void Seadista_Test() {
        MultiFragmentTuvastus(mActivityRule);
        KustutaTeosedUI(InstrumentationRegistry.getTargetContext());
        LisaArhiiviTestiTeosed();
    }

    @Test
    public void TestArhiiviKustutamine() {

        Context context = InstrumentationRegistry.getTargetContext();
        Resources resources = context.getResources();
        Tooriistad.SeadistaNaitaArhiiviSeadeteFailis(context, true);

        ValiTeos(resources.getString(R.string.test_teos1_nimi));
        VajutaArhiivNupp();
        VajutaTagasiKui1Fragment();
        onView(TestTooriistad.withRecyclerView(R.id.harjutua_list).
                atPositionOnView(0,R.id.arhiivipilt)).
                check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)));

        ValiTeos(resources.getString(R.string.test_teos3_nimi));
        VajutaArhiivNupp();
        VajutaTagasiKui1Fragment();
        onView(TestTooriistad.withRecyclerView(R.id.harjutua_list).
                atPositionOnView(2,R.id.arhiivipilt)).
                check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)));


        // Arhiivis olevaid asju ei näidata edaspidi
        VajutaArhiivMenuu();
        onView(allOf(withId(R.id.content), withText(resources.getString(R.string.test_teos2_nimi)))).check(ViewAssertions.matches(isDisplayed()));
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos1_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos3_nimi)))))
                .check(ViewAssertions.doesNotExist());

        ValiTeos(resources.getString(R.string.test_teos4_nimi));
        VajutaKustutaTeos();
        VajutaDialoogOK();

        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos1_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos3_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos4_nimi)))))
                .check(ViewAssertions.doesNotExist());

        onView(allOf(withId(R.id.content), withText(resources.getString(R.string.test_teos2_nimi)))).check(ViewAssertions.matches(isDisplayed()));
        if(OnMultiFragment()) {
            onView(allOf(withId(R.id.nimi), withText(resources.getString(R.string.test_teos2_nimi)))).check(ViewAssertions.matches(isDisplayed()));
            LeiaHarjutus(resources.getString(R.string.test_teos2_h1_nimi)).check(ViewAssertions.matches(isDisplayed()));
        }


        // Arhiivis asju näidatakse
        VajutaArhiivMenuu();
        // Teos 4 kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos4_nimi)))))
                .check(ViewAssertions.doesNotExist());
        onView(allOf(withId(R.id.content), withText(resources.getString(R.string.test_teos1_nimi)))).check(ViewAssertions.matches(isDisplayed()));
        onView(allOf(withId(R.id.content), withText(resources.getString(R.string.test_teos2_nimi)))).check(ViewAssertions.matches(isDisplayed()));
        onView(allOf(withId(R.id.content), withText(resources.getString(R.string.test_teos3_nimi)))).check(ViewAssertions.matches(isDisplayed()));
        onView(TestTooriistad.withRecyclerView(R.id.harjutua_list).
                atPositionOnView(0,R.id.arhiivipilt)).
                check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(TestTooriistad.withRecyclerView(R.id.harjutua_list).
                atPositionOnView(1,R.id.arhiivipilt)).
                check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)));
        onView(TestTooriistad.withRecyclerView(R.id.harjutua_list).
                atPositionOnView(2,R.id.arhiivipilt)).
                check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)));


        // Arhiivi ei näidata
        VajutaArhiivMenuu();
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos1_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos3_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos4_nimi)))))
                .check(ViewAssertions.doesNotExist());
        ValiTeos(resources.getString(R.string.test_teos2_nimi));
        VajutaKustutaTeos();
        VajutaDialoogOK();

        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos1_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos2_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos3_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos4_nimi)))))
                .check(ViewAssertions.doesNotExist());

        EiOoleTeosFragment();
        EiOoleHarjutusMuudaFragment();

        // Arhiivi näidatakse
        VajutaArhiivMenuu();
        onView(allOf(withId(R.id.content), withText(resources.getString(R.string.test_teos1_nimi)))).check(ViewAssertions.matches(isDisplayed()));
        onView(allOf(withId(R.id.content), withText(resources.getString(R.string.test_teos3_nimi)))).check(ViewAssertions.matches(isDisplayed()));
        onView(TestTooriistad.withRecyclerView(R.id.harjutua_list).
                atPositionOnView(0,R.id.arhiivipilt)).
                check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(TestTooriistad.withRecyclerView(R.id.harjutua_list).
                atPositionOnView(1,R.id.arhiivipilt)).
                check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)));
        if(OnMultiFragment()) {
            onView(allOf(withId(R.id.nimi), withText(resources.getString(R.string.test_teos1_nimi)))).check(ViewAssertions.matches(isDisplayed()));
            LeiaHarjutus(resources.getString(R.string.test_teos1_h1_nimi)).check(ViewAssertions.matches(isDisplayed()));
        }

        // Arhiivi ei näidata
        VajutaArhiivMenuu();
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos1_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos2_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos3_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos4_nimi)))))
                .check(ViewAssertions.doesNotExist());

        EiOoleTeosFragment();
        EiOoleHarjutusMuudaFragment();

        // Arhiivi näidatakse
        VajutaArhiivMenuu();
        onView(allOf(withId(R.id.content), withText(resources.getString(R.string.test_teos1_nimi)))).check(ViewAssertions.matches(isDisplayed()));
        onView(allOf(withId(R.id.content), withText(resources.getString(R.string.test_teos3_nimi)))).check(ViewAssertions.matches(isDisplayed()));
        onView(TestTooriistad.withRecyclerView(R.id.harjutua_list).
                atPositionOnView(0,R.id.arhiivipilt)).
                check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(TestTooriistad.withRecyclerView(R.id.harjutua_list).
                atPositionOnView(1,R.id.arhiivipilt)).
                check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)));
        if(OnMultiFragment()) {
            onView(allOf(withId(R.id.nimi), withText(resources.getString(R.string.test_teos1_nimi)))).check(ViewAssertions.matches(isDisplayed()));
            LeiaHarjutus(resources.getString(R.string.test_teos1_h1_nimi)).check(ViewAssertions.matches(isDisplayed()));
        }

        ValiTeos(resources.getString(R.string.test_teos1_nimi));
        VajutaKustutaTeos();
        VajutaDialoogOK();

        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos1_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos2_nimi)))))
                .check(ViewAssertions.doesNotExist());
        // Mõlemas kadunud
        onView(AllOf.allOf(withId(R.id.harjutua_list), hasDescendant(withText(resources.getString(R.string.test_teos4_nimi)))))
                .check(ViewAssertions.doesNotExist());

        onView(allOf(withId(R.id.content), withText(resources.getString(R.string.test_teos3_nimi)))).check(ViewAssertions.matches(isDisplayed()));

        if(OnMultiFragment()) {
            onView(allOf(withId(R.id.nimi), withText(resources.getString(R.string.test_teos3_nimi)))).check(ViewAssertions.matches(isDisplayed()));
            EiOoleHarjutusMuudaFragment();
        }
    }

    @After
    public void Lopeta_Test() {
        Tooriistad.SeadistaNaitaArhiiviSeadeteFailis(InstrumentationRegistry.getTargetContext(), true);
    }
}
