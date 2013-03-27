(ns my-pallet-lab.groups.aws
  "Node definitions for aws"
  (:use [pallet.api                        :only [group-spec server-spec node-spec plan-fn]]
        [pallet.crate.automated-admin-user :only [automated-admin-user]])
  (:require [pallet.actions        :refer [package]]
            [pallet.configure      :as pc]
            [my-pallet-lab.groups.provision :as pr]))

;; potential amis
;; :image-id "ami-2861685c" - ;; ubuntu 12.10 eu-west-1 i386 instance-store
;; :image-id "ami-7e636a0a" - ;; ubuntu 12.10 eu-west-1 i386 ebs

(def service-aws (pc/compute-service :aws))

;; define a specification for a group of machine

(def
  ^{:doc "Defines a group spec that can be passed to converge (creation/termination) or lift (updates)."}
  mygroup-ec2
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
               :image {
                       ;;:os-family :ubuntu
;;                       :os-version-matches "12.10"
                       :image-id "eu-west-1/ami-2861685c"}
               :hardware {:smallest true
                          :min-cores 1
                          :min-ram 512})))

(comment
  ;; personal function:
  ;; - to start a node
  (pr/start-node mygroup-ec2 service-aws)
  ;; - to stop one
  (pr/stop-cluster mygroup-ec2 service-aws)

  ;; to list the nodes
  (pallet.compute/nodes service-aws))
