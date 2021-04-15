package twitter;

import static org.junit.Assert.*;

import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * Make sure you have partitions.
     *
     * partitions for getTimespan method
     * sorted tweets
     * unsorted tweets
     * empty list
     *
     * partitions for getUsers method
     * 0 , 1 , 2 mentions
     * no mentions
     * containing email
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2017-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2015-02-17T22:00:00Z");
    private static final Instant d4 = Instant.parse("2019-02-17T22:00:00Z");



    private static final Tweet tweet1 = new Tweet(1, "@alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "@bbitdiddle", "@artur talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(2, "@salaxakatu", "i love @seroj i am the only person", d3);
    private static final Tweet tweet4 = new Tweet(2, "@salaxakatu", "i love @seroj i am the only person mit@gmail.com", d4);


    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoSortedTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));


        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }

    @Test
    public void testGetTimespanUnsortedTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet3, tweet2));

        assertEquals("expected start", tweet3.getTimestamp(), timespan.getStart());
        assertEquals("expected end", tweet2.getTimestamp(), timespan.getEnd());
    }


    @Test
    public void testGetTimespanZeroTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList());

        assertEquals("expected start", timespan.getEnd(), timespan.getStart());

    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    @Test
    public void testGetUsersForZeroUsers() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(new ArrayList<>());
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    @Test
    public void testGetUsersForManyUsers() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet2, tweet1, tweet3));


        assertTrue("Expected @seroj", mentionedUsers.contains("@seroj") );
        assertTrue("Expected @artur", mentionedUsers.contains("@artur") );

    }

    @Test
    public void testGetUsersForEmails() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet2, tweet1, tweet3, tweet4));

        assertTrue("Expected @seroj", mentionedUsers.contains("@seroj") );
        assertTrue("Expected @artur", mentionedUsers.contains("@artur") );
    }
    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
