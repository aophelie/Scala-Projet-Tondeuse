package parcours

import scala.io.Source

object ParcoursTondeuse {
  def main(args: Array[String]): Unit = {
    //println("Hello World")
    val tondeuse = new Tondeuse
    val pelouse = new Pelouse
    tondeuse.affichage
    val path ="D:\\M2-IFLogiciels\\Scala\\Projet-M2-Tondeuse\\test.txt"
    val ln = readFile(path)
    initialisationPelouse(ln.next(), pelouse)
    pelouse.affichage
    //println(ln.next())
    initialisationTondeuse(ln.next(), tondeuse)
    tondeuse.affichage
    //println(ln.next())
    parcoursTotal(ln.next(), tondeuse, pelouse)
    tondeuse.affichage
  }

  def readFile(path: String): Iterator[String] = {
    Source.fromFile(path).getLines
  }

  def initialisationPelouse(lines: String, pelouse: Pelouse) ={
    if(!(lines.isEmpty)) {
      val coordPelouse = lines.map(x => x.toString).filter(x => ! (x.contains(" ")))
      //println(coordPelouse)
      pelouse.abscisse_fin = coordPelouse.apply(0).toInt
      pelouse.ordonnee_fin = coordPelouse.apply(1).toInt
    }
  }

  def initialisationTondeuse(lines: String, tondeuse: Tondeuse) ={
    if(!(lines.isEmpty)) {
      val coordTondeuse = lines.map(x => x.toString).filter(x => ! (x.contains(" ")))
      //println(coordTondeuse)
      tondeuse.abscisse = coordTondeuse.apply(0).toInt
      //println(tondeuse.abscisse)
      tondeuse.ordonnee = coordTondeuse.apply(1).toInt
      //println(tondeuse.ordonnee)
      tondeuse.position = coordTondeuse.apply(2).charAt(0)
      //println(tondeuse.position)
    }
  }

  def parcoursPartielNord(cr: Char, td: Tondeuse, pel: Pelouse) = cr match {
    case 'A' => if(td.ordonnee < pel.ordonnee_fin) td.ordonnee += 1
    case 'D' => td.position = 'E'
    case 'G' => td.position = 'O'
    case _ => Nil
  }

  def parcoursPartielSud(cr: Char, td: Tondeuse, pel: Pelouse) = cr match {
    case 'A' => if(td.ordonnee < pel.ordonnee_fin) td.ordonnee -= 1
    case 'D' => td.position = 'O'
    case 'G' => td.position = 'E'
    case _ => Nil
  }

  def parcoursPartielEst(cr: Char, td: Tondeuse, pel: Pelouse) = cr match {
    case 'A' => if(td.abscisse < pel.abscisse_fin) td.abscisse += 1
    case 'D' => td.position = 'S'
    case 'G' => td.position = 'N'
    case _ => Nil
  }

  def parcoursPartielOuest(cr: Char, td: Tondeuse, pel: Pelouse) = cr match {
    case 'A' => if(td.abscisse < pel.abscisse_fin) td.abscisse -= 1
    case 'D' => td.position = 'N'
    case 'G' => td.position = 'S'
    case _ => Nil
  }

  def parcoursPartiel(ch: Char, td: Tondeuse,  pel: Pelouse): Unit = td.position match {
    case 'N' => parcoursPartielNord(ch, td, pel)
    case 'S' => parcoursPartielSud(ch, td, pel)
    case 'E' => parcoursPartielEst(ch, td, pel)
    case 'O' => parcoursPartielOuest(ch, td, pel)
    case _ => Nil
  }

  def parcoursTotal(path: String, td: Tondeuse,  pel: Pelouse): Unit = {
    println(path)
    if(path.length > 0){
      parcoursPartiel(path.charAt(0), td, pel)
      if(path.length > 1) parcoursTotal(path.substring(1), td, pel)
    }
  }
}
