# ClojureScript Quick Start Tutorial



## ClojureScript Compiler

create `build.clj `

    (require 'cljs.build.api)

    (cljs.build.api/build "src" {:output-to "out/main.js"})

build ClojureScript

    java -cp cljs.jar:src clojure.main build.clj

add to HTML

    <script type="text/javascript" src="out/goog/base.js"></script>
    <script type="text/javascript" src="out/main.js"></script>
    <script type="text/javascript">
        goog.require("cljs-hello-world.core");
        // Note the underscore "_"! 
    </script>


## Less Boilerplate

- eliminate boilerplate
    + specify `:main` entry point 
    + in options that we pass to `cljs.build.api/build`

edit build.clj

    (require 'cljs.build.api)

    (cljs.build.api/build "src"
      {:main 'cljs-hello-world.core
       :output-to "out/main.js"}) 

edit HTML

    <html>
        <body>
            <script type="text/javascript" src="out/main.js"></script>
        </body>
    </html>


## Auto-building

create helper script `watch.clj`

    (require 'cljs.build.api)

    (cljs.build.api/watch "src"
      {:main 'cljs-hello-world.core
       :output-to "out/main.js"})

start auto building

    java -cp cljs.jar:src clojure.main watch.clj


## Browser REPL

- install rlwrap (recommended)
- build project at least once before constructing REPL

create REPL script `repl.clj`

    (require 'cljs.repl)
    (require 'cljs.build.api)
    (require 'cljs.repl.browser)

    (cljs.build.api/build "src"
      {:main 'cljs-hello-world.core
       :output-to "out/main.js"
       :verbose true})

    (cljs.repl/repl (cljs.repl.browser/repl-env)
      :watch "src"
      :output-dir "out")

edit core.cljs

    (ns cljs-hello-world.core
      (:require [clojure.browser.repl :as repl]))

    (defonce conn
      (repl/connect "http://localhost:9000/repl")) 

    (enable-console-print!)

    (println "Hello world!")

run rlwrap

    rlwrap java -cp cljs.jar:src clojure.main repl.clj

view auto build progress

    tail -f out/watch.log

reload code changes in REPL

    (require '[cljs-hello-world.core :as hello] :reload)


## Production Builds

create helper build script `release.clj`

    (require 'cljs.build.api)

    (cljs.build.api/build "src"
      {:output-to "out/main.js"
       :optimizations :advanced})

    (System/exit 0)

remove dev time REPL from `src/hello_world/core.cljs`

    (ns cljs-hello-world.core)

    (enable-console-print!)

    (println "Hello world!")

create release build

    java -cp cljs.jar:src clojure.main release.clj


## Running ClojureScript on Node.js

edit `src/hello_world/core.cljs`

    (ns cljs-hello-world.core
      (:require [cljs.nodejs :as nodejs]))

    (nodejs/enable-util-print!)

    (defn -main [& args]
      (println "Hello world!"))

    (set! *main-cli-fn* -main)

create build helper file `node.clj`

    (require 'cljs.build.api)

    (cljs.build.api/build "src"
      {:main 'cljs-hello-world.core
       :output-to "main.js"
       :target :nodejs})

install `source-map-support`

    npm install source-map-support

build Node project
    
    java -cp cljs.jar:src clojure.main node.clj

run file with

    node main.js


### Node.js REPL

create helper build file `node_repl.clj`

    (require 'cljs.repl)
    (require 'cljs.build.api)
    (require 'cljs.repl.node)

    (cljs.build.api/build "src"
      {:main 'cljs-hello-world.core
       :output-to "out/main.js"
       :verbose true})

    (cljs.repl/repl (cljs.repl.node/repl-env)
      :watch "src"
      :output-dir "out")

start REPL
    
    rlwrap java -cp cljs.jar:src clojure.main node_repl.clj
