(ns stat
  (require pixie.io-blocking :as io))

(println (io/slurp "Makefile"))
