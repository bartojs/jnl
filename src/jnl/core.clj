(require '[clojure.data.json :as json])
(prn (slurp (get-in (json/read-str (slurp (str (System/getProperty "user.home") (java.io.File/separator) ".jrnl_config"))) ["journals" "default"])))
