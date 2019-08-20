package model

class Track {

    private var mId : Long = 0
    private var mTitle : String = ""
    private var mDuration : Long = 0

    fun setId(id: Long) {
        mId = id
    }

    fun getId() : Long {
        return mId
    }

    fun setTitle(title: String) {
        mTitle = title
    }

    fun getTitle() : String {
        return mTitle
    }

    fun setDuration(duration: Long) {
        mDuration = duration
    }

    fun getDuration() : Long {
        return mDuration
    }

    fun isSameTrack(id: Long) :Boolean {
        return this.mId == id
    }
}