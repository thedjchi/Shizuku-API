package com.thedjchi.shizukux.exampleapp.data

import android.content.Context
import android.os.IDeviceIdentifiersPolicyService
import androidx.annotation.Keep
import androidx.annotation.RequiresPermission
import com.thedjchi.shizukux.exampleapp.IUserService
import rikka.shizuku.SystemServiceHelper
import kotlin.system.exitProcess

class UserService : IUserService.Stub {
    @Suppress("unused")
    constructor()

    @Suppress("unused")
    @Keep
    constructor(context: Context)

    override fun destroy() {
        exitProcess(0)
    }

    override fun exit() {
        destroy()
    }

    private val deviceIdentifiersPolicyService by lazy {
        IDeviceIdentifiersPolicyService.Stub.asInterface(
            SystemServiceHelper.getSystemService("device_identifiers")
        )
    }

    @RequiresPermission("android.permission.READ_PRIVILEGED_PHONE_STATE")
    override fun getSerial(): String =
        deviceIdentifiersPolicyService.getSerial()
}
