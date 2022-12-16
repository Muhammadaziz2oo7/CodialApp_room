package uz.muhamadaziz.roomcodialapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Teacher {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var name: String? = null
    var surname: String? = null
    var fatherName: String? = null
    var courseId: Int? = null

    constructor(id: Int?, name: String?, surname: String?, fatherName: String?, courseId: Int?) {
        this.id = id
        this.name = name
        this.surname = surname
        this.fatherName = fatherName
        this.courseId = courseId
    }

    constructor(name: String?, surname: String?, fatherName: String?, courseId: Int?) {
        this.name = name
        this.surname = surname
        this.fatherName = fatherName
        this.courseId = courseId
    }

    constructor()
}