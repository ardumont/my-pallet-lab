(ns my-pallet-lab.groups.vmfest
  "Node definitions for vmfest"
  (:use
   [pallet.api                        :only [group-spec server-spec node-spec plan-fn]]
   [pallet.crate.automated-admin-user :only [automated-admin-user]])
  (:require
   [pallet.actions                 :refer [package]]
   [pallet.configure               :as pc]
   [my-pallet-lab.groups.provision :as pr]))

(def
  ^{:doc "Defines a group spec that can be passed to converge (creation/termination) or lift (updates)."}
  mygroup-ec2
  (group-spec
   "mygroup"
   :phases {:bootstrap (plan-fn (automated-admin-user))
            :configure (plan-fn
                        (package "curl"))}
   :node-spec (node-spec
               :image {:os-family :debian
                       :image-id :debian-6.0.2.1-64bit-v0.3}
               :hardware {:smallest true
                          :min-cores 1
                          :min-ram 512})))

(comment
  ;; personal function:
  ;; - to start a node
  (pr/start-node mygroup-ec2 :vmfest)
  ;; - to stop one
  (pr/stop-cluster mygroup-ec2 :vmfest)

  ;; to list the nodes
  (pr/pallet.compute/nodes (pc/compute-service :vmfest)))
