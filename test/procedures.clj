(ns procedures
  (:use [clojure.test] :reload)
  (:use [selectscript.vm] :reload)
  (:use [selectscript.core] :reload)
  (:use [macross] :reload))


;(run-tests)

(deftest procedure_1
  (let [env (vm:init 100 100 0)]
    (iss 1  "p = PROC: (a=a+b); "
            "a=1; b=1;          ")
    (iss 2  "p();               ")
    (iss 3  "p();               ")
    (iss 4  "p();               ")
    (vm:exit env)))

(deftest procedure_2
  (let [env (vm:init 100 100 0)]
    (iss 1  "p = PROC: (a = 1; a=a+1;);"
            "a=1; b=1;                 ")
    (iss 2  "p();")
    (iss 2  "p();")
    (iss 2  "p();")
    (vm:exit env)))

(deftest procedure_3
  (let [env (vm:init 100 100 0)]
    (iss 1  "p = PROC (a,b): $a + $b;"
            "a=1; b=1;")
    (iss 2  "p(a , b);    ")
    (iss 2  "p(1 , 1);    ")
    (iss 5  "p(2*a , 3*b);")

    (iss 4  "p = PROC (a,b): (a=22; $a + $b;);"
            "p(2 , 2);    ")
    (iss 22 "a;           ")
    (vm:exit env)))

(deftest procedure_4
  (let [env (vm:init 100 100 0)]
    (iss 1  "p = PROC : ( b = PROC(a) : 3*$a;       "
            "             b(2)*b(3);           ); 1;")
    (iss 54 "p();    ")
    (iss 54 "p();    ")
    (vm:exit env)))

(deftest procedure_5
  (let [env (vm:init 100 100 0)]
    (iss 1  "fak = PROC(x): if($x,"
            "                  $x*fak($x - 1),"
            "                  1); 1;")
    (iss   1  "fak(0);    ")
    (iss   1  "fak(1);    ")
    (iss   2  "fak(2);    ")
    (iss   6  "fak(3);    ")
    (iss  24  "fak(4);    ")
    (iss 120  "fak(5);    ")
    (iss 720  "fak(6);    ")
    (vm:exit env)))

(deftest procedure_6
  (let [env (vm:init 100 100 0)]
    (iss 1  "p = PROC(x, y): $x + str($y) ; 1;      ")
    (iss [] "x = [];                                ")
    (iss ["22"]         "x@p(22);")
    (iss ["22", "22"]   "x@p(22);")
    (vm:exit env)))

(deftest procedure_karl
  (let [env (vm:init 100 100 0)]
    (iss 0  "v = 0")
    (iss 1  "p = PROC() : print(v@+(1)); 1;")
    (iss 1  "p(); ")
    (iss 2  "p(); ")
    (iss 3  "p(); ")

    (vm:exit env)))
