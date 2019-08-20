package com.example.unittest

import model.PlayList
import model.Track
import model.User
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import java.util.*
import kotlin.collections.ArrayList

/**
 * In this unit testing, intentionally made three assertion errors to make sure that business logic
 * is working or not. Those are when
 *
 * 1. When passing null track
 * 2. When passing more than 100 track where as the user is not a PRO
 * 3. When passing more than 200 track to add into play list where the user is PRO
 */

class PlayListTest {

    companion object {
        var mPlayList = PlayList()
        var mUser = User()
    }

    /**
     * Initialize the user
     */
    @Test
    fun init_user () {
        mUser.setId(1);
        mUser.setUserName("user1")
        mUser.setPro(false);
    }

    /**
     * Setting specific user to play list
     */
    @Test
    fun add_user() {
        mPlayList.setUser(mUser)
    }

    /**
     * During adding of new track to play list, track shoud not be null
     *
     * This test case will fail as we trying to add null track. Through this test case, our
     * aim is, user should not add any null track.
     */
    @Test
    fun add_null_track_test_should_not_add_return_false() {
        //either one of them need to un comment to show assertion error
        //assertTrue(mPlayList.addTrack(null)) OR
        assertNotNull("Trying to add null track", null)
    }

    /**
     * During adding of new track to play list, track should not be null
     */
    @Test
    fun add_actual_single_track_return_true_if_success() {
        println("add_actual_single_track_return_true_if_success mPlayList="+mPlayList)
        var track = Track()
        track.setDuration(110000)
        track.setId(1234567)
        assertTrue("Added successfully", mPlayList.addTrack(track))
    }

    /**
     * Adding same 10 tracks
     */
    @Test
    fun add_n_number_of_track() {
        assertNotNull("List is null", getSomeTracks(10, 21, false))
    }

    /**
     * Adding same 101 tracks
     *
     * This test case will not fail as we are trying adding same tracks, so basically 101 same tracks,
     * among that only one will take and add to main play lis
     */
    @Test
    fun add_more_than_hundred_number_of_same_track() {
        // getting identical 101 tracks
        var actualLists = getSomeTracks(101, 203, false)
        //checking whether it's null or not
        assertNotNull("Lists are null", actualLists)
        // we need to make sure thet whatever the passed tracks should be unique.
        println("add_more_than_hundred_number_of_same_track mPlayList="+mPlayList)
        assertThat("Should not add more than 100 tracks if user is not a PRO", true,
                (equalTo(mPlayList.addTracks(actualLists))))
    }

    /**
     * Adding unique 101 tracks
     *
     * This test case will fail as (it will show assertion error) as we trying to add unique set of
     * tracks which more than 100
     */
    @Test
    fun add_more_than_hundred_number_of_unique_track() {
        // getting identical 101 tracks
        var actualLists = getSomeTracks(501, 608, true)
        //checking whether it's null or not
        assertNotNull("Lists are null", actualLists)
        // we need to make sure thet whatever the passed tracks should be unique.
        assertThat("Should not add more than 100 tracks if user is not a PRO", true,
                (equalTo(mPlayList.addTracks(actualLists))))
    }

    /**
     * get total number of tracks present in play list
     */
    @Test
    fun get_current_number_of_tracks_in_play_list() {
        assertTrue("Zero track available in play list", mPlayList.getNumberTask() != 0)
        assertThat("Duration is 0", true, equalTo(mPlayList.getTotalDuration()>0))
    }

    /**
     * Getting current total duration of play list, it shoulb greater than zero as we have
     * added at least one track to list
     */
    @Test
    fun get_total_track_duration() {
        assertThat("Duration is empty", true, equalTo(mPlayList.getTotalDuration()>0))
    }

    /**
     * Adding upto 200 tracks for pro users
     */
    @Test
    fun adding_tracks_to_pro_user_less_than_200() {
        mUser.setPro(true)
        mPlayList.setUser(mUser)
        var actualLists = getSomeTracks(1001, 1190,  true)
        assertThat("Should not add more than 200 tracks", true,
                (equalTo(mPlayList.addTracks(actualLists))))

    }

    /**
     * Trying to add more than 200 tracks to play list.
     *
     * This will show assertion error since more than 200 tracks will not support to list even user
     * is pro.
     */
    @Test
    fun adding_tracks_to_pro_user_greater_than_200() {
        mUser.setPro(true)
        mPlayList.setUser(mUser)
        var actualLists = getSomeTracks(2222, 2500, true)
        assertThat("Should not add more than 200 tracks", true,
                (equalTo(mPlayList.addTracks(actualLists))))

    }

    /**
     * To remove the random task from the list
     */
    @Test
    fun remove_random_track() {
        var track = getRandomId()
        assertTrue("No such track available",
                track != null && track.getId() != 0L && mPlayList.removeTask(track))
    }

    private fun getRandomId() : Track {
        return mPlayList.getRandomId()
    }


     /**
     * To get the number of tracks
     */
    private fun getSomeTracks(start: Int, end: Int, isUnique: Boolean): List<Track>? {
        var list : ArrayList<Track> = ArrayList()
        //println("isUnique=$isUnique")
        for (i in start..end) {
            var track = Track()
            track.setDuration(2345678)
            if (isUnique) {
                track.setId(i.toLong())
                list?.add(track)
            } else {
                list?.add(track)
            }
        }
        //println("inside if and list=$list, size=${list?.size}")
        return list
    }

    /**
     * To generate long track id
     */
    /*var mList = ArrayList<Long>()
    private fun getRandom(i: Int) : Long {

        var random = (Math.random()*i/.5)*.2
        var longRandom = random.toLong()
        if (!mList.contains(longRandom))
            mList.add(longRandom)
        else
            longRandom = getRandom(i+20905)

        return longRandom
    }*/
}
