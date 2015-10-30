build: cljs.jar
	time java -cp cljs.jar:src clojure.main build.clj

cljs.jar:
	curl -o cljs.jar -L https://github.com/clojure/clojurescript/releases/download/r1.7.145/cljs.jar

run:
	time node main.js

runclj:
	time java -cp cljs.jar clojure.main src/jnl/core.clj

runpxi:
	time pxi src/jnl/core.pxi
