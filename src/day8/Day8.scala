package day8

import adventOfCode.LinesParser

import scala.jdk.CollectionConverters._

object Day8 extends App{
  val INPUT_FILE = "C:\\Learn\\advent-of-code\\src\\adventOfCode\\inputDay8.txt"

  val instructions: List[String] = LinesParser.getLinesFromFile(INPUT_FILE).asScala.toList
  val parseNum = (s: String) => Integer.parseInt(s.substring(5, s.length)) * (if (s.charAt(4).equals('+')) 1 else -1)
  val finalLine = instructions.size

  def puzzle1(checked: Set[Integer],acc: Int, currentIndex: Int, instruction: String, prevIndex: Integer): (Int, Int) = {
    if (checked.contains(currentIndex)) (acc, currentIndex)
    else {
      val updatedChecked = checked + currentIndex
      val value = parseNum(instruction)
      instructions match {
        case _ if instruction.startsWith("acc") =>
          puzzle1(updatedChecked, acc + value, currentIndex + 1, instructions(currentIndex + 1), currentIndex)
        case _ if instruction.startsWith("jmp") =>
          puzzle1(updatedChecked, acc, currentIndex + value, instructions(currentIndex + value), currentIndex)
        case _ if instruction.startsWith("nop") =>
          puzzle1(updatedChecked, acc, currentIndex + 1, instructions(currentIndex + 1), currentIndex)
        case _ => (acc, currentIndex)
      }
    }

  }

  print(s"Solution: ${puzzle1(Set.empty, 0,0,instructions(0), null)._1}")

}
