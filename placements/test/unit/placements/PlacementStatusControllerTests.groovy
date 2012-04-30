package placements



import org.junit.*
import grails.test.mixin.*

@TestFor(PlacementStatusController)
@Mock(PlacementStatus)
class PlacementStatusControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/placementStatus/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.placementStatusInstanceList.size() == 0
        assert model.placementStatusInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.placementStatusInstance != null
    }

    void testSave() {
        controller.save()

        assert model.placementStatusInstance != null
        assert view == '/placementStatus/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/placementStatus/show/1'
        assert controller.flash.message != null
        assert PlacementStatus.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/placementStatus/list'


        populateValidParams(params)
        def placementStatus = new PlacementStatus(params)

        assert placementStatus.save() != null

        params.id = placementStatus.id

        def model = controller.show()

        assert model.placementStatusInstance == placementStatus
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/placementStatus/list'


        populateValidParams(params)
        def placementStatus = new PlacementStatus(params)

        assert placementStatus.save() != null

        params.id = placementStatus.id

        def model = controller.edit()

        assert model.placementStatusInstance == placementStatus
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/placementStatus/list'

        response.reset()


        populateValidParams(params)
        def placementStatus = new PlacementStatus(params)

        assert placementStatus.save() != null

        // test invalid parameters in update
        params.id = placementStatus.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/placementStatus/edit"
        assert model.placementStatusInstance != null

        placementStatus.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/placementStatus/show/$placementStatus.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        placementStatus.clearErrors()

        populateValidParams(params)
        params.id = placementStatus.id
        params.version = -1
        controller.update()

        assert view == "/placementStatus/edit"
        assert model.placementStatusInstance != null
        assert model.placementStatusInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/placementStatus/list'

        response.reset()

        populateValidParams(params)
        def placementStatus = new PlacementStatus(params)

        assert placementStatus.save() != null
        assert PlacementStatus.count() == 1

        params.id = placementStatus.id

        controller.delete()

        assert PlacementStatus.count() == 0
        assert PlacementStatus.get(placementStatus.id) == null
        assert response.redirectedUrl == '/placementStatus/list'
    }
}
