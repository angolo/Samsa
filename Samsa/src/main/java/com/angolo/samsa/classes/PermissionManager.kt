package com.angolo.samsa.classes

import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

class PermissionManager private constructor (private val fragment: WeakReference<Fragment>){

    private var requiredPermissions = mutableListOf<Permission>()
    private var callback: (Boolean)-> Unit = {}



    //for the dialog TODO: implement show dialog
    private var modalMessage : String? = null
    private var layout : Int? = null
    //private var detCallback: (Map<Permission,Boolean>)-> Unit = {}



    private fun Permission.isGranted(fragment: Fragment) =
        permissions.all { hasPermission(fragment, it) }

    companion object {
        fun from(fragment: Fragment) = PermissionManager(WeakReference(fragment))
    }

    private val checkPermission = fragment.get()?.registerForActivityResult(RequestMultiplePermissions()){
        done(it)
    }

    fun request(vararg  permission: Permission): PermissionManager{
        requiredPermissions.addAll(permission)
        return this
    }

    fun checkPermission(callback : (Boolean)-> Unit){
        this.callback=callback
        handleRequest()
    }

    fun motive(desc: String):PermissionManager{
        modalMessage =desc
        return this
    }


    private fun handleRequest(){
        fragment.get()?.let{
            when{
                areGranted(it)->{  }
                modalMessage!=null -> {
                    showModal()
                }
                else -> requestPermissions()
            }
        }
    }

    private fun areGranted(fragment: Fragment) =
            requiredPermissions.all { it.isGranted(fragment) }

    private fun showModal(){
        /*AlertDialog.Builder(fragment.get()?.context)
            .setTitle("")
            .setMessage(modalMessage)
            .setCancelable(false)
            .setPositiveButton("") { _, _ ->
                requestPermissions()
            }
            .show()*/
        requestPermissions()
    }

    private fun requestPermissions(){
        checkPermission?.launch(getPermissionList())
    }

    private fun getPermissionList() =
        requiredPermissions.flatMap { it.permissions.toList() }.toTypedArray()


    private fun done(grantResults: Map<String, Boolean>) {
        callback(grantResults.all { it.value })
        //detCallback(grantResults.mapKeys { Permission.from(it.key) })
        cleanUp()
    }

    private fun cleanUp() {
        requiredPermissions.clear()
        modalMessage = null
        callback = {}
        //detCallback = {}
    }

    private fun hasPermission(fragment: Fragment, permission: String) =
        ContextCompat.checkSelfPermission(fragment.requireContext(), permission) == PackageManager.PERMISSION_GRANTED

}