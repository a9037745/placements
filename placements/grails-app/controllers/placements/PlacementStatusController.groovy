package placements

import org.springframework.dao.DataIntegrityViolationException

class PlacementStatusController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [placementStatusInstanceList: PlacementStatus.list(params), placementStatusInstanceTotal: PlacementStatus.count()]
    }

    def create() {
        [placementStatusInstance: new PlacementStatus(params)]
    }

    def save() {
        def placementStatusInstance = new PlacementStatus(params)
        if (!placementStatusInstance.save(flush: true)) {
            render(view: "create", model: [placementStatusInstance: placementStatusInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'placementStatus.label', default: 'PlacementStatus'), placementStatusInstance.id])
        redirect(action: "show", id: placementStatusInstance.id)
    }

    def show() {
        def placementStatusInstance = PlacementStatus.get(params.id)
        if (!placementStatusInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'placementStatus.label', default: 'PlacementStatus'), params.id])
            redirect(action: "list")
            return
        }

        [placementStatusInstance: placementStatusInstance]
    }

    def edit() {
        def placementStatusInstance = PlacementStatus.get(params.id)
        if (!placementStatusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'placementStatus.label', default: 'PlacementStatus'), params.id])
            redirect(action: "list")
            return
        }

        [placementStatusInstance: placementStatusInstance]
    }

    def update() {
        def placementStatusInstance = PlacementStatus.get(params.id)
        if (!placementStatusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'placementStatus.label', default: 'PlacementStatus'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (placementStatusInstance.version > version) {
                placementStatusInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'placementStatus.label', default: 'PlacementStatus')] as Object[],
                          "Another user has updated this PlacementStatus while you were editing")
                render(view: "edit", model: [placementStatusInstance: placementStatusInstance])
                return
            }
        }

        placementStatusInstance.properties = params

        if (!placementStatusInstance.save(flush: true)) {
            render(view: "edit", model: [placementStatusInstance: placementStatusInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'placementStatus.label', default: 'PlacementStatus'), placementStatusInstance.id])
        redirect(action: "show", id: placementStatusInstance.id)
    }

    def delete() {
        def placementStatusInstance = PlacementStatus.get(params.id)
        if (!placementStatusInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'placementStatus.label', default: 'PlacementStatus'), params.id])
            redirect(action: "list")
            return
        }

        try {
            placementStatusInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'placementStatus.label', default: 'PlacementStatus'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'placementStatus.label', default: 'PlacementStatus'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
