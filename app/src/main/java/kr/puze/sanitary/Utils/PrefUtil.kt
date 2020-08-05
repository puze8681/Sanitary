package www.okit.co.Utils

import android.content.Context
import android.content.SharedPreferences

class PrefUtil(context: Context) {
    private var preferences: SharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = preferences.edit()

    var isLogin : Boolean
        get() = preferences.getBoolean("isLogin", false)
        set(value) {
            editor.putBoolean("isLogin", value)
            editor.apply()
        }

    var isAdmin : Boolean
        get() = preferences.getBoolean("isAdmin", false)
        set(value) {
            editor.putBoolean("isAdmin", value)
            editor.apply()
        }

    var firebaseToken: String
        get() = preferences.getString("firebaseToken", "").toString()
        set(value) {
            editor.putString("firebaseToken", value)
            editor.apply()
        }

    var userName: String
        get() = preferences.getString("userName", "").toString()
        set(value) {
            editor.putString("userName", value)
            editor.apply()
        }

    var userID: String
        get() = preferences.getString("userID", "").toString()
        set(value) {
            editor.putString("userID", value)
            editor.apply()
        }

    var userPW: String
        get() = preferences.getString("userPW", "").toString()
        set(value) {
            editor.putString("userPW", value)
            editor.apply()
        }

    fun logout(){
        preferences.edit().clear().commit()
        editor.clear().commit()
    }
}