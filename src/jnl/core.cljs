(ns jnl.core 
  (:require [cljs.nodejs :as nodejs]
            [clojure.string :as string]))

(nodejs/enable-util-print!)
(def process nodejs/process)
(def fs (nodejs/require "fs"))
(def path (nodejs/require "path"))

(def home (aget process "env" (if (= (.-platform process) "win32") "USERPROFILE" "HOME")))

(defn process-line [result line]
  (if (re-seq #"^\d{4}-\d\d-\d\d \d\d:\d\d " line) 
    (conj result (str (.trim line) "\n"))
    (update-in result [(dec (count result))] str "| " (.trim line) "\n")))

(defn has-tags? [tags]
  (fn [entry] (apply or (map #(>= (.indexOf entry %) 0) tags))))

(defn -main [& args]
  (doseq [arg (.-argv process)] (println "arg=" arg))
  (let [cfg (.parse js/JSON (.readFileSync fs (.join path home ".jnl_config") "utf8"))
        jrnl (.readFileSync fs (aget cfg "journals" "default") "utf8")
        entries (reduce process-line [] (string/split-lines jrnl))]
    (println (sort-by #(subs % 0 16) (if-let [tags (seq (drop 2 (.-argv process)))]
                                       (filter #(>= (.indexOf % (first tags)) 0) entries)
                                       entries)))))

(set! *main-cli-fn* -main)
