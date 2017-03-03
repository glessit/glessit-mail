package com.glessit.microservice.mail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(value = JUnit4.class)
public class MailTypeUnit extends Assert {

    private final List<MailType> mailTypeList = new ArrayList<>();

    @Before
    public void setupMailTypes() {

    }

    @Test
    public void test() {
        assertEquals(MailType.values().length, 1);
    }

}
