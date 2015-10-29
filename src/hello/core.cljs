(ns hello.core
  (:require [cljs.nodejs :as nodejs]))
(nodejs/enable-util-print!)
(def fs (nodejs/require "fs"))
(def process nodejs/process)

(def home (aget (.-env process) (if (= (.-platform process) "win32") "USERPROFILE" "HOME")))

(defn -main [& args]
  (println "Hello world!")
  (doseq [arg (.-argv process)] (println "arg=" arg))
  (println (.readFileSync fs (str home "/.jrnl_config") "utf8")))

(set! *main-cli-fn* -main)
