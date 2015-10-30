(require '[clojure.data.json :as json])
(prn (slurp (get-in (json/read-str (slurp "/Users/justin/.jrnl_config")) ["journals" "default"])))
