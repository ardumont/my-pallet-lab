(ns my-pallet-lab.core
  (:require [environ.core          :as env]
            [pallet.core           :as p]
            [pallet.phase          :as ph]
            [pallet.action.package :as pa]))

;; provider's credentials

(def aws-creds
  {:access-key (env/env :aws-access-key)
   :secret-key (env/env :aws-secret-key)})

(comment

  ;; node specification (to start a node via a provider service)
  ;; An empty :image specification implies a default image will be used for the nodes, usually the latest Ubuntu version, or CentOS if no Ubuntu images are available.
  ;; An empty :hardware specification implies the smallest hardware configuration will be used.

  (def mynodes
    (p/node-spec
     :image    {:os-family :ubuntu :os-version-matches "12.10"}
     :hardware {:min-cores 1 :min-ram 512}
     :network  {:inbound-ports [22 80]}))

  ;; server specification (define a set of configuration to apply to the machine - permit to add phase to run during the lift or converge operations)

  (def with-wget
    (p/server-spec
     :phases  {:configure (ph/phase-fn
                           (pa/package "wget"))}))

  (def with-curl
    (p/server-spec
     :phases {:configure (ph/phase-fn
                          (pa/package "curl"))}))

  ;; group-spec (map node spec to their server-spec)

  (def mygroup
    (p/group-spec
     "mygroup"
     :extends [with-curl with-wget]
     :node-spec my-nodes)))

(def mygroup
  (p/group-spec
   "mygroup"
   :phases {:configure (ph/phase-fn
                        (pa/package "curl")
                        (pa/package "wget")
                        (pa/package "git")
                        (pa/package "emacs24"))}
   :node-spec (p/node-spec
               :image    {:os-family :ubuntu :os-version-matches "12.10"}
               :hardware {:min-cores 1 :min-ram 512}
               :network  {:inbound-ports [22 80]})))
