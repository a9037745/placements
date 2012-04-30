import placements.Status

class BootStrap {

    def init = { servletContext ->

  def applied = new Status(code:'001',description:'Applied').save();
  def notinvited = new Status(code:'002',description:'Not invited for interview').save();
def invited = new Status(code:'003',description:'Invited for interview').save();
def notoffered = new Status(code:'004',description:'Not offered').save();
def offered = new Status(code:'005',description:'Offered').save();
def accepted = new Status(code:'006',description:'Accepted').save();
def rejected = new Status(code:'007',description:'Rejected').save();

    }
    def destroy = {
    }
}

