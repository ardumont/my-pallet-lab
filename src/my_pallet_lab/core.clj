(ns my-pallet-lab.core
  (:require [environ.core :as env]))

(def aws-creds
  {:access-key (env/env :aws-access-key)
   :secret-key (env/env :aws-secret-key)})
