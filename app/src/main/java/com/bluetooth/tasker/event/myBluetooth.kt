package com.bluetooth.tasker.event

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.support.v4.app.ActivityCompat.startActivityForResult

object myBluetooth {
    val REQUEST_ENABLE_BT=22
    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    val pairedDevices: Set<BluetoothDevice>?=bluetoothAdapter?.bondedDevices
    var isSupported:Boolean=false
    init {
        if (bluetoothAdapter != null) {
            // Device doesn't support Bluetooth
            isSupported=true

        }
    }


}