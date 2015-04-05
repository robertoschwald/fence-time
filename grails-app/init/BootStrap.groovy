import org.grails.fencetime.Fish
import org.grails.fencetime.FishI18n

class BootStrap {

  def init = { servletContext ->

    log.info "START BOOTSTRAP INIT"

    createFish(
      "Brown Trout",
      "Salmo trutta fario",
      "https://de.wikipedia.org/wiki/Bachforelle",
      "Die Bachforelle (Salmo trutta fario) ist ein zu den Salmoniden zählender Raubfisch und eine Unterart der Forelle. Sie wird auch Flussforelle, Bergforelle oder Fario genannt. Kleinwüchsige Bachforellen in nahrungsarmen Gewässern werden als Steinforellen bezeichnet. Bachforellen werden je nach Nahrungsangebot 20 bis 80 Zentimeter lang. Ihr Rücken ist oliv-schwarzbraun und silbrig blau, bauchwärts treten rote Flecken mit hellem Rand auf, die Bauchseite ist weißgelb. Die Bachforelle erreicht in der Regel ein Gewicht von bis zu zwei Kilogramm. Bachforellen können bis zu 18 Jahre alt werden.",
      "de"
    )

    createFish(
      "Rainbow Trout",
      "Oncorhynchus mykiss",
      "https://de.wikipedia.org/wiki/Regenbogenforelle",
      "Die Regenbogenforelle (Oncorhynchus mykiss, früher: Salmo gairdneri und S. irideus) ist ein aus Nordamerika stammender, raschwüchsiger Salmonide, der in der zweiten Hälfte des 19. Jahrhunderts in England als Speisefisch gezüchtet und ab 1882 durch Hofer auch in Württemberg teichwirtschaftlich erzeugt wurde. Später wurde der Fisch in verschiedene Fließ- und Stehgewässer Europas besetzt. In vielen Forellenbächen und -flüssen wird er heute, auch mittels Gesetzesauflagen, wieder verfolgt, weil er beispielsweise einheimische Bachforellen verdrängen kann.",
      "de"
    )
  }


  def destroy = {
  }

  def createFish = { String _name, String _latinName, String _url, String _description, String _lang ->
    Fish fish = Fish.findOrCreateByName _name
    fish.with {
      latinName = _latinName

    }
    fish.save failOnError:true
    FishI18n fishI18n = FishI18n.findOrCreateByFish fish
    fishI18n.with {
      text = _description
      language = _lang
    }
    fish.i18ns = [fishI18n]
    fishI18n.save failOnError:true
  }
}
