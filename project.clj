(defproject my-pallet-lab "0.1.0-SNAPSHOT"
  :description "My pallet learning playground"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.cloudhoist/pallet "0.7.3"]
                 [org.cloudhoist/pallet-jclouds "1.4.3"]
                 ;; To get started we include all jclouds compute providers.
                 ;; You may wish to replace this with the specific jclouds
                 ;; providers you use, to reduce dependency sizes.
                 [org.jclouds/jclouds-allblobstore "1.4.2"]
                 [org.jclouds/jclouds-allcompute "1.4.2"]
                 [org.jclouds.driver/jclouds-slf4j "1.4.2"
                  ;; the declared version is old and can overrule the
                  ;; resolved version
                  :exclusions [org.slf4j/slf4j-api]]
                 [org.jclouds.driver/jclouds-sshj "1.4.2"]
                 [ch.qos.logback/logback-classic "1.0.0"]]
  :dev-dependencies [[org.cloudhoist/pallet
                      "0.7.3" :type "test-jar"]
                     [org.cloudhoist/pallet-lein "0.5.2"]]
  :profiles {:dev
             {:dependencies
              [[org.cloudhoist/pallet "0.7.3" :classifier "tests"]]
              :plugins [[org.cloudhoist/pallet-lein "0.5.2"]]}
             :leiningen/reply
             {:dependencies [[org.slf4j/jcl-over-slf4j "1.7.2"]]
              :exclusions [commons-logging]}}
  :local-repo-classpath true
  :repositories
  {"sonatype-snapshots" "https://oss.sonatype.org/content/repositories/snapshots"
   "sonatype" "https://oss.sonatype.org/content/repositories/releases/"})
