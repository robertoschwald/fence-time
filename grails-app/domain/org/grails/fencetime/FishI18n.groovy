package org.grails.fencetime

class FishI18n {

    Fish fish
    String language
    String text

    static belongsTo = [Fish]

    static constraints = {
        language maxSize:2, unique:['fish']
        text nullable:true, maxSize:100000
    }

    static mapping = {
        cache true
    }

    static namedQueries = {
        forFish { domain, lang ->
            fish {
                eq 'id', domain.id
            }
            forLang lang
        }

        forLang { lang ->
            eq 'language', lang
        }
    }

}
