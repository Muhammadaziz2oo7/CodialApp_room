package uz.muhamadaziz.roomcodialapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.muhamadaziz.roomcodialapp.model.Course

@Dao
interface CourseDao {

    @Insert
    fun insertCourse(course: Course)

    @Query("SELECT * FROM course")
    fun getAllCourse(): List<Course>




}