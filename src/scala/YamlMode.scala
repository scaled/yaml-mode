//
// Scaled YAML Mode - a Scaled major mode for editing YAML files
// http://github.com/scaled/yaml-mode/blob/master/LICENSE

package scaled.yaml

import scaled._
import scaled.code.Indenter
import scaled.grammar._
import scaled.code.{CodeConfig, Commenter}

@Plugin(tag="textmate-grammar")
class YamlGrammarPlugin extends GrammarPlugin {
  import EditorConfig._
  import CodeConfig._

  override def grammars = Map("source.yaml" -> "YAML.ndf")

  override def effacers = List(
    effacer("comment.line", commentStyle),
    effacer("comment.block", docStyle),
    effacer("constant", constantStyle),
    effacer("invalid", warnStyle),
    effacer("keyword", keywordStyle),
    effacer("string", stringStyle)
  )

  override def syntaxers = List(
    syntaxer("comment.line", Syntax.LineComment),
    syntaxer("comment.block", Syntax.DocComment),
    syntaxer("constant", Syntax.OtherLiteral),
    syntaxer("string.quoted.single", Syntax.StringLiteral),
    syntaxer("string.quoted.double", Syntax.StringLiteral)
  )
}

@Major(name="yaml",
       tags=Array("code", "project", "yaml"),
       pats=Array(".*\\.yaml", ".*\\.yml"),
       desc="A major mode for editing YAML files.")
class YamlMode (env :Env) extends GrammarCodeMode(env) {

  override def dispose () {} // nada for now

  override def langScope = "source.yaml"

  // override def createIndenter() = new YamlIndenter(config)

  override val commenter = new Commenter() {
    override def linePrefix  = "#"
    override def blockOpen   = "#"
    override def blockPrefix = "#"
  }
}
