(ns my-pallet-lab.groups.my-pallet-lab
  "Node definitions for my-pallet-lab"
  (:use
   [pallet.core :only [group-spec server-spec node-spec converge]]
   [pallet.crate.automated-admin-user :only [automated-admin-user]]
   [pallet.phase :only [phase-fn]])
  (:require
   [pallet.action.package :as pa]
   [pallet.configure      :as pc]))

;; ami
;; :image-id "ami-2861685c" - ;; ubuntu 12.10 eu-west-1 i386 instance-store
;; :image-id "ami-7e636a0a" - ;; ubuntu 12.10 eu-west-1 i386 ebs

;; define the machine

(def
  ^{:doc "Defines a group spec that can be passed to converge or lift."}
  mygroup
  (group-spec
   "mygroup"
   :phases {:bootstrap automated-admin-user
            :configure (phase-fn
                        (pa/package "curl")
                        (pa/package "wget")
                        (pa/package "git")
                        (pa/package "emacs24"))}
   :node-spec (node-spec
               :image {:os-family :ubuntu
                       :os-version-matches "12.10"
                       :image-id "eu-west-1/ami-2861685c"}
               :hardware {:min-cores 1 :min-ram 512})))

(defn start-node
  "Starting the group-spec with one node"
  [group-spec]
  (-> group-spec
      (assoc-in [:count] 1)
      (converge :compute (pc/compute-service :aws))))

(defn stop-node
  "Stopping the group-spec"
  [group-spec]
  (-> group-spec
      (assoc-in [:count] 0)
      (converge :compute (pc/compute-service :aws))))

(comment
  ;; personal function:
  ;; - to start a node
  (start-node mygroup)
  ;; - to stop one
  (stop-node mygroup)

  ;; to list the nodes
  (pallet.compute/nodes (pc/compute-service :aws)))
