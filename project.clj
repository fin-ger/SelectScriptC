(defproject selectscript "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure   "1.8.0"]]
  ;;[clj-antlr "0.2.2"]]
                 ;;[lein-shell            "0.5.0"]]
  :resource-paths ["resources/antlr-4.5.3-complete.jar"]
  :java-source-paths ["src/antlr"]
  :main selectscript.core)
