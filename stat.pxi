(ns stat
  (require pixie.ffi :as ffi)
  (require pixie.uv :as uv))

;;(uv/defuvfsfn fs_stat args cb)
;;(fs_stat ["Makefile"] prn)

(defn fsstat [args return]
  (pixie.stacklets/call-cc (fn [k]
     (let [cb (atom nil)]
        (reset! cb (ffi/ffi-prep-callback uv/uv_fs_cb (fn [req]
          (try
              (pixie.stacklets/run-and-process k (return (ffi/cast req uv/uv_fs_t)))
              (uv/uv_fs_req_cleanup req)
              (-dispose! @cb)
              (catch e (println e))))))
        (uv/uv_fs_stat (uv/uv_default_loop) (uv/uv_fs_t) args @cb)))))

(fsstat "Makefile" #(prn (:statbuf.st_size %)))
