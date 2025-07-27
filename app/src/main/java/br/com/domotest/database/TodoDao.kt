package br.com.domotest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

const val TODO_MAX_SIZE = 256

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoEntity: TodoEntity)

    @Query("SELECT COUNT(*) FROM todo")
    suspend fun getTodoCount(): Int

    @Query("SELECT id FROM todo ORDER BY id ASC LIMIT :count")
    suspend fun getOldestTodoIds(count: Int): List<Long>

    @Transaction
    suspend fun insertAndTrim(todoEntity: TodoEntity) {
        insertTodo(todoEntity)
        val currentCount = getTodoCount()
        if (currentCount > TODO_MAX_SIZE) {
            val excessCount = currentCount - TODO_MAX_SIZE
            val idsToDelete = getOldestTodoIds(excessCount)
            if (idsToDelete.isNotEmpty()) {
                deleteByIds(idsToDelete)
            }
        }
    }

    @Query("DELETE FROM todo WHERE id IN (:ids)")
    suspend fun deleteByIds(ids: List<Long>)

    @Query("DELETE FROM todo")
    suspend fun deleteAllTodos()
}