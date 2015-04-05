

import grails.rest.render.hal.HalJsonCollectionRenderer
import grails.rest.render.hal.HalJsonRenderer
import org.grails.fencetime.Fish

// Place your Spring DSL code here
beans = {

  // HAL support
  halFishRenderer(HalJsonRenderer, Fish)
  halFishCollectionRenderer(HalJsonCollectionRenderer, Fish) {
    collectionName = 'i18n'
  }
}
