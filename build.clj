(ns build
  (:refer-clojure :exclude [test])
  (:require [clojure.tools.deps :as t]
            [clojure.tools.build.api :as b]
            [deps-deploy.deps-deploy :as dd]))

(def lib 'im.kimi/clj-chart)
(def version "0.1.11")
#_ ; alternatively, use MAJOR.MINOR.COMMITS:
(def version (format "1.0.%s" (b/git-count-revs nil)))
(def class-dir "target/classes")

(defn test "Run all the tests." [opts]
  (println "\nRunning tests...")
  (let [basis    (b/create-basis {:aliases [:test]})
        combined (t/combine-aliases basis [:test])
        cmds     (b/java-command
                  {:basis basis
                   :java-opts (:jvm-opts combined)
                   :main      'clojure.main
                   :main-args ["-m" "kaocha.runner"]})
        {:keys [exit]} (b/process cmds)]
    (when-not (zero? exit) (throw (ex-info "Tests failed" {}))))
  opts)

(defn- jar-opts [opts]
  (assoc opts
         :lib lib :version version
         :jar-file (format "target/%s-%s.jar" lib version)
         :scm {:tag (str "v" version)}
         :basis (b/create-basis {})
         :class-dir class-dir
         :target "target"
         :src-dirs ["src"]
         :pom-data [[:licenses
                     [:license
                      [:name "Apache-2.0"]
                      [:url "https://www.apache.org/licenses/LICENSE-2.0.txt"]]]]))

(defn ci "Run the CI pipeline of tests (and build the JAR)." [opts]
  (test opts)
  (b/delete {:path "target"})
  (let [opts (jar-opts opts)]
    (println "\nWriting pom.xml...")
    (b/write-pom opts)
    (println "\nCopying source...")
    (b/copy-dir {:src-dirs ["resources" "src"] :target-dir class-dir})
    (println "\nBuilding JAR...")
    (b/jar opts))
  opts)

(defn install "Install the JAR locally." [opts]
  (let [opts (jar-opts opts)]
    (b/install opts))
  opts)

(defn deploy "Deploy the JAR to Clojars." [opts]
  (let [{:keys [jar-file] :as opts} (jar-opts opts)]
    (dd/deploy {:installer :remote :artifact (b/resolve-path jar-file)
                :pom-file (b/pom-path (select-keys opts [:lib :class-dir]))}))
  opts)