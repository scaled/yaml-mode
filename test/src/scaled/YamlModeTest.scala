//
// Scaled Yaml Mode - support for editing Yaml code
// https://github.com/scaled/yaml-mode/blob/master/LICENSE

package scaled.yaml

import org.junit.Assert._
import org.junit._
import scaled._
import scaled.code.CodeConfig
import scaled.grammar._
import scaled.impl.BufferImpl

class YamlModeTest {

  val testYamlCode = Seq(
    //                1         2         3         4         5         6         7         8
    //      012345678901234567890123456789012345678901234567890123456789012345678901234567890123456
    /* 0*/ "name: foo",
    /* 1*/ "description: Some test Yaml Code.",
    /* 2*/ "",
    /* 3*/ "# A comment",
    /* 4*/ "# Wow, comments are great",
    /* 5*/ "stuff:",
    /* 6*/ "  nested_stuff:",
    /* 7*/ "  - list stuff",
    /* 8*/ "  - more list stuff",
    /* 9*/ "",
    /*10*/ "more_stuff:",
    /*11*/ "- top level ilst",
    /*12*/ "- yay",
    /*13*/ "").mkString("\n")

  def yaml = getClass.getClassLoader.getResource("Yaml.ndf")
  val grammars = Seq(Grammar.parseNDF(yaml))

  // @Test def dumpGrammar () :Unit = {
  //   Grammar.parseNDF(yaml).print(System.out)
  // }

  @Test def testScoper () :Unit = {
    val buffer = BufferImpl(new TextStore("Test.yaml", "", testYamlCode))
    val scoper = Grammar.testScoper(
      grammars, buffer, List(new Selector.Processor(new YamlGrammarPlugin().effacers)))
    scoper.rethinkBuffer()
    // println(scoper.showMatchers(Set("#code", "#class")))

    // 0 until buffer.lines.length foreach { ll =>
    //   println(s"$ll: ${buffer.lines(ll)}")
    //   scoper.showScopes(ll) foreach { s => println(s"$ll: $s") }
    // }

    // assertTrue("@link contents scoped as link",
    //            scoper.scopesAt(Loc(3, 61)).contains("markup.underline.link.yamldoc"))
    // assertEquals("@link contents styled as link",
    //              List(CodeConfig.preprocessorStyle), buffer.stylesAt(Loc(3, 61)))
  }
}
