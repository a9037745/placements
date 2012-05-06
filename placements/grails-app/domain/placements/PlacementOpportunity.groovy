package placements

class PlacementOpportunity {

  String jobTitle
  String companyName
  String applications
  String status

    static hasMany = [placement: Application]
    }
