package com.example.ethan.dream_fit;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainPageTest {

    @Rule
    public ActivityTestRule<MainPage> mActivityTestRule = new ActivityTestRule<>(MainPage.class);

    @Test
    public void mainPageTest() {
    }

}
