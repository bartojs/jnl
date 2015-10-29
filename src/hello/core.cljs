(ns hello.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(def fs (nodejs/require "fs"))

(defn -main [& args]
  (println "Hello world!")
  (println (.readFileSync fs "Makefile" "utf8")))

(set! *main-cli-fn* -main)
