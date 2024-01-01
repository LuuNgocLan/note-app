package lanltn.com.note_app.data.source.local.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import lanltn.com.note_app.data.source.local.roomdb.entity.NoteEntity

@Dao

interface NoteDao {
    @Query("SELECT * FROM notes")
    fun observeAll():LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :noteiId")
    fun observeById(noteiId: Int): LiveData<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Insert
    suspend fun insertMany(notes: List<NoteEntity>)

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)
}