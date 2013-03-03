(ns my-pallet-lab.groups.vmfest
  "Node definitions for vmfest"
  (:use
   [pallet.api                        :only [group-spec server-spec node-spec plan-fn]]
   [pallet.crate.automated-admin-user :only [automated-admin-user]])
  (:require
   [pallet.actions                 :refer [package]]
   [pallet.configure               :as pc]
   [my-pallet-lab.groups.provision :as pr]))

(comment ;; pre-requisite to download the image (called model in vmfest nomenclatura)
  (def vmfest-service (pc/compute-service "vmfest" nil nil))
  (pallet.compute.vmfest/add-image vmfest-service "https://s3.amazonaws.com/vmfest-images/debian-6.0.2.1-64bit-v0.3.vdi.gz")
  (pallet.compute/images vmfest-service))
