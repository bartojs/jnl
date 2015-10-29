(ns stat
  (require pixie.uv :as uv))

(uv/defuvfsfn fs_stat [args] #(prn args (str (:path %)) "statbuf" (:statbuf.st_size %) (:statbuf.st_uid %) (:statbuf.st_flags %)))

(fs_stat "Makefile")
(fs_stat "stat.pxi")
