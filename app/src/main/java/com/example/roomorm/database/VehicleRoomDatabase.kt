package com.example.roomorm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomorm.dao.VehicleDao
import com.example.roomorm.entities.Vehicle

@Database(entities = [Vehicle::class], version = 1, exportSchema = false)
abstract class VehicleRoomDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    companion object {

        @Volatile
        private var INSTANCE: VehicleRoomDatabase? = null
        fun getDatabase(context: Context): VehicleRoomDatabase{

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VehicleRoomDatabase::class.java,
                    "vehicle_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}