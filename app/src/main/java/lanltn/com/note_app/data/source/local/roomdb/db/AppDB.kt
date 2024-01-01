package lanltn.com.note_app.data.source.local.roomdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lanltn.com.note_app.data.source.local.roomdb.entity.NoteEntity
import lanltn.com.note_app.data.source.local.roomdb.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        NoteEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    companion object {
        const val DB_NAME = "note_app.db"
    }

    @Volatile
    private var INSTANCE: AppDB? = null

    // Double-checked locking singleton pattern
    fun getInstance(context: Context): AppDB {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDB::class.java,
                DB_NAME
            )
                .build()
                .also { INSTANCE = it }
        }
    }
}