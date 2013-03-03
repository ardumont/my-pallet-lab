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

  ;; (pallet.compute.vmfest/add-image vmfest-service "https://s3.amazonaws.com/vmfest-images/debian-6.0.2.1-64bit-v0.3.vdi.gz")
  ;; download both the vdi.gz and .meta file from the server
  (pallet.compute.vmfest/add-image vmfest-service "/home/tony/Downloads/pallet/ubuntu-12.04.vdi")

  {:ubuntu-12.04 {:os-type-id "Ubuntu_64"
                  :sudo-password "vmfest"
                  :no-sudo false
                  :packager :apt
                  :username "vmfest"
                  :os-family :ubuntu
                  :os-version "12.04"
                  :uuid "/home/tony/.vmfest/models/vmfest-ubuntu-12.04.vdi"
                  :os-64-bit true
                  :password "vmfest"
                  :description "Ubuntu 12.04 (64bit)"}}

  (pallet.compute.vmfest/add-image vmfest-service "/home/tony/Downloads/pallet/debian-6.0.2.1-64bit-v0.3.vdi")

  {:debian-6.0.2.1-64bit-v0.3 {:os-type-id "Debian_64"
                               :sudo-password "vmfest"
                               :no-sudo false
                               :username "vmfest"
                               :os-family :debian
                               :os-version "6.0.2.1"
                               :uuid "/home/tony/.vmfest/models/vmfest-debian-6.0.2.1-64bit-v0.3.vdi"
                               :os-64-bit true
                               :password "vmfest"
                               :description "Debian 6.0.2.1 (64bit) v0.3"}
   :ubuntu-12.04 {:os-type-id "Ubuntu_64"
                  :sudo-password "vmfest"
                  :no-sudo false
                  :packager :apt
                  :username "vmfest"
                  :os-family :ubuntu
                  :os-version "12.04"
                  :uuid "/home/tony/.vmfest/models/vmfest-ubuntu-12.04.vdi"
                  :os-64-bit true
                  :password "vmfest"
                  :description "Ubuntu 12.04 (64bit)"}}

  (pallet.compute/images vmfest-service)

  {:debian-6.0.2.1-64bit-v0.3 {:os-type-id "Debian_64"
                               :sudo-password "vmfest"
                               :no-sudo false
                               :username "vmfest"
                               :os-family :debian
                               :os-version "6.0.2.1"
                               :uuid "/home/tony/.vmfest/models/vmfest-debian-6.0.2.1-64bit-v0.3.vdi"
                               :os-64-bit true
                               :password "vmfest"
                               :description "Debian 6.0.2.1 (64bit) v0.3"}
   :ubuntu-12.04 {:os-type-id "Ubuntu_64"
                  :sudo-password "vmfest"
                  :no-sudo false
                  :packager :apt
                  :username "vmfest"
                  :os-family :ubuntu
                  :os-version "12.04"
                  :uuid "/home/tony/.vmfest/models/vmfest-ubuntu-12.04.vdi"
                  :os-64-bit true
                  :password "vmfest"
                  :description "Ubuntu 12.04 (64bit)"}})
