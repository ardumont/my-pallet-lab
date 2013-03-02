(ns my-pallet-lab.core
  (:require [environ.core          :as env]
            [pallet.core           :as p]
            [pallet.phase          :as ph]
            [pallet.action.package :as pa]
            [pallet.compute        :as c]))

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

;; define group of nodes

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

;; converge (update the number of nodes for the group mygroup)

(comment
  (pallet.core/converge
   (pallet.core/group-spec "mygroup" :count 1)
   :compute (pallet.compute/service "aws")))


(comment :some-output-reworked-to-remove-list-of-possible-nodes-and-to-be-able-to-read-what-is-going-on
         {:selected-nodes ()
          :group {:servers ()
                  :count 1
                  :image {:os-family :ubuntu
                          :image-id "us-west-1/ami-2861685c"}
                  :phases {:bootstrap "#<automated_admin_user$automated_admin_user pallet.crate.automated_admin_user$automated_admin_user@7c14dcba>"
                           :configure "#<my_pallet_lab$fn__9662 my_pallet_lab.groups.my_pallet_lab$fn__9662@25703b9b>"}
                  :hardware {:min-cores 1
                             :min-ram 512}
                  :group-name :mygroup}
          :node-set [{:count 1
                      :image {:os-family :ubuntu
                              :image-id "us-west-1/ami-2861685c"}
                      :phases {:bootstrap "#<automated_admin_user$automated_admin_user pallet.crate.automated_admin_user$automated_admin_user@7c14dcba>"
                               :configure "#<my_pallet_lab$fn__9662 my_pallet_lab.groups.my_pallet_lab$fn__9662@25703b9b>"}
                      :hardware {:min-cores 1
                                 :min-ram 512}
                      :group-name :mygroup}]
          :phase :pallet.phase/post-configure
          :new-nodes ()
          :environment {:blobstore nil
                        :compute "#<JcloudsService pallet.compute.jclouds.JcloudsService@793b1fb5>"
                        :user #pallet.utils.User{:username "tony"
                                                 :public-key-path "/home/tony/.ssh/id_rsa.pub"
                                                 :private-key-path "/home/tony/.ssh/id_rsa"
                                                 :passphrase nil
                                                 :password nil
                                                 :sudo-password nil
                                                 :no-sudo nil}
                        :middleware ["#<core$translate_action_plan pallet.core$translate_action_plan@529f68e5>" "#<execute$ssh_user_credentials pallet.execute$ssh_user_credentials@7021d740>" "#<execute$execute_with_ssh pallet.execute$execute_with_ssh@38e00a5e>" "#<core$raise_on_error pallet.core$raise_on_error@5f52408b>"]
                        :algorithms {:lift-fn "#<core$parallel_lift pallet.core$parallel_lift@fca1255>"
                                     :converge-fn "#<core$parallel_adjust_node_counts pallet.core$parallel_adjust_node_counts@4a84336b>"
                                     :execute-status-fn "#<core$stop_execution_on_error pallet.core$stop_execution_on_error@4f59e37e>"}}
          :middleware ["#<core$translate_action_plan pallet.core$translate_action_plan@529f68e5>" "#<execute$ssh_user_credentials pallet.execute$ssh_user_credentials@7021d740>" "#<execute$execute_with_ssh pallet.execute$execute_with_ssh@38e00a5e>" "#<core$raise_on_error pallet.core$raise_on_error@5f52408b>"]
          :old-nodes ()
          :groups-new-nodes nil
          :compute "#<JcloudsService pallet.compute.jclouds.JcloudsService@793b1fb5>"
          :original-nodes []
          :phase-list (:settings :configure)
          :algorithms {:lift-fn "#<core$parallel_lift pallet.core$parallel_lift@fca1255>"
                       :converge-fn "#<core$parallel_adjust_node_counts pallet.core$parallel_adjust_node_counts@4a84336b>"
                       :execute-status-fn "#<core$stop_execution_on_error pallet.core$stop_execution_on_error@4f59e37e>"}
          :blobstore nil
          :target-id :mygroup
          :groups ({:servers ()
                    :count 1
                    :image {:os-family :ubuntu
                            :image-id "us-west-1/ami-2861685c"}
                    :phases {:bootstrap "#<automated_admin_user$automated_admin_user pallet.crate.automated_admin_user$automated_admin_user@7c14dcba>"
                             :configure "#<my_pallet_lab$fn__9662 my_pallet_lab.groups.my_pallet_lab$fn__9662@25703b9b>"}
                    :hardware {:min-cores 1
                               :min-ram 512}
                    :group-name :mygroup})
          :executor {:script/bash {:origin "#<execute$bash_on_origin pallet.execute$bash_on_origin@26edc0e0>"
                                   :target "#<core$eval5776$raise__5777$fn__5778 pallet.core$eval5776$raise__5777$fn__5778@43e43267>"}
                     :fn/clojure {:origin "#<execute$clojure_on_origin pallet.execute$clojure_on_origin@3846f00b>"
                                  :target "#<core$eval5776$raise__5777$fn__5778 pallet.core$eval5776$raise__5777$fn__5778@39397f0a>"}
                     :transfer/to-local {:origin "#<core$eval5776$raise__5777$fn__5778 pallet.core$eval5776$raise__5777$fn__5778@6dc386df>"
                                         :target "#<core$eval5776$raise__5777$fn__5778 pallet.core$eval5776$raise__5777$fn__5778@3fcbd2c5>"}
                     :transfer/from-local {:origin "#<core$eval5776$raise__5777$fn__5778 pallet.core$eval5776$raise__5777$fn__5778@5e7aa43b>"
                                           :target "#<core$eval5776$raise__5777$fn__5778 pallet.core$eval5776$raise__5777$fn__5778@45b44df2>"}}
          :groups-old-nodes nil
          :all-nodes ()
          :user #pallet.utils.User{:username "tony"
                                   :public-key-path "/home/tony/.ssh/id_rsa.pub"
                                   :private-key-path "/home/tony/.ssh/id_rsa"
                                   :passphrase nil
                                   :password nil
                                   :sudo-password nil
                                   :no-sudo nil}
          :target-type :node})
