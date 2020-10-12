package com.fm.lastfm;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testIsEmailValid() {
        String testURL = "http://ws.audioscrobbler.com/2.0/?method=album.search&album=believe&api_key=5c6343105a3e010ac876dca899f2887f&format=json";
        Assert.assertThat(String.format("URL Validity Test failed for %s ", testURL), Utils.checkURL(testURL), is(true));

    }


}
