package uz.muhamadaziz.roomcodialapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.muhamadaziz.roomcodialapp.model.Group

@Dao
interface GroupDao {

    @Insert
    fun insertGroup(group: Group)

    @Query("SELECT * FROM `group` where id=:id")
    fun getGroupById(id:Int): Group

    @Query("SELECT * FROM `Group`")
    fun getAllGroup(): List<Group>

    @Update
    fun updateGroup(group: Group)

    @Delete
    fun deleteGroup(group: Group)

    @Query("SELECT * FROM `Group` WHERE teacherId=:id")
    fun getGroupByTeacherId(id: Int): List<Group>

}