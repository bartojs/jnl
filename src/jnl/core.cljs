(ns jnl.core 
  (:require [cljs.nodejs :as nodejs]
            [clojure.string :as string]))

(nodejs/enable-util-print!)
(def process nodejs/process)
(def fs (nodejs/require "fs"))
(def path (nodejs/require "path"))

(def home (aget process "env" (if (= (.-platform process) "win32") "USERPROFILE" "HOME")))

(defn process-line [result line]
  (if-let [date (re-seq #"^\d{4}-\d\d-\d\d \d\d:\d\d " line)] 
    (conj result line) 
    (update-in result [(dec (count result))] str "| " line "\n")))

(defn -main [& args]
  (println "Hello world!")
  (doseq [arg (.-argv process)] (println "arg=" arg))
  (let [cfg (.parse js/JSON (.readFileSync fs (.join path home ".jrnl_config") "utf8"))
        jrnl (.readFileSync fs (aget cfg "journals" "default") "utf8")]
    (println (reduce process-line [] (string/split-lines jrnl)))))

(set! *main-cli-fn* -main)
