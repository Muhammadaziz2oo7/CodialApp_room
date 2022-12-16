package uz.muhamadaziz.roomcodialapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.muhamadaziz.roomcodialapp.model.Teacher

@Dao
interface TeacherDao {

    @Insert
    fun insertTeacher(teacher: Teacher)

    @Query("SELECT * FROM teacher")
    fun getAllTeachers(): List<Teacher>

    @Query("SELECT * FROM teacher where id=:id")
    fun getTeachersById(id: Int): Teacher

    @Update
    fun updateTeacher(teacher: Teacher)

    @Delete
    fun deleteTeacher(teacher: Teacher)


}