package twitter;

import java.sql.Time;
import java.time.Instant;
import java.util.*;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        if (tweets.isEmpty()) {
            Instant instant = Instant.now();
            Timespan timespan = new Timespan(instant, instant);
            return timespan;
        }

        List<Tweet> sortedTweets = sort(tweets);
        Instant start = sortedTweets.get(0).getTimestamp();
        Instant end = sortedTweets.get(sortedTweets.size() - 1).getTimestamp();
        Timespan timespan = new Timespan(start, end);
        System.out.println("this are tweets " + sortedTweets);

        return  timespan;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> set = new HashSet<String>();
        return set;
    }
    /**
     @param tweets
                list of tweets
     @return sorted tweets list by their Instant.
     */
    private static List<Tweet> sort(List<Tweet> tweets) {
        List<Tweet> sortedTweets = tweets;

        for (int i = 0; i < sortedTweets.size(); i++) {
            Tweet first = sortedTweets.get(i);
            for (int j = 0; j < i; j++) {
                Tweet second = sortedTweets.get(j);
                if (first.getTimestamp().isBefore(second.getTimestamp())) {
                    sortedTweets = swap(sortedTweets, i, j);
                }
            }
        }
        return sortedTweets;
    }

    /**
     *
     * @param tweets
     *          the list that should be swaped
     * @param first
     *           first index
     * @param second
     *           second index
     * @return swaped list
     *
     */
    private static List<Tweet> swap(List<Tweet> tweets, int first, int second) {
        Tweet c = tweets.get(first);
        tweets.set(first, tweets.get(second));
        tweets.set(second, c);
        System.out.println("swaped " + c + " to " + tweets.get(first));
        return  tweets;
    }
    /* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
