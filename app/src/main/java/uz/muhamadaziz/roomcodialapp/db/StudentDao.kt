package uz.muhamadaziz.roomcodialapp.db

import androidx.room.*
import uz.muhamadaziz.roomcodialapp.model.Group
import uz.muhamadaziz.roomcodialapp.model.Student
import uz.muhamadaziz.roomcodialapp.model.Teacher

@Dao
interface StudentDao {

    @Insert
    fun insertStudent(student: Student)

    @Query("SELECT * FROM student")
    fun getAllStudents(): List<Student>

    @Query("SELECT * FROM student where id=:id")
    fun getStudentsById(id: Int): Student

    @Update
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Query("SELECT * FROM `student` WHERE groupId=:id")
    fun getStudentByGroupId(id: Int): List<Student>
}