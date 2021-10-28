package com.example.roomorm.repository

import android.content.Context
import com.example.roomorm.dao.VehicleDao
import com.example.roomorm.database.VehicleRoomDatabase
import com.example.roomorm.entities.Vehicle
import kotlinx.coroutines.flow.Flow

class VehicleRepository(
    private val vehicleDao: VehicleDao) {
    companion object {
        private var INSTANCE : VehicleRepository? = null
        fun getRepository(context: Context) : VehicleRepository {
            return INSTANCE ?: synchronized(this) {
                val database = VehicleRoomDatabase.getDatabase(context)
                val instance = VehicleRepository(database.vehicleDao())
                INSTANCE = instance
                instance
            }
        }

}
    val allVehicles: Flow<List<Vehicle>> = vehicleDao.getAlphabetizedVehicles()
    suspend fun insert(vehicle: Vehicle) {
        vehicleDao.insert(vehicle)
    }
    suspend fun deleteAll() {
        vehicleDao.deleteAll()
    }
    suspend fun deleteById(id:Int){
        vehicleDao.deleteById(id)
    }
}