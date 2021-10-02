package com.example.myfaith.model


import android.content.Context
import androidx.room.*
import androidx.room.Database
import androidx.room.OnConflictStrategy.IGNORE

@Entity
data class Favorite(
        @PrimaryKey val churchId: Int
)

@Dao
interface FavoriteDao {
    @Insert(onConflict = IGNORE)
    fun insertFavorite(f: Favorite)

    @Delete
    fun deleteFavorite(f: Favorite)

    @Query("SELECT * FROM favorite")
    fun getAll(): List<Favorite>
}

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    companion object {
        private var INSTANCE: FavoriteDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?: buildDatabase(context).also {
            INSTANCE = it
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FavoriteDatabase::class.java, "favorites.db"
        ).build()
    }
}


