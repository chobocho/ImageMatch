package com.chobocho.mahjong.button;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageButtonImplTest {
    ImageButton button;

    @Before
    public void setUp() throws Exception {
        button = new ImageButtonImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void  testMakeImageButton() {
         assertEquals(false, button.isInRange(10, 10));
    }
}