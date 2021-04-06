(ns fourclojure.core-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [fourclojure.core :as core]))

(deftest last-element-test
  (testing "core/last-element"
    (is (= (core/last-element [1 2 3 4 5]) 5))
    (is (= (core/last-element '(5 4 3)) 3))
    (is (= (core/last-element ["b" "c" "d"]) "d"))))

(deftest penultimate-element-test
  (testing "core/penultimate-element"
    (is (= (core/penultimate-element (list 1 2 3 4 5)) 4))
    (is (= (core/penultimate-element ["a" "b" "c"]) "b"))
    (is (= (core/penultimate-element [[1 2] [3 4]]) [1 2]))))

(deftest nth-element-test
  (testing "core/nth-element"
    (is (= (core/nth-element '(4 5 6 7) 2) 6))
    (is (= (core/nth-element [:a :b :c] 0) :a))
    (is (= (core/nth-element '([1 2] [3 4] [5 6]) 2) [5 6]))
    (is (= (core/nth-element [1 2 3 4] 1) 2))))

(deftest count-a-sequence-test
  (testing "core/count-a-sequence"
    (is (= (core/count-a-sequence '(1 2 3 3 1)) 5))
    (is (= (core/count-a-sequence "Hello World") 11))
    (is (= (core/count-a-sequence [[1 2] [3 4] [5 6]]) 3))
    (is (= (core/count-a-sequence '(13)) 1))
    (is (= (core/count-a-sequence '(:a :b :c)) 3))))

(deftest reverse-a-sequence-test
  (testing "core/reverse-a-sequence"
    (is (= (core/reverse-a-sequence [1 2 3 4 5]) [5 4 3 2 1]))
    (is (= (core/reverse-a-sequence (sorted-set 5 7 2 7)) '(7 5 2)))
    (is (= (core/reverse-a-sequence [[1 2] [3 4] [5 6]]) [[5 6] [3 4] [1 2]]))))

(deftest sum-test
  (testing "core/sum"
    (is (= (core/sum [1 2 3]) 6))
    (is (= (core/sum (list 0 -2 5 5)) 8))
    (is (= (core/sum '(1 10 3)) 14))
    (is (= (core/sum '(0 0 -1)) -1))
    (is (= (core/sum #{4 2 1}) 7))))

(deftest odd-numbers-test
  (testing "core/odd-numbers"
    (is (= (core/odd-numbers #{1 2 3 4 5}) '(1 3 5)))
    (is (= (core/odd-numbers [4 2 1 6]) '(1)))
    (is (= (core/odd-numbers [2 2 4 6]) '()))
    (is (= (core/odd-numbers [1 1 1 3]) '(1 1 1 3)))))

(deftest fibonacci-sequence-test
  (testing "core/fibonacci-sequence"
    (is (= (core/fibonacci-sequence 3) '(1 1 2)))
    (is (= (core/fibonacci-sequence 6) '(1 1 2 3 5 8)))
    (is (= (core/fibonacci-sequence 8) '(1 1 2 3 5 8 13 21)))))

(deftest palindrome-detector-test
  (testing "core/palindrome-detector"
    (is (false? (core/palindrome-detector '(1 2 3 4 5))))
    (is (true? (core/palindrome-detector "racecar")))
    (is (true? (core/palindrome-detector [:foo :bar :foo])))
    (is (true? (core/palindrome-detector '(1 1 3 3 1 1))))
    (is (false? (core/palindrome-detector '(:a :b :c))))))

(deftest flatten-sequence-test
  (testing "core/flatten-sequence"
    (is (= (core/flatten-sequence '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6)))
    (is (= (core/flatten-sequence ["a" ["b"] "c"]) '("a" "b" "c")))
    (is (= (core/flatten-sequence '((((:a))))) '(:a)))))

(deftest caps-test
  (testing "core/caps"
    (is (= (core/caps "HeLlO, WoRlD!") "HLOWRD"))
    (is (empty? (core/caps "nothing")))
    (is (= (core/caps "$#A(*&987Zf") "AZ"))))

(deftest compress-test
  (testing "core/compress"
    (is (= (apply str (core/compress "Leeeeeerrroyyy")) "Leroy"))
    (is (= (core/compress [1 1 2 3 3 2 2 3]) '(1 2 3 2 3)))
    (is (= (core/compress [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])))))

(deftest pack-a-sequence-test
  (testing "core/pack-a-sequence"
    (is (= (core/pack-a-sequence [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3))))
    (is (= (core/pack-a-sequence [:a :a :b :b :c]) '((:a :a) (:b :b) (:c))))
    (is (= (core/pack-a-sequence [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4]))))))

(deftest duplicate-a-sequence-test
  (testing "core/duplicate-a-sequence"
    (is (= (core/duplicate-a-sequence [1 2 3]) '(1 1 2 2 3 3)))
    (is (= (core/duplicate-a-sequence [:a :a :b :b]) '(:a :a :a :a :b :b :b :b)))
    (is (= (core/duplicate-a-sequence [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4])))))

(deftest replicate-a-sequence-test
  (testing "core/replicate-a-sequence"
    (is (= (core/replicate-a-sequence [1 2 3] 2) '(1 1 2 2 3 3)))
    (is (= (core/replicate-a-sequence [:a :b] 4) '(:a :a :a :a :b :b :b :b)))
    (is (= (core/replicate-a-sequence [4 5 6] 1) '(4 5 6)))
    (is (= (core/replicate-a-sequence [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4])))
    (is (= (core/replicate-a-sequence [44 33] 2) [44 44 33 33]))))

(deftest implement-range-test
  (testing "core/implement-range"
    (is (= (core/implement-range 1 4) '(1 2 3)))
    (is (= (core/implement-range -2 2) '(-2 -1 0 1)))
    (is (= (core/implement-range 5 8) '(5 6 7)))))

(deftest maximum-value-test
  (testing "core/maximum-value"
    (is (= (core/maximum-value 1 8 3 4) 8))
    (is (= (core/maximum-value 30 20) 30))
    (is (= (core/maximum-value 45 67 11) 67))))

(deftest interleave-two-seqs-test
  (testing "core/interleave-two-seqs"
    (is (= (core/interleave-two-seqs [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c)))
    (is (= (core/interleave-two-seqs [1 2] [3 4 5 6]) '(1 3 2 4)))
    (is (= (core/interleave-two-seqs [1 2 3 4] [5]) [1 5]))
    (is (= (core/interleave-two-seqs [30 20] [25 15]) [30 25 20 15]))))

(deftest interpose-a-seq-test
  (testing "core/interpose-a-seq"
    (is (= (core/interpose-a-seq 0 [1 2 3]) [1 0 2 0 3]))
    (is (= (apply str (core/interpose-a-seq ", " ["one" "two" "three"])) "one, two, three"))
    (is (= (core/interpose-a-seq :z [:a :b :c :d]) [:a :z :b :z :c :z :d]))))

(deftest drop-every-nth-item-test
  (testing "core/drop-every-nth-item"
    (is (= (core/drop-every-nth-item [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8]))
    (is (= (core/drop-every-nth-item [:a :b :c :d :e :f] 2) [:a :c :e]))
    (is (= (core/drop-every-nth-item [1 2 3 4 5 6] 4) [1 2 3 5 6]))))

(deftest factorial-fun-test
  (testing "core/factorial-fun"
    (is (= (core/factorial-fun 1) 1))
    (is (= (core/factorial-fun 3) 6))
    (is (= (core/factorial-fun 5) 120))
    (is (= (core/factorial-fun 8) 40320))))

(deftest reverse-interleave-test
  (testing "core/reverse-interleave"
    (is (= (core/reverse-interleave [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6))))
    (is (= (core/reverse-interleave (range 9) 3) '((0 3 6) (1 4 7) (2 5 8))))
    (is (= (core/reverse-interleave (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9))))))

(deftest rotate-sequence-test
  (testing "core/rotate-sequence"
    (is (= (core/rotate-sequence 2 [1 2 3 4 5]) '(3 4 5 1 2)))
    (is (= (core/rotate-sequence -2 [1 2 3 4 5]) '(4 5 1 2 3)))
    (is (= (core/rotate-sequence 6 [1 2 3 4 5]) '(2 3 4 5 1)))
    (is (= (core/rotate-sequence 1 '(:a :b :c)) '(:b :c :a)))
    (is (= (core/rotate-sequence -4 '(:a :b :c)) '(:c :a :b)))))

(deftest flipping-out-test
  (testing "core/flipping-out"
    (is (= 3 ((core/flipping-out nth) 2 [1 2 3 4 5])))
    (is (= true ((core/flipping-out >) 7 8)))
    (is (= 4 ((core/flipping-out quot) 2 8)))
    (is (= [1 2 3] ((core/flipping-out take) [1 2 3 4 5] 3)))))

(deftest split-sequence-test
  (testing "core/split-sequence"
    (is (= (core/split-sequence 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]]))
    (is (= (core/split-sequence 1 [:a :b :c :d]) [[:a] [:b :c :d]]))
    (is (= (core/split-sequence 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]]))))

(deftest split-by-type-test
  (testing "core/split-by-type"
    (is (= (set (core/split-by-type [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]}))
    (is (= (set (core/split-by-type [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]}))
    (is (= (set (core/split-by-type [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]}))))

(deftest longest-increasing-sub-seq-test
  (testing "core/longest-increasing-sub-seq"
    (is (= (core/longest-increasing-sub-seq [1 0 1 2 3 0 4 5]) [0 1 2 3]))
    (is (= (core/longest-increasing-sub-seq [5 6 1 3 2 7]) [5 6]))
    (is (= (core/longest-increasing-sub-seq [2 3 3 4 5]) [3 4 5]))
    (is (= (core/longest-increasing-sub-seq [7 6 5 4]) []))))

(deftest partition-a-sequence-test
  (testing "core/partition-a-sequence"
    (is (= (core/partition-a-sequence 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8))))
    (is (= (core/partition-a-sequence 2 (range 8)) '((0 1) (2 3) (4 5) (6 7))))
    (is (= (core/partition-a-sequence 3 (range 8)) '((0 1 2) (3 4 5))))))

(deftest count-occurrences-test
  (testing "core/count-occurrences"
    (is (= (core/count-occurrences [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1}))
    (is (= (core/count-occurrences [:b :a :b :a :b]) {:a 2, :b 3}))
    (is (= (core/count-occurrences '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2}))))

(deftest find-distinct-items-test
  (testing "core/find-distinct-items"
    (is (= (core/find-distinct-items [1 2 1 3 1 2 4]) [1 2 3 4]))
    (is (= (core/find-distinct-items [:a :a :b :b :c :c]) [:a :b :c]))
    (is (= (core/find-distinct-items (range 50)) (range 50)))
    (is (= (core/find-distinct-items '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3])))))

(deftest juxtaposition-test
  (testing "core/juxtaposition"
    (is (= [21 6 1] ((core/juxtaposition + max min) 2 3 5 1 6 4)))
    (is (= ["HELLO" 5] ((core/juxtaposition #(.toUpperCase %) count) "hello")))
    (is (= [2 6 4] ((core/juxtaposition :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10})))))

(deftest sequence-reductions-test
  (testing "core/sequence-reductions"
    (is (= (take 5 (core/sequence-reductions + (range))) [0 1 3 6 10]))
    (is (= (core/sequence-reductions conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]]))
    (is (= (last (core/sequence-reductions * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120))))

(deftest map-construction-test
  (testing "core/map-construction"
    (is (= (core/map-construction [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3}))
    (is (= (core/map-construction [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"}))
    (is (= (core/map-construction [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"}))))

(deftest my-iterate-test
  (testing "core/my-iterate"
    (is (= (take 5 (core/my-iterate #(* 2 %) 1)) [1 2 4 8 16]))
    (is (= (take 100 (core/my-iterate inc 0)) (take 100 (range))))
    (is (= (take 9 (core/my-iterate #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3]))))))

(deftest group-a-sequence-test
  (testing "core/group-a-sequence"
    (is (= (core/group-a-sequence #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]}))
    (is (= (core/group-a-sequence #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
           {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]}))
    (is (= (core/group-a-sequence count [[1] [1 2] [3] [1 2 3] [2 3]])
           {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]}))))

;; (deftest black-box-testing-test
;;   (testing "core/black-box-testing"
;;     (is (= :map (core/black-box-testing {:a 1, :b 2})))
;;     (is (= :list (core/black-box-testing (range (rand-int 20)))))
;;     (is (= :vector (core/black-box-testing [1 2 3 4 5 6])))
;;     (is (= :set (core/black-box-testing #{10 (rand-int 5)})))
;;     (is (= [:map :set :vector :list] (map core/black-box-testing [{} #{} [] ()])))))

(deftest gcd-test
  (testing "core/gcd"
    (is (= (core/gcd 2 4) 2))
    (is (= (core/gcd 10 5) 5))
    (is (= (core/gcd 5 7) 1))
    (is (= (core/gcd 1023 858) 33))))

(deftest prime-numbers-test
  (testing "core/prime-numbers"
    (is (= (core/prime-numbers 2) [2 3]))
    (is (= (core/prime-numbers 5) [2 3 5 7 11]))
    (is (= (last (core/prime-numbers 100)) 541))))

(deftest my-merge-with-test
  (testing "core/my-merge-with"
    (is (= (core/my-merge-with * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
           {:a 4, :b 6, :c 20}))
    (is (= (core/my-merge-with - {1 10, 2 20} {1 3, 2 10, 3 15})
           {1 7, 2 10, 3 15}))
    (is (= (core/my-merge-with concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
           {:a [3 4 5], :b [6 7], :c [8 9]}))))

(deftest word-sorting-test
  (testing "core/word-sorting"
    (is (= (core/word-sorting "Have a nice day.")
           ["a" "day" "Have" "nice"]))
    (is (= (core/word-sorting "Clojure is a fun language!")
           ["a" "Clojure" "fun" "is" "language"]))
    (is (= (core/word-sorting "Fools fall for foolish follies.")
           ["fall" "follies" "foolish" "Fools" "for"]))))

(deftest analyze-ttt-test
  (testing "core/analyze-ttt"
    (is (= nil (core/analyze-ttt [[:e :e :e]
                                  [:e :e :e]
                                  [:e :e :e]])))
    (is (= :x (core/analyze-ttt [[:x :e :o]
                                 [:x :e :e]
                                 [:x :e :o]])))
    (is (= :o (core/analyze-ttt [[:e :x :e]
                                 [:o :o :o]
                                 [:x :e :x]])))
    (is (= nil (core/analyze-ttt [[:x :e :o]
                                  [:x :x :e]
                                  [:o :x :o]])))
    (is (= :x (core/analyze-ttt [[:x :e :e]
                                 [:o :x :e]
                                 [:o :e :x]])))
    (is (= :o (core/analyze-ttt [[:x :e :o]
                                 [:x :o :e]
                                 [:o :e :x]])))
    (is (= nil (core/analyze-ttt [[:x :o :x]
                                  [:x :o :x]
                                  [:o :x :o]])))))

(deftest filter-perfect-squares-test
  (testing "core/filter-perfect-squares"
    (is (= (core/filter-perfect-squares "4,5,6,7,8,9") "4,9"))
    (is (= (core/filter-perfect-squares "15,16,25,36,37") "16,25,36"))))

(deftest eulers-totient-test
  (testing "core/eulers-totient"
    (is (= (core/eulers-totient 1) 1))
    (is (= (core/eulers-totient 10) (count '(1 3 7 9)) 4))
    (is (= (core/eulers-totient 40) 16))
    (is (= (core/eulers-totient 99) 60))))

(deftest anagram-finder-test
  (testing "core/anagram-finder"
    (is (= (core/anagram-finder ["meat" "mat" "team" "mate" "eat"])
           #{#{"meat" "team" "mate"}}))
    (is (= (core/anagram-finder ["veer" "lake" "item" "kale" "mite" "ever"])
           #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}}))))

;; (deftest triangle-minimal-path-test
;;   (testing "core/triangle-minimal-path"
;;     (is (= 7 (core/triangle-minimal-path '([1]
;;                                            [2 4]
;;                                            [5 1 4]
;;                                            [2 3 4 5]))))
;;     (is (= 20 (core/triangle-minimal-path '([3]
;;                                             [2 4]
;;                                             [1 9 3]
;;                                             [9 9 2 4]
;;                                             [4 6 6 7 8]
;;                                             [5 7 3 5 1 4]))))))

(deftest perfect-numbers-test
  (testing "core/perfect-numbers"
    (is (= (core/perfect-numbers 6) true))
    (is (= (core/perfect-numbers 7) false))
    (is (= (core/perfect-numbers 496) true))
    (is (= (core/perfect-numbers 500) false))
    (is (= (core/perfect-numbers 8128) true))))

(deftest set-intersection-test
  (testing "core/set-intersection"
    (is (= (core/set-intersection #{0 1 2 3} #{2 3 4 5}) #{2 3}))
    (is (= (core/set-intersection #{0 1 2} #{3 4 5}) #{}))
    (is (= (core/set-intersection #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d}))))

(deftest half-truth-test
  (testing "core/half-truth"
    (is (= false (core/half-truth false false)))
    (is (= true (core/half-truth true false)))
    (is (= false (core/half-truth true)))
    (is (= true (core/half-truth false true false)))
    (is (= false (core/half-truth true true true)))
    (is (= true (core/half-truth true true true false)))))
