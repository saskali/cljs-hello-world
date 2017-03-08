(require 'cljs.build.api)

(cljs.build.api/watch "src"
  {:main 'cljs-hello-world.core
   :output-to "out/main.js"})