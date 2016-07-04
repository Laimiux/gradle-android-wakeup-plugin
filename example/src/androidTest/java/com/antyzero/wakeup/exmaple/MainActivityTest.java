package com.antyzero.wakeup.exmaple;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.antyzero.wakeup.example.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSwipeWithHolder() throws Exception {

        // Given
        MainActivity activity = activityTestRule.getActivity();

        // When
        // do nothing

        // Then
        assertTrue(activity != null);
    }
}