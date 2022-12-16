package uz.muhamadaziz.roomcodialapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Group {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var groupPosition: Int? = null
    var groupTitle: String? = null
    var groupTeacherName: String? = null
    var groupTime: String? = null
    var groupDay: String? = null
    var teacherId: Int? = null
    var courseId: Int? = null

    constructor(
        id: Int?,
        groupPosition: Int?,
        groupTitle: String?,
        groupTeacherName: String?,
        groupTime: String?,
        groupDay: String?,
        teacherId: Int?,
        courseId: Int
    ) {
        this.id = id
        this.groupPosition = groupPosition
        this.groupTitle = groupTitle
        this.groupTeacherName = groupTeacherName
        this.groupTime = groupTime
        this.groupDay = groupDay
        this.teacherId = teacherId
        this.courseId = courseId
    }

    constructor(
        groupPosition: Int?,
        groupTitle: String?,
        groupTeacherName: String?,
        groupTime: String?,
        groupDay: String?,
        teacherId: Int?,
        courseId: Int
    ) {
        this.groupPosition = groupPosition
        this.groupTitle = groupTitle
        this.groupTeacherName = groupTeacherName
        this.groupTime = groupTime
        this.groupDay = groupDay
        this.teacherId = teacherId
        this.courseId = courseId
    }

    constructor()
}