(ns my-pallet-lab.groups.provision
  "Node definitions for my-pallet-lab"
  (:use
   [pallet.api            :only [converge]])
  (:require
   [pallet.configure      :as pc]))

;; personal functions

(defn scale-cluster
  "Scale the cluster of nodes from the group-spec group-spec with nb-nodes nodes (0 to terminate all nodes from the group)"
  [group-spec nb-nodes service]
    (-> group-spec
      (assoc :count nb-nodes)
      (converge :compute service)))

(defn start-node
  "Starting one node belonging to the group-spec"
  [group-spec service]
  (scale-cluster group-spec 1 service))

(defn stop-cluster
  "Stopping the cluster of group-spec machines"
  [group-spec service]
  (scale-cluster group-spec 0 service))
