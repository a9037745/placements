package placements

class Student {

  String name
  String coursecode
  String notes
  String applications

    static hasMany = [student: Application]
    }
