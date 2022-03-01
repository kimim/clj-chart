(defproject kimim/clj-chart "0.1.7"
  :description "A clojure lib wraps XChart"
  :url "http://github.com/kimim/clj-chart"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.knowm.xchart/xchart "3.8.1"]]
  :plugins [[lein-localrepo "0.5.4"]]
  :source-paths ["src"]
  :main ^:skip-aot clj-chart.core
  :repl-options {:init-ns clj-chart.core}
  :profiles {:dev
             {:dependencies [[org.slf4j/slf4j-simple "1.7.32"]]}
             :codox
             {:dependencies [[codox-theme-rdash "0.1.2"]]
              :plugins [[lein-codox "0.10.7"]]
              :codox {:project {:name "clj-chart"}
                      :themes [:rdash]
                      :metadata {:doc/format :markdown}
                      :source-paths ["src"]
                      :source-uri "https://github.com/kimim/clj-chart/blob/master/{filepath}#L{line}"
                      :output-path "docs"}}}
  :aliases {"codox" ["with-profile" "codox" "codox"]}
  :repositories [["sonatype" "https://oss.sonatype.org/content/repositories/snapshots/"]])
