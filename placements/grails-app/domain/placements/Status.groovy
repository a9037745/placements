package placements

class Status {

  String code
  String description

    static hasMany = [status: Application]
    }
