package com.permissionx.zxdev

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

typealias PermissionCallback = (Boolean,List<String>) -> Unit
class InvisibleFragment : Fragment() {

    private var callback: PermissionCallback? = null

    fun requestNow(cb: PermissionCallback,vararg permissions: String) {
        callback = cb
        requestPermissions(permissions,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1)  {
            val deniedList = ArrayList<String>()  //记录未被授权的权限
            for ((index,result) in grantResults.withIndex()) {
                if(result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty() //标记是否申请的权限都被授权
            callback?.let {
                it(allGranted,deniedList)
            }
        }
    }
}