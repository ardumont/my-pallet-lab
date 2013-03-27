(defproject my-pallet-lab "0.1.0-SNAPSHOT"
  :description "My pallet learning playground"
  :dependencies [[org.clojure/clojure                            "1.4.0"]

                 ;; The latest pallet
                 [com.palletops/pallet                           "0.8.0-beta.5"]

                 ;; The latest jclouds fun
                 [org.cloudhoist/pallet-jclouds                  "1.5.2"]
                 [org.cloudhoist/pallet-vmfest                   "0.2.1"]
                 [vmfest                                         "0.2.8"]
                 [org.jclouds/jclouds-allblobstore               "1.5.5"]

                 [org.jclouds/jclouds-allcompute                 "1.5.5"]
                 [org.jclouds.driver/jclouds-slf4j               "1.5.5"]
                 [org.jclouds.driver/jclouds-jsch                "1.5.5"]
                 [org.jclouds/jclouds-compute                    "1.5.5"]
                 [org.jclouds/jclouds-blobstore                  "1.5.5"]
                 [org.slf4j/slf4j-api                            "1.6.1"]
                 [ch.qos.logback/logback-core                    "1.0.0"]
                 [ch.qos.logback/logback-classic                 "1.0.0"]

                 ;; Rackspace London and Greenqloud jclouds providers,
                 ;; becuase if you are going to be in the cloud you
                 ;; might as well be green. See
                 ;; https://www.mastodonc.com/dashboard for more
                 ;; information.
                 [org.jclouds.provider/rackspace-cloudservers-uk "1.5.5"]
                 [org.jclouds.labs/greenqloud-compute            "1.5.5"]
                 ]
  :exclusions [org.jclouds/jclouds-allblobstore
               org.jclouds/jclouds-allcompute
               org.jclouds/jclouds-compute
               org.jclouds/jclouds-blobstore
               org.jclouds.driver/jclouds-slf4j
               org.jclouds.driver/jclouds-jsch
               org.jclouds.labs/greenqloud-compute]
  :local-repo-classpath true
  :repositories
  {"sonatype-snapshots" "https://oss.sonatype.org/content/repositories/snapshots"
   "sonatype" "https://oss.sonatype.org/content/repositories/releases/"})
