(ns my-pallet-lab.groups.my-pallet-lab
  "Node definitions for my-pallet-lab"
  (:use
   [pallet.api                        :only [group-spec server-spec node-spec converge plan-fn]]
   [pallet.crate.automated-admin-user :only [automated-admin-user]])
  (:require
   [pallet.actions        :refer [package]]
   [pallet.configure      :as pc]))

;; potential amis
;; :image-id "ami-2861685c" - ;; ubuntu 12.10 eu-west-1 i386 instance-store
;; :image-id "ami-7e636a0a" - ;; ubuntu 12.10 eu-west-1 i386 ebs

;; define a specification for a group of machine

(def
  ^{:doc "Defines a group spec that can be passed to converge (creation/termination) or lift (updates)."}
  mygroup
  (group-spec
   "mygroup"
   :phases {:bootstrap (plan-fn (automated-admin-user))
            :configure (plan-fn
                        (package "curl")
                        ;; (package "wget")
                        ;; (package "git")
                        ;; (package "emacs24")
                        ;; your crates (set of packages or software to install) here
                        )}
   :node-spec (node-spec
               :image {:os-family :ubuntu
;;                       :os-version-matches "12.10"
                       :image-id "eu-west-1/ami-2861685c"}
               :hardware {:smallest true
                          :min-cores 1
                          :min-ram 512})))

;; personal functions

(defn scale-cluster
  "Scale the cluster of nodes from the group-spec group-spec with nb-nodes nodes (0 to terminate all nodes from the group)"
  [group-spec nb-nodes]
    (-> group-spec
      (assoc :count nb-nodes)
      (converge :compute (pc/compute-service :aws))))

(defn start-node
  "Starting one node belonging to the group-spec"
  [group-spec]
  (scale-cluster group-spec 1))

(defn stop-cluster
  "Stopping the cluster of group-spec machines"
  [group-spec]
  (scale-cluster group-spec 0))

(comment
  ;; personal function:
  ;; - to start a node
  (start-node mygroup)
  ;; - to stop one
  (stop-cluster mygroup)

  ;; to list the nodes
  (pallet.compute/nodes (pc/compute-service :aws)))
