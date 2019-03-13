package com.bluetooth.tasker.event

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import com.bluetooth.tasker.event.activityutil.bind
import com.bluetooth.tasker.event.myBluetooth.REQUEST_ENABLE_BT

class MainActivity : AppCompatActivity() {

    private val switch_Appon: Switch by bind(R.id.switch_app_enable)
    private val switch_cleintmsg: Switch by bind(R.id.switch_cleintmessage)
    private val switch_showtoast: Switch by bind(R.id.switch_showtoast)
    private val button_setting: Switch by bind(R.id.button_setting)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switch_Appon.setOnCheckedChangeListener(checklistener)
        switch_cleintmsg.setOnCheckedChangeListener(checklistener)
        switch_showtoast.setOnCheckedChangeListener(checklistener)
        button_setting.setOnClickListener(clicklistener)
        if (UserInstance.isCleintmesage) switch_cleintmsg.setChecked(true) else switch_cleintmsg.setChecked(false)
        if (UserInstance.isToastOn) switch_showtoast.setChecked(true) else switch_showtoast.setChecked(false)
        if (UserInstance.isAppOn) switch_Appon.setChecked(true) else switch_Appon.setChecked(false)
        if (UserInstance.isAppOn) startapp()
    }


    private val checklistener = CompoundButton.OnCheckedChangeListener { view, isChecked ->
        when (view.id) {
            R.id.switch_showtoast -> UserInstance.isToastOn = isChecked
            R.id.switch_cleintmessage -> UserInstance.isCleintmesage = isChecked
            R.id.switch_app_enable -> {
                UserInstance.isAppOn = isChecked
                findBluetooth()
            }
        }
    }


    private fun startapp() {
        if (!myBluetooth.isSupported) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_LONG).show()
            switch_Appon.setChecked(false)
            switch_Appon.setEnabled(false)
            return
        }
        if (myBluetooth.bluetoothAdapter?.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }
        Toast.makeText(this, "Bluetooth name: ${myBluetooth.bluetoothAdapter?.name}\n " +
                "Bluetooth Adress: ${myBluetooth.bluetoothAdapter?.address}", Toast.LENGTH_LONG).show()
        findBluetooth()

        //todo start the app
    }

    private val clicklistener=View.OnClickListener{view->
        when(view.id){
            R.id.button_setting->startSettingFragment()
        }
    }

    private fun startSettingFragment() {

    }


    private fun findBluetooth() {

    }
}
