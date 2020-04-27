package com.permissionx.zxdev

import androidx.fragment.app.FragmentActivity

object PermissionX {
    private const val TAG = "InvisibleFragment"

    fun request(activity: FragmentActivity,vararg permissions: String,callback: PermissionCallback) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if(existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment //返回创建的对象
        }
        // *（星号） 对于数组，不可以直接将它传递给另外一个接收可变长度参数的方法。因此可以在参数前加上一个星号 *
        fragment.requestNow(callback,*permissions)

    }
}