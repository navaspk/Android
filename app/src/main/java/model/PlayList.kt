package model

class PlayList {

    private lateinit var mUser: User
    private var mMaxTrackCount = 100

    /**
     * Using linked hash set store the list of tracks, reason are below
     *
     * 1. Set is having property to avoid duplicate entry by default. No need of extra logic to write
     * 2. Using linked hash set, we can make sure that entries are getting adding in the order of
     * user selection. So we can play one by one by tracks based on the order which chose by user
     * 3. Since linked has set is implemented using linked list, removing and adding of tracks is
     * faster when compared to array list
     */
    private var mTracks: LinkedHashSet<Track> = linkedSetOf()

    /**
     * Set the user when creating play list, so that we will get all info about the user
     */
    fun setUser(user: User) {
        mUser = user
        mMaxTrackCount = if (mUser.getPro()) 200 else mMaxTrackCount
    }

    /**
     * Adding one track to play list by checking existing limit and if that track already not
     * present in list
     */
    fun addTrack(track: Track?): Boolean {
        if (track == null)
            return false
        if (!isIdentical(track) && getNumberTask() < mMaxTrackCount) {
            //println("addTrack current mTracks size="+mTracks.size)
            mTracks.add(track)
        }
        return true
    }

    /**
     * Adding list of tracks and same time get restricting adding more than limit to tracks
     * As well as checking uniqueness
     */
    fun addTracks(tracks: List<Track>?): Boolean {
        var newList = getNewUniqueList(tracks!!)
        if (getNumberTask() < mMaxTrackCount) {
            //println("addTracks getNumberTask()="+getNumberTask()+", newList size="+newList.size)
            val countAfterAdding = getNumberTask() + newList.size
            if (countAfterAdding < mMaxTrackCount) {
                mTracks.addAll(newList)
            } else {
                println("Cannot add more than $mMaxTrackCount")
                return false
            }
        } else {
            //println("Cannot add more than $mMaxTrackCount")
            return false
        }
        //println("mTracks size="+mTracks.size)
        return true
    }

    /**
     * get the total number of task currently available
     */
    fun getNumberTask(): Int {
        //println("getNumberTask, size=${mTracks.size}")
        return mTracks.size
    }

    /**
     * To remove the task from existing list
     */
    fun removeTask(track: Track) : Boolean {
        //mTracks.remove(track) simply we can do OR
        val it = mTracks.iterator()
        while(it.next() != null) {
            if(it.next().getId() == track.getId()) {
                return mTracks.remove(track)
            }
        }
        return false
    }

    /**
     * To remove list tracks from play list
     */
    fun removeTasks(tracks: List<Track>) {
        mTracks.removeAll(tracks)
    }

    /**
     * To get total duration of tracks
     */
    fun getTotalDuration(): Long {
        var duration : Long = 0
        for (track in mTracks) {
            //println("dura=${track.getDuration()}")
            duration += track.getDuration()
        }
        return duration
    }

    /**
     * Checking identical and return true if list does not have that specific track
     */
    private fun isIdentical(track: Track?): Boolean {
        for (t in mTracks) {
            if (t.isSameTrack(track!!.getId()) == true) {
                return true
            }
        }
        return false
    }

    private fun getNewUniqueList(tracks: List<Track>): List<Track> {
        var newList = mutableListOf<Track>()
        var isPresent = false
        for (newItem in tracks) {
            for (existingItem in mTracks) {
                //println("getNewUniqueList existingItem.getId()=${existingItem.getId()}, newItem id=${newItem.getId()}")
                if (newItem.getId() == existingItem.getId()) {
                    // item already present, so no need to add the track to new list
                    isPresent = true
                    break
                }
            }
            if (!isPresent) {
                if (!isItemPresentInNewList(newList , newItem))
                    newList.add(newItem)
            }
        }
        //println("getNewUniqueList, newList size="+newList.size)
        return newList
    }

    private fun isItemPresentInNewList(newList: List<Track>, newTrack: Track): Boolean {
        for (eachItem in newList) {
            if (eachItem.getId() == newTrack.getId()) {
                return true
            }
        }
        return false
    }

    /**
     * As of now just taking middle element as a random track
     */
    public fun getRandomId() : Track {
        if (mTracks?.size > 0) {
            val it = mTracks.iterator()
            var count = 0
            while(it.next() != null) {
                if (count == (mTracks?.size/2) && it.next().getId() > 0) {
                    return it.next()
                    break
                }
                count++
            }
        }
        return Track()
    }
}