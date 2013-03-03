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
  mygroup-vbox
  (group-spec
   "mygroup-vbox"
   :phases {:bootstrap (plan-fn (automated-admin-user))
            :configure (plan-fn
                        (package "curl")
                        ;; (package "wget")
                        ;; (package "git")
                        ;; (package "emacs24")
                        ;; your crates (set of packages or software to install) here
                        )}
   :node-spec (node-spec
               :image {:os-family :debian
                       :image-id :debian-6.0.2.1-64bit-v0.3}
               :hardware {:smallest true
                          :min-cores 1
                          :min-ram 512})))

(comment
  ;; personal function:
  ;; - to start a node using vbox service (vmfest under cf. ~/.pallet/services/vbox.clj)
  (pr/start-node mygroup-vbox :vbox)
  ;; - to stop one
  (pr/stop-cluster mygroup-vbox :vbox)

  ;; to list the nodes
  (pallet.compute/nodes (pc/compute-service :vbox)))
