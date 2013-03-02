(ns my-pallet-lab.groups.my-pallet-lab
    "Node definitions for my-pallet-lab"
    (:use
     [pallet.core :only [group-spec server-spec node-spec]]
     [pallet.crate.automated-admin-user :only [automated-admin-user]]
     [pallet.phase :only [phase-fn]]))

(def
  ^{:doc "Defines a group spec that can be passed to converge or lift."}
  my-pallet-lab
  (group-spec
   "my-pallet-lab"
   :extends [base-server]
   :phases {:bootstrap (phase-fn (automated-admin-user))
            :configure (ph/phase-fn
                        (pa/package "curl")
                        (pa/package "wget")
                        (pa/package "git")
                        (pa/package "emacs24"))}
   :node-spec (node-spec
               :image {:os-family :ubuntu :os-version-matches "12.10"}
               :hardware {:min-cores 1 :min-ram 512})))
