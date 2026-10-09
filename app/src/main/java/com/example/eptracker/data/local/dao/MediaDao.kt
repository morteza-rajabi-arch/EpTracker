
package com.example.eptracker.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eptracker.data.local.entity.MediaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(media: MediaEntity): Long

    @Update
    suspend fun update(media: MediaEntity)

    @Delete
    suspend fun delete(media: MediaEntity)

    @Query("SELECT * FROM media ORDER BY updatedAt DESC")
    fun getAllMedia(): Flow<List<MediaEntity>>


    @Query("SELECT * FROM media WHERE id = :mediaId LIMIT 1")
    fun getMediaById(mediaId: Long): Flow<MediaEntity?>

    @Query("SELECT * FROM media WHERE title LIKE '%' || :query || '%' ORDER BY title ASC")
    fun searchMedia(query: String): Flow<List<MediaEntity>>

    @Query("SELECT * FROM media WHERE type = :type ORDER BY updatedAt DESC")
    fun getMediaByType(type: String): Flow<List<MediaEntity>>

    @Query("SELECT * FROM media WHERE status = :status ORDER BY updatedAt DESC")
    fun getMediaByStatus(status: String): Flow<List<MediaEntity>>

    @Query("SELECT * FROM media WHERE folderId = :folderId ORDER BY updatedAt DESC")
    fun getMediaByFolder(folderId: Long): Flow<List<MediaEntity>>

    @Query("UPDATE media SET lastWatchedEpisode = lastWatchedEpisode + 1, updatedAt = :updatedAt WHERE id = :mediaId")
    suspend fun incrementEpisode(mediaId: Long, updatedAt: Long = System.currentTimeMillis())

    @Query("UPDATE media SET lastWatchedEpisode = CASE WHEN lastWatchedEpisode > 0 THEN lastWatchedEpisode - 1 ELSE 0 END, updatedAt = :updatedAt WHERE id = :mediaId")
    suspend fun decrementEpisode(mediaId: Long, updatedAt: Long = System.currentTimeMillis())

    @Query("SELECT COUNT(*) FROM media")
    fun getMediaCount(): Flow<Int>
}