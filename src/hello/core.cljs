(ns hello.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(def fs (nodejs/require "fs"))

(defn -main [& args]
  (println "Hello world!")
  (doseq [arg (.-argv nodejs/process)] (println "arg=" arg))
  (println (.readFileSync fs "Makefile" "utf8")))

(set! *main-cli-fn* -main)
