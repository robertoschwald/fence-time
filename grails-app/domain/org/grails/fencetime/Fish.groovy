package org.grails.fencetime

import grails.rest.Resource

class Fish {

    String name
    String latinName

    static hasMany = [i18ns:FishI18n]

    static constraints = {
        name blank:false
        latinName blank:true
    }
}
