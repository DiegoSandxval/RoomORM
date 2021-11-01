package com.example.roomorm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomorm.dao.BrandDao
import com.example.roomorm.dao.VehicleDao
import com.example.roomorm.entities.Brand
import com.example.roomorm.entities.Vehicle
// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Vehicle::class, Brand::class], version = 1, exportSchema = false)
abstract class VehicleRoomDatabase :RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun brandDao(): BrandDao
    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: VehicleRoomDatabase? = null
        fun getDatabase(context: Context): VehicleRoomDatabase{
// if the INSTANCE is not null, then return it,
// if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VehicleRoomDatabase::class.java,
                    "vehicle_database"
                ).build()
                INSTANCE = instance
// return instance
                instance
            }
        }
    }
}
