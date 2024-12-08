package de.flix29.notionApiClient.model.block.blockContent;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProgrammingLanguages {

    ABAP("abap"),
    ARDUINO("arduino"),
    BASH("bash"),
    BASIC("basic"),
    C("c"),
    CLOJURE("clojure"),
    COFFEESCRIPT("coffeescript"),
    C_PLUS_PLUS("c++"),
    C_SHARP("c#"),
    CSS("css"),
    DART("dart"),
    DIFF("diff"),
    DOCKER("docker"),
    ELIXIR("elixir"),
    ELM("elm"),
    ERLANG("erlang"),
    FLOW("flow"),
    FORTRAN("fortran"),
    F_SHARP("f#"),
    GHERKIN("gherkin"),
    GLSL("glsl"),
    GO("go"),
    GRAPHQL("graphql"),
    GROOVY("groovy"),
    HASKELL("haskell"),
    HTML("html"),
    JAVA("java"),
    JAVASCRIPT("javascript"),
    JSON("json"),
    JULIA("julia"),
    KOTLIN("kotlin"),
    LATEX("latex"),
    LESS("less"),
    LISP("lisp"),
    LIVESCRIPT("livescript"),
    LUA("lua"),
    MAKEFILE("makefile"),
    MARKDOWN("markdown"),
    MARKUP("markup"),
    MATLAB("matlab"),
    MERMAID("mermaid"),
    NIX("nix"),
    OBJECTIVE_C("objective-c"),
    OCAML("ocaml"),
    PASCAL("pascal"),
    PERL("perl"),
    PHP("php"),
    PLAIN_TEXT("plain text"),
    POWERSHELL("powershell"),
    PROLOG("prolog"),
    PROTOBUF("protobuf"),
    PYTHON("python"),
    R("r"),
    REASON("reason"),
    RUBY("ruby"),
    RUST("rust"),
    SASS("sass"),
    SCALA("scala"),
    SCHEME("scheme"),
    SCSS("scss"),
    SHELL("shell"),
    SQL("sql"),
    SWIFT("swift"),
    TYPESCRIPT("typescript"),
    VB_DOT_NET("vb.net"),
    VERILOG("verilog"),
    VHDL("vhdl"),
    VISUAL_BASIC("visual basic"),
    WEBASSEMBLY("webassembly"),
    XML("xml"),
    YAML("yaml"),
    JAVA_OR_ANY_C("java/c/c++/c#");

    private final String language;

    public static ProgrammingLanguages fromString(String language) {
        for (ProgrammingLanguages value : ProgrammingLanguages.values()) {
            if (value.getLanguage().equals(language)) {
                return value;
            }
        }
        return null;
    }
}
