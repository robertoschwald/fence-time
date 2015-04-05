package org.grails.fencetime

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FishController extends RestfulController {


    FishController(){
        super(Fish)
    }

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Fish.list(params), model:[fishCount: Fish.count()]
    }

    def show(Fish fish) {
        respond fish
    }

    def create() {
        respond new Fish(params)
    }

    @Transactional
    def save(Fish fish) {
        if (fish == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (fish.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond fish.errors, view:'create'
            return
        }

        fish.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fish.label', default: 'Fish'), fish.id])
                redirect fish
            }
            '*' { respond fish, [status: CREATED] }
        }
    }

    def edit(Fish fish) {
        respond fish
    }

    @Transactional
    def update(Fish fish) {
        if (fish == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (fish.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond fish.errors, view:'edit'
            return
        }

        fish.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fish.label', default: 'Fish'), fish.id])
                redirect fish
            }
            '*'{ respond fish, [status: OK] }
        }
    }

    @Transactional
    def delete(Fish fish) {

        if (fish == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        fish.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fish.label', default: 'Fish'), fish.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fish.label', default: 'Fish'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
