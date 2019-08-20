package model

class User {

    private var mId : Long = 0
    private var mUserName : String = ""
    private var mIsPro : Boolean = false

    fun setId(id: Long) {
        mId = id
    }

    fun getId() : Long {
        return mId
    }

    fun setUserName(userName: String) {
        mUserName = userName
    }

    fun getUserName() : String {
        return mUserName
    }

    fun setPro(isPro: Boolean) {
        mIsPro = isPro
    }

    fun getPro() : Boolean {
        return mIsPro
    }

}