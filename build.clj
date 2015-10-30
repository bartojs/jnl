(require '[cljs.build.api :as build])
(build/build "src" {:main 'jnl.core :output-to "main.js" :target :nodejs})
