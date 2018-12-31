package parcours

import scala.io.Source
import scala.util.Try


object ParcoursTondeuse {
  def main(args: Array[String]): Unit = {
    //println(args.apply(0))
    if(Try(args.apply(0)).isSuccess) {
      lazy val path = args.apply(0)

      if (readFileTry(path).isSuccess) {

        lazy val ln = readFile(path)
        lazy val pelouse = new Pelouse
        if (ln.hasNext) initialisationPelouse(ln.next, pelouse)

        print("Coordonnées pelouse : ")
        pelouse.affichage

        while (ln.hasNext) {
          lazy val tondeuse = new Tondeuse
          initialisationTondeuse(ln.next, tondeuse)

          print("Coordonnées initiales tondeuse : ")
          tondeuse.affichage

          if (ln.hasNext) parcoursTotal(ln.next, tondeuse, pelouse)
          print("Coordonnées finales tondeuse : ")
          tondeuse.affichage
        }

      } else println("Fichier introuvable.")

    } else println("Chemin du fichier non spécifié.")
  }

  def readFileTry(path: String): Try[Iterator[String]] = {
    Try(Source.fromFile(path).getLines)
  }

  def readFile(path: String): Iterator[String] = {
    Source.fromFile(path).getLines
  }

  /*def readFile(path: String): Option[Iterator[String]] = path  match {
    case path => Some(Source.fromFile(path).getLines)
    case _ => None
  }*/

  def initialisationPelouse(lines: String, pelouse: Pelouse) ={
    if(!(lines.isEmpty)) {
      lazy val coordPelouse = lines.map(x => x.toString).filter(x => ! (x.contains(" ")))

      pelouse.abscisse_fin = coordPelouse.apply(0).toInt
      pelouse.ordonnee_fin = coordPelouse.apply(1).toInt
    }
  }

  def initialisationTondeuse(lines: String, tondeuse: Tondeuse) ={
    if(!(lines.isEmpty)) {
      lazy val coordTondeuse = lines.map(x => x.toString).filter(x => ! (x.contains(" ")))

      tondeuse.abscisse = coordTondeuse.apply(0).toInt

      tondeuse.ordonnee = coordTondeuse.apply(1).toInt

      tondeuse.position = coordTondeuse.apply(2).charAt(0)

    }
  }

  def parcoursPartielNord(cr: Char, td: Tondeuse, pel: Pelouse) = cr match {
    case 'A' => if(td.ordonnee < pel.ordonnee_fin) td.ordonnee += 1
    case 'D' => td.position = 'E'
    case 'G' => td.position = 'O'
    case _ => None
  }

  def parcoursPartielSud(cr: Char, td: Tondeuse, pel: Pelouse) = cr match {
    case 'A' => if(td.ordonnee < pel.ordonnee_fin) td.ordonnee -= 1
    case 'D' => td.position = 'O'
    case 'G' => td.position = 'E'
    case _ => None
  }

  def parcoursPartielEst(cr: Char, td: Tondeuse, pel: Pelouse) = cr match {
    case 'A' => if(td.abscisse < pel.abscisse_fin) td.abscisse += 1
    case 'D' => td.position = 'S'
    case 'G' => td.position = 'N'
    case _ => None
  }

  def parcoursPartielOuest(cr: Char, td: Tondeuse, pel: Pelouse) = cr match {
    case 'A' => if(td.abscisse < pel.abscisse_fin) td.abscisse -= 1
    case 'D' => td.position = 'N'
    case 'G' => td.position = 'S'
    case _ => None
  }

  def parcoursPartiel(ch: Char, td: Tondeuse,  pel: Pelouse): Unit = td.position match {
    case 'N' => parcoursPartielNord(ch, td, pel)
    case 'S' => parcoursPartielSud(ch, td, pel)
    case 'E' => parcoursPartielEst(ch, td, pel)
    case 'O' => parcoursPartielOuest(ch, td, pel)
    case _ => None
  }

  def parcoursTotal(path: String, td: Tondeuse,  pel: Pelouse): Unit = {
    if(path.length > 0){
      parcoursPartiel(path.charAt(0), td, pel)
      if(path.length > 1) parcoursTotal(path.substring(1), td, pel)
    }
  }
}
