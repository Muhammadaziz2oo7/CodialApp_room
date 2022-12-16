package uz.muhamadaziz.roomcodialapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.muhamadaziz.roomcodialapp.model.Course
import uz.muhamadaziz.roomcodialapp.model.Group
import uz.muhamadaziz.roomcodialapp.model.Student
import uz.muhamadaziz.roomcodialapp.model.Teacher

@Database(entities = [Course::class, Teacher::class, Group::class, Student::class], version = 1)
abstract class MyDataBase : RoomDatabase() {

    abstract fun courseDao(): CourseDao
    abstract fun teacherDao(): TeacherDao
    abstract fun groupDao(): GroupDao
    abstract fun studentDao(): StudentDao
    companion object {
        private var instance: MyDataBase? = null

        @Synchronized
        fun getInstance(context: Context): MyDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDataBase::class.java,
                    "roomDataBase_db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}