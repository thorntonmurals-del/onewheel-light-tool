package com.example.onewheellight

import android.bluetooth.*
import android.content.Context
import java.util.UUID

class OnewheelBleManager(private val context: Context) {

    private val BATTERY_CHARACTERISTIC_UUID = UUID.fromString("b22b1e74-32aa-419b-ab04-d19e9cae54d3")

    fun connectToOnewheel(deviceAddress: String, onBatteryUpdated: (Int) -> Unit) {
        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val bluetoothAdapter = bluetoothManager.adapter
        val device = bluetoothAdapter.getRemoteDevice(deviceAddress)
        
        device.connectGatt(context, false, object : BluetoothGattCallback() {
            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    gatt.discoverServices()
                }
            }
            
            override fun onCharacteristicRead(gatt: BluetoothGatt, char: BluetoothGattCharacteristic, status: Int) {
                if (char.uuid == BATTERY_CHARACTERISTIC_UUID) {
                    val batteryLevel = char.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0)
                    onBatteryUpdated(batteryLevel)
                }
            }
        })
    }
}